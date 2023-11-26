package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;
import com.example.exception.IllegalOperationException;

/**
 * Camp DataStore edit operator for generating an enquiry report.
 * @see IDataStoreEditOperation
 */
public class StaffGenerateEnquiryReport implements IDataStoreEditOperation<Camp> {
    Staff staff;
    String fileName;

    /**
     * Constructor for StaffGenerateEnquiryReport
     * @param staff     Staff generating the report
     * @param fileName  File path & name for report to be saved as.
     */
    public StaffGenerateEnquiryReport(Staff staff, String fileName){
        this.staff = staff;
        this.fileName = fileName + ".txt";
    }

    /**
     * Write enquiries and replies to file if camp is created by staff.
     * @param data ArrayList of Camp from DataStore.
     */
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
                        writer.write("| No enquiries\n\n");
                        continue;
                    }
                    for (Enquiry enquiry : camp.getEnquiries()) {
                        writer.write("| " + enquiry.getAuthor().getName() + ":\n");
                        writer.write("| " + enquiry.getText() + "\n");
                        // Write replies to enquiry to file.
                        if (enquiry.getReplies().isEmpty()){
                            writer.write("  | No replies\n\n");
                            continue;
                        }
                        for (Message reply : enquiry.getReplies()){
                            writer.write("| | " + reply.getAuthor().getName() + ":\n");
                            writer.write("| | " + reply.getText() + "\n");
                            writer.write("| |\n");
                        }
                    }
                    writer.write("|\n\n");
                }
            }
            writer.close();
        } 
        catch (IOException e){
            throw new IllegalOperationException("FileIO exception");
        }
    }
}
