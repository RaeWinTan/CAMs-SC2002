package com.example.view.pages;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.dataloader.UserCSVLoader;
import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;
import com.example.view.RegexType;

public class EditCampPromptPage implements IPromptPage<Camp> {


    private ArrayList<IPrompt> prompts = new ArrayList<>();
    private ArrayList<Camp> camps;
    private Camp value;

    public EditCampPromptPage(ArrayList<Camp> camps ) {
        this.camps = camps;
    }
    

    private void initPrompts() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //show all camps 
        Camp campToChange;
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        String d = " (just press enter if you don't want to edit this value)";
        questions.add(new Pair<>(0,"Chose Camp you want to edit"+ d));
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
        for(Camp c:this.camps){campnames.add(c.getCampName());}
        IPrompt getCampPrompt = new Prompt("init");
        getCampPrompt = new PromptOption(questions.get(0).getSecond(), campnames);
        getCampPrompt.startPrompt();
        int idx = campnames.indexOf(getCampPrompt.getResult());
        campToChange = this.camps.get(idx);
        for(int i = 1; i < questions.size();i++){
            IPrompt tmp;
            if(i==1 || i==2 || i ==3){
                
                if(i==1){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getDates()[0]));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    Date[] ds = campToChange.getDates();
                    try{ds[0] = sdf.parse(tmp.getResult());}catch(Exception e){}
                    campToChange.setDates(ds);
                } else if(i==2){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getDates()[1]));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    Date[] ds = campToChange.getDates();
                    try{ds[1] = sdf.parse(tmp.getResult());}catch(Exception e){}
                    campToChange.setDates(ds);
                } else{
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.DATE.toString(), true, sdf.format(campToChange.getClosingDate()));
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    try{campToChange.setClosingDate(sdf.parse(tmp.getResult()));}catch(Exception e){}
                }    
                
                 
            }else if(i==4){
                tmp = new PromptOption(questions.get(i).getSecond(), true, campToChange.getUserGroup().name(), campnames);
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
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(), true, campToChange.getLocation());
                    tmp.startPrompt();
                    if (tmp.getResult().isEmpty()) continue;
                    campToChange.setTotalSlots(Integer.valueOf(tmp.getResult()));
                }
                else if(i==7){
                    tmp = new Prompt(questions.get(i).getSecond(), RegexType.INTEGER.toString(),true, campToChange.getLocation());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setCommitteeSlot(Integer.valueOf(tmp.getResult()));
                }
                else if(i==8){
                    tmp = new Prompt(questions.get(i).getSecond(), true, campToChange.getLocation());
                    tmp.startPrompt();
                    if(tmp.getResult().isEmpty()) continue;
                    campToChange.setLocation(tmp.getResult());
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
        this.value = campToChange;
    }


    @Override
    public void perform() {
        initPrompts();
        
    }


    @Override
    public Camp getObject() {
       return this.value;
    }
}
