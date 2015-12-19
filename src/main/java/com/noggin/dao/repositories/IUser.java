package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noggin.models.User;

public interface IUser extends JpaRepository<User, Integer> {

}
