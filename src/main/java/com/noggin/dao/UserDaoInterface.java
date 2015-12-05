package com.noggin.dao;

import java.util.List;

import com.noggin.models.User;

public interface UserDaoInterface {
	
	public void addUser(User user);
	public void removeUser(Integer id);
	public void updateUser(User user);
	public User getUser(Integer id);
	public List<User> getAllUsers();
}
