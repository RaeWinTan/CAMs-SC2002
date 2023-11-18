package com.example.view;

import java.util.ArrayList;

public class PromptOption extends Prompt {
    private ArrayList<String> options;

    public PromptOption(String question, String attributeName, ArrayList<String> options) throws Exception {
        super(question, attributeName, true);
        this.options = options;
        this.prompting();
        if (this.options == null){
            throw new Exception("There is an error");
        }
    }
    private void prompting(){
        System.out.println(this.question);
        int v = -1;

        do {
            System.out.println("Choose one of the options:");
            for (int i = 0; i < this.options.size(); i++) {
                System.out.println((i + 1) + ". " + this.options.get(i)); // Display options starting from 1
            }

            while (!userInput.hasNextInt()) {
                System.out.println("That's not a number. Please enter a valid option number.");
                userInput.next();
            }

            v = userInput.nextInt() - 1;
            userInput.nextLine();

            if (v < 0 || v >= this.options.size()) {
                System.out.println("Option does not exist. Please try again.");
            }
        } while (v < 0 || v >= this.options.size());

        this.value = this.options.get(v);
    }



}
