package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noggin.models.Book;

@Repository
public interface IBook extends JpaRepository<Book, Integer> {

}
