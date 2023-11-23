package com.example.view;

import java.util.Scanner;

public class Prompt implements IPrompt{

    protected String question;
    protected String value;
    protected Scanner sc = new Scanner(System.in);

    public Prompt(String question) {
        this.question = question;        
    }
    
    public void setValue(String newValue) {
        this.value = newValue;
    }    
    
    public void startPrompt(){
        System.out.println(this.question);
        this.value = this.sc.nextLine();
    }
    
    public String getResult() {
        return this.value;
    }

}
