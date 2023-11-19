package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public class ChangePasswordPromptPage implements IPromptPage{
    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public ChangePasswordPromptPage(){
        initialise_question_attribute_mapping();
    }

    //public void addQuestion_attribute(String question, String attributeName) {return;}

    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }


    private void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String, String>("Enter your new password ",
                "newPassword"));
        //question_attribute_mapping.add(new Pair<String, String>("Confirm your password",
                //"confirmPassword"));
    }

    @Override
    public void perform() {
        Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond());
        this.prompts.add(tmp);
    }
}
