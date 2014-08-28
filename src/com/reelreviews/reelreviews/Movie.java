package com.reelreviews.reelreviews;

public class Movie {
	private int id;
	private String title;
	private int year;
	private String image;
	
	//All fields
	public Movie(int m, String t, int y, String i) {
		id = m;
		title = t;
		year = y;
		image = i;
	}
	//No id
	public Movie(String t, int y, String i) {
		this(-1, t, y, i);
	}
	//No image
	public Movie(int m, String t, int y) {
		this(m, t, y, "");
	}
	//No id, image
	public Movie(String t, int y) {
		this(-1, t, y);
	}
	//Nothing
	public Movie() {
		this("", -1);
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setMovie_id(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String toString() {
		return title + "(" + year + ")";
	}
}
