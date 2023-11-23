package com.example.view;

import java.util.ArrayList;

public class PromptOption extends Prompt {
    private ArrayList<String> options;

    public PromptOption(String question, ArrayList<String> options) throws Exception {
        super(question); 
        this.options = options;
        if (this.options == null || options.size() == 0) throw new Exception("There is an error");
    }
    public void startPrompt(){
        System.out.println(this.question);
        int ans;
        System.out.println("Choose one of the options:");
        for (int i = 0; i < this.options.size(); i++) { System.out.println(i  + ". " + this.options.get(i));}
        while (!this.sc.hasNextInt()) {
            System.out.println("That's not a number. Please enter a valid option.");
            this.sc.next();
        }
        ans = this.sc.nextInt();
        while(ans < 0 || ans >=this.options.size()){
            System.out.println("Options does not exists. Try Again!");
            while (!this.sc.hasNextInt()) {
                System.out.println("That's not a number. Please enter a valid option number.");
                this.sc.next();
            }
            ans = this.sc.nextInt(); 
        }    
        this.value = options.get(ans);
    }



}
