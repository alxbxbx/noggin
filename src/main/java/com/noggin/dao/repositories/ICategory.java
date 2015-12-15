package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noggin.models.Category;

@Repository
public interface ICategory extends JpaRepository<Category, Integer> {
	
	Category findByName(String name);
}
