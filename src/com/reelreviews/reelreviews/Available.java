package com.reelreviews.reelreviews;

public class Available {
	private int id;
	private Movie movie;
	private Source source;
	
	//All fields
	public Available(int i, Movie m, Source s) {
		id = i;
		movie = m;
		source = s;
	}
	//No id
	public Available(Movie m, Source s) {
		this(-1, m, s);
	}
	//No source
	public Available(int i, Movie m) {
		this(i, m, null);
	}
	//No id, source
	public Available(Movie m) {
		this(-1, m);
	}
	//No movie
	public Available(int i, Source s) {
		this(i, null, s);
	}
	//No id, movie
	public Available(Source s) {
		this(-1, s);
	}
	//No fields
	public Available() {
		this(-1, null, null);
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
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	
	public String toString() {
		return movie.getTitle() + " at " + source.getOrigin();
	}
}
