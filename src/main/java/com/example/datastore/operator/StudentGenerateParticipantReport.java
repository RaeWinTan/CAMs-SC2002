package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;

public class StudentGenerateParticipantReport implements IDataStoreEditOperation<Camp> {
    Student student;
    String fileName;

    public StudentGenerateParticipantReport(Student student, String fileName){
        this.student = student;
        this.fileName = fileName + ".csv";
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            // Get camp with student as committee.
            for (Camp camp : data) {
                for (CampMember cm : camp.getCommittees()){
                    if (cm.getStudent().isEquals(this.student)){
                        // Write details of camp to file.
                        writer.write(camp.toString());
                        writer.write("Student ID,Student Name,Role\n");
                        Student student;
                        for (CampMember cm2 : camp.getAttendees()) {
                            student = cm2.getStudent();
                            writer.write(String.format("%s,%s,Attendee\n", student.getUserId(), student.getName()));
                        }

                        for (CampMember cm2 : camp.getCommittees()) {
                            student = cm2.getStudent();
                            writer.write(String.format("%s,%s,Committee Member\n", student.getUserId(), student.getName()));
                        }
                        writer.write("\n");
                    }
                }
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("not implementing this hehe");
        }
        
    }
}
