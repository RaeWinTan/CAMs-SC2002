package com.example.view.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.controllerlibs.ReportFilter;
import com.example.view.IPromptPage;
import com.example.view.TablePromptOption;

public class GenerateStudentReportPromptPage implements IPromptPage<ReportFilter>{
    private ReportFilter value;
    @Override
    public void perform() {
        ArrayList<ReportFilter> filters = new ArrayList<>(Arrays.asList(ReportFilter.values()));
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Attribute name");
        ArrayList<String> fs = new ArrayList<>();
        for(ReportFilter rf:filters) fs.add(rf.toString());
        ArrayList<ArrayList<String>> columns = new ArrayList<>();
        columns.add(fs);
        TablePromptOption p = new TablePromptOption("What would you like to sort by", headers, columns);
        p.startPrompt();
        int idx = Integer.valueOf(p.getResult());
        value = filters.get(idx);
    }

    @Override
    public ReportFilter getObject() {
        return value;
    }
    
}
