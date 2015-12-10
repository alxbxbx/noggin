package com.noggin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import com.noggin.dao.repositories.ICategory;
import com.noggin.models.Category;

public class Test {
	
	@Autowired
	private ICategory ic;
	
	public static void main(String[] args){
		/*
		ApplicationContext context = new AnnotationConfigApplicationContext(
	            PersistenceConfig.class);
		Test t = (Test) context.getBean("Test");
		t.doSomething();
		*/
		
	}
	
	public void doSomething(){
		Category cate = new Category();
		cate.setName("NEWCAT");
		ic.saveAndFlush(cate);
	}
	
	
}
