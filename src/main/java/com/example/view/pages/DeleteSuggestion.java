package com.example.view.pages;

import java.util.ArrayList;


import com.example.datastructure.Suggestion;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.TablePromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for camp committee members to delete 
 * suggestions that they have previously submitted 
 * but have not been accepted yet
 */
public class DeleteSuggestion implements IPromptPage<Suggestion> {

    private IPrompt prompt;
    private Suggestion value;
    private ArrayList<Suggestion> suggestions;

    /**Constructor for the class
     * @param suggestions which is an array list of suggestions submitted 
     * by the member
     */
    public DeleteSuggestion(ArrayList<Suggestion> suggestions) {
        //the suggestions are filtered in the controler to only give suggestions not approved
        this.suggestions = suggestions;
        this.initPrompts();
    }

    /**Initialises the questions to be asked in this prompt. It also passes in the
     * relevant data to populate the table of suggestions and camps
     * and then it asks the user to choose which suggestion they would
     * like to delete
     */
    private void initPrompts() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Original camp");
        headers.add("Suggestion");
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

    /**Begin the prompting process
     * and stores the input the user provides
     */
    @Override
    public void perform() {
        this.prompt.startPrompt();
        int idx = Integer.valueOf(this.prompt.getResult());
        this.value = this.suggestions.get(idx);
    }

    /**Getter method to return the result
     * @return value as a Suggestion object
     */
    @Override
    public Suggestion getObject() {
        return this.value;
    }
}
