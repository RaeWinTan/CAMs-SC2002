package com.example.datastore.operator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.example.datastore.IDataStoreRetrivable;
import com.example.datastructure.Camp;
import com.example.datastructure.CampMember;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.exception.IllegalOperationException;

/**
 * Camp DataStore edit operator for generating a committee member performance report.
 * @see IDataStoreEditOperation
 */
public class StaffGeneratePerformanceReport implements IDataStoreEditOperation<Camp> {

    Staff staff;
    String fileName;
    IDataStoreRetrivable<Student> studentDSRetrivable;

    /**
     * Constructor for StaffGenerateParticipantReport
     * @param staff         Staff generating the report.
     * @param fileName      File path & name for report to be saved as.
     * @param studentDSRetrivable  Student DataStore, required for getting the latest copy of student to get access to their points (in theory this should not be necessary but I'm in a rush so I have no time to test xD)
     */
    public StaffGeneratePerformanceReport(Staff staff, String fileName, IDataStoreRetrivable<Student> studentDSRetrivable){
        this.staff = staff;
        this.fileName = fileName + ".csv";
        this.studentDSRetrivable = studentDSRetrivable;
    }

    /**
     * For every camp, for every committee member, write down their points to file.
     * @param data  ArrayList of Camp from Camp DataStore
     */
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
            throw new IllegalOperationException("FileIO exception");
        }
    }
    
}
