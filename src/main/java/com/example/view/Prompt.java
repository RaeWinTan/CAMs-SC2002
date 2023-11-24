package com.example.view;

import java.util.Scanner;

public class Prompt implements IPrompt{

    protected String question;
    protected String value;
    protected Scanner sc = new Scanner(System.in);
    protected String regex = null;
    protected boolean allowEmpty =false;

    public Prompt(String question) {
        this.question = question;
    }
    public Prompt(String question, String regex){
        //
        this.question = question;
        this.regex = regex;
    }
    public Prompt(String question, String regex, boolean allowEmpty){
        this.question = question;
        this.regex = regex;
        this.allowEmpty = allowEmpty;
    }
    public Prompt(String question, boolean allowEmpty){
        this.question = question;
        this.allowEmpty = allowEmpty;
    }
    public void setValue(String newValue) {
        this.value = newValue;
    }

    public void startPrompt(){
        while (true) {
            System.out.println(this.question);
            this.value = this.sc.nextLine();

            // check if got empty input
            if (!allowEmpty && this.value.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }

            if (this.regex != null && !this.value.matches(this.regex)) {
                System.out.println("Incorrect format! Please try again.");
            } else {
                break; // valid input or empty input allowed
            }
        }
    }

    public String getResult() {
        return this.value;
    }
}
