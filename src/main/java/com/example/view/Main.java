package com.example.view;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



/*public class Main {
    public static void main(String[] args) {
        LoginPromptPage lpp = new LoginPromptPage();
        for (Prompt p : lpp.returnInputs()){
            if (p.getResult().getSecond().equalsIgnoreCase("Student")){
                StudentPromptPage studentPromptPage = new StudentPromptPage();
            }
            else {
                StaffPromptPage staffPromptPage = new StaffPromptPage();
            }
        }
    }
}*/
        //spp.returnInputs().forEach(i-> {
            //System.out.println(i.getResult().getFirst() +":"+i.getResult().getSecond());
public class Main {
    public static void main(String[] args) {
        HashMap<String, IPromptPage> staffRedirection = new HashMap<String, IPromptPage>();
        HashMap<String, IPromptPage> studentRedirection = new HashMap<String, IPromptPage>();
        try{
            ArrayList<ArrayList<Pair<String, String>>> a = new ArrayList<ArrayList<Pair<String, String>>>();
            ArrayList<Pair<String, String>> b = new ArrayList<Pair<String, String>>();
            b.add(new Pair("campName", "Camp 1"));
            a.add(b);
            b = new ArrayList<Pair<String, String>>();
            b.add(new Pair("campName", "Camp 2"));
            a.add(b);
            IPromptPage p = new EditCampPage(a);
            p.returnInputs().forEach(i->{
                System.out.println(i.getResult().getFirst() + ":"+i.getResult().getSecond());
            });
            /*
            staffRedirection.put("ChangePasswordPage", new ChangePasswordPromptPage());
            staffRedirection.put("CreateCampPage", new CreateCampPage());
            staffRedirection.put("EditCampPage", new EditCampPage(a));
            staffRedirection.put("AcceptRejectSuggestionPage", new AcceptRejectSuggestionPage());

            studentRedirection.put("ChangePasswordPage", new ChangePasswordPromptPage());
            studentRedirection.put("Register for camp", new RegisterForCampPage());
            studentRedirection.put("Withdraw from camp", new CampWithdrawalPage());
            studentRedirection.put("Submit enquiry", new SubmitCampEnquiry());
            studentRedirection.put("Register as camp committee member", new RegisterForCampCommitteeMemberPage());
            studentRedirection.put("Submit suggestion", new SubmitCampSuggestion());*/
        }catch(Exception e){


        }
        //studentPromptPage need to have a parametarised constructor (isCommittee)



    }
}



