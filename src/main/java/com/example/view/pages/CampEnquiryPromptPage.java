package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Student;

import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for students to submit camp enquiries
 */
public class CampEnquiryPromptPage implements IPromptPage<Enquiry> {
    private Student student;
    private Camp campReferingTo;
    private String text;
    private ArrayList<Camp> camps;
    private ArrayList<String> cs = new ArrayList<String>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<String> questions = new ArrayList<String>();

    /**
     * Constructor for the class
     *
     * @param student which is the student submitting the enquiry
     * @param camps which is the list of camps the student can submit an enquiry for
     */
    public CampEnquiryPromptPage(Student student) {
        this.student = student;
        this.camps = new ArrayList<>();
        for(CampMember cm: student.getAttending()){
            camps.add(cm.getCamp());
            
        } 
        initQuestions();
        for(int i = 0; i < camps.size(); i++) cs.add(camps.get(i).getCampName());
        for(int i = 0; i < this.questions.size();i++){
            if(i == 0){
               this.prompts.add(new PromptOption(this.questions.get(i),cs));
            }else { 
                this.prompts.add(new Prompt(this.questions.get(i)));
                
            }
        }
    }
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    /**
     * Initializes the questions to be asked in this prompt.
     */
    private void initQuestions() {
        questions.add("Which camp you want to enquire about: ");
        questions.add("What do you want to enquire about this camp");
    }

    /**Begin the prompting process
     * and stores the input the user provides
     */
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

    /**Getter method to return the result
     * @return value as a Enquiry object
     */
    @Override
    public Enquiry getObject() {
        return new Enquiry(text, student, campReferingTo);
    }
}

