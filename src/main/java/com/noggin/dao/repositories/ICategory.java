package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.noggin.models.Category;

@Transactional
public interface ICategory extends JpaRepository<Category, Integer> {
	
	public Category findByName(String name);

}
