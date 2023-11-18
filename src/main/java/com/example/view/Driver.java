package com.example.view;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) {
        ArrayList<Pair<String, String>> campsData = new ArrayList<>();
        campsData.add(new Pair<>("Code Camp", "5"));
        campsData.add(new Pair<>("Science Camp", "8"));

        CreateCampPage createCampPage = new CreateCampPage();
        //ViewCampsStaff viewCampsStaff = new ViewCampsStaff(campsData);
        //ViewCampsStudent viewCampsStudent = new ViewCampsStudent(campsData);
    }
}
   /*
    ArrayList<Pair<String, String>> campsData = new ArrayList<>();
        campsData.add(new Pair<>("Code Camp", "5"));
        campsData.add(new Pair<>("Science Camp", "8"));


        ViewCampsStudent viewCampsStudent = new ViewCampsStudent(campsData);


        viewCampsStudent.displayData();*/