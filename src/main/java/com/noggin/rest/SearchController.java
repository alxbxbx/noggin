package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.models.Book;

@RestController
public class SearchController {

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	public List<Book> search(String title, String author, String keywords, 
			String content, String language) {

		// Test Data
		Book book1 = new Book();
		book1.setId(1);
		book1.setAuthor("Filip Bekic");
		book1.setKeywords("omg yes god wtf what are these");
		book1.setPublicationYear(2011);
		book1.setTitle("Ivica Dacic ubija AZDAHU");
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setAuthor("Milorad Dragutinovic");
		book2.setKeywords("omg yes god wtf what are these");
		book2.setPublicationYear(2001);
		book2.setTitle("Cigani igraju kolo");
		
		List<Book> books = new ArrayList<Book>();
		
		books.add(book1);
		books.add(book2);

		return books;
	}
}
