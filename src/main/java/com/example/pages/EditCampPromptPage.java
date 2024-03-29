package com.example.pages;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.example.dataloader.UserCSVLoader;
import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;
import com.example.view.RegexType;

/**
 * This class implements IPromptPage.
 * It is a prompt page for staff members to create a new camp
 * and consists of one compulsory question to 
 * indicate which camp they are editing and
 * then a series of optional questions to fill
 * in the details for the new camp that they are creating
 */
public class EditCampPromptPage implements IPromptPage<Camp> {


    private ArrayList<IPrompt> prompts = new ArrayList<>();
    private ArrayList<Camp> camps;
    private Camp value;

    /**Constructor for the class
     * @param camps which is the array list of camps the staff user has created
     */
    public EditCampPromptPage(ArrayList<Camp> camps ) {
        this.camps = camps;
    }
    
    /**
     * Initializes the questions to be asked in this prompt.
     * It displays the different camps the user created to choose from
     * to edit.
     * The rest of the questions are optional since the allowEmpty variable is set
     * to true. Pressing enter indicates the user doesn't want to change the 
     * existing value
     * It has the relevant regex for date and integer inputs. This is
     * to prevent parse exceptions and also ensure that users only
     * enter valid input types.
     * This method also stores the values.
     */
    private void initPrompts() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //show all camps 
        Camp campToChange;
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        String d = " (enter to skip)";
        questions.add(new Pair<>(0,"Choose Camp you want to edit"));
        questions.add(new Pair<>(1,"start date (dd/mm/yyyy)" + d));
        questions.add(new Pair<>(2,"end date (dd/mm/yyyy)" + d));
        questions.add(new Pair<>(3,"Registration closing date (dd/mm/yyyy)" + d));
        questions.add(new Pair<>(4,"Open to which Faculty" + d));
        questions.add(new Pair<>(5,"Location" + d));
        questions.add(new Pair<>(6,"total slots" + d));
        questions.add(new Pair<>(7,"committee slots" + d));
        questions.add(new Pair<>(8,"camp description" + d));
        questions.add(new Pair<>(9, "Visibility"+d));
        ArrayList<String> campnames = new ArrayList<>();
        for(Camp c:this.camps){campnames.add(c.getCampName());}
        IPrompt getCampPrompt = new Prompt("init");
        getCampPrompt = new PromptOption(questions.get(0).getSecond(), campnames);
        getCampPrompt.startPrompt();
        int idx = campnames.indexOf(getCampPrompt.getResult());
        campToChange = this.camps.get(idx);
        Date[] ds = new Date[2];
        ds[0] = (Date)campToChange.getDates()[0].clone();
        ds[1] = (Date)campToChange.getDates()[1].clone();
        for(int i = 1; i < questions.size();i++){
            IPrompt tmp;
            if(i==1 || i==2 || i ==3){
                
                if(i==1){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getDates()[0]));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    
                    try{ds[0] = sdf.parse(tmp.getResult());}catch(Exception e){}
                    campToChange.setDates(ds);
                } else if(i==2){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getDates()[1]));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    
                    try{ds[1] = sdf.parse(tmp.getResult());}catch(Exception e){}
                    campToChange.setDates(ds);
                } else{
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getClosingDate()));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    try{campToChange.setClosingDate(sdf.parse(tmp.getResult()));}catch(Exception e){}
                }    
                
                 
            }else if(i==4){
                ArrayList<String> faculty = new ArrayList<>();
                for (GroupName grpname : GroupName.values())
                    faculty.add(grpname.toString());
                
                tmp = new PromptOption(questions.get(i).getSecond(), true, campToChange.getUserGroup().name(), faculty);
                tmp.startPrompt();
                if(tmp.getResult().isEmpty()) continue;
                campToChange.setUserGroup(UserCSVLoader.getFacultyFromString(tmp.getResult()));
            }else{
                if(i==5){
                    tmp = new Prompt(questions.get(i).getSecond(), true, campToChange.getLocation());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setLocation(tmp.getResult());
                }
                
                else if(i==6){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(), true, campToChange.getTotalSlots().toString());
                    tmp.startPrompt();
                    if (tmp.getResult().isEmpty()) continue;
                    campToChange.setTotalSlots(Integer.valueOf(tmp.getResult()));
                }
                else if(i==7){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(),true, campToChange.getCommitteeSlot().toString());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setCommitteeSlot(Integer.valueOf(tmp.getResult()));
                }
                else if(i==8){
                    tmp = new Prompt(questions.get(i).getSecond(), true, campToChange.getDescription());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setDescription(tmp.getResult());
                }
                else {
                    ArrayList<String> ops = new ArrayList<>();
                    ops.add("false");
                    ops.add("true");
                    tmp = new PromptOption(questions.get(i).getSecond(), true, campToChange.getVisibility()+"", ops);
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setVisibility(Boolean.valueOf(tmp.getResult()));
                }
            }
            
        }
        this.value = campToChange;
    }

    /**Begins prompting */
    @Override
    public void perform() {
        initPrompts();
        
    }

    /**Returns the Camp object with the set edit values 
     * @return value as a Camp object
    */
    @Override
    public Camp getObject() {
       return this.value;
    }
}
