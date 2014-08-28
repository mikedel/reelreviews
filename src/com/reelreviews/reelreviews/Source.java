package com.reelreviews.reelreviews;

public class Source {
	private int id;
	private String origin;
	private String link;
	
	//All fields
	public Source(int i, String o, String l) {
		id = i;
		origin = o;
		link = l;
	}
	//No id
	public Source(String o, String l) {
		this(-1, o, l);
	}
	//No link
	public Source(int i, String o) {
		this(o, "");
	}
	//No id, link
	public Source(String o) {
		this(-1, o);
	}
	//Nothing
	public Source() {
		this("");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String toString() {
		return origin;
	}
}
