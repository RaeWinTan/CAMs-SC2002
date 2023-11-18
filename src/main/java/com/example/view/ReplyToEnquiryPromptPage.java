package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;

public class ReplyToEnquiryPromptPage extends PromptPage {

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    ReplyToEnquiryPromptPage() {
        initialise_question_attribute_mapping();
        
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String,String>("Whose enquiry would you like to reply to? ",
                "campEnquiryID"));
        question_attribute_mapping.add(new Pair<String,String>("Submit your reply below ",
                "campEnquiryReply"));

    }

    @Override
    public void prompting() {
        for (Pair<String, String> p : question_attribute_mapping){
        Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond());
        this.prompts.add(tmp);}
    }
}


