package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;

public class RegisterForCampPromptPage implements IPromptPage<Camp> {

    private ArrayList<Camp> camps;
    private IPrompt prompt;
    private Camp value;
    public RegisterForCampPromptPage(ArrayList<Camp> camps) {//must intilize list of camps
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
            this.prompt = new PromptOption("Enter the name of the camp you would like to register for as an attendee: ", cs);
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