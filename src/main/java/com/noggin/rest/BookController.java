package com.noggin.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.noggin.dao.repositories.IBook;
import com.noggin.models.Book;

@RestController
@RequestMapping(value = "book")
public class BookController {
	
	@Autowired
	private IBook ib;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAll(){
		List<Book> books = new ArrayList<Book>();
		books = ib.findAll();
		
		return books;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Book> get(@PathVariable String id){
		Integer intId = null;
		Book book = null;
		try{
			intId = Integer.parseInt(id);
			book = ib.findOne(intId);
		}catch (Exception e){
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
		
	}
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Book> returnKeywords(){
		Book book = new Book();
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
	public ResponseEntity<Book> book(@PathVariable String id){
		Integer intId = null;
		try{
			intId = Integer.parseInt(id);
			ib.delete(intId);
		}catch (Exception e){
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Book>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Book> book(@PathVariable String id, @RequestBody Book book){
		Book b = new Book();
		Integer intId = null;
		try{
			intId = Integer.parseInt(id);
			b = ib.findOne(intId);
		}catch (Exception e){
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		b.setAuthor(book.getAuthor());
		b.setCategory(book.getCategory());
		b.setKeywords(book.getKeywords());
		b.setLanguage(book.getLanguage());
		b.setPublicationYear(book.getPublicationYear());
		b.setTitle(book.getTitle());
		return new ResponseEntity<Book>(ib.save(b), HttpStatus.OK);
	}
	
	
	
	

}
