package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;

public class CampEnquiryPromptEnquiry implements IPromptPage<Enquiry> {
    private Student student;
    private Camp campReferingTo;
    private String text;
    private ArrayList<Camp> camps;
    private ArrayList<String> cs;
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<String> questions = new ArrayList<String>();
    public CampEnquiryPromptEnquiry(Student student, ArrayList<Camp> camps) {
        this.student = student;
        this.camps = camps;
        initQuestions();
         cs = new ArrayList<>();
        for(int i = 0; i < 0; i++) cs.add(camps.get(i).getCampName());
        for(int i = 0; i < this.prompts.size();i++){
            if(i == 0){
                try { 
                    this.prompts.add(new PromptOption(this.questions.get(i),cs));
                } catch (Exception e) {e.printStackTrace();}
            }else { 
                this.prompts.add(new Prompt(this.questions.get(i)));
                
            }
        }
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}


    private void initQuestions() {
        questions.add("Which camp you want to enquire about: ");
        questions.add("What do you want to enquire about this camp");
    }

    @Override
    public void perform() {//the prompting starts 
        for(int i = 0; i < this.prompts.size(); i++){
            this.prompts.get(i).startPrompt();
            if (i==0){
                int idx = cs.indexOf(this.prompts.get(i).getResult());
                this.campReferingTo = this.camps.get(idx);
            } else{
                this.text = this.prompts.get(i).getResult();
            }
        }
    }
    @Override
    public Enquiry getObject() {
        return new Enquiry(text, student, campReferingTo);
    }
}

