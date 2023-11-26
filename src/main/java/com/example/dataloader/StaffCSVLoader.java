package com.example.dataloader;

import java.util.ArrayList;

import com.example.datastructure.GroupName;
import com.example.datastructure.Staff;

/** Class to convert CSV file into Staff objects. */
public class StaffCSVLoader extends UserCSVLoader<Staff> {

    /**
     * Constructor for StaffCSVLoader.
     * @param sourceFile    Path of csv file.
     */
    public StaffCSVLoader(String sourceFile){
        super(sourceFile);
    }

    /**
     * Read data from csv file, convert to a 2D ArrayList of String, then convert to Staff objects.
     * @return An ArrayList of Staff.
     */
    public ArrayList<Staff> loadData() {
        ArrayList<ArrayList<String>> strData = this.getCellStringFormat().toDataString();

		ArrayList<Staff> users = new ArrayList<Staff>();
		for (ArrayList<String> row : strData) {
            // Each row is a String array which contains [Name,Email,Faculty]
            String name = row.get(0);
            String email = row.get(1);
            String facultyStr = row.get(2);

            String userId = email.split("@")[0];

            GroupName faculty = super.getFacultyFromString(facultyStr);

            users.add(new Staff(userId, name, faculty));
		}
		return users;
    }

    
}
