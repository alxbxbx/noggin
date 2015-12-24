package com.noggin.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noggin.models.User;

@Repository
public interface IUser extends JpaRepository<User, Integer> {

}
