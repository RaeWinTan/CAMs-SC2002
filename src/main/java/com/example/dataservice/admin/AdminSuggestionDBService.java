package com.example.dataservice.admin;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastore.operator.StaffSuggestionApproveOperator;
import com.example.datastore.operator.StaffSuggestionRetrival;
import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;

public class AdminSuggestionDBService implements IAdminSuggestionDBService {

    private Staff staff;
	public AdminSuggestionDBService(Staff staff){
		this.staff = staff;
	}

    @Override
    public DataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion) {
        return new StaffSuggestionApproveOperator(this.staff, suggestion);
    }

    @Override
    public DataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival() {
        return new StaffSuggestionRetrival(this.staff);
    }
    
}
