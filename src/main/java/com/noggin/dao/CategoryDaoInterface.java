package com.noggin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noggin.models.Category;

public interface CategoryDaoInterface extends JpaRepository<Category, Integer>{
	
	public void addCategory(Category category);
	public void removeCategory(Integer id);
	public void updateCategory(Category category);
	public Category getCategory(Integer id);
	List<Category> getAllCategories();

}
