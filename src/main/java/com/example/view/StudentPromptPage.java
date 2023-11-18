package com.example.view;

/*import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentPromptPage extends PromptPage{

    private ArrayList<Pair<String, String>>question_attribute_mapping = new ArrayList<Pair<String, String>>();
    private ArrayList<Prompt> prompts = new ArrayList<Prompt>();
    private Scanner studentInput = new Scanner(System.in);
    public StudentPromptPage(){
        this.initialise_question_attribute_mapping();
        studentMainMenu();
    }

    public void initialise_question_attribute_mapping() {
        question_attribute_mapping.add(new Pair<String,String>("Enter your new password ",
                "newPassword"));
        question_attribute_mapping.add(new Pair<String,String>("Enter the name of the camp you would like " +
                "to register for as an attendee: ",
                "campAttendeeRegister"));
        question_attribute_mapping.add(new Pair<String,String>("Submit your enquiry regarding the camp ",
                "campEnquiry"));
        question_attribute_mapping.add(new Pair<String, String>("Enter the name of the camp you would " +
                "like to withdraw from",
                "campWithdrawal"));
        question_attribute_mapping.add(new Pair<String,String>("Enter the name of the camp you would " +
                "like to register for as a committee member: ",
                "campCommitteeRegister"));
        question_attribute_mapping.add(new Pair<String,String>("Submit your suggestion regarding the camp ",
                "campSuggestion"));

    }
    public ArrayList<Prompt> returnInputs() {
        return this.prompts;
    }

    @Override
    public void addQuestion_attribute(String question, String attributeName) {
        return;
    }

    public void studentMainMenu(){
    while (true){
        System.out.println("\nWelcome to the Student Dashboard");
        System.out.println("1. Change password");
        System.out.println("2. View available camps");
        System.out.println("3. Register for a camp as attendee");
        System.out.println("4. Submit enquiry for a camp");
        System.out.println("5. View your registered camps");
        System.out.println("6. View replies to enquiries");
        System.out.println("7. Withdraw from a camp");
        System.out.println("8. Register for camp as camp committee member");
        System.out.println("9. Submit suggestions to change camp details");
        System.out.println("10. View enquiries from students");
        System.out.println("11. View your submitted suggestions");
        System.out.println("12. Edit your submitted suggestions");
        System.out.println("13. Delete your submitted suggestions");
        System.out.println("14. Generate report of attendees for each camp");
        System.out.println("15. Generate student's enquiry report");
        System.out.println("16. Exit");
        System.out.println("Please enter your choice: ");
        try {
            int choice = studentInput.nextInt();

            switch (choice) {
                case 1:
                I realised ideally the password prompt
                should be overwritten everytime the user
                enters a new password
                since we only care about returning the most recent one
                    Prompt tmp = new Prompt(question_attribute_mapping.get(0).getFirst(),
                            question_attribute_mapping.get(0).getSecond());
                    Prompt existingPasswordPrompt = null;
                    // here I search for an existing password prompt
                    for (Prompt password : prompts) {
                        if (password.getAttributeName().equals("newPassword")) {
                            existingPasswordPrompt = password;
                            break;
                        }
                    }
                    // here I remove old password prompt if new one is found
                    if (existingPasswordPrompt != null) {
                        prompts.remove(existingPasswordPrompt);
                    }
                    // here I will add the updated new password prompt
                    prompts.add(tmp);
                    break;

                case 3:
                    Prompt tmp2 = new Prompt(question_attribute_mapping.get(1).getFirst(),
                            question_attribute_mapping.get(1).getSecond());
                    this.prompts.add(tmp2);
                    break;
                case 4:
                    Prompt tmp3 = new Prompt(question_attribute_mapping.get(2).getFirst(),
                            question_attribute_mapping.get(2).getSecond());
                    this.prompts.add(tmp3);
                    break;
                case 7:
                    Prompt tmp4 = new Prompt(question_attribute_mapping.get(3).getFirst(),
                            question_attribute_mapping.get(3).getSecond());
                    this.prompts.add(tmp4);
                    break;
                case 8:
                    Prompt tmp5 = new Prompt(question_attribute_mapping.get(4).getFirst(),
                            question_attribute_mapping.get(4).getSecond());
                    this.prompts.add(tmp5);
                    break;
                case 9:
                    Prompt tmp6 = new Prompt(question_attribute_mapping.get(5).getFirst(),
                            question_attribute_mapping.get(5).getSecond());
                    this.prompts.add(tmp6);
                    break;
                case 16:
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 8");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Please only enter integers!");
            studentInput.next();
        }
}
}}*/
