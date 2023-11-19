package com.example.view;

import java.util.ArrayList;
public interface IPromptPage {
    ArrayList<IPrompt> returnInputs();

    //void addQuestion_attribute(String question, String attributeName);
    void prompting();
}
