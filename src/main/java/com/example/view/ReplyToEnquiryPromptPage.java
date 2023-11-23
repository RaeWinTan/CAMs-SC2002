package com.example.view;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.utility.Pair;

public class ReplyToEnquiryPromptPage implements IPromptPage<String> {

    private ArrayList<String>questions = new ArrayList<>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<Camp> camps = new ArrayList<>();
    private String value;
    
    public ReplyToEnquiryPromptPage(ArrayList<Camp> camps) {
        this.camps = camps;
        initialise_questions();
        init_prompts();
        
        
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    private void initialise_questions() {
        questions.add("Which Camp do you want to see enquiries from? ");
        questions.add("Which enquiry you want to reply to");
        questions.add("Submit your reply below");

    }
    private void init_prompts(){
        ArrayList<String> cs = new ArrayList<String>();
        for(int i = 0; i < this.camps.size(); i++){
            cs.add(this.camps.get(i).getCampName());
        }
        Camp c;
        for(int i = 0; i < questions.size(); i++){
            if(i==0){
                try {
                    this.prompts.add(new PromptOption(questions.get(i), cs));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(i==1){
                try {
                    this.prompts.add(new PromptOption(questions.get(i), cs));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                this.prompts.add(new Prompt(questions.get(i)));
            }
        }
    }

    @Override
    public void perform() {
        Camp campReferingTo;
        Enquiry enquiryReferingTo;
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
        ArrayList<String> es = new ArrayList<String>();//enquiry identifiers 
        ArrayList<String> cs = new ArrayList<String>();
        for(int i = 0; i < this.camps.size(); i++){
            cs.add(this.camps.get(i).getCampName());
        }
        for(int i = 0; i < this.prompts.size(); i++){
            if(i==0){
                this.prompts.get(i).startPrompt();
                int idx = cs.indexOf(this.prompts.get(i).getResult());
                campReferingTo = this.camps.get(idx);
            }else if(i==1){
                enquiries = campReferingTo.getEnquiries();
                for(int j = 0; j < enquiries.size(); j++){
                    es.add(enquiries.get(j).get);
                }
                this.prompts.set(i, new PromptOption(this.questions.get(i), ));
                this.prompts.get(i).startPrompt();

            }else{

            }
        }
    }

    @Override
    public String getObject() {
        // TODO Auto-generated method stub
        return this.value;
    }
}


