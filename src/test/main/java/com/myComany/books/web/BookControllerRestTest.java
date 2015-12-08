package com.myComany.books.web;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
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

import com.jayway.jsonpath.JsonPath;
import com.myComany.books.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BookControllerRestTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BookControllerRestTest.class);
	
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
		@SuppressWarnings("rawtypes")
		ResponseEntity<ArrayList> response = restTemplate.exchange(url, HttpMethod.GET, entity, ArrayList.class);
		// Then
		Assert.assertEquals("", HttpStatus.OK, response.getStatusCode());
		@SuppressWarnings("rawtypes")
		ArrayList body = response.getBody();
		Assert.assertEquals("Number of Books", 2, body.size());
		Assert.assertEquals("Title of 1st book", "Spring First Look!", JsonPath.read(body, "$[0].title"));
		Assert.assertEquals("Title of 2nd book", "Maven in Action", JsonPath.read(body, "$[1].title"));
		Assert.assertEquals("1st Author of 2nd book",  "not me....", JsonPath.read(body, "$[1].authors[0]"));
		assertLength("Number of Books", body, "$", (long) 2);
		assertLength("Number of Author", body, "$[1].authors", (long) 1);
		/* length is not available in 0.8.1.
		*  assertEquals("Number of Books", 2, JsonPath.read(body, "$.length()"));
		* 
		*  assertEquals("Number of Books",  hasSize(2), JsonPath.read(body, "$"));		
		*  assertEquals("Number of Books",  Matchers.hasSize(2), JsonPath.read(body, "$"));
		*  assertEquals("Number of Books",  Matchers.arrayWithSize(2), JsonPath.read(body, "$"));
		*/
	}
	
	/**
	 * Assert legnth of JSON object specified by jsonPath from body.
	 * @param message
	 * @param body
	 * @param jsonPath
	 * @param index
	 */
	public void assertLength(
			final String message, final Object body, 
			final String jsonPath, final Long length
			) {
		ArrayList<Object> arrayList = JsonPath.read(body, jsonPath);
		Assert.assertEquals(message, length, new Long(arrayList.size()));
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
