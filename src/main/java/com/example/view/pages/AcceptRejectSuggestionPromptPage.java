package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.TablePromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for accepting or rejecting suggestions. 
 * It displays suggestions from the camp committee members and the 
 * staff user can select one to accept it
 */
public class AcceptRejectSuggestionPromptPage implements IPromptPage<Suggestion> {
    private Staff staff;
    private ArrayList<Suggestion> suggestions = new ArrayList<>();
    private IPrompt prompt;
    private String question;
    private Suggestion value;

    /**
     * Constructor for the class
     *
     * @param staff which is the staff user who is accepting the suggestions
     */
    public AcceptRejectSuggestionPromptPage(Staff staff) {
        this.staff = staff;
        this.initQuestions();
    }


    
    /**
     * Initializes the questions to be asked in this prompt.
     * This includes displaying all the options to be chosen
     * and setting up the prompt for selection.
     */
    private void initQuestions() {
        this.question = "Which suggestion do you want to accept";
        for(int j = 0; j < staff.getCampsCreated().size();j++) suggestions.addAll(staff.getCampsCreated().get(j).getSuggestions());
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp name");
        headers.add("author");
        headers.add("Suggestion");
        ArrayList<String> camp_string = new ArrayList<>();
        ArrayList<String> author_string = new ArrayList<>();
        ArrayList<String> suggestion_string = new ArrayList<>();
        ArrayList<ArrayList<String>> objs = new ArrayList<ArrayList<String>>();
        for(int k = 0; k < suggestions.size(); k++){
            camp_string.add(suggestions.get(k).getCamp().toString());
            author_string.add(suggestions.get(k).getAuthor().getUserId());
            suggestion_string.add(suggestions.get(k).getCamp().toString());            
        }
        objs.add(camp_string);
        objs.add(author_string);
        objs.add(suggestion_string);
        this.prompt = new TablePromptOption(this.question, headers, objs);
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
