package com.example.view.pages;

import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.User;
import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;
import com.example.view.TablePromptOption;

/**
 * This class implements IPromptPage.
 * It is a prompt page for the user to reply to enquiries from students 
 * It displays enquiries from the students and the 
 * user can select one to accept it
 */
public class ReplyToEnquiryPromptPage implements IPromptPage<Pair<Enquiry, Message>> {

    private ArrayList<String>questions = new ArrayList<>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    private ArrayList<Camp> camps = new ArrayList<>();
    private User author;
    private Pair<Enquiry, Message> value;

    //should a message
    /**Constructor for the class
     * @param author which is the sender of the message
     * @param camps which is the list of camps the user can see enquiries from
     */
    public ReplyToEnquiryPromptPage(User author, ArrayList<Camp> camps) {
        this.author = author;
        this.camps = camps;
        initialise_questions();
        init_prompts();
        
        
    }
    /**Initialise the list of questions to ask the user */
    private void initialise_questions() {
        questions.add("Which Camp do you want to see enquiries from? ");
        questions.add("Which enquiry you want to reply to");
        questions.add("Submit your reply below");

    }

    /**Initialise the list of prompts based off the questions.
     * Also displays the list of enquiries addressed to the user
     */
    private void init_prompts(){
        ArrayList<String> cs = new ArrayList<String>();
        for(int i = 0; i < this.camps.size(); i++){
            cs.add(this.camps.get(i).getCampName());
        }
        Camp c;
        for(int i = 0; i < questions.size(); i++){
            if(i==0){
                this.prompts.add(new PromptOption(questions.get(i), cs)); 
            }else if(i==1){
                this.prompts.add(new PromptOption(questions.get(i), cs)); 
            }else{
                this.prompts.add(new Prompt(questions.get(i)));
            }
        }
    }

    /**Begin prompting
     * Displays the options of camps for the student user to choose
     */
    @Override
    public void perform() {
        Camp campReferingTo = new Camp();
        Enquiry enquiryReferingTo = new Enquiry();
        Message messageReferingTo = new Message();
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
                ArrayList<String> headers = new ArrayList<>();
                ArrayList<String> author_string = new ArrayList<>();
                ArrayList<String> enquiry_string = new ArrayList<>();
                ArrayList<ArrayList<String>> columns = new ArrayList<>(); 
                headers.add("student name");
                headers.add("enquiry");
                for(int k = 0; k < enquiries.size(); k++){
                    author_string.add(enquiries.get(k).getAuthor().getName());
                    enquiry_string.add(enquiries.get(k).getText());                   
                }
                columns.add(author_string);
                columns.add(enquiry_string);
                this.prompts.set(i, new TablePromptOption(this.questions.get(i), headers, columns));
                this.prompts.get(i).startPrompt();
                int e = Integer.valueOf(this.prompts.get(i).getResult());
                enquiryReferingTo = enquiries.get(e);
            } else {
                this.prompts.get(i).startPrompt();
                messageReferingTo = new Message(this.prompts.get(i).getResult(), author);
                
            }
        }
        this.value = new Pair<>(enquiryReferingTo, messageReferingTo);
    }

    /**Getter method to return result
     * @return value as a Pair of Enquiry object and Message Object
     */
    @Override
    public Pair<Enquiry, Message> getObject() {
        return this.value;
    }
}


