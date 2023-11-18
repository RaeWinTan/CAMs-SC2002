package com.example.view;

import java.util.ArrayList;

import com.example.utility.Pair;
public class ViewListOfCommittee extends DisplayTableDataPage {

    private ArrayList<Pair<String, String>> data;
    public ViewListOfCommittee(ArrayList<Pair<String, String>> data) {
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
        headers.add("Committee Member Name");
        headers.add("Registered Camp");
        return headers;
    }
}
