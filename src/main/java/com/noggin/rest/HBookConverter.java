package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.dao.repositories.IBook;
import com.noggin.lucene.model.ResultData;
import com.noggin.models.Book;
import com.noggin.models.HighlightBook;

@RestController
public class HBookConverter {
	
	@Autowired
	private IBook ib;
	
	public List<HighlightBook> convert(List<ResultData> results){
		List<HighlightBook> hbooks = new ArrayList<HighlightBook>();
		List<Book> books = ib.findAll();
		for(ResultData r : results){
			Book book = new Book();
			System.out.println("THIS IS TITLE: "+ r.getTitle());
			System.out.println("HIGHLIGHT IS: " + r.getHighlight());
			System.out.println("AUTHOR IS: " + r.getAuthor());
			System.out.println("KEYWORDS ARE: " + r.getKeywords());
			System.out.println("FILENAME IS: " + r.getLocation());
			for(Book b : books){
				System.out.println("Book: "+b.getFilename() + " location: " + r.getLocation());
				if(b.getFilename().equals(r.getLocation()))
					book = b;
			}
			HighlightBook hbook = (HighlightBook) book;
			try{
				hbook.setHighlight(r.getHighlight());
			}catch(NullPointerException e){
				System.out.println("There is no highlight.");
			}
			hbooks.add(hbook);
			
		}
		
		return hbooks;
	}

}
