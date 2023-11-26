package com.example.datastructure;

import java.util.ArrayList;
import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

/**
 * Class storing data of a Student.
 */
public class Student extends User implements IDataStoreObject<Student>{

	private int points = 0;
	private ArrayList<CampMember> leading = new ArrayList<>();
	private ArrayList<CampMember> attending = new ArrayList<>();
	private ArrayList<Enquiry> enquireAbout = new ArrayList<>();
	private ArrayList<Suggestion> suggestions = new ArrayList<>();

	/**
	 * Constructor for Student class, password will be set to default.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 */
	public Student(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
	}

	/**
	 * Constructor for student class.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 * @param password		Password of the student.
	 * @param points		Points accumulated by the Student.
	 * @param leading		ArrayList of CampMember with camp the Student is a committee member of.
	 * @param attending		ArrayList of CampMember with camp the Student is an attendee of.
	 * @param enquireAbout	ArrayList of Enquries made by the Student.
	 * @param suggestions	ArrayList of Suggestions made by the Student.
	 * @param isDefaultPassword	Flag for if the student is still using the default password.
	 */
	private Student(String userId, String name, GroupName faculty, String password, int points, ArrayList<CampMember> leading, ArrayList<CampMember> attending, ArrayList<Enquiry> enquireAbout, ArrayList<Suggestion> suggestions, boolean isDefaultPassword) {
		super(userId, name, faculty, password, isDefaultPassword);
		this.points = points;
		this.leading = leading;
		this.attending = attending;
		this.enquireAbout = enquireAbout;
		this.suggestions = suggestions;
	}


	public int getPoints(){
		return this.points;
	}

	public ArrayList<CampMember> getAttending(){
		return this.attending;
	}

	public ArrayList<CampMember> getLeading(){
		return this.leading;
	}
	
	public void increasePoints(){
		this.points++;
	}

	public ArrayList<Enquiry> getEnquireAbout(){
		return this.enquireAbout;
	}

	public ArrayList<Suggestion> getSuggestions(){
		return this.suggestions;
	}

	/**
	 * This method returns a copy of the student.
	 * @return		A copy of the student.
	 */
	@Override
	public Student copyOf() {
		return new Student(this.getUserId(), this.getName(), this.getFaculty(), this.getPassword(), this.getPoints(), (ArrayList<CampMember>)this.getLeading().clone(), (ArrayList<CampMember>)this.getAttending().clone(), (ArrayList<Enquiry> )this.enquireAbout.clone(), (ArrayList<Suggestion> )this.suggestions.clone(), this.getIsDefaultPassword());
	}
	
	/**
	 * Returns true if o is equal to the user. userId is used for the comparison.
	 * @param o		other user to compare to.
	 * @return		true if o is equal to the user.
	 */
	@Override
	public boolean isEquals(Student o) {
		return super.isEquals(o);
	}
}