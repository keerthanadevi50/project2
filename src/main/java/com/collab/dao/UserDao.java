package com.collab.dao;

import java.util.List;

import com.collab.model.User;

public interface UserDao {
	public List list();

	User registerUser(User user);

	User getUser(int id);

	public User getbyUsername(String username);

	public User delete(int id);

	User login(User user);

	User saveUser(User user);

	User update(User user);

}
