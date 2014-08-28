package com.reelreviews.reelreviews;

public class Person {
	private int id;
	private String name;
	
	//All fields
	public Person(int i, String n) {
		id = i;
		name = n;
	}
	//No id
	public Person(String n) {
		this(-1, n);
	}
	//No name
	public Person(int i) {
		this(i, "");
	}
	//No fields
	public Person() {
		this(-1);
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
}
