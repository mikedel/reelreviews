package com.reelreviews.reelreviews;

public class Recommendation {
	private int id;
	private User sender;
	private User receiver;
	private Movie movie;
	private String review;
	private long date;
	private int stars;
	private Source source;
	
	//All fields
	public Recommendation(int i, User f, User t, Movie m, String r, long d, int s, Source so) {
		id = i;
		sender = f;
		receiver = t;
		movie = m;
		review = r;
		date = d;
		stars = s;
		source = so;
	}
	//No id
	public Recommendation(User f, User t, Movie m, String r, long d, int s, Source so) {
		this(-1, f, t, m, r, d, s, so);
	}
	//No source
	public Recommendation(int i, User f, User t, Movie m, String r, long d, int s) {
		this(i, f, t, m, r, d, s, null);
	}
	//No id, source
	public Recommendation(User f, User t, Movie m, String r, long d, int s) {
		this(-1, f, t, m, r, d, s, null);
	}
	//No id, stars
	public Recommendation(User f, User t, Movie m, String r, long d, Source s) {
		this(-1, f, t, m, r, d, -1, s);
	}
	//No id, stars, source
	public Recommendation(User f, User t, Movie m, String r, long d) {
		this(f, t, m, r, d, -1);
	}
	//No fields
	public Recommendation() {
		this(null, null, null, "", -1);
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	
	public String toString() {
		return "Recommendation from " + sender.getUsername() + " to " + receiver.getUsername() + " for "
				+ movie.getTitle();
	}
}
