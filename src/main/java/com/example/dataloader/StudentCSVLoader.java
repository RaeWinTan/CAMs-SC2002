package com.example.dataloader;

import java.util.ArrayList;

import com.example.datastructure.GroupName;
import com.example.datastructure.Student;

    public class StudentCSVLoader extends UserCSVLoader<Student>{

        public StudentCSVLoader(String sourceFile){
            super(sourceFile);
        }
    
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
