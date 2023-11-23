package com.example.view;

import java.util.ArrayList;

import com.example.datastructure.Camp;


public class RegisterForCampCommitteeMemberPromptPage implements IPromptPage<Camp> {
    private ArrayList<Camp> camps;
    private IPrompt prompt;
    private Camp value;
    public RegisterForCampCommitteeMemberPromptPage(ArrayList<Camp> camps) {//must intilize list of camps
        this.camps = camps;
    }

    @Override
    public void perform() {
        ArrayList<String> cs = new ArrayList<String>();
        for(int i = 0; i < this.camps.size(); i++){
            cs.add(this.camps.get(i).getCampName());
        }
        this.prompt = new Prompt("INIT");
        try {
            this.prompt = new PromptOption("Enter the name of the camp you would like to register for as a committee member:", cs);
            this.prompt.startPrompt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int index = cs.indexOf(this.prompt.getResult());
        this.value = this.camps.get(index);
    }



    @Override
    public Camp getObject() {
        return this.value;
    }
}
