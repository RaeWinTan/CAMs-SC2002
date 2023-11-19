package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public class editSuggestion implements IPromptPage {

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    SubmitCampSuggestionPromptPage() {
        viewSubmittedSuggestions();
        initialise_question_attribute_mapping();
        
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    private void initialise_question_attribute_mapping() {
                question_attribute_mapping.add(new Pair<String,String>("Which suggestion do you want to edit? ",
                "suggestionID"));
                question_attribute_mapping.add(new Pair<String,String>("Edit your suggestion ",
                "editSuggestion"));
    }

    @Override
    public void prompting() {
        for (int i = 0, i < 2, i++){
        Prompt tmp = new Prompt(question_attribute_mapping.get(i).getFirst(),
                question_attribute_mapping.get(i).getSecond());
        this.prompts.add(tmp);}
    }
}

