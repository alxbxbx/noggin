package com.noggin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.noggin.dao.repositories.CategoryRepository;
import com.noggin.models.Category;
import com.noggin.spring.PersistenceConfig;

@ContextConfiguration(classes = {PersistenceConfig.class})
public class CategoryTest {
	
	@Autowired
	private static CategoryRepository cr;
	
	public static void main(String[] args){
		Category cat = cr.getOne(3);
		System.out.println(cat.getName());
		
	}
	
}
