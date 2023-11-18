package com.example.view;

import java.util.Scanner;

public class Prompt implements IPrompt{

    protected String question;
    protected String attributeName;
    protected String value;
    protected Scanner userInput = new Scanner(System.in);


    protected boolean shouldPromptImmediately;

    public Prompt(String question, String attributeName) {
        this.question = question;
        this.attributeName = attributeName;
        this.prompting();
    }
    public void setValue(String newValue) {
        this.value = newValue;
    }

    // Protected constructor for derived classes, does not call prompting()
    protected Prompt(String question, String attributeName, boolean skipPrompting) {
        this.question = question;
        this.attributeName = attributeName;
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
