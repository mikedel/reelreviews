package com.reelreviews.reelreviews;

public class Genre {
	private int id;
	private String name;
	
	//All fields
	public Genre(int i, String n) {
		id = i;
		name = n;
	}
	//No id
	public Genre(String n) {
		this(-1, n);
	}
	//No name
	public Genre(int i) {
		this(i, "");
	}
	//No fields
	public Genre() {
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