package com.reelreviews.reelreviews;

public class MovieGenre {
	private int id;
	private Movie movie;
	private Genre genre;
	
	//All fields
	public MovieGenre(int i, Movie m, Genre g) {
		id = i;
		movie = m;
		genre = g;
	}
	//No id
	public MovieGenre(Movie m, Genre g) {
		this(-1, m, g);
	}
	//No fields
	public MovieGenre() {
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
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public String toString() {
		return movie.toString() + ": " + genre.toString();
	}
}
