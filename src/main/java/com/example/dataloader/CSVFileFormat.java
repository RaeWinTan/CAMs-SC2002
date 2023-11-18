package com.example.dataloader;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.exception.FileNotFoundRuntimeException;

import java.io.FileNotFoundException;

public class CSVFileFormat extends FileFormat implements CellStringReturnable {

	public CSVFileFormat(String sourceFile) {
		super(sourceFile);	
	}

	public ArrayList<ArrayList<String>> toDataString(){
		ArrayList<ArrayList<String>> dataString2D = new ArrayList<ArrayList<String>>();

		Scanner scanner = this.getScanner();

		while (scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] rowValues = line.split(",");
			ArrayList<String> row = new ArrayList<String>();
			for (String value: rowValues){
				row.add(value);
			}
			dataString2D.add(row);
		}
		scanner.close();
			


		return dataString2D;
		
	}

	private Scanner getScanner(){
		try {
			return new Scanner(this.getFile());
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundRuntimeException(this.getFile().getPath());
		}
	}

}