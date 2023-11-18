package com.example.dataloader;

import java.util.ArrayList;

public interface DataLoader<T> {
	ArrayList<T> loadData();
}