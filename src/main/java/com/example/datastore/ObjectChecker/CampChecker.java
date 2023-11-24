package com.example.datastore.ObjectChecker;

import java.util.Date;

import com.example.datastructure.Camp;
import com.example.exception.CampCheckerException;

public class CampChecker {
    Camp camp = null;
    public CampChecker(Camp camp){//for create camp
        this.camp = camp;
        checkDates();
        checkSlots();
    }
    private void checkDates(){
        Date startDate = camp.getDates()[0];
        Date endDate = camp.getDates()[1];
        Date closingDate = camp.getClosingDate();
        if(endDate.before(startDate)) {throw new CampCheckerException("End date cannot be before start date");}     
        if(closingDate.after(startDate) || closingDate.equals(startDate)) {throw new CampCheckerException("Closing date cannot be after or on start date");}
    }
    private void checkSlots(){
        if(this.camp.getTotalSlots() < this.camp.getCommitteeSlot()){throw new CampCheckerException("totalSlots cannot be less than committee slots");}   
    }
}
