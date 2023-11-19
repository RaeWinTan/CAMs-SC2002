package com.example.view;

import com.example.utility.Pair;

public interface IPrompt {
    public Pair<String,String> getResult();

    Object getAttributeName();
}
