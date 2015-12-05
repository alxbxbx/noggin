package com.noggin.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.noggin.models.EBook;

@Controller
public class SearchController {

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
	public List<EBook> search(String title, String author, String keywords, 
			String content, String language) {
		
		List<EBook> books = null;
		return books;
	}
}
