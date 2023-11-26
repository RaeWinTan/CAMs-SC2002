package com.example.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.TablePromptOption;

/**
 * 
 * It is a prompt page for camp committee members to delete suggestions that they have previously submitted but have not been accepted yet. 
 * This class implements IPromptPage.
 */
public class DeleteSuggestion implements IPromptPage<Suggestion> {

    private IPrompt prompt;
    private Suggestion value;
    private ArrayList<Suggestion> suggestions = new ArrayList<>();
    private Student student;

    /**Constructor for the class
     * @param suggestions which is an array list of suggestions submitted 
     * by the member
     */
    public DeleteSuggestion(Student student) {
        this.student = student;
        initPrompts();
    }

    /**Initialises the questions to be asked in this prompt. It also passes in the
     * relevant data to populate the table of suggestions and camps
     * and then it asks the user to choose which suggestion they would
     * like to delete
     */
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
