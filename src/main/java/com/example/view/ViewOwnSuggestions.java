package com.example.view;

import java.util.ArrayList;
public class ViewOwnSuggestions extends DisplayTableDataPage {

    private ArrayList<Pair<String, String>> data;
    public ViewOwnSuggestions(ArrayList<Pair<String, String>> data) {
        super(data);
        Display(data);
    }

    @Override
    public void Display(ArrayList<Pair<String, String>> data) {
        this.data = data;
        displayData();
    }

    @Override
    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Sent by");
        headers.add("Suggestions");
        return headers;
    }
}

