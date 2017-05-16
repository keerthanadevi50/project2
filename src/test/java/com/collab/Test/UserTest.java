package com.collab.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collab.dao.UserDao;
import com.collab.model.User;

public class UserTest {
	public static void main (String[] args) {

		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.collab");
		context.refresh();
		
		User user = (User) context.getBean("user");

		UserDao userDao = (UserDao) context.getBean("UserDao");

		user.setUsername("xyz");
		user.setEmail("krishna@email.com");
		user.setPassword("xyz");
		user.setRole("ROLE_USER");
		user.setOnline(true);
		userDao.saveUser(user);

		
	}


}
