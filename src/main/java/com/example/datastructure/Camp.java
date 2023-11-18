package com.example.datastructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.example.datastore.IDataStoreObject;

public class Camp implements IDataStoreObject<Camp>{
	private UUID campId;
	private String campName;
	private ArrayList<Date> dates;
	private Date closingDate;
	private GroupName userGroup;
	private String location;
	private int totalSlots;
	private int committeeSlot;
	private String description;
	private boolean visibility;
	private Staff createdBy;

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
	public Camp(String campName, ArrayList<Date> dates, Date closingDate, GroupName userGroup, String location, int totalSlots, int committeeSlot, String description, boolean visibility, Staff createdBy) {
		this.campId = UUID.randomUUID();
		this.campName = campName;
		this.dates = dates;
		this.closingDate = closingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlot = committeeSlot;
		this.description = description;
		this.visibility = visibility;
		this.createdBy = createdBy;
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
	public Camp(UUID campId, String campName, ArrayList<Date> dates, Date closingDate, GroupName userGroup, String location, int totalSlots, int committeeSlot, String description, boolean visibility, Staff createdBy) {
		this.campId = campId;
		this.campName = campName;
		this.dates = dates;
		this.closingDate = closingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlot = committeeSlot;
		this.description = description;
		this.visibility = visibility;
		this.createdBy = createdBy;
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
	public ArrayList<Date> getDates() {
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
		return this.committeeSlot;
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
		// TODO - implement Camp.getRemaindingSlots
		throw new UnsupportedOperationException();
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
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
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
	 * @param committeeSlot		Maximum number of slots for commitee members.
	 */
	public void setCommitteeSlot(int committeeSlot) {
		this.committeeSlot = committeeSlot;
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
		this.setCommitteeSlot(other.committeeSlot);
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
		return new Camp(campId, campName, dates,  closingDate,  userGroup,  location,  totalSlots,  committeeSlot,  description,  visibility,  createdBy);
	}

}