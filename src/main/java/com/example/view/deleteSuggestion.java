package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.datastructure.Suggestion;
import com.example.utility.Pair;

public class DeleteSuggestion implements IPromptPage<Suggestion> {

    private ArrayList<IPrompt> prompts = new ArrayList<>();
    private Suggestion deletedSuggestion;

    public DeleteSuggestion() {
        this.initPrompts();
    }

    private void initPrompts() {
        
        this.prompts.add(new TablePrpo"Choose which suggestion you'd like to delete: ");
        question_attribute_mapping.add(new Pair<>("Would you like to delete this suggestion: ", "suggestionDelete"));
    }

    @Override
    public void perform() {
        for (Pair<String, String> questionPair : question_attribute_mapping) {
            String attribute = questionPair.getSecond();

            if ("suggestionDelete".equals(attribute)) {
                ArrayList<String> options = new ArrayList<>(Arrays.asList("Delete", "Keep"));
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

    @Override
    public Suggestion getObject() {
        return this.deletedSuggestion;
    }
}
