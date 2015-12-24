package com.noggin.rest;

import com.noggin.dao.repositories.ICategory;
import com.noggin.models.Category;

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
public class CategoryController {
	
	@Autowired
	private ICategory ic;

    @RequestMapping(value = "/category", method = RequestMethod.GET, produces = "application/json")
    public List<Category> getAll() {
        List<Category> list = ic.findAll();
       
        return list;
    }
    @RequestMapping(value= "/category/{id}", method = RequestMethod.PUT, consumes = "application/json")
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
    	ic.save(cat);
    	return new ResponseEntity<Category>(cat,HttpStatus.OK);
    }

}
