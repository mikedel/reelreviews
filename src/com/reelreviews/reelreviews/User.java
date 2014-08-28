package com.reelreviews.reelreviews;

public class User {
	private int id;
	private String email;
	private String username;
	private String password;
	private int score;
	
	//All fields
	public User(int i, String e, String n, String p, int s) {
		id = i;
		email = e;
		username = n;
		password = p;
		score = s;
	}
	//No id
	public User(String e, String n, String p, int s) {
		this(-1, e, n, p, s);
	}
	//No id, score -- Probably the most used
	public User(String e, String n, String p) {
		this(e, n, p, 0);
	}
	//No id, username
	public User(String e, String p, int s) {
		this(e, "", p, s);
	}
	//No id, username, score
	public User(String e, String p) {
		this(e, p, 0);
	}
	//Only an email
	public User(String e) {
		this(e, "");
	}
	//Nothing
	public User() {
		this("");
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setUser_id(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return email;
	}
	
}
