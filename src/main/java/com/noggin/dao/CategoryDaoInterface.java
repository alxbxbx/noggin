package com.noggin.dao;

import com.noggin.models.Category;

public interface CategoryDaoInterface {
	
	public void addCategory(Category category);
	public void removeCategory(Integer id);
	public void updateCategory(Category category);
	public Category getCategory(Integer id);

}
