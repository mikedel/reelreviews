package com.reelreviews.reelreviews;

public class Credit {
	private int id;
	private Movie movie;
	private Person person;
	private String role;
	
	//All fields
	public Credit(int i, Movie m, Person p, String r) {
		id = i;
		movie = m;
		person = p;
		role = r;
	}
	//No id
	public Credit(Movie m, Person p, String r) {
		this(-1, m, p, r);
	}
	//No role
	public Credit(int i, Movie m, Person p) {
		this(i, m, p, "");
	}
	//No id, role
	public Credit(Movie m, Person p) {
		this(-1, m, p);
	}
	//No fields
	public Credit() {
		this(null, null);
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString() {
		return person.getName() + " in " + movie.getTitle() + " as " + role;
	}
}
