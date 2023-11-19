package com.example.dataservice.admin;

import com.example.datastore.operator.DataStoreEditOperation;
import com.example.datastore.operator.DataStoreRetrivalOperation;
import com.example.datastore.operator.StaffEnquiryRetrivalOperation;
import com.example.datastructure.Enquiry;
import com.example.datastructure.Message;
import com.example.datastructure.Staff;

public class AdminEnquiryDBService implements IAdminEnquiryDBService {

    private Staff staff;
	public AdminEnquiryDBService(Staff staff){
		this.staff = staff;
	}

    @Override
    public DataStoreEditOperation<Enquiry> DSEnquiryAddReply(Enquiry enquiry, Message reply) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DSEnquiryAddReply'");
    }

    @Override
    public DataStoreRetrivalOperation<Enquiry> DSEnquiryRetrival() {
        return new StaffEnquiryRetrivalOperation(this.staff);
    }
    
}
