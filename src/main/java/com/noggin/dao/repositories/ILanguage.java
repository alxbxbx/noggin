package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noggin.models.Language;

@Repository
public interface ILanguage extends JpaRepository<Language, Integer> {

}
