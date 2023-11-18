package com.example.DataLoaderPackage;

import java.io.File;

import com.example.ExceptionPackage.FileNotFoundRuntimeException;

public abstract class FileFormat {

	/**
	 * 
	 * @param filePath
	 */

	private File file; 
	public FileFormat(String filePath) {
		this.file = new File(filePath);
		if (!this.fileExist())
			throw new FileNotFoundRuntimeException(filePath);
	}

	private boolean fileExist() {
		return this.file.exists() && !this.file.isDirectory();
	}

	public File getFile(){
		return this.file;
	}

}