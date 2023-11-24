package com.example.datastructure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;

public class Camp implements IDataStoreObject<Camp>{
	private UUID campId = null;
	private String campName= null;
	private Date[] dates;
	private Date closingDate= null;
	private GroupName userGroup= null;
	private String location= null;
	private Integer totalSlots= null;
	private Integer committeeSlots= null;
	private String description= null;
	private Boolean visibility= null;
	private Staff createdBy= null;
	private ArrayList<CampMember> committees= new ArrayList<>();
	private ArrayList<CampMember> attendees= new ArrayList<>();
	private ArrayList<Enquiry> enquiries= new ArrayList<>();
	private ArrayList<Suggestion> suggestions= new ArrayList<>(); 

	public Camp(){
		this.campId = UUID.randomUUID();
	}

	public Camp(Camp camp){
		this.campId = camp.getCampId();
	}

	/**
	 * Constructor for Camp class, used to create a copy of the Camp.
	 * @param campId		Unique identifier of the camp
	 * @param campName 		Name of the camp.
	 * @param dates 		Dates in which the camp is active.
	 * @param closingDate	Last day for registration.
	 * @param userGroup		Faculty in which the camp is open to.
	 * @param location		Location of the camp.
	 * @param totalSlots	Maximum number of slots for students to participate (including committees).
	 * @param committeeSlot	Maximum number of slots for commitee members.
	 * @param description	Description of the camp.
	 * @param visibility	Flag for Student's access to view the camp.
	 * @param createdBy		Staff who created the camp.
	 */
	private Camp(UUID campId, String campName, Date[] dates, Date closingDate, GroupName userGroup, String location, int totalSlots, int committeeSlot, String description, boolean visibility, Staff createdBy, ArrayList<CampMember> attendees, ArrayList<CampMember> committees, ArrayList<Enquiry> enquiries) {
		this.campId = campId;
		this.campName = campName;
		this.dates = dates;
		this.closingDate = closingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlots = committeeSlot;
		this.description = description;
		this.visibility = visibility;
		this.createdBy = createdBy;
		this.attendees = attendees;
		this.committees = committees;
		this.enquiries = enquiries;
	}

	/**
	 * Get method for campId
	 * @return		Unique identifier of the camp
	 */
	public UUID getCampId() {
		return this.campId;
	}

	/**
	 * Get method for campName.
	 * @return		Name of the camp.
	 */
	public String getCampName() {
		return this.campName;
	}

	/**
	 * Get method for dates.
	 * @return		Dates in which the camp is active.
	 */
	public Date[] getDates() {
		return this.dates;
	}

	/**
	 * Get method for closingDate.
	 * @return		Last day for registration.
	 */
	public Date getClosingDate() {
		return this.closingDate;
	}

	/**
	 * Get method for location.
	 * @return		Location of the camp. 
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Get method for totalSlots.
	 * @return		Maximum number of slots for students to participate (including committees).
	 */
	public int getTotalSlots() {
		return this.totalSlots;
	}

	/**
	 * Get method for comitteeSlot.
	 * @return 		Maximum number of slots for commitee members.
	 */
	public int getCommitteeSlot() {
		return this.committeeSlots;
	}

	/**
	 * Get method for description.
	 * @return		Description of the camp.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get method for userGroup.
	 * @return		Faculty in which the camp is open to.
	 */
	public GroupName getUserGroup() {
		return this.userGroup;
	}

	/**
	 * Get method for visibility.
	 * @return		Flag for Student's access to view the camp.
	 */
	public boolean getVisibility() {
		return this.visibility;
	}

	/**
	 * Get method for createdBy.
	 * @return		Staff who created the camp.
	 */
	public Staff getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Returns the number of slots remaining.
	 * @return 		Number of slots remaining.
	 */
	public int getRemaindingSlots() {
		return this.totalSlots - this.committees.size() - this.attendees.size();
	}

	public int getRemaindingCommitteeSlots() {
		return this.committeeSlots - this.committees.size();
	}

	public ArrayList<CampMember> getAttendees(){
		return this.attendees;
	}

	public ArrayList<CampMember> getCommittees(){
		return this.committees;
	}

	public ArrayList<Enquiry> getEnquiries(){
		return this.enquiries;
	}


	/**
	 * Set method for campName.
	 * @param campName		Name of the camp.
	 */
	public void setCampName(String campName) {
		this.campName = campName;
	}

	/**
	 * Set method for dates.
	 * @param dates		Dates in which the camp is active.
	 */
	public void setDates(Date[] dates) {
		if (dates[1].before(dates[0]))
			throw new IllegalArgumentException("End date cannot be earlier than start date.");
		if (closingDate != null){
			if (dates[0].before(closingDate))
				throw new IllegalArgumentException("Closing date cannot be after camp start date.");
		}
		
		this.dates = dates.clone();
	}

