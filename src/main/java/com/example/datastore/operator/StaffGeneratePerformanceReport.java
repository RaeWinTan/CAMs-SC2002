package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;

public class StaffGeneratePerformanceReport implements IDataStoreEditOperation<Camp> {

    Staff staff;
    String fileName;
    IDataStoreRetrivable<Student> studentDSRetrivable;

    public StaffGeneratePerformanceReport(Staff staff, String fileName, IDataStoreRetrivable<Student> studentDSRetrivable){
        this.staff = staff;
        this.fileName = fileName + ".csv";
        this.studentDSRetrivable = studentDSRetrivable;
    }

    @Override
    public void perform(ArrayList<Camp> data) {
        try{
            FileWriter writer = new FileWriter(this.fileName);
            // Get camp created by staff.
            for (Camp camp : data) {
                if (camp.getCreatedBy().isEquals(this.staff)){
                    // Write details of camp to file.
                    writer.write(camp.getCampName() + "\n");
                    writer.write("Student ID,Student Name,Points\n");
                    for (CampMember cm : camp.getCommittees()) {
                        Student student = this.studentDSRetrivable.retrieveData(new DataStoreRetrieve<Student>(cm.getStudent())).get(0);
                        writer.write(String.format("%s,%s,%d\n", student.getUserId(), student.getName(), student.getPoints()));
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
