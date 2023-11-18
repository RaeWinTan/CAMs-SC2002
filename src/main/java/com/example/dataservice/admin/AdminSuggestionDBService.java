package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastore.monolist.operator.StaffSuggestionRetrival;
import com.example.datastore.monolist.operator.StaffSuggestionApproveOperator;
import com.example.datastructure.Staff;
import com.example.datastructure.Suggestion;

public class AdminSuggestionDBService implements IAdminSuggestionDBService {

    private Staff staff;
	public AdminSuggestionDBService(Staff staff){
		this.staff = staff;
	}

    @Override
    public IMonoListDataStoreEditOperation<Suggestion> DSApproveSuggestion(Suggestion suggestion) {
        return new StaffSuggestionApproveOperator(this.staff, suggestion);
    }

    @Override
    public IMonoListDataStoreRetrivalOperation<Suggestion> DSSuggestionRetrival() {
        return new StaffSuggestionRetrival(this.staff);
    }
    
}
