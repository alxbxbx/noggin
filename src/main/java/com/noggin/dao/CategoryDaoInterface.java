package com.noggin.dao;

import java.util.List;

import com.noggin.models.Category;

public interface CategoryDaoInterface{
	
	public void addCategory(Category category);
	public void removeCategory(Integer id);
	public void updateCategory(Category category);
	public Category getCategory(Integer id);
	List<Category> getAllCategories();

}
