package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;

public class StaffGenerateParticipantReport implements IDataStoreEditOperation<Camp> {

    Staff staff;
    String fileName;

    public StaffGenerateParticipantReport(Staff staff, String fileName){
        this.staff = staff;
        this.fileName = fileName + ".csv";
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            // Get camp created by staff.
            for (Camp camp : data) {
                if (camp.getCreatedBy().isEquals(this.staff)){
                    // Write details of camp to file.
                    writer.write(camp.toString());
                    writer.write("Student ID,Student Name,Role\n");
                    Student student;
                    for (CampMember cm : camp.getAttendees()) {
                        student = cm.getStudent();
                        writer.write(String.format("%s,%s,Attendee\n", student.getUserId(), student.getName()));
                    }

                    for (CampMember cm : camp.getCommittees()) {
                        student = cm.getStudent();
                        writer.write(String.format("%s,%s,Committee Member\n", student.getUserId(), student.getName()));
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
