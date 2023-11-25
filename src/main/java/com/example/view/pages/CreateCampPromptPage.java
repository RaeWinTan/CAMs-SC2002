package com.example.view.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.example.controllerlibs.UserType;
import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;

public class CreateCampPromptPage implements IPromptPage<Camp>{
    private String CampName;
    private String CampStartDate;
    private Date startDate;
    private Date endDate;
    private Date closingDate; //this is the camp reg closing date
    private String CampEndDate;
    private String CampRegCloseDate;
    private String CampFaculty;
    private String CampLocation;
    private String CampTotalSlots;
    private int totalSlots;
    private String CampCommmitteeSlots;
    private int committeeSlots;
    private String CampDescription;
    private String CampVisibility;

    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<String> questions = new ArrayList<String>();
    private Camp newCamp;
    public CreateCampPromptPage() {
        this.newCamp = new Camp();//
        initPrompts();
    }


    //public void addQuestion_attribute(String question, String attributeName) {return;}



    private void initPrompts() {
        String dateRegex = "\\d{2}/\\d{2}/\\d{4}"; // Regex for date format DD/MM/YYYY
        String integerRegex = "\\d+"; // Regex for integer values
        this.questions.add("Enter camp name ");
        this.questions.add("Enter camp start date ");
        this.questions.add("Enter camp end date ");
        this.questions.add("Enter closing date for registration ");
        this.questions.add("Enter faculty the camp is open to ");
        this.questions.add("Enter camp location ");
        this.questions.add("Enter total slots ");
        this.questions.add("Enter committee slots ");
        this.questions.add("Enter camp description ");
        this.questions.add("Set Camp Visibility ");
        for(int i = 0; i < questions.size(); i++){
            if (i == 1 || i == 2 || i == 3) {
                this.prompts.add(new Prompt(questions.get(i), dateRegex));
            } else if (i == 6 || i == 7) {
                this.prompts.add(new Prompt(questions.get(i), integerRegex));
            }
            else if(i == 4){
                ArrayList<String> ops = new ArrayList<>();
                for(int j = 0; j < GroupName.values().length;j++){
                    ops.add(GroupName.values()[j].toString());//TODO LATER MUST CHANGE ACCORDING TO ROBIN COD
                }
                this.prompts.add(new PromptOption(questions.get(i), ops));
                
            }else if(i==9){
                ArrayList<String> ops = new ArrayList<>();
                ops.add("On");
                ops.add("Off");
                this.prompts.add(new PromptOption(questions.get(i), ops));
                
            }else{
                this.prompts.add(new Prompt(questions.get(i)));
            }
        }

    }



    public void perform() {
        int i = 0;
        while (i < prompts.size()) {
  
            //String q = questions.get(i);
            //IPrompt tmp;
            if(i==0) {
                this.prompts.get(i).startPrompt();
                CampName = this.prompts.get(i).getResult();
            }else if(i==1){
                this.prompts.get(i).startPrompt();
                CampStartDate = this.prompts.get(i).getResult();
            }

            else if(i==2){
                this.prompts.get(i).startPrompt();
                CampEndDate = this.prompts.get(i).getResult();
            }

            else if(i==3){
                this.prompts.get(i).startPrompt();
                CampRegCloseDate = this.prompts.get(i).getResult();}

            else if(i==4){
                System.out.println("STSRT 4");
                this.prompts.get(i).startPrompt();
                System.out.println("END 4");
                CampFaculty = this.prompts.get(i).getResult();}

            else if(i==5){
                this.prompts.get(i).startPrompt();
                CampLocation = this.prompts.get(i).getResult();}

            else if(i==6){
                this.prompts.get(i).startPrompt();
                CampTotalSlots = this.prompts.get(i).getResult();}

            else if(i==7){
                this.prompts.get(i).startPrompt();
                CampCommmitteeSlots = this.prompts.get(i).getResult();}

            else if(i==8){
                this.prompts.get(i).startPrompt();
                CampDescription = this.prompts.get(i).getResult();}

            else if(i==9){
                this.prompts.get(i).startPrompt();
                CampVisibility = this.prompts.get(i).getResult();}
            i++;
        }}



    public Camp getObject() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            startDate = formatter.parse(CampStartDate);
            endDate = formatter.parse(CampEndDate);
            closingDate = formatter.parse(CampRegCloseDate);
        } catch (ParseException e) {
            e.printStackTrace(); // This is the basic way to handle the exception.
        }
        Date[] dates = new Date[2];
        dates[0] = startDate;
        dates[1] = endDate;
        totalSlots = Integer.valueOf(CampTotalSlots);
        committeeSlots = Integer.valueOf(CampCommmitteeSlots);
        
        GroupName faculty;
        if(CampFaculty.equalsIgnoreCase("NTU")){
            faculty = GroupName.NTU;}
        else if(CampFaculty.equalsIgnoreCase("SCSE")){
            faculty = GroupName.SCSE;}
        else if(CampFaculty.equalsIgnoreCase("ADM")){
            faculty = GroupName.ADM;}
        else if(CampFaculty.equalsIgnoreCase("EEE")){
            faculty = GroupName.EEE;}
        else if(CampFaculty.equalsIgnoreCase("NBS")){
            faculty = GroupName.NBS;}

        else{
            faculty = GroupName.SSS;}

        boolean visibility = false;
        if(CampVisibility.equals("On")){
            visibility = true;
        }else{
            visibility = false;
        }
        this.newCamp.setCampName(CampName);
        this.newCamp.setDates(dates);
        this.newCamp.setClosingDate(closingDate);
        this.newCamp.setTotalSlots(totalSlots);
        this.newCamp.setCommitteeSlot(committeeSlots);
        this.newCamp.setLocation(CampLocation);
        this.newCamp.setDescription(CampDescription);
        this.newCamp.setUserGroup(faculty);
        this.newCamp.setVisibility(visibility);

        return this.newCamp;
    }}

