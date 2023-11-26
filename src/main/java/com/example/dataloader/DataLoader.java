package com.example.dataloader;

import java.util.ArrayList;

/**
 * Interface for loading data.
 */
public interface DataLoader<T> {
	ArrayList<T> loadData();
}