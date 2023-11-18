package com.example.datastore.associative;

import com.example.UtilityPackage.*;
import java.util.ArrayList;
public interface IAssociativeDataStore<T, U> {

	/**
	 * 
	 * @param obj
	 * @throws Exception 
	 */
	void addList1(Pair<T, U> obj) throws Exception;

	/**
	 * 
	 * @param obj
	 */
	void addList2(Pair<T, U> obj) throws Exception;

	ArrayList<Pair<T, U>> getList1();

	ArrayList<Pair<T, U>> getList2();

}