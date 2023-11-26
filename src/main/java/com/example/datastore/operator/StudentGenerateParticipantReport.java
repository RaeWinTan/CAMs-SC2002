package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.controllerlibs.ReportFilter;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StudentGenerateParticipantReport implements IDataStoreEditOperation<Camp> {
    Student student;
    String fileName;
    ReportFilter reportFilter;

    public StudentGenerateParticipantReport(Student student, String fileName, ReportFilter reportFilter){
        this.student = student;
        this.fileName = fileName + ".csv";
        this.reportFilter = reportFilter;
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

                        if (this.reportFilter == ReportFilter.All || this.reportFilter == ReportFilter.Attendee){
                            for (CampMember cm2 : camp.getAttendees()) {
                                student = cm2.getStudent();
                                writer.write(String.format("%s,%s,Attendee\n", student.getUserId(), student.getName()));
                            }
                        }

                        if (this.reportFilter == ReportFilter.All || this.reportFilter == ReportFilter.Committee){
                            for (CampMember cm2 : camp.getCommittees()) {
                                student = cm2.getStudent();
                                writer.write(String.format("%s,%s,Committee Member\n", student.getUserId(), student.getName()));
                            }
                        }
                        writer.write("\n");
                    }
                }
            }
            writer.close();
        }
        catch (IOException e){
            throw new IllegalOperationException("FileIO exception");
        }
        
    }
}
