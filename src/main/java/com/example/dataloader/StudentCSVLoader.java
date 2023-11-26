package com.example.dataloader;

import java.util.ArrayList;

import com.example.datastructure.GroupName;
import com.example.datastructure.Student;

/** Class to convert CSV file into Student objects. */
public class StudentCSVLoader extends UserCSVLoader<Student>{

    /**
     * Constructor for StudentCSVLoader.
     * @param sourceFile    Path of csv file.
     */
    public StudentCSVLoader(String sourceFile){
        super(sourceFile);
    }
    
    /**
     * Read data from csv file, convert to a 2D ArrayList of String, then convert to Student objects.
     * @return An ArrayList of Student.
     */
    public ArrayList<Student> loadData() {
        ArrayList<ArrayList<String>> strData = this.getCellStringFormat().toDataString();

        ArrayList<Student> users = new ArrayList<Student>();
        for (ArrayList<String> row : strData) {
            String name = row.get(0);
            String email = row.get(1);
            String facultyStr = row.get(2);

            String userId = email.split("@")[0];

            GroupName faculty = super.getFacultyFromString(facultyStr);

            users.add(new Student(userId, name, faculty));
        }
        return users;
    }
}
