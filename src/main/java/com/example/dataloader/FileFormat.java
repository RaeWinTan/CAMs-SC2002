package com.example.dataloader;

import java.io.File;

import com.example.exception.FileNotFoundRuntimeException;

/**
 * Class for storing a file.
 */
public abstract class FileFormat {
	private File file; 

	/**
	 * Constructor for FileFormat.
	 * @param filePath	Path of the file.
	 */
	public FileFormat(String filePath) {
		this.file = new File(filePath);
		if (!this.fileExist())
			throw new FileNotFoundRuntimeException(filePath);
	}

	/**
	 * Check if file exists.
	 * @return true if file exists.
	 */
	private boolean fileExist() {
		return this.file.exists() && !this.file.isDirectory();
	}

	/**
	 * Get method for file.
	 * @return	File object.
	 */
	public File getFile(){
		return this.file;
	}

}