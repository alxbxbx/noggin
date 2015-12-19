package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noggin.models.Language;

public interface ILanguage extends JpaRepository<Language, Integer> {

}
