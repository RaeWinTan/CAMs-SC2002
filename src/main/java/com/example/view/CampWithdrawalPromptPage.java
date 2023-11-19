package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public class CampWithdrawalPromptPage implements IPromptPage {

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    CampWithdrawalPromptPage() {
        initialise_question_attribute_mapping();
        
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    private void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String, String>("Enter the name of the camp you would " +
                "like to withdraw from",
                "campWithdrawal"));
    }

    @Override
    public void perform() {
        Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond());
        this.prompts.add(tmp);
    }
}
