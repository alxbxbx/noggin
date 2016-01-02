package com.noggin.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noggin.models.Book;
import com.noggin.models.Category;
import com.noggin.models.Language;

@Repository
public interface IBook extends JpaRepository<Book, Integer> {
	
	List<Book> findByAuthor (String author);
	List<Book> findByTitle (String title);
	List<Book> findByKeywords (String keywords);
	List<Book> findByLanguage (Language language);
	List<Book> findByCategory (Category category);
	Book findByFilename (String filename);
}
