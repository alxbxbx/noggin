package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noggin.models.Book;


public interface IBook extends JpaRepository<Book, Integer> {

}
