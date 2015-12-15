package com.noggin.rest;

import com.noggin.dao.config.PersistenceConfig;
import com.noggin.dao.repositories.ICategory;
import com.noggin.models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

}
