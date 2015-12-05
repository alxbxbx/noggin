package com.noggin.dao;

import com.noggin.models.User;

public interface UserDaoInterface {
	
	public void addUser(User user);
	public void removeUser(Integer id);
	public void updateUser(User user);
	public User getUser(Integer id);
}
