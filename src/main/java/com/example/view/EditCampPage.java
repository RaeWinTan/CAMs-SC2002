package com.example.view;
import java.util.ArrayList;

public class EditCampPage extends PromptPage {

    private ArrayList<Pair<String, String>> question_attribute_mapping = new ArrayList<>();
    private ArrayList<IPrompt> prompts = new ArrayList<>();

    public EditCampPage(ArrayList<ArrayList<Pair<String,String>>> camps ) throws Exception {
        initialise_question_attribute_mapping();


        ArrayList<String> campNames = new ArrayList<String>();
        for (ArrayList<Pair<String,String>> campList: camps){
            for(Pair<String,String> camp:campList){
                if(camp.getFirst().equals("campName")) campNames.add(camp.getSecond());
            }
        }
        IPrompt first = new PromptOption(question_attribute_mapping.get(0).getFirst(),
                question_attribute_mapping.get(0).getSecond(), campNames);

        this.prompts.add(first);
        int i = 0;
        while (i < question_attribute_mapping.size()) {
            Pair<String, String> questionPair = question_attribute_mapping.get(i);
            String attribute = questionPair.getSecond();
            Prompt tmp = new Prompt(questionPair.getFirst(), questionPair.getSecond());

            if (tmp.getResult().getSecond().isEmpty()) {
                // replace with blank space if empty
                tmp.setValue(" ");
                //dd/mm/yyyy
            } else if ("campEdit_startDate".equals(attribute) &&
                    tmp.getResult().getSecond().matches("[a-zA-Z]+")) {
                System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                continue;
            } else if ("campEdit_endDate".equals(attribute) &&
                    tmp.getResult().getSecond().matches("[a-zA-Z]+")) {
                System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                continue;
            } else if ("campEdit_registrationEndDate".equals(attribute) &&
                    tmp.getResult().getSecond().matches("[a-zA-Z]+")) {
                System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                continue;
            } else if ("campEdit_totalSlots".equals(attribute) &&
                    !tmp.getResult().getSecond().matches("[0-9]+")) {
                System.out.println("Please only enter a valid number value");
                continue;
            } else if ("campEdit_committeeSlots".equals(attribute)) {
                String input = tmp.getResult().getSecond();

                if (input.matches("[0-9]+")) {
                    int number = Integer.parseInt(input);

                    if (number < 1 || number > 10) {
                        System.out.println("Number must be between 1 and 10");
                        continue;
                    }
                } else {
                    System.out.println("Please only enter a valid number value");
                    continue;
                }
            }

            prompts.add(tmp);
            i++;
        }
        System.out.println("Thank you for your input!");
    }


    @Override
    public ArrayList<IPrompt> returnInputs() {
        return this.prompts;
    }

    @Override
    //public void addQuestion_attribute(String question, String attributeName) { return; }

    public void initialise_question_attribute_mapping() {

        question_attribute_mapping.add(new Pair<String, String>("Enter new camp name." +
                " Press enter for no change: ", "newCampName"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new camp start date." +
                " Press enter for no change: ", "startDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new camp end date. " +
                "Press enter for no change: ", "endDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new closing date for" +
                " registration. Press enter for no change: ", "closingDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new faculty the camp is open to" +
                ". Press enter for no change: ",
                "userGroup"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new camp location" +
                ". Press enter for no change: ", "location"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new total slots" +
                ". Press enter for no change: ", "totalSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new committee slots" +
                ". Press enter for no change: ",
                "committeeSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter new camp description" +
                ". Press enter for no change: ",
                "description"));
    }
}
