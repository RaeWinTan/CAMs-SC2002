package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for students to choose which camp they 
 * would like to withdraw from
 */
public class CampWithdrawalPromptPage implements IPromptPage<Camp> {

    private IPrompt prompt;
    private Student student;
    private ArrayList<Camp> camps;
    private ArrayList<String>camp_str = new ArrayList<>();
    private Camp value;

    /**Constructor for the class
     * @param student which is the student who is withdrawing from camp
     */
    public CampWithdrawalPromptPage(Student student) {
        this.student = student;
        this.camps = new ArrayList<Camp>();
        for(CampMember cm:student.getAttending()) this.camps.add(cm.getCamp());
        initPrompt();
        
        
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    /**
     * Initializes the questions to be asked in this prompt.
     */
    private void initPrompt() {
        
        for(Camp c:this.camps) camp_str.add(c.getCampName());
        this.prompt = new PromptOption("Enter the name of the camp you would like to withdraw from", camp_str);
        
    }

    /**Begin the prompting process
     * and stores the input the user provides
     */
    @Override
    public void perform() {
        this.prompt.startPrompt();
        int idx = camp_str.indexOf(this.prompt.getResult());
        this.value = this.camps.get(idx);
    }

    /**Getter method to return the result
     * @return value as a Camp object
     */
    @Override
    public Camp getObject() {
        return this.value;
    }
}
