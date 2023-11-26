package com.example.datastructure;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;

/**
 * Class storing data of a Camp.
 */
public class Camp implements IDataStoreObject<Camp>{
	private UUID campId = null;
	private String campName= null;
	private Date[] dates = null;
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

	/**
	 * Constructor for a new camp, used when staff is creating a new camp.
	 */
	public Camp(){
		this.campId = UUID.randomUUID();
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
	 * @param attendees		ArrayList of CampMember with students attending the camp.
	 * @param committees	ArrayList of CampMember with students leading the camp.
	 * @param enquiries		ArrayList of Enquiry made for the camp.
	 * @param suggestions	ArrayList of Suggestion for the camp.
	 */
	private Camp(UUID campId, String campName, Date[] dates, Date closingDate, GroupName userGroup, String location, int totalSlots, int committeeSlot, String description, boolean visibility, Staff createdBy, ArrayList<CampMember> attendees, ArrayList<CampMember> committees, ArrayList<Enquiry> enquiries, ArrayList<Suggestion> suggestions) {
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
		this.suggestions = suggestions;
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
	public Integer getTotalSlots() {
		return this.totalSlots;
	}

	/**
	 * Get method for comitteeSlot.
	 * @return 		Maximum number of slots for commitee members.
	 */
	public Integer getCommitteeSlot() {
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
	public Boolean getVisibility() {
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

	/**
	 * Returns the number of committee slots remaining.
	 * @return	Number of committee slots remaining.
	 */
	public int getRemaindingCommitteeSlots() {
		return this.committeeSlots - this.committees.size();
	}

	/**
	 * Get method for attendees.
	 * @return	ArrayList of CampMember with students attending the camp.
	 */
	public ArrayList<CampMember> getAttendees(){
		return this.attendees;
	}

	/**
	 * Get method for committees.
	 * @return	ArrayList of CampMember with students leading the camp.
	 */
	public ArrayList<CampMember> getCommittees(){
		return this.committees;
	}

	/**
	 * Get method for enquiries.
	 * @return	ArrayList of Enquiry made for the camp.
	 */
	public ArrayList<Enquiry> getEnquiries(){
		return this.enquiries;
	}

	/**
	 * Get method for suggestions.
	 * @return	ArrayList of Suggestion for the camp.
	 */
	public ArrayList<Suggestion> getSuggestions(){
		return this.suggestions;
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
		
		this.totalSlots = totalSlots;
	}

	/**
	 * Set method for committeeSlot.
	 * @param committeeSlots		Maximum number of slots for commitee members.
	 */
	public void setCommitteeSlot(int committeeSlots) {
		
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

	/**
	 * Copy other camp's attributes to this camp. Used when editing camp/approving suggestion. Only attributes that can be changed in edit/suggestion will be changed.
	 * @param other	Other camp.
	 */
	public void setAll(Camp other){
		this.setCampName(other.getCampName());
		this.setDates(other.getDates());
		this.setClosingDate(other.getClosingDate());
		this.setUserGroup(other.userGroup);
		this.setLocation(other.getLocation());
		this.setTotalSlots(other.getTotalSlots());
		this.setCommitteeSlot(other.getCommitteeSlot());
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
		return new Camp(campId, campName, dates,  closingDate,  userGroup,  location,  totalSlots,  committeeSlots,  description,  visibility,  createdBy, (ArrayList<CampMember>)this.attendees.clone(), (ArrayList<CampMember>)this.committees.clone(), (ArrayList<Enquiry>)this.enquiries.clone(), (ArrayList<Suggestion>)this.suggestions.clone());
	}

	/**
	 * Convert Camp to String.
	 */
	@Override
	public String toString(){
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (this.getCampName()!=null)
			str += "Camp Name: " + this.getCampName() ;
		if (this.getDates()!=null)
			str += "Dates: " + sdf.format(this.dates[0]) + " - " + sdf.format(this.dates[1]);
		if (this.getClosingDate()!=null)
			str += "Last day of registration: " + sdf.format(this.getClosingDate());
		if (this.getUserGroup()!=null)
			str += "User Group:" + this.userGroup.toString();
		if (this.getLocation()!=null)
			str += "Location: " + this.getLocation();
		if (this.getTotalSlots()>=0)
			str += "Total Slot: " + this.getTotalSlots();	
		if (this.getCommitteeSlot()>=0)
			str += "Committee Slot: " + this.getCommitteeSlot();	
		if (this.getDescription()!=null)
			str += "Description: " + this.getDescription();	
		if (this.getVisibility())
			str += "Visibility: " + (this.getVisibility()?"Visible":"Not visible");
		return str;
	}
}