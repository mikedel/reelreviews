package com.reelreviews.reelreviews;

public class Favorite {
	private int id;
	private User user;
	private Movie movie;
	private int num;
	
	//All fields
	public Favorite(int i, User u, Movie m, int n) {
		id = i;
		user = u;
		movie = m;
		num = n;
	}
	//No id
	public Favorite(User u, Movie m, int n) {
		this(-1, u, m, n);
	}
	//No num
	public Favorite(int i, User u, Movie m) {
		this(i, u, m, -1);
	}
	//No id, num
	public Favorite(User u, Movie m) {
		this(u, m, -1);
	}
	//No fields
	public Favorite() {
		this(null, null);
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String toString() {
		return user.getUsername() + " - " + movie.getTitle();
	}
}
