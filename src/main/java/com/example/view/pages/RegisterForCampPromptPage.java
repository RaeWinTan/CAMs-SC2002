package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for the students to choose which camp they
 * would like to register for.
 * It displays the different camps open to them and prompts them
 * to enter an option
 */
public class RegisterForCampPromptPage implements IPromptPage<Camp> {

    private ArrayList<Camp> camps;
    private IPrompt prompt;
    private Camp value;

    /**Constructor for the class
     * @param camps which is an array list of camps open to the student
     */
    public RegisterForCampPromptPage(ArrayList<Camp> camps) {//must intilize list of camps
        this.camps = camps;
    }

    /**Begin prompting.
     * Displays the options of camps for the student user to choose
     */
    @Override
    public void perform() {
        ArrayList<String> cs = new ArrayList<String>();
        for(int i = 0; i < this.camps.size(); i++){
            cs.add(this.camps.get(i).getCampName());
        }
        this.prompt = new PromptOption("Enter the name of the camp you would like to register for as an attendee: ", cs);
        this.prompt.startPrompt();
        int index = cs.indexOf(this.prompt.getResult());
        this.value = this.camps.get(index);
    }


    /**Getter method to return result
     * @return value as a Camp object
     */
    @Override
    public Camp getObject() {
        return this.value;
    }
}