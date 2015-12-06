package com.myComany.books.web;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

//import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.myComany.books.service.BookService;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BookControllerMockTest {
	private static final Logger logger = LoggerFactory.getLogger(BookControllerMockTest.class);
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Autowired
    private BookService bookService;
    
	@Before
	public void initialize() {
		// MockMvcBuilders.webAppContextSetup
		mockMvc = webAppContextSetup(wac).build();
		
		bookService.initialize();
		logger.info("initialized");
	}
	
	@Test
	public void getBooksApi() throws Exception {
		// Given
		String url = "/API/books";
		logger.info(url);
		// When
		// Then
		mockMvc.perform(get(url))  // MockMvcRequestBuilders.get
			.andExpect(status().isOk())  // MockMvcResultMatchers.status
			.andExpect(content().contentType("application/json;charset=UTF-8"));
			/*
			 *  JsonPath 2.1.0 does not work. 
				.andExpect(jsonPath("$").isArray())  // MockMvcResultMatchers.jsonPath
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title").value("Spring First Look!"))
				.andExpect(jsonPath("$[1].title").value("Maven in Action"))
				.andExpect(jsonPath("$[1].authors[0]").value("not me...."));
			 */
	}
	
	/*
	 */

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
