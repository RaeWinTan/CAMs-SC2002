package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.utility.Pair;

public class AcceptRejectSuggestionPromptPage implements IPromptPage {

    private ArrayList<Pair<String, String>> question_attribute_mapping = new ArrayList<>();
    private ArrayList<IPrompt> prompts = new ArrayList<>();

    public AcceptRejectSuggestionPromptPage() {
        this.initialise_question_attribute_mapping();

        
    }

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    
    //public void addQuestion_attribute(String question, String attributeName) { return; }

    private void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<>("Would you like to accept or reject this suggestion: ", "suggestionAcceptance"));
    }

    @Override
    public void prompting() {
        for (Pair<String, String> questionPair : question_attribute_mapping) {
            String attribute = questionPair.getSecond();

            if ("suggestionAcceptance".equals(attribute)) {
                ArrayList<String> options = new ArrayList<>(Arrays.asList("I would like to Accept it", "I would like to Reject it"));
                try {
                    prompts.add(new PromptOption(questionPair.getFirst(), attribute, options));
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    break;
                }
            } else {
                prompts.add(new Prompt(questionPair.getFirst(), attribute));
            }
        }
    }
}
