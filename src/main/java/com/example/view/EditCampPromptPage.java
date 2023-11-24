package com.example.view;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.datastructure.Camp;
import com.example.utility.Pair;

public class EditCampPromptPage implements IPromptPage<Camp> {


    private ArrayList<IPrompt> prompts = new ArrayList<>();
    private ArrayList<Camp> camps;

    public EditCampPromptPage(ArrayList<Camp> camps ) throws Exception {
        this.camps = camps;
    }
    

    private void initPrompts() {
        //show all camps 
        Camp campToChange;
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        String d = " (just press enter if you don't want to edit this value)";
        questions.add(new Pair<>(0,"Chose Camp you want to edit"+ d));
        questions.add(new Pair<>(1,"start date" + d));
        questions.add(new Pair<>(2,"end date" + d));
        questions.add(new Pair<>(3,"Registration closing date" + d));
        questions.add(new Pair<>(4,"Open to which Faculty" + d));
        questions.add(new Pair<>(5,"Location" + d));
        questions.add(new Pair<>(6,"total slots" + d));
        questions.add(new Pair<>(7,"committee slots" + d));
        questions.add(new Pair<>(8,"camp description" + d));
        questions.add(new Pair<>(9, "Visibility"+d));
        ArrayList<String> campnames = new ArrayList<>();
        for(Camp c:this.camps){campnames.add(c.getCampName());}
        IPrompt getCampPrompt = new Prompt("init");
        try {getCampPrompt = new PromptOption(questions.get(0).getSecond(), campnames);} 
        catch (Exception e) {e.printStackTrace();}
        getCampPrompt.startPrompt();
        int idx = campnames.indexOf(getCampPrompt.getResult());
        campToChange = this.camps.get(idx);
        for(int i = 1; i < questions.size();i++){
            if(questions.get(i).getFirst() == 4){
                if(campToChange.getAttendees().size()==0 && campToChange.getCommittees().size() == 0){
                    
                    for(int  j=0; j < ; j++)
                    this.prompts.add(new PromptOption(questions.get(i).getSecond(), ));
                }else continue;
            }   
            else if(questions.get(i).getFirst() == 9){
                if(campToChange.getAttendees().size()==0 && campToChange.getCommittees().size() == 0){

                }
            }
        }
    }


    @Override
    public void perform() {
        initPrompts();
        
    }
}
