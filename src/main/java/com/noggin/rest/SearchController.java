package com.noggin.rest;

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
		
		List<Book> books = null;
		return books;
	}
}
