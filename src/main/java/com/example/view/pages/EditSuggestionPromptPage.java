package com.example.view.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.dataloader.UserCSVLoader;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.GroupName;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;
import com.example.view.RegexType;
import com.example.view.TablePromptOption;

public class EditSuggestionPromptPage implements IPromptPage<Suggestion>{
    private Student student;
    private Camp value;
    private Suggestion newSuggestion;
    private ArrayList<Camp> newCamps = new ArrayList<>();

    
    public EditSuggestionPromptPage(Student s) {
        this.student = s;
    }

    private void initFirstPrompt(){
        ArrayList<String> oldCamps = new ArrayList<>();
        ArrayList<String> newCamps_str = new ArrayList<>();
        ArrayList<Suggestion> sugs = new ArrayList<>();
        for(Suggestion sus:student.getSuggestions()){
            Camp nc = sus.getCamp();
            Camp oc = new Camp();
            this.newCamps.add(nc);
            sugs.add(sus);
            for(CampMember mc:student.getLeading()){
                if(nc.isEquals(mc.getCamp())){
                    oc = mc.getCamp();
                    break;
                } 
            }
            oldCamps.add(oc.toString());
            newCamps_str.add(nc.toString());
        }
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Original Camp");
        headers.add("Suggested Camp");
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(oldCamps);
        columns.add(newCamps_str); 
        IPrompt p= new TablePromptOption("choose a suggestion you want to edit", headers, columns);
        p.startPrompt();
        int idx = Integer.valueOf(p.getResult());
        this.value = newCamps.get(idx).copyOf();
        this.newSuggestion = sugs.get(idx).copyOf();
        
    }
    
   
    private void initPrompts() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //show all camps 
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        String d = " (press enter for default value)";
        questions.add(new Pair<>(0,"start date" + d));
        questions.add(new Pair<>(1,"end date" + d));
        questions.add(new Pair<>(2,"Registration closing date" + d));
        questions.add(new Pair<>(3,"Open to which Faculty" + d));
        questions.add(new Pair<>(4,"Location" + d));
        questions.add(new Pair<>(5,"total slots" + d));
        questions.add(new Pair<>(6,"committee slots" + d));
        questions.add(new Pair<>(7,"camp description" + d));
        questions.add(new Pair<>(8, "Visibility"+d));
        Camp campToChange = this.value;
        Date[] ds = new Date[2];
        ds[0] = (Date)campToChange.getDates()[0].clone();
        ds[1] = (Date)campToChange.getDates()[1].clone();

        for(int i = 0; i < questions.size();i++){
            IPrompt tmp;
            if(i==0 || i==1 || i ==2){
                
                if(i==0){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getDates()[0]));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    
                    try{ds[0] = sdf.parse(tmp.getResult());}catch(Exception e){}
                    campToChange.setDates(ds);
                } else if(i==1){
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
                
                 
            }else if(i==3){
                ArrayList<String> faculty = new ArrayList<>();
                for (GroupName grpname : GroupName.values())
                    faculty.add(grpname.toString());
                
                tmp = new PromptOption(questions.get(i).getSecond(), true, campToChange.getUserGroup().name(), faculty);
                tmp.startPrompt();
                if(tmp.getResult().isEmpty()) continue;
                campToChange.setUserGroup(UserCSVLoader.getFacultyFromString(tmp.getResult()));
            }else{
                if(i==4){
                    tmp = new Prompt(questions.get(i).getSecond(), true, campToChange.getLocation());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setLocation(tmp.getResult());
                }
                
                else if(i==5){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(), true, campToChange.getTotalSlots()+"");
                    tmp.startPrompt();
                    if (tmp.getResult().isEmpty()) continue;
                    campToChange.setTotalSlots(Integer.valueOf(tmp.getResult()));
                }
                else if(i==6){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(),true, campToChange.getCommitteeSlot()+"");
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setCommitteeSlot(Integer.valueOf(tmp.getResult()));
                }
                else if(i==7){
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
        this.newSuggestion.getCamp().setAll(this.value);
        
        
    }

    /**Begins prompting */
    @Override
    public void perform() {
        initFirstPrompt();
        initPrompts();
        
    }

    /**Returns the Camp object with the set edit values 
     * @return value as a Camp object
    */
    @Override
    public Suggestion getObject() {
       return this.newSuggestion;
    }
}
