package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.TablePromptOption;


public class DeleteSuggestion implements IPromptPage<Suggestion> {

    private IPrompt prompt;
    private Suggestion value;
    private ArrayList<Suggestion> suggestions = new ArrayList<>();
    private Student student;

    public DeleteSuggestion(Student student) {
        this.student = student;
        initPrompts();
    }

    private void initPrompts() {
        ArrayList<String> oldCamps = new ArrayList<>();
        ArrayList<String> newCamps = new ArrayList<>();
        this.suggestions = student.getSuggestions();
        for(Suggestion sus:student.getSuggestions()){
            Camp nc = sus.getCamp();
            Camp oc = new Camp();
            for(CampMember mc:student.getLeading()){
                if(nc.isEquals(mc.getCamp())){
                    oc = mc.getCamp();
                    break;
                } 
            }
            oldCamps.add(oc.toString());
            newCamps.add(nc.toString());
        }
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Original Camp");
        headers.add("Suggested Camp");
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(oldCamps);
        columns.add(newCamps); 
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
