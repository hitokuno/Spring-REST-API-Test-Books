package com.myComany.books.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myComany.books.domain.Book;
import com.myComany.books.service.BookService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookApiController {
	
	@Autowired
	private BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookApiController.class);
	
	@RequestMapping(value="/API/books/initialize", method = RequestMethod.GET)
	@ResponseBody
	public String initializeBooksApi() {
		bookService.initialize();
		logger.info("initialized");
		return "initialized.";
	}
	
	@RequestMapping(value="/API/books", method = RequestMethod.GET)
	@ResponseBody
	public List<Book> getBooksApi() {
		return bookService.getBooks();
	}
	
	@RequestMapping(value="/API/books/{id}", method = RequestMethod.GET, 
			produces = "application/json")
	@ResponseBody
	public Book getBookApi(final @PathVariable Integer id) {
		return bookService.getBook(id);
	}
	
	@RequestMapping(value="/API/books/", method = RequestMethod.POST, 
			produces = "application/json")
	@ResponseBody
	public Book addBookApi(final @RequestBody Book book) {
		bookService.addBook(book);
		return book;
	}
	
	@RequestMapping(value="/API/books/{id}", method = RequestMethod.DELETE, 
			produces = "application/json")
	@ResponseBody
	public Integer deleteBookApi(final @PathVariable Integer id) {
		bookService.deleteBook(id);
		return id;
	}
	
	@RequestMapping(value="/API/books/{id}", method = RequestMethod.PUT, 
			produces = "application/json")
	@ResponseBody
	public Book updateBookApi(
			final @PathVariable Integer id,
			final @RequestBody Book book) {
		bookService.updateBook(id, book);
		return book;
	}
	
}
