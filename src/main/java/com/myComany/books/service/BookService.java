package com.myComany.books.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.myComany.books.domain.Book;


@Service
public class BookService {
	
	private List<Book> books = new ArrayList<Book>();
	
	public BookService() {
		initialize();
	}
	
	public void initialize() {
		books.clear();
		Book book1 = new Book("Spring First Look!", "myself");
		Book book2 = new Book("Maven in Action", "not me....");
		books.add(book1);
		books.add(book2);
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public Book getBook(final Integer id) {
		return books.get(id);
	}
	
	public void addBook(final Book book) {
		books.add(book);
	}
	
	public void deleteBook(final Integer id) {
		books.remove(id);
	}
	
	public void updateBook(final Integer id, final Book book) {
		books.set(id, book);
	}
}
