package com.example.view;

import java.util.ArrayList;

public class ChangePasswordPromptPage extends PromptPage{
    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public ChangePasswordPromptPage(){
        initialise_question_attribute_mapping();
        Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond());
        this.prompts.add(tmp);
        }

    //public void addQuestion_attribute(String question, String attributeName) {return;}

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String, String>("Enter your new password ",
                "newPassword"));
        //question_attribute_mapping.add(new Pair<String, String>("Confirm your password",
                //"confirmPassword"));
    }
}
