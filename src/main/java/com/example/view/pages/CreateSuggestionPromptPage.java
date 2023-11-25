package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;

public class CreateSuggestionPromptPage implements IPromptPage<Suggestion>{
    private Student s;
    private ArrayList<IPrompt> prompts = new ArrayList<>();
    public CreateSuggestionPromptPage(Student s){
        this.s = s;
        ArrayList<Pair<Integer,String>> questions = new ArrayList<>();
        questions.add(new Pair<>(0, "Which Camp you want to make suggestion on"));
        questions.add(new Pair<>(1, "What suggestion you have"));
    }
    @Override
    public void perform() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'perform'");
    }

    @Override
    public Suggestion getObject() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObject'");
    }
    
}
