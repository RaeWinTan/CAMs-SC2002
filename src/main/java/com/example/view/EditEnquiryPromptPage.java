package com.example.view;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.utility.Pair;

public class EditEnquiryPromptPage implements IPromptPage<Enquiry>{
    private ArrayList<Enquiry> enquiries;
    private Enquiry value;
    public EditEnquiryPromptPage(ArrayList<Enquiry> enquiries){
        //must make sure it its a enquiry with not replies
        this.enquiries = enquiries;
    }
    public void initPrompt(){
        Enquiry enquiryToEdit;
        ArrayList<Pair<Integer, String>> questions = new ArrayList<>();
        questions.add(new Pair<>(0, "chose enquiry you want to edit"));
        questions.add(new Pair<>(1, "type your text"));
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Camp Name");
        headers.add("text");
        ArrayList<String> camp_names = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();
        for(Enquiry e:enquiries){
            camp_names.add(e.getCamp().getCampName());
            texts.add(e.getText());
        }
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(camp_names);
        columns.add(texts);
        IPrompt table = new TablePromptOption(questions.get(0).getSecond(), headers, columns);
        table.startPrompt();
        int idx = Integer.valueOf(table.getResult());
        enquiryToEdit = enquiries.get(idx);
        IPrompt editText = new Prompt(questions.get(1).getSecond());
        editText.startPrompt();
        enquiryToEdit.setText(editText.getResult());
        this.value = enquiryToEdit;
    }
    @Override
    public void perform() {
        initPrompt();
    }

    @Override
    public Enquiry getObject() {
        return value; 
    }

}
