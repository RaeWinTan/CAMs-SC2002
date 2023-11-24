package com.example.view;

import java.util.Scanner;

public class Prompt implements IPrompt{

    private String question;
    private String value;
    private Scanner sc = new Scanner(System.in);
    private String regex = null;
    private boolean allowEmpty =false;
    public Scanner getSc(){
        return sc;
    }
    public String getQuestion(){
        return question;
    }
    public String getValue(){
        return value;
    }
    
    public String getRegex(){
        return regex;
    }
    public boolean allowEmpty(){
        return allowEmpty;
    }

    public Prompt(String question) {
        this.question = question;
    }
    public Prompt(String question, boolean allowEmpty, String defaultValue){
        this.question = question+" ("+defaultValue+")";
        this.allowEmpty = allowEmpty;
        
    }
    public Prompt(String question, String regex, boolean allowEmpty, String defaultValue){
        this.question = question+" ("+defaultValue+")";
        this.regex = regex;
        this.allowEmpty = allowEmpty;
        
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
            } 
            break;
        }
    }

    public String getResult() {
        return this.value;
    }
}
