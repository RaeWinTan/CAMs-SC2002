package com.example.view.pages;



import com.example.datastructure.User;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;

/**
 * This class implements IPromptPage.
 * It is a prompt page common to all users
 *  to change their password in the system
 */
public class ChangePasswordPromptPage implements IPromptPage<User>{
    private User user;
    private IPrompt prompt;
    
    /**Constructor for the class
     * @param user which is the user who is changing their password
     */
    public ChangePasswordPromptPage(User user){
        this.user = user;
        initPrompt();
    }

    /**
     * Initializes the questions to be asked in this prompt.
     */
    private void initPrompt() {
        this.prompt = new Prompt("Type in new password");        
    }

    /**Begin the prompting process
     * and stores the input the user provides
     */
    @Override
    public void perform() {
        this.prompt.startPrompt();
        this.user.setPassword(this.prompt.getResult());
    }

    /**Getter method to return the result
     * @return value as a User object
     */
    @Override
    public User getObject() {
        return this.user;
    }
}
