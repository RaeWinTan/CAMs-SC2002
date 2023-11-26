package com.example.dataloader;

import java.util.ArrayList;

import com.example.datastructure.GroupName;
import com.example.datastructure.User;
import com.example.exception.UnexpectedValueException;


/** Abstract class to convert CSV file into subclasses of User class. */
public abstract class UserCSVLoader<T extends User> implements DataLoader<T> {

	private CellStringReturnable cellStringFormat;

    /**
     * Constructor for UserCSVLoader.
     * @param sourceFile    Path of csv file.
     */
	public UserCSVLoader(String sourceFile) {
		this.cellStringFormat = new CSVFileFormat(sourceFile);

	}

    /**
     * Read data from csv file, convert to a 2D ArrayList of String, then convert to subclass of User objects.
     * @return An ArrayList of subclass of User.
     */
	public abstract ArrayList<T> loadData();

	protected CellStringReturnable getCellStringFormat(){
		return this.cellStringFormat;
	}

	/** Convert String into GroupName enum. */
	public static GroupName getFacultyFromString(String strFaculty){
		switch(strFaculty){
			case "NTU":
				return GroupName.NTU;
			case "ADM":
				return GroupName.ADM;
			case "EEE":
				return GroupName.EEE;
			case "NBS":
				return GroupName.NBS;
			case "SCSE":
				return GroupName.SCSE;
			case "SSS":
				return GroupName.SSS;
			default:
				throw new UnexpectedValueException("Faculty", strFaculty);
		}
	}
}