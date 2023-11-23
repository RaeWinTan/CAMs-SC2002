package com.example.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;

public class AcceptRejectSuggestionPromptPage implements IPromptPage<Suggestion> {
    private Staff staff;
    private ArrayList<Pair<String, String>> question_attribute_mapping = new ArrayList<>();
    private ArrayList<Suggestion> suggestions = new ArrayList<>();
    private IPrompt prompt;
    private ArrayList<String> questions = new ArrayList<>();
    private Suggestion value;
    public AcceptRejectSuggestionPromptPage(Staff staff) {
        this.staff = staff;
        this.initQuestions();
    }


    
    //public void addQuestion_attribute(String question, String attributeName) { return; }

    private void initQuestions() {
        this.questions.add("Which suggestion do you want to accept");
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
            camp_string.add(suggestions.get(k).getCamp().getCampName());
            author_string.add(suggestions.get(k).getAuthor().getUserId());
            suggestion_string.add(suggestions.get(k).getCamp().toString());//TODO : NEED TO BE DEVELOPED IN CAMP CLASS                 
        }
        objs.add(camp_string);
        objs.add(author_string);
        objs.add(suggestion_string);
        this.prompt = new TablePromptOption(this.questions.get(i), headers, objs);//AMIT CODE HERE
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
