package com.example.view.pages;



import com.example.datastructure.User;
import com.example.view.IPrompt;
import com.example.view.IPromptPage;
import com.example.view.Prompt;


public class ChangePasswordPromptPage implements IPromptPage<User>{
    private User user;
    private IPrompt prompt;
    public ChangePasswordPromptPage(User user){
        this.user = user;
        initPrompt();
    }


    private void initPrompt() {
        this.prompt = new Prompt("Type in new password");        
    }

    @Override
    public void perform() {
        this.prompt.startPrompt();
        this.user.setPassword(this.prompt.getResult());
    }

    @Override
    public User getObject() {
        return this.user;
    }
}
