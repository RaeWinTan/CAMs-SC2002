package com.example.view;

import java.util.ArrayList;
public class ViewCreatedCamps extends DisplayTableDataPage {

    private ArrayList<Pair<String, String>> data;
    public ViewCreatedCamps(ArrayList<Pair<String, String>> data) {
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
        headers.add("Option number");
        headers.add("Camp Name");

        return headers;
    }
}
