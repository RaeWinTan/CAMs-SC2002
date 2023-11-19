package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public class LoginPromptPage implements IPromptPage{

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public LoginPromptPage() {
        this.initialise_question_attribute_mapping();
        
    }


    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }


    //public void addQuestion_attribute(String question, String attributeName) {return;}

    private void initialise_question_attribute_mapping(){
        question_attribute_mapping.add(new Pair<String,String>("Please enter your UserID: ", "username"));
        question_attribute_mapping.add(new Pair<String,String>("Please enter your password: ", "password"));
        question_attribute_mapping.add(new Pair<String,String>("Please enter your User Type", "userType"));
        
    }


    @Override
    public void prompting() {
        // TODO Auto-generated method stub
        int i = 0;
        while (i < question_attribute_mapping.size()) {
            Pair<String, String> questionPair = question_attribute_mapping.get(i);
            String attribute = questionPair.getSecond();
            IPrompt tmp;
            if ("userType".equals(attribute)) {
                ArrayList<String> options = new ArrayList<>();
                options.add("Staff");
                options.add("Student");
                try {
                    
                    tmp = new PromptOption(questionPair.getFirst(), questionPair.getSecond(), options);
                    this.prompts.add(tmp);
                    
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                tmp = new Prompt(questionPair.getFirst(), questionPair.getSecond());
                this.prompts.add(tmp);
            }
            i++;
        }
    }

}