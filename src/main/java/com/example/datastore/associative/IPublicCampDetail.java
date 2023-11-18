package com.example.datastore.associative;

import java.util.Date;

import com.example.DataStructurePackage.*;

public interface IPublicCampDetail {

	String getCampName();

	Date[] getDates();

	Date getClosingDate();

	String getLocation();

	GroupName getUserGroup();

	int getRemaindingAttendeeSlots();

	int getRemaindingCommiteeSlots();

	Staff getStaffIncharge();

	String getDescription();

}