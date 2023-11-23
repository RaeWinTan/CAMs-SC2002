package com.example.view;

import java.util.ArrayList;
public interface IPromptPage<T> extends IViewPage<T>{
    public void perform();
    public T getObject();
    
}
