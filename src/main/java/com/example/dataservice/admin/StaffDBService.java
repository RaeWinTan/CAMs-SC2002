package com.example.dataservice.admin;

import com.example.controllerlibs.ReportFilter;
import com.example.dataservice.UserDBService;
import com.example.datastore.IDataStoreEditable;
import com.example.datastore.IDataStoreRetrivable;
import com.example.datastore.operator.StaffGenerateParticipantReport;
import com.example.datastore.operator.AdminReplyToEnquiry;
import com.example.datastore.operator.IDataStoreEditOperation;
import com.example.datastore.operator.IDataStoreRetrivalOperation;
import com.example.datastore.operator.StaffApproveSuggestion;
import com.example.datastore.operator.StaffCampCreate;
import com.example.datastore.operator.StaffCampDelete;
import com.example.datastore.operator.StaffCampEdit;
import com.example.datastore.operator.StaffCampRetrival;
import com.example.datastore.operator.StaffGenerateEnquiryReport;
import com.example.datastore.operator.StaffGeneratePerformanceReport;
import com.example.datastructure.Camp;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;
import com.example.datastructure.Student;
import com.example.datastructure.Suggestion;
import com.example.utility.Pair;

public class StaffDBService extends UserDBService<Staff> implements IStaffCampDBService, IStaffEnquiryDBService, IStaffSuggestionDBService, IStaffReportDBService {


	private Staff staff;
	
	public StaffDBService(Staff staff){
		this.staff = staff;
	}

    /**
	 * This method returns a datastore operation to create a new camp.
	 * @param camp: Camp to be created.
	 */
	@Override
	public IDataStoreEditOperation<Camp> DSCreateCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore) {
		return new StaffCampCreate(this.staff, camp, staffDataStore);
	}

	/**
	 * This method returns a datastore operation to delete an existing camp.
	 * @param camp: Camp to be deleted.
	 */
	@Override
	public IDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore) {
		return new StaffCampDelete(this.staff, camp, staffDataStore);
	}

	/**
	 * This method returns a datastore operation to edit an existing camp.
	 * @param camp: Camp with updated attributes.
	 */
	@Override
	public IDataStoreEditOperation<Camp> DSEditCamp(Camp camp) {
		return new StaffCampEdit(this.staff, camp);
	}

	/**
	 * 
	 * This method returns a datastore operation to retrieve camps.
	 */
	@Override
	public IDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StaffCampRetrival();
	}

	/**
	 * 
	 * This method returns a datastore operation to retrieve relevant camps.
	 */
	@Override
	public IDataStoreRetrivalOperation<Camp> DSRelevantCampRetrival() {
		return new StaffCampRetrival(this.staff);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSEnquiryReply(Pair<Enquiry,Message> em) {
		return new AdminReplyToEnquiry(this.staff, em.getSecond(), em.getFirst());
	}

	@Override
	public IDataStoreEditOperation<Camp> DSSuggestionApprove(Suggestion suggestion, IDataStoreEditable<Student> studenttDataStore, IDataStoreEditable<Camp> campDataStore) {
		return new StaffApproveSuggestion(this.staff, suggestion, studenttDataStore, campDataStore);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSGenerateEnquiryReport(String fileName){
		return new StaffGenerateEnquiryReport(staff, fileName);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSGeneratePerformanceReport(String fileName, IDataStoreRetrivable<Student> studentDSRetrivable){
		return new StaffGeneratePerformanceReport(staff, fileName, studentDSRetrivable);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSGenerateParticipantReport(String fileName, ReportFilter reportFilter){
		return new StaffGenerateParticipantReport(staff, fileName, reportFilter);
	}
}