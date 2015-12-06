package com.myComany.books.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {
	
	private String title = "";
	
	private List<String> authors = new ArrayList<String>();
	
	public Book() {
	}
	
	public Book(final String title, final String author) {
		setTitle(title);
		authors.add(author);
		setAuthors(authors);
	}
	
	public Book(final String title, final List<String> authors) {
		setTitle(title);
		setAuthors(authors);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	
	public void setAuthors(final List<String> authors) {
		this.authors = authors;
	}
	
	@Override
	public String toString() {
		String ret = "title:"+title+", author:[";
		for (String author : authors ) {
			ret = ret + author + ", ";
		}
		ret = ret.replaceFirst("(, )$", "]");
		return ret;
	}

}