	/**
	 * Set method for closingDate.
	 * @param closingDate		Last day for registration.
	 */
	public void setClosingDate(Date closingDate) {
		if (dates[0] != null){
			if (dates[0].before(closingDate))
				throw new IllegalArgumentException("Closing date cannot be after camp start date.");
		}
		this.closingDate = closingDate;
	}

	/**
	 * Set method for userGroup.
	 * @param userGroup		Faculty in which the camp is open to.
	 */
	public void setUserGroup(GroupName userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * Set method for location.
	 * @param location		Location of the camp.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Set method for totalSlots.
	 * @param totalSlots		Maximum number of slots for students to participate (including committees).
	 */
	public void setTotalSlots(int totalSlots) {
		if (totalSlots < this.committeeSlots){
			throw new IllegalArgumentException("Number of committee members shall not exceed total number of participants");
		}
		if (totalSlots < this.attendees.size() + this.committees.size()){
			throw new IllegalArgumentException("Number of participants exceeds the new total slot value.");
		}
		this.totalSlots = totalSlots;
	}

	/**
	 * Set method for committeeSlot.
	 * @param committeeSlots		Maximum number of slots for commitee members.
	 */
	public void setCommitteeSlot(int committeeSlots) {
		if (committeeSlots < this.committees.size()){
			throw new IllegalArgumentException("Number of committees exceeds the new commitee slot value.");
		}
		this.committeeSlots = committeeSlots;
	}

	/**
	 * Set method for description.
	 * @param description		Description of the camp.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Set method for visibility.
	 * @param visibility		Flag for Student's access to view the camp.
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * Set method for createdBy.
	 * @param createdBy		Staff who created the camp.
	 */
	public void setCreatedBy(Staff createdBy) {
		this.createdBy = createdBy;
	}

	public void setAll(Camp other){
		if (other.getCampName()!=null)
			this.setCampName(other.getCampName());
		if (other.getDates()!=null)
			this.setDates(other.getDates());
		if (other.getClosingDate()!=null)
			this.setClosingDate(other.getClosingDate());
		if (other.getUserGroup()!=null)
			this.setUserGroup(other.userGroup);
		if (other.getLocation()!=null)
			this.setLocation(other.getLocation());
		if (other.getTotalSlots()>=0)
			this.setTotalSlots(other.getTotalSlots());
		if (other.getCommitteeSlot()>=0)
			this.setCommitteeSlot(other.getCommitteeSlot());
		if (other.getDescription()!=null)
			this.setDescription(other.getDescription());
		if (other.getVisibility())
			this.setVisibility(other.getVisibility());
	}

	/**
	 * Returns true if o is equal to the camp. campId is used for the comparison.
	 * @param o		other camp to compare to.
	 * @return		true if o is equal to the camp.
	 */
	@Override
	public boolean isEquals(Camp o) {
		return o.getCampId().equals(this.getCampId()); 
	}

	/**
	 * This method returns a copy of the camp.
	 * @return		A copy of the camp.
	 */
	@Override
	public Camp copyOf() {
		return new Camp(campId, campName, dates,  closingDate,  userGroup,  location,  totalSlots,  committeeSlots,  description,  visibility,  createdBy, (ArrayList<CampMember>)this.attendees.clone(), (ArrayList<CampMember>)this.committees.clone(), (ArrayList<Enquiry>)this.enquiries.clone());
	}

	public ArrayList<Suggestion> getSuggestions(){
		return this.suggestions;
	}

	@Override
	public String toString(){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (this.getCampName()!=null)
			str += "Camp Name: " + this.getCampName() + "\n";
		if (this.getDates()!=null)
			str += "Dates: " + sdf.format(this.dates[0]) + " - " + sdf.format(this.dates[1])+ "\n";
		if (this.getClosingDate()!=null)
			str += "Last day of registration: " + sdf.format(this.getClosingDate())+ "\n";
		if (this.getUserGroup()!=null)
			str += "User Group:" + this.userGroup.toString()+ "\n";
		if (this.getLocation()!=null)
			str += "Location: " + this.getLocation()+ "\n";
		if (this.getTotalSlots()>=0)
			str += "Total Slot: " + this.getTotalSlots()+ "\n";	
		if (this.getCommitteeSlot()>=0)
			str += "Committee Slot: " + this.getCommitteeSlot()+ "\n";	
		if (this.getDescription()!=null)
			str += "Description: " + this.getDescription()+ "\n";	
		if (this.getVisibility())
			str += "Visibility: " + (this.getVisibility()?"Visible":"Not visible")+ "\n";
		return str;
	}

}