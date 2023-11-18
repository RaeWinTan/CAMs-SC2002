package com.example.view;

/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffPromptPage extends PromptPage {

    private ArrayList<Pair<String, String>> question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<Prompt> prompts = new ArrayList<Prompt>();
    private Scanner staffInput = new Scanner(System.in);

    public StaffPromptPage() {
        initialise_question_attribute_mapping();
        staffMainMenu();
    }

    @Override
    public void addQuestion_attribute(String question, String attributeName) {
        return;
    }

    @Override
    public ArrayList<Prompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String, String>("Enter your new password ",
                "newPassword"));
        //for creating a new camp
        question_attribute_mapping.add(new Pair<String, String>("Enter camp name ", "campCreate_name"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp start date ", "campCreate_startDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp end date ", "campCreate_endDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter closing date for registration ",
                "campCreate_registrationEndDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter faculty the camp is open to ",
                "campCreate_faculty"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp location ", "campCreate_location"));
        question_attribute_mapping.add(new Pair<String, String>("Enter total slots ", "campCreate_totalSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter committee slots ",
                "campCreate_committeeSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp description ",
                "campCreate_description"));

        //for editing an existing camp
        question_attribute_mapping.add(new Pair<String, String>("Enter which camp you'd like to edit ", "campEdit_ID"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp name ", "campEdit_name"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp start date ", "campEdit_startDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp end date ", "campEdit_endDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter closing date for registration ",
                "campEdit_registrationEndDate"));
        question_attribute_mapping.add(new Pair<String, String>("Enter faculty the camp is open to ",
                "campEdit_faculty"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp location ", "campEdit_location"));
        question_attribute_mapping.add(new Pair<String, String>("Enter total slots ", "campEdit_totalSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter committee slots ",
                "campEdit_committeeSlots"));
        question_attribute_mapping.add(new Pair<String, String>("Enter camp description ",
                "campEdit_description"));
    }

    public void staffMainMenu() {
        HashMap<String, IPromptPage> hm = new HashMap<>();

        while (true) {
            System.out.println("\nWelcome to the Staff Dashboard");
            System.out.println("1. Change password");
            System.out.println("2. View all camps");
            System.out.println("3. Create a camp");
            System.out.println("4. Edit a camp");
            System.out.println("5. View list of students registered for camps");
            System.out.println("6. View and respond to camp enquiries");
            System.out.println("7. Generate reports for camps");
            System.out.println("8. Exit");
            System.out.print("Please enter your choice: ");
            try {
            int choice = staffInput.nextInt();
            switch (choice) {
                case 1:
                    //this one is taken from the StudentPromptPage implementation
                    Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                            question_attribute_mapping.get(0).getSecond());
                    Prompt existingPasswordPrompt = null;
                    for (Prompt password : prompts) {
                        if (password.getAttributeName().equals("newPassword")) {
                            existingPasswordPrompt = password;
                            break;
                        }
                    }
                    if (existingPasswordPrompt != null) {
                        prompts.remove(existingPasswordPrompt);
                    }
                    prompts.add(tmp);
                    break;

                case 3:
                    int i = 1;
                    while (i < 10){
                        Pair<String, String> questionPair = question_attribute_mapping.get(i);
                        String attribute = questionPair.getSecond();
                    Prompt tmp2 = new Prompt(question_attribute_mapping.get(i).getFirst(),
                            question_attribute_mapping.get(i).getSecond());
                    if ("campCreate_startDate".equals(attribute) && tmp2.getResult().getSecond().contains("[a-zA-Z]+")){
                        System.out.println("Incorrect date format! Please use DD/MM/YYYY");
                    }
                    else{
                    i++;
                    this.prompts.add(tmp2);}}
                    System.out.println("Thank you for your input!");
                    break;

                case 4:
                    Prompt cmpID = new Prompt(question_attribute_mapping.get(10).getFirst(),
                            question_attribute_mapping.get(10).getSecond());


                    for (i = 10; i < question_attribute_mapping.size(); i++){
                    Prompt tmp3 = new Prompt(question_attribute_mapping.get(i).getFirst(),
                            question_attribute_mapping.get(i).getSecond());
                    this.prompts.add(tmp3);}
                    System.out.println("Thank you for your input");
                    break;
                case 8:
                    return;

                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 8");
            }
            }catch (InputMismatchException exception) {
                System.out.println("Please only enter integers!");
                staffInput.next();
            }
        }
    }
}*/
