package com.example.view;

import java.util.Scanner;

import com.example.utility.Pair;

public class Prompt implements IPrompt{

    protected String question;
    protected String attributeName;
    protected String value;
    protected Scanner userInput = new Scanner(System.in);

    public Prompt(String question, String attributeName) {
        this.question = question;
        this.attributeName = attributeName;
        this.prompting();
    }
    public Prompt(String question, String attributeName, boolean isOptions) {
        this.question = question;
        this.attributeName = attributeName;
        
    }
    public void setValue(String newValue) {
        this.value = newValue;
    }

    
    private void prompting(){
        System.out.println(this.question);
        this.value = this.userInput.nextLine();

    }
    public String getAttributeName(){
        return attributeName;
    }
    public Pair<String, String> getResult() {
        return new Pair<String,String>(this.attributeName, this.value);
    }

}
