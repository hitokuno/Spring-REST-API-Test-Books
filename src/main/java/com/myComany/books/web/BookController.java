package com.myComany.books.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myComany.books.domain.Book;
import com.myComany.books.service.BookService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Access books.");
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books );
		
		return "books";
	}
	
}
