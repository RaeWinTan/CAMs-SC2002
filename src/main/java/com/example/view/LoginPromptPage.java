package com.example.view;

import java.util.ArrayList;

public class LoginPromptPage extends PromptPage{

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<IPrompt> prompts = new ArrayList<IPrompt>();
    public LoginPromptPage() {
        this.initialise_question_attribute_mapping();
        int i = 0;
        int timesEnteredWrong = 0;
        while (i < question_attribute_mapping.size() && timesEnteredWrong < 4) {
            Pair<String, String> questionPair = question_attribute_mapping.get(i);
            String attribute = questionPair.getSecond();
            IPrompt tmp;

            if ("userType".equals(attribute)) {

                ArrayList<String> options = new ArrayList<>();
                options.add("I am a Staff");
                options.add("I am a Student");
                try {
                    tmp = new PromptOption(questionPair.getFirst(), questionPair.getSecond(), options);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    break;
                }
            } else {
                tmp = new Prompt(questionPair.getFirst(), questionPair.getSecond());
                this.prompts.add(tmp);
            }

            if ("password".equals(attribute) && !tmp.getResult().getSecond().equals("password")) {
                timesEnteredWrong++;
            } else if ("firstTimeChangePassword".equals(attribute) && tmp.getResult().getSecond().equals("password")) {
                System.out.println("You cannot use the same password!");
                timesEnteredWrong++;
            } else {
                this.prompts.add(tmp);
                i++;
            }

            if (timesEnteredWrong == 3) {
                System.out.println("Too many incorrect tries. Try again later");
                break;
            }
        }
    }


    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    //public void addQuestion_attribute(String question, String attributeName) {return;}

    public void initialise_question_attribute_mapping(){
        question_attribute_mapping.add(new Pair<String,String>("Please enter your UserID: ", "username"));
        question_attribute_mapping.add(new Pair<String,String>("Please enter your password: ", "password"));
        question_attribute_mapping.add(new Pair<String,String>("Please enter your User Type" +
               " (Staff/Student): ", "userType"));
        question_attribute_mapping.add(new Pair<String,String>("You have logged in for the first time. Please " +
                "change your password: ", "firstTimeChangePassword"));
    }

}