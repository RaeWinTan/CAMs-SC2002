package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.UserType;
import com.example.datastructure.Camp;
import com.example.datastructure.GroupName;
import com.example.utility.Pair;

public class CreateCampPromptPage implements IPromptPage<Camp>{
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private Camp newCamp;
    public CreateCampPromptPage() {
        this.newCamp = new Camp();//

        initPrompts();
    }


    //public void addQuestion_attribute(String question, String attributeName) {return;}

  

    private void initPrompts() {
        ArrayList<String> questions = new ArrayList<>();
        questions.add("Enter camp name ");
        questions.add("Enter camp start date ");
        questions.add("Enter camp end date ");
        questions.add("Enter closing date for registration ");
        questions.add("Enter faculty the camp is open to ");
        questions.add("Enter camp location ");
        questions.add("Enter total slots ");
        questions.add("Enter committee slots ");
        questions.add("Enter camp description ");
        questions.add("Set Camp Visibility ");
        for(int i = 0; i < questions.size(); i++){
            if(i == 4){
                ArrayList<String> ops = new ArrayList<>();
                for(i = 0; i < GroupName.values().length;i++){
                    ops.add(GroupName.values()[i].toString());//TODO LATER MUST CHANGE ACCORDING TO ROBIN CODE
                }
                try {
                    this.prompts.add(new PromptOption(questions.get(i), ops));
                } catch (Exception e) {
                    e.printStackTrace();
      
                }
            }else if(i==9){
                ArrayList<String> ops = new ArrayList<>();
                ops.add("true");
                ops.add("false");
                try{
                    this.prompts.add(new PromptOption(questions.get(i), ops));
                }catch (Exception e){
                    e.printStackTrace();
                   
                }
            }else{
                this.prompts.add(new Prompt(questions.get(i)));
            }
        }
        
    }


    @Override
    public void perform() {
        int i = 0;
        while (i < question_attribute_mapping.size()) {
            Pair<String, String> questionPair = question_attribute_mapping.get(i);
            String attribute = questionPair.getSecond();

            if ("campCreate_visibility".equals(attribute)) {
                ArrayList<String> options = new ArrayList<>(Arrays.asList("On", "Off"));
                try {
                    prompts.add(new PromptOption(questionPair.getFirst(), attribute, options));
                    i++;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                Prompt tmp = new Prompt(questionPair.getFirst(), attribute);
                if ("campCreate_startDate".equals(attribute) && tmp.getResult().getSecond().matches("[a-zA-Z]+")){
                    System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                }
                else if("campCreate_endDate".equals(attribute) && tmp.getResult().getSecond().matches("[a-zA-Z]+")){
                    System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                }
                else if("campCreate_registrationEndDate".equals(attribute) && tmp.getResult().getSecond().matches("[a-zA-Z]+")){
                    System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                }
                else if("campCreate_totalSlots".equals(attribute) && !tmp.getResult().getSecond().matches("[0-9]+")){
                    System.out.println("Please only enter a valid number value");
                }
                else if ("campCreate_committeeSlots".equals(attribute)) {
                    String input = tmp.getResult().getSecond();
                    if (input.matches("[0-9]+")) {
                        int number = Integer.parseInt(input);

                        if (number < 1 || number > 10) {
                            System.out.println("Number must be between 1 and 10");
                        } else {
                            this.prompts.add(tmp);
                            i++;
                        }
                    } else {
                        System.out.println("Please only enter a valid number value");
                    }
                }
                else {
                    this.prompts.add(tmp);
                    i++;
                }
            }
            //i++; // Increment at the end of the loop
        }
        
    }


    @Override
    public Camp getObject() {
        // TODO Auto-generated method stub
        return this.newCamp;
    }
}

