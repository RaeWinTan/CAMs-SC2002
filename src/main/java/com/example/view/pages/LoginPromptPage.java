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

public class LoginPromptPage implements IPromptPage<UserCredentials>{
    private String userId;
    private String password;
    private String userType;
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public LoginPromptPage() {
        this.initialise_questions();   
        this.initialise_prompts();
    }

    //public void addQuestion_attribute(String question, String attributeName) {return;}

    private void initialise_questions(){
        questions.add("Please enter your UserID: ");
        questions.add("Please enter your password: ");
        questions.add("Please enter your User Type");   
    }
    private void initialise_prompts(){
        for(int i = 0; i < this.questions.size(); i++){
            if(i!=2)prompts.add(new Prompt(this.questions.get(i)));
            else{
                ArrayList<String> options = new ArrayList<String>();
                options.add("Staff");
                options.add("Student");
                try {
                    prompts.add(new PromptOption(this.questions.get(i), options));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } 
        } 
    }

    
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
                try {
                    tmp = new PromptOption(q, options);
                    tmp.startPrompt();
                    userType = tmp.getResult();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            i++;
        }
    }
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