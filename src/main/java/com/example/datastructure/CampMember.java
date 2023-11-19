package com.example.datastructure;

public class CampMember {
    private Student student;
    private Camp camp;

    public CampMember(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    public Student getStudent(){
        return this.student;
    }

    public Camp getCamp(){
        return this.camp;
    }
}
