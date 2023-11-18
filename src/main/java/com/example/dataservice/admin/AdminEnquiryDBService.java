package com.example.dataservice.admin;

import com.example.datastore.monolist.operator.IMonoListDataStoreEditOperation;
import com.example.datastore.monolist.operator.IMonoListDataStoreRetrivalOperation;
import com.example.datastore.monolist.operator.StaffEnquiryRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;

public class AdminEnquiryDBService implements IAdminEnquiryDBService {

    private Staff staff;
	public AdminEnquiryDBService(Staff staff){
		this.staff = staff;
	}

    @Override
    public IMonoListDataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryAddReply'");
    }

    @Override
    public IMonoListDataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival() {
        return new StaffEnquiryRetrivalOperation(this.staff);
    }
    
}
