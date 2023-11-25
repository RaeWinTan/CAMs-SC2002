package com.example.view;

import java.util.ArrayList;

import com.example.exception.PromptOptionException;

/**PromptOption is a class that inherits from Prompt. We use
 * it when instead of allowing the user to freely enter input
 * we want them to a choose from a few options
 * instead. This helps us minimize our error handling especially
 * in cases when there are only a few valid answers.
 */
public class PromptOption extends Prompt {
    private ArrayList<String> options;

    /**constructor with the question that is displayed to the user
     * as well as an ArrayList of options in the form of String.
     * @param question a String that is displayed to the user
     * @param options an ArrayList of String options for the user to choose
     * from
     * @exception PromptOptionException thrown when there are no options
     * initialised
     * @see PromptOptionException
     */
    public PromptOption(String question, ArrayList<String> options) {
        super(question);
        this.options = options;
        if (this.options == null || options.size() == 0) throw new PromptOptionException("The is not options for prompt message of " + question);
    }

    /**constructor with question, boolean, default value and option array list
     * @param question a String that is displayed to the user
     * @param allowEmpty a boolean that decides whether the user can
     * effectively skip the question by pressing enter or not
     * @param defaultValue displays the default value of the parameter to the user
     * @param options an ArrayList of String options for the user to choose
     * from
     */
    public PromptOption(String question, boolean allowEmpty, String defaultValue, ArrayList<String> options){
        super(question,allowEmpty, defaultValue);
        this.options = options;

        /**method to start the prompting process. It checks if the user's input
         * matches any input requirements such as valid option number, integer value
         * only, compulsory question
         */
    }
    public void startPrompt(){
        System.out.println(this.getQuestion());
        int ans;
        System.out.println("Choose one of the options:");
        while(true){
            for (int i = 0; i < this.options.size(); i++) { System.out.println(i  + ". " + this.options.get(i));}
            setValue(getSc().nextLine());
            if(allowEmpty() && getValue().trim().isEmpty()){
                setValue("");
                break;
            }
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
