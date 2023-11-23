package com.example.datastructure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;
import com.example.utility.Pair;

public class Camp implements IDataStoreObject<Camp>{
	private UUID campId;
	private String campName;
	private Date[] dates;
	private Date closingDate;
	private GroupName userGroup;
	private String location;
	private int totalSlots;
	private int committeeSlots;
	private String description;
	private boolean visibility;
	private Staff createdBy;
	private ArrayList<CampMember> committees;
	private ArrayList<CampMember> attendees;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Suggestion> suggestions; 

	/**
	 * Constructor for Camp class.
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
	public Camp(String campName, Date[] dates, Date closingDate, GroupName userGroup, String location, int totalSlots, int committeeSlot, String description, boolean visibility, Staff createdBy) {
		this.campId = UUID.randomUUID();
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
		this.attendees = new ArrayList<CampMember>();
		this.committees = new ArrayList<CampMember>();
	}

	/**
	 * Constructor for Camp class.
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

	public ArrayList<Pair<String, String>> toAttributeValueMapping(){
		ArrayList<Pair<String, String>> rtn = new ArrayList<Pair<String, String>>();
		rtn.add(new Pair<String, String>("campId", this.campId.toString()));
		rtn.add(new Pair<String, String>("campName", this.campName));
		rtn.add(new Pair<String, String>("startDate", this.dates[0].toString()));
		rtn.add(new Pair<String, String>("endDate", this.dates[1].toString()));
		rtn.add(new Pair<String, String>("closingDate", this.closingDate.toString()));
		rtn.add(new Pair<String, String>("userGroup", this.userGroup.toString()));
		rtn.add(new Pair<String, String>("location", this.location));
		rtn.add(new Pair<String, String>("totalSlots", this.totalSlots + ""));
		rtn.add(new Pair<String, String>("committeeSlot", this.committeeSlots+""));
		rtn.add(new Pair<String, String>("description", this.description));
		rtn.add(new Pair<String, String>("visibility", ""+this.visibility));
		rtn.add(new Pair<String, String>("createdBy", this.createdBy.getUserId()));
		return rtn;
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
		this.dates = dates.clone();
	}

	/**
	 * Set method for closingDate.
	 * @param closingDate		Last day for registration.
	 */
	public void setClosingDate(Date closingDate) {
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
			throw new IllegalArgumentException("Number of committee members shall not exceed number of attendees");
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
		this.setCampName(other.getCampName());
		this.setDates(other.getDates());
		this.setClosingDate(other.getClosingDate());
		this.setUserGroup(other.userGroup);
		this.setLocation(other.getLocation());
		this.setTotalSlots(other.getTotalSlots());
		this.setCommitteeSlot(other.committeeSlots);
		this.setDescription(other.getDescription());
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

}