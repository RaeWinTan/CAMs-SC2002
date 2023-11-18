package com.example.datastore.monolist.operator;

import java.util.ArrayList;

import com.example.datastructure.Enquiry;
import com.example.datastructure.Staff;

public class StaffEnquiryRetrivalOperation implements IMonoListDataStoreRetrivalOperation<Enquiry>{

    private Staff staff;

    public StaffEnquiryRetrivalOperation(Staff staff){
        this.staff = staff;
    }

    @Override
    public ArrayList<Enquiry> perform(ArrayList<Enquiry> data) {

        ArrayList<Enquiry> relevantEnquiries = new ArrayList<Enquiry>();

        for (Enquiry enquiry : data) {
            if (enquiry.getCamp().getCreatedBy().isEquals(this.staff)){
                relevantEnquiries.add(enquiry);
            }
        }

        return relevantEnquiries;
    }
    
}
