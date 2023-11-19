package com.example.datastructure;

import java.util.ArrayList;
import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

public class Student extends User implements IDataStoreObject<Student>{

	private int points;
	private ArrayList<CampMember> leading;
	private ArrayList<CampMember> attending;
	private ArrayList<Message> repliedTo;
	private ArrayList<Enquiry> enquireAbout;
	private ArrayList<Suggestion> suggestions;

	/**
	 * Constructor for Student class, password will be set to default.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 */
	public Student(String userId, String name, GroupName faculty) {
		super(userId, name, faculty);
		this.points = 0;
		this.leading = new ArrayList<CampMember>();
		this.attending = new ArrayList<CampMember>();
		this.repliedTo = new ArrayList<>();
		this.enquireAbout = new ArrayList<>();
		this.suggestions = new ArrayList<>();
	}

	/**
	 * Constructor for Student class.
	 * @param userId		Unique Identifier of the student.
	 * @param name			Name of the student.
	 * @param faculty		Faculty the student is from.
	 * @param password		Password of the student.
	 */
	private Student(String userId, String name, GroupName faculty, String password, int points, ArrayList<CampMember> leading, ArrayList<CampMember> attending, ArrayList<Message> repliedTo, ArrayList<Enquiry> enquireAbout) {
		super(userId, name, faculty, password);
		this.points = points;
		this.leading = leading;
		this.attending = attending;
		this.repliedTo = repliedTo;
		this.enquireAbout = enquireAbout;
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

	public ArrayList<Message> getRepliedTo(){
		return this.repliedTo;
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
		return new Student(this.getUserId(), this.getName(), this.getFaculty(), this.getPassword(), this.getPoints(), (ArrayList<CampMember>)this.getLeading().clone(), (ArrayList<CampMember>)this.getAttending().clone(), (ArrayList<Message>)this.getRepliedTo().clone(), (ArrayList<Enquiry> )this.enquireAbout.clone());
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

	public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("name", this.getName()));
		rtn.add(new Pair<String, String>("faculty", this.getFaculty().toString()));
		rtn.add(new Pair<String, String>("points", ""+this.points));
		return rtn;
	}
}