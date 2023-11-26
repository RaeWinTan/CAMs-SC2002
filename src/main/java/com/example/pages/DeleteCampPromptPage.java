package com.example.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Staff;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.PromptOption;

/** This prompt page is for the staff to delete camps they have created.
 * 
*/
public class DeleteCampPromptPage implements IPromptPage<Camp> {
    private Staff staff;
    private Camp value;
    public DeleteCampPromptPage(Staff staff){
        this.staff = staff;
    }

    @Override
    public void perform() {
        // TODO Auto-generated method stub
        ArrayList<Camp> camps = staff.getCampsCreated();
        ArrayList<String> campnames = new ArrayList<>();
        for(Camp c:camps){
            campnames.add(c.getCampName());
        }
        IPrompt p = new PromptOption("What camp you want to delete", campnames);
        p.startPrompt();
        int idx = campnames.indexOf(p.getResult());
        value = camps.get(idx);
    }

    @Override
    public Camp getObject() {
        
        return this.value;
    }
    
}
