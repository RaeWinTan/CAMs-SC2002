package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.controllerlibs.ReportFilter;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

public class StaffGenerateParticipantReport implements IDataStoreEditOperation<Camp> {

    Staff staff;
    String fileName;
    ReportFilter reportFilter;

    public StaffGenerateParticipantReport(Staff staff, String fileName, ReportFilter reportFilter){
        this.staff = staff;
        this.fileName = fileName + ".csv";
        this.reportFilter = reportFilter;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            // Get camp created by staff.
            for (Camp camp : data) {
                if (camp.getCreatedBy().isEquals(this.staff)){
                    // Write details of camp to file.
                    writer.write(
                        String.format("%s (%s-%s)\n",
                            camp.getCampName(),
                            sdf.format(camp.getDates()[0]),
                            sdf.format(camp.getDates()[1])
                        )
                    );
                    writer.write("Student ID,Student Name,Role\n");
                    Student student;

                    if (this.reportFilter == ReportFilter.All || this.reportFilter == ReportFilter.Attendee){
                        for (CampMember cm : camp.getAttendees()) {
                            student = cm.getStudent();
                            writer.write(String.format("%s,%s,Attendee\n", student.getUserId(), student.getName()));
                        }
                    }

                    if (this.reportFilter == ReportFilter.All || this.reportFilter == ReportFilter.Committee){
                        for (CampMember cm : camp.getCommittees()) {
                            student = cm.getStudent();
                            writer.write(String.format("%s,%s,Committee Member\n", student.getUserId(), student.getName()));
                        }
                    }
                    writer.write("########,########,########\n");
                }
            }
            writer.close();
        }
        catch (IOException e){
            throw new IllegalOperationException("FileIO exception");
        }
        
    }
    
}
