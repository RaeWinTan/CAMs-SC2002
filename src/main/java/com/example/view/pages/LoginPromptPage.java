package com.example.view.pages;

import java.util.ArrayList;

import com.example.utility.Pair;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;
import com.example.view.PromptOption;
import com.example.controllerlibs.UserCredentials;
import com.example.controllerlibs.UserType;
import com.example.datastructure.User;

/**
 * This class implements IPromptPage.
 * It is a prompt page common to all users
 *  displayed to them when entering the system.
 * It requires them to enter their credentials.
 */
public class LoginPromptPage implements IPromptPage<UserCredentials>{
    private String userId;
    private String password;
    private String userType;
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();

    /**Constructor for the class */
    public LoginPromptPage() {
        this.initialise_questions();   
        this.initialise_prompts();
    }

    //public void addQuestion_attribute(String question, String attributeName) {return;}

    /**Initialise the questions for the prompts*/
    private void initialise_questions(){
        questions.add("Please enter your UserID: ");
        questions.add("Please enter your password: ");
        questions.add("Please enter your User Type");   
    }

    /**Adds the questions to the list of prompts to be asked to the user
     * and also adds options for the question regarding the user type
     */
    private void initialise_prompts(){
        for(int i = 0; i < this.questions.size(); i++){
            if(i!=2)prompts.add(new Prompt(this.questions.get(i)));
            else{
                ArrayList<String> options = new ArrayList<String>();
                options.add("Staff");
                options.add("Student");
                prompts.add(new PromptOption(this.questions.get(i), options));
                
            } 
        } 
    }

    /**Begins prompting.
     * Stores the inputs.
     */
    public void perform() {
        // TODO Auto-generated method stub
        int i = 0;
        while (i < questions.size()) {
            String q = questions.get(i);
            IPrompt tmp;
            if(i==0) {
                this.prompts.get(i).startPrompt();
                userId = this.prompts.get(i).getResult();
            }else if(i==1){
                this.prompts.get(i).startPrompt();
                password = this.prompts.get(i).getResult();
            } else if (i==2) {
                ArrayList<String> options = new ArrayList<String>();
                options.add("Staff");
                options.add("Student");
                tmp = new PromptOption(q, options);
                tmp.startPrompt();
                userType = tmp.getResult();
            }
            i++;
        }
    }

    /**Getter method to return result
     * @return new UserCredentials object with the user inputs set
     */
    public UserCredentials getObject(){
        UserType tmp;
        if(userType.equals("Staff")){
            tmp = UserType.STAFF;
        }else{
            tmp = UserType.STUDENT;
        }
        return new UserCredentials(userId, password, tmp);
    }

}