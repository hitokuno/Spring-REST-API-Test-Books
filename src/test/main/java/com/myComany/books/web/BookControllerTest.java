package com.myComany.books.web;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import com.myComany.books.domain.Book;
import com.myComany.books.service.BookService;

import net.minidev.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BookControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BookControllerTest.class);
	
	private static final String baseUrl = "http://localhost:8080/Books/";
	
	private RestTemplate restTemplate = new RestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	private HttpEntity<String> entity;

    @Autowired
    private BookService bookService;

	@Before
	public void initialize() {
		restTemplate = new RestTemplate();
		bookService.initialize();
		logger.info("initialized");
	}
	
	@Test
	public void getBooksApi() throws IOException {
		// Given
		restTemplate = new RestTemplate();
		String url = baseUrl+"API/books";
	    entity = new HttpEntity<String>("parameters", headers);
		// When
		logger.info(url);
		ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
		// Then
		assertEquals("", HttpStatus.OK, response.getStatusCode());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(response.getBody());
		
		// Requires Book objects 
		assertEquals("Book: Number of Books", 2, JsonPath.parse(json).read("$.length()", Book.class));
		assertEquals("Book: Title of 1st book", "Spring First Look!", JsonPath.parse(json).read("$[0].title", Book.class));
		assertEquals("Book: Title of 2nd book", "Maven in Action", JsonPath.parse(json).read("$[1].title", Book.class));
		assertEquals("Book: 1st Author of 2nd book",  "not me....", JsonPath.parse(json).read("$[1].authors[0]", Book.class));
		
		// No specific object required
		Object books = Configuration.defaultConfiguration().jsonProvider().parse(json);
		JSONArray expect = new JSONArray();
		expect.add("Spring First Look!");
		expect.add("Maven in Action");
		assertEquals("Titles", expect, JsonPath.read(books, "$..title"));
		assertEquals("Number of Books", 2, JsonPath.read(books, "$.length()"));
		assertEquals("Title of 1st book", "Spring First Look!", JsonPath.read(books, "$[0].title"));
		assertEquals("Title of 2nd book", "Maven in Action", JsonPath.read(books, "$[1].title"));
		assertEquals("1st Author of 2nd book",  "not me....", JsonPath.read(books, "$[1].authors[0]"));
	}
	

	/*
	@RequestMapping(value="/API/books/{title}", method = RequestMethod.GET, 
			produces = "application/json")
	@ResponseBody
	public Book getBookApi(final @PathVariable String title) {
		return bookService.getBook(title);
	}
	
	@RequestMapping(value="/API/books/", method = RequestMethod.POST, 
			produces = "application/json")
	@ResponseBody
	public Book addBookApi(final @RequestBody Book book) {
		bookService.addBook(book);
		return book;
	}
	
	@RequestMapping(value="/API/books/{title}", method = RequestMethod.DELETE, 
			produces = "application/json")
	@ResponseBody
	public String deleteBookApi(final @PathVariable String title) {
		bookService.deleteBook(title);
		return title;
	}
	
	@RequestMapping(value="/API/books/", method = RequestMethod.PUT, 
			produces = "application/json")
	@ResponseBody
	public Book updateBookApi(final @RequestBody Book book) {
		bookService.updateBook(book);
		return book;
	}
	*/
	
}
