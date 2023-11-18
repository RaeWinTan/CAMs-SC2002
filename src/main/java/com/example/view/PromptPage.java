package com.example.view;

import java.util.ArrayList;
//import javafx.util.Pair; // Make sure to import the correct Pair class.

abstract public class PromptPage implements IPromptPage{

    public PromptPage() {}
    public abstract ArrayList<IPrompt> returnInputs();
    public abstract void initialise_question_attribute_mapping();
}
