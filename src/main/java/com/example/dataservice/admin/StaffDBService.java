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

/**
 * Class for creating DataStore operator classes that Staff has access to.
 * @see IDataStoreEditOperation  
 * @see IDataStoreRetrivalOperation  
 */
public class StaffDBService extends UserDBService<Staff> implements IStaffCampDBService, IStaffEnquiryDBService, IStaffSuggestionDBService, IStaffReportDBService {

	private Staff staff;
	
	/**
	 * Constructor for StaffDBService.
	 * @param staff	Reference to staff, required by some operations.
	 */
	public StaffDBService(Staff staff){
		this.staff = staff;
	}

	@Override
	public IDataStoreEditOperation<Camp> DSCreateCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore) {
		return new StaffCampCreate(this.staff, camp, staffDataStore);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSDeleteCamp(Camp camp, IDataStoreEditable<Staff> staffDataStore) {
		return new StaffCampDelete(this.staff, camp, staffDataStore);
	}

	@Override
	public IDataStoreEditOperation<Camp> DSEditCamp(Camp camp) {
		return new StaffCampEdit(this.staff, camp);
	}

	@Override
	public IDataStoreRetrivalOperation<Camp> DSCampRetrival() {
		return new StaffCampRetrival();
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