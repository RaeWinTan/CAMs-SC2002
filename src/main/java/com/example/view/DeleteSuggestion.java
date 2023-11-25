package com.example.view;

import java.util.ArrayList;


import com.example.datastructure.Suggestion;


public class DeleteSuggestion implements IPromptPage<Suggestion> {

    private IPrompt prompt;
    private Suggestion value;
    private ArrayList<Suggestion> suggestions;

    public DeleteSuggestion(ArrayList<Suggestion> suggestions) {
        //the suggestions are filtered in the controler to only give suggestions not approved
        this.suggestions = suggestions;
        this.initPrompts();
    }

    private void initPrompts() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("oringial camp");
        headers.add("suggestion");
        ArrayList<String> ori_string = new ArrayList<>();
        ArrayList<String> suggestion_string = new ArrayList<>();
        for(int i = 0;i < this.suggestions.size();i++){
            ori_string.add(this.suggestions.get(i).getOriginalCamp().toString());
            suggestion_string.add(this.suggestions.get(i).getSuggestedCamp().toString());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(ori_string);
        columns.add(suggestion_string);
        this.prompt = new TablePromptOption("choose a suggestion you want to delete", headers, columns);        
    }

    @Override
    public void perform() {
        this.prompt.startPrompt();
        int idx = Integer.valueOf(this.prompt.getResult());
        this.value = this.suggestions.get(idx);
    }

    @Override
    public Suggestion getObject() {
        return this.value;
    }
}
