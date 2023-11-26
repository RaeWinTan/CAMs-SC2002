package com.example.datastructure;

/** Associative class between Camp and Student */
public class CampMember {
    private Student student;
    private Camp camp;

    /**
     * Constructor for CampMember.
     * @param student   Student in the camp.
     * @param camp      Camp joined by student.
     */
    public CampMember(Student student, Camp camp){
        this.student = student;
        this.camp = camp;
    }

    /** Get method for student. */
    public Student getStudent(){
        return this.student;
    }

    /** Get method for camp. */
    public Camp getCamp(){
        return this.camp;
    }
}
