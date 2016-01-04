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
import com.noggin.dao.repositories.IUser;
import com.noggin.models.Book;
import com.noggin.models.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUser iu;
	
	@Autowired
	private IBook ib;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<User> getAll() {
		List<User> list = new ArrayList<User>();
		list = iu.findAll();
		return list;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<User> get(@PathVariable String id) {
		Integer intId = null;
		User user = null;
		try{
			intId = Integer.parseInt(id);
			user = iu.getOne(intId);
		}catch (Exception e){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<User> getAll(@RequestBody User user) {
		if (user.getFirstName() == null || user.getLastName() == null || user.getPassword() == null
				|| user.getUsername() == null || user.getType() == null){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<User>(iu.save(user), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<User> update(@RequestBody User user){
    	User u = new User();
    	try{
    		u = iu.getOne(user.getId());
    	}catch (Exception e){
    		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    	}
    	u.setCategory(user.getCategory());
    	u.setFirstName(user.getFirstName());
    	u.setPassword(user.getPassword());
    	u.setUsername(user.getUsername());
    	u.setLastName(user.getLastName());
    	u.setType(user.getType());
    	return new ResponseEntity<User>(iu.save(u),HttpStatus.OK);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable String id){
		Integer intId = null;
		try{
			intId = Integer.parseInt(id);
		}catch (Exception e){
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		User u = iu.findAll().get(0);
		if(u.getId().equals(intId))
			u = iu.findAll().get(1);
		List<Book> books = ib.findAll();
		for(Book b : books){
			if(b.getUser().getId().equals(intId)){
				b.setUser(u);
				ib.save(b);
			}
		}
		iu.delete(intId);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

}
