package com.example.view;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.User;
import com.example.utility.Pair;

public class ReplyToEnquiryPromptPage implements IPromptPage<Message> {

    private ArrayList<String>questions = new ArrayList<>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<Camp> camps = new ArrayList<>();
    private User author;
    private Message value;
    //should a message
    public ReplyToEnquiryPromptPage(User author, ArrayList<Camp> camps) {
        this.author = author;
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
            if (i==0){
                this.prompts.get(i).startPrompt();
                int idx = cs.indexOf(this.prompts.get(i).getResult());
                campReferingTo = this.camps.get(idx);
            } else if(i==1){
                enquiries = campReferingTo.getEnquiries();
                this.prompts.set(i, new EnquiryPromptOptionTable(enquiries));
                this.prompts.get(i).startPrompt();
                int e = Integer.valueOf(this.prompts.get(i).getResult());
                enquiryReferingTo = enquiries.get(e);
            } else {
                this.prompts.get(i).startPrompt();
                this.value = new Message(this.prompts.get(i).getResult(), author);
            }
        }
    }

    @Override
    public Message getObject() {
        // TODO Auto-generated method stub
        return this.value;
    }
}


