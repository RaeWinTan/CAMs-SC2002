package com.example.view;

import java.util.ArrayList;

import com.example.exception.PromptOptionException;

public class PromptOption extends Prompt {
    private ArrayList<String> options;
    

    public PromptOption(String question, ArrayList<String> options) {
        super(question); 
        this.options = options;
        if (this.options == null || options.size() == 0) throw new PromptOptionException("There is no options for you to chose from");
    }
    public PromptOption(String question, boolean allowEmpty, String defaultValue, ArrayList<String> options){
        super(question,allowEmpty, defaultValue);
        this.options = options;
        
    }
    public void startPrompt(){
        System.out.println(this.getQuestion());
        int ans;
        System.out.println("Choose one of the options:");
        while(true){
            for (int i = 0; i < this.options.size(); i++) { System.out.println(i  + ". " + this.options.get(i));}
            setValue(getSc().nextLine());
            
            if(!allowEmpty() && getValue().trim().isEmpty()){
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            if(!getValue().matches(RegexType.INTEGER.toString())){
                System.out.println("Require an number as input");
                continue;
            }
            ans = Integer.valueOf(getValue());
            if(ans<0 || ans >= this.options.size()){
                System.out.println("Require number between 0 & "+ (this.options.size()-1));
                continue;
            }
            setValue(this.options.get(ans));
            break;
        } 
    }



}
