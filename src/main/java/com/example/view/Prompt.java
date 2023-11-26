package com.example.view;

import java.util.Scanner;

/**Prompt class is in charge of displaying the question to the user as well as allowing them to input their responses to the questions. Depending on the situation
 * different types of prompts are required - some with Regex,
 * some where inputs are optional and the default value
 * of the parameter must be specified, some where inputs are compulsory.
 * Prompt takes care of all of these.
 * There are many different types of constructors for Prompt to match these
 * needs
 */
public class Prompt implements IPrompt{

    private String question;
    private String value;
    private Scanner sc = new Scanner(System.in);
    private String regex = null;
    private boolean allowEmpty =false;

    /**getter method to get the scanner object
     * @return sc Scanner object
     */
    public Scanner getSc(){
        return sc;
    }
    /**getter method to get the question
     * @return question As a string
     */
    public String getQuestion(){
        return question;
    }
    /**getter method to get the input
     * @return value As a string
     */
    public String getValue(){
        return value;
    }

    /**getter method to get the Regex
     * @return regex
     */
    public String getRegex(){
        return regex;
    }

    /**getter method to return allowEmpty value
     * @return allowEmpty As a boolean
     */
    public boolean allowEmpty(){
        return allowEmpty;
    }

    /**constructor with  question to be asked
     * @param question
     */
    public Prompt(String question) {
        this.question = question;
    }

    /**constructor with question, boolean and default value for that parameter
     * @param question
     * @param allowEmpty
     * @param defaultValue
     */
    public Prompt(String question, boolean allowEmpty, String defaultValue){
        this.question = question+" ("+defaultValue+")";
        this.allowEmpty = allowEmpty;

    }

    /**constructor with question, regex, boolean and default value for that parameter
     * @param question
     * @param allowEmpty
     * @param defaultValue
     * @param regex
     */
    public Prompt(String question, String regex, boolean allowEmpty, String defaultValue){
        this.question = question+" ("+defaultValue+")";
        this.regex = regex;
        this.allowEmpty = allowEmpty;

    }

    /**constructor with question and regex
     * @param question
     * @param regex
     */
    public Prompt(String question, String regex){
        //
        this.question = question;
        this.regex = regex;
    }

    /**constructor with question, regex and boolean
     * @param question
     * @param regex
     * @param allowEmpty
     */
    public Prompt(String question, String regex, boolean allowEmpty){
        this.question = question;
        this.regex = regex;
        this.allowEmpty = allowEmpty;
    }

    /**constructor with question and boolean
     * @param question
     * @param allowEmpty
     */
    public Prompt(String question, boolean allowEmpty){
        this.question = question;
        this.allowEmpty = allowEmpty;
    }

    /**setter method to set result
     * @param newValue a string that can be put as the result
     * in case of modification
     */
    public void setValue(String newValue) {
        this.value = newValue;
    }

    /**method to start prompting. It checks for a mismatch of input:
     * for example, if the user has not entered any value but it is
     * a compulsory prompt. Or if the user's inputted value
     * does not match specified regex. Then it will continuously prompt
     * the user - and will not move on to the next prompt -
     * till the input conditions are satisfied
     */
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

    /**getter method to get the result of the prompt
     * @return value as a String
     */
    public String getResult() {
        return this.value;
    }
}
