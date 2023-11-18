package com.example.UtilityPackage;

public class Pair<T, U> {

	private T first;
	private U second;

	/**
	 * 
	 * @param first
	 * @param second
	 */
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return this.first;
	}

	public U getSecond() {
		return this.second;
	}

}