package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;
 
public class SubmitCampSuggestionPromptPage implements IPromptPage<> {

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    SubmitCampSuggestionPromptPage() {
        initPrompt();
        
    }


    private void initPrompt() {
        question_attribute_mapping.add(new Pair<String,String>("Submit your suggestion regarding the camp ",
                "campSuggestion"));
    }

    @Override
    public void perform() {
        Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond());
        this.prompts.add(tmp);
    }
}
