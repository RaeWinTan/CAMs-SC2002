package com.example.view;

import java.util.ArrayList;

public class PromptOption extends Prompt {
    private ArrayList<String> options;

    public PromptOption(String question, String attributeName, ArrayList<String> options) throws Exception {
        super(question, attributeName, true);
        this.options = options;
        if (this.options == null || options.size() == 0){
            throw new Exception("There is an error");
        }
        
        this.prompting();
        
    }
    private void prompting(){
        System.out.println(this.question);
        
        int ans;
        System.out.println("Choose one of the options:");
        for (int i = 0; i < this.options.size(); i++) { System.out.println(i  + ". " + this.options.get(i));}
        while (!this.userInput.hasNextInt()) {
            System.out.println("That's not a number. Please enter a valid option.");
            this.userInput.next();
        }
        ans = this.userInput.nextInt();
        while(ans < 0 || ans >=this.options.size()){
            System.out.println("Options does not exists. Try Again!");
            while (!this.userInput.hasNextInt()) {
                System.out.println("That's not a number. Please enter a valid option number.");
                userInput.next();
            }
            ans = userInput.nextInt(); 
        }
        
        this.value = options.get(ans);
        
    }



}
