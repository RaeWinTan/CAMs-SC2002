package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;


public class StaffGenerateEnquiryReport implements IDataStoreEditOperation<Camp> {
    Staff staff;
    String fileName;

    public StaffGenerateEnquiryReport(Staff staff, String fileName){
        this.staff = staff;
        this.fileName = fileName + ".txt";
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        
        try{
            FileWriter writer = new FileWriter(this.fileName);
            
            // Get camp created by staff.
            for (Camp camp : data) {
                if (camp.getCreatedBy().isEquals(this.staff)){
                    // Write enquries to file.
                    writer.write(camp.getCampName() + "\n");
                    if (camp.getEnquiries().isEmpty()){
                        writer.write("| No enquiries\n");
                        continue;
                    }
                    for (Enquiry enquiry : camp.getEnquiries()) {
                        writer.write("| " + enquiry.getAuthor().getName() + ":\n");
                        writer.write("| " + enquiry.getText() + "\n");
                        // Write replies to enquiry to file.
                        if (enquiry.getReplies().isEmpty()){
                            writer.write("  | No replies\n");
                            continue;
                        }
                        for (Message reply : enquiry.getReplies()){
                            writer.write("  | " + reply.getAuthor().getName() + ":\n");
                            writer.write("  | " + reply.getText() + "\n");
                        }
                    }
                    writer.write("\n");
                }
            }
            writer.close();
        } 
        catch (IOException e){
            System.out.println("not implementing this hehe");
        }
    }
}
