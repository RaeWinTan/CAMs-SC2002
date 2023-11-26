package com.example.view.pages;

import java.util.ArrayList;

import javax.management.relation.RoleList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;

/**
 * It is a prompt page for students to choose which camp they would like to withdraw from.
 * This class implements IPromptPage.
 * 
 */
public class CampWithdrawalPromptPage implements IPromptPage<Camp> {

    private IPrompt prompt;
    private Student student;
    private Camp value;

    /**Constructor for the class
     * @param student which is the student who is withdrawing from camp
     */
    public CampWithdrawalPromptPage(Student student) {
        this.student = student;
        
        
    }

    /**
     * Initializes the questions to be asked in this prompt.
     */
   

    /**Begin the prompting process
     * and stores the input the user provides
     */
    @Override
    public void perform() {
        ArrayList<Camp> camps = new ArrayList<>();
        for(CampMember cm:student.getAttending()) camps.add(cm.getCamp());
        ArrayList<String> campNames = new ArrayList<>();
        for(Camp c: camps) campNames.add(c.getCampName());
        System.out.println(campNames.size()+"size;");
        prompt = new PromptOption("What camp you want to withdraw from", campNames);
        prompt.startPrompt();
        int idx = campNames.indexOf(this.prompt.getResult());
        this.value = camps.get(idx);
    }

    /**Getter method to return the result
     * @return value as a Camp object
     */
    @Override
    public Camp getObject() {
        return this.value;
    }
}
