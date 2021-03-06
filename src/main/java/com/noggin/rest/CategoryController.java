package com.noggin.rest;

import com.noggin.dao.repositories.IBook;
import com.noggin.dao.repositories.ICategory;
import com.noggin.dao.repositories.IUser;
import com.noggin.models.Book;
import com.noggin.models.Category;
import com.noggin.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private ICategory ic;
	
	@Autowired
	private IUser iu;
	
	@Autowired
	private IBook ib;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAll() {
        List<Category> list = ic.findAll();
        return list;
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Category> get(@PathVariable String id){
    	Integer intId = null;
    	Category cat = null;
    	try{
    		intId = Integer.parseInt(id);
    		cat = ic.findOne(intId);
    	}catch (Exception e){
    		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
    	}
    	return new ResponseEntity<Category>(cat, HttpStatus.OK);
    }
    @RequestMapping(value= "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category category){
    	Category cat = new Category();
    	Integer intId = null;
    	try{
    		intId = Integer.parseInt(id);
    		cat = ic.getOne(intId);
    	}catch (Exception e){
    		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
    	}
    	cat.setName(category.getName());
    	return new ResponseEntity<Category>(ic.save(cat), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Category> add(@RequestBody Category category){
    	if(category.getName() == null)
    		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
    	return new ResponseEntity<Category>(ic.save(category), HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> delete(@PathVariable String id){
    	Integer intId = null;
    	try{
    		intId = Integer.parseInt(id);
    	}catch (Exception e){
    		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
    	}
    	Category cat = ic.findAll().get(0);
    	if(cat.getId().equals(intId))
    		cat = ic.findAll().get(1);
    	List<User> users = iu.findAll();
    	for(User u : users){
    		try{
    			if(u.getCategory().getId().equals(intId)){
        			u.setCategory(cat);
        			iu.save(u);
        		}
    		}catch(Exception e){	
    		}	
    	}
    	List<Book> books = ib.findAll();
    	for(Book b : books){
    		if(b.getCategory().getId().equals(intId)){
    			b.setCategory(cat);
    			ib.save(b);
    		}
    	}
    	ic.delete(intId);
    	return new ResponseEntity<Category>(HttpStatus.OK);
    }

}
