package com.example.DataLoaderPackage;

import java.util.ArrayList;

public interface DataLoader<T> {
	ArrayList<T> loadData();
}