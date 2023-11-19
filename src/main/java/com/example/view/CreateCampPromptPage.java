package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.utility.Pair;

public class CreateCampPromptPage implements IPromptPage{
    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public CreateCampPromptPage() {
        initialise_question_attribute_mapping();
    }


    //public void addQuestion_attribute(String question, String attributeName) {return;}

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    private void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String, String>("Enter camp name ", "campName"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp start date ", "startDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp end date ", "endDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter closing date for registration ",
                "closingDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter faculty the camp is open to ",
                "userGroup"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp location ", "location"));
        question_attribute_mapping.add(new Pair<String, String>("Enter total slots ", "totalSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter committee slots ",
                "committeeSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp description ",
                "description"));
        question_attribute_mapping.add(new Pair<String, String>("Toggle Camp Visibility ",
                "visibility"));
    }


    @Override
    public void prompting() {
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
}

