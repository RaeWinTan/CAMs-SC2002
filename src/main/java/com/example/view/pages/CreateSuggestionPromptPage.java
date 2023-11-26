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

public class CreateSuggestionPromptPage implements IPromptPage<Suggestion>{
    private Student student;
    private ArrayList<IPrompt> prompts = new ArrayList<>();
    private Suggestion value;
    public CreateSuggestionPromptPage(Student s){
        this.student = s;
        
    }
    private void initPrompts(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //show all camps 
        Camp campToChange;
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        String d = " (just press enter if you don't want to edit this value)";
        questions.add(new Pair<>(0,"Chose Camp you want to make suggestion"));
        questions.add(new Pair<>(1,"start date" + d));
        questions.add(new Pair<>(2,"end date" + d));
        questions.add(new Pair<>(3,"Registration closing date" + d));
        questions.add(new Pair<>(4,"Open to which Faculty" + d));
        questions.add(new Pair<>(5,"Location" + d));
        questions.add(new Pair<>(6,"total slots" + d));
        questions.add(new Pair<>(7,"committee slots" + d));
        questions.add(new Pair<>(8,"camp description" + d));
        questions.add(new Pair<>(9, "Visibility"+d));
        ArrayList<String> campnames = new ArrayList<>();
        ArrayList<Camp> camps = new ArrayList<>();
        for(CampMember cm : student.getLeading()){
            camps.add(cm.getCamp().copyOf());
        }
        for(Camp c:camps){campnames.add(c.getCampName());}
        IPrompt getCampPrompt = new PromptOption(questions.get(0).getSecond(), campnames);
        getCampPrompt.startPrompt();
        int idx = campnames.indexOf(getCampPrompt.getResult());
        campToChange = camps.get(idx);
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
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(), true, campToChange.getTotalSlots() + "");
                    tmp.startPrompt();
                    if (tmp.getResult().isEmpty()) continue;
                    campToChange.setTotalSlots(Integer.valueOf(tmp.getResult()));
                }
                else if(i==7){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(),true, campToChange.getCommitteeSlot() + "");
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
                    ops.add("true");
                    ops.add("false");
                    tmp = new PromptOption(questions.get(i).getSecond(), true, campToChange.getVisibility()+"", ops);
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setVisibility(Boolean.valueOf(tmp.getResult()));
                }
            }
            
        }
        this.value = new Suggestion(student, campToChange);
    }

    @Override
    public void perform() {
        initPrompts();
    }

    @Override
    public Suggestion getObject() {
        return this.value;
    }
    
}
