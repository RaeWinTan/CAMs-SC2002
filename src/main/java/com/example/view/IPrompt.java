package com.example.view;

/**IPrompt is an interface  which the 3 prompt types we have: Prompt, PromptOption and TablePromptOption implement. 
 * It declares the relevant methods to begin
 * the prompting process and the getter method to retrieve
 * the result
 */
public interface IPrompt{
    /**Getter method */
    public String getResult();
    /**Method to begin prompting process */
    public void startPrompt();

}
