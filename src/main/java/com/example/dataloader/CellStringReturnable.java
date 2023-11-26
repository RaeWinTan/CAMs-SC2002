package com.example.dataloader;

import java.util.ArrayList;

/** Interface for converting a set of data into a 2D ArrayList of String */
public interface CellStringReturnable {
	ArrayList<ArrayList<String>> toDataString();
}