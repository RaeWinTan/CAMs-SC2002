package com.example.dataloader;

import java.util.ArrayList;

import com.example.datastructure.GroupName;
import com.example.datastructure.User;



public abstract class UserCSVLoader<T extends User> implements DataLoader<T> {

	private CellStringReturnable cellStringFormat;

	/**
	 * 
	 * @param sourceFile
	 */
	public UserCSVLoader(String sourceFile) {
		this.cellStringFormat = new CSVFileFormat(sourceFile);

	}

	public abstract ArrayList<T> loadData();

	protected CellStringReturnable getCellStringFormat(){
		return this.cellStringFormat;
	}

	protected GroupName getFacultyFromString(String strFaculty){
		switch(strFaculty){
			case "ADM":
				return GroupName.ADM;
			case "EEE":
				return GroupName.EEE;
			case "NMS":
				return GroupName.NMS;
			case "SCSE":
				return GroupName.SCSE;
			case "SSS":
				return GroupName.SSS;
			default:
				// TODO: Error handling
				//throw new Exception("Invalid faculty: " + facultyStr);
				// TEMPCODE:
				return GroupName.NTU;
				
		}
	}

}