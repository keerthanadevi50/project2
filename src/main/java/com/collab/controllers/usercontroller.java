package com.collab.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collab.dao.UserDao;
import com.collab.model.Error;
import com.collab.model.User;

@RestController
public class usercontroller {
	@Autowired
	private UserDao userDao;
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/User", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		@SuppressWarnings("rawtypes")
		List listuser = userDao.list();
		return new ResponseEntity<List<User>>(listuser,HttpStatus.OK);
	}

   @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
   public ResponseEntity delete(@PathVariable("id") int id){
   	//ONLY FOR AUTHENTICATION
          //   User user=(User)session.getAttribute("user");
           	  userDao.delete(id);
           	  return new ResponseEntity(id,HttpStatus.OK);
             }
   
  

   @RequestMapping(value="/getuser",method=RequestMethod.GET)
   public ResponseEntity<User> getUser(HttpSession session){
   	//ONLY FOR AUTHENTICATION
             User user=(User)session.getAttribute("user");
           	  user=userDao.getUser(user.getId());
           	  return new ResponseEntity<User>(user,HttpStatus.OK);
             }
   @RequestMapping(value="/getbyusername",method=RequestMethod.GET)
   public ResponseEntity<User>getbyUsername(HttpSession session){
   	//ONLY FOR AUTHENTICATION
             User user=(User)session.getAttribute("user");
           	  user=userDao.getbyUsername(user.getUsername());
           	  return new ResponseEntity<User>(user,HttpStatus.OK);
             }
   
   @RequestMapping(value="/update", method=RequestMethod.PUT)
   public ResponseEntity<User> updateUser( @RequestBody User user) {

		user = userDao.update(user);

		if (null == user) {
			return new ResponseEntity("No User found for ID " + user.getId(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
   	
   

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		// client will send only username, password, email, role
		try {

			user.setOnline(false);
			User savedUser = userDao.registerUser(user);

			return new ResponseEntity<User>(savedUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Error error = new Error(2,
					"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session) {
		User validUser = userDao.login(user);
		if (validUser == null) {
			Error error = new Error(3, "Invalid credentials.. please enter valid username and password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			session.setAttribute("user", validUser);
			
			validUser.setOnline(true);
			userDao.saveUser(validUser);
			System.out.println(validUser.getEmail());
			System.out.println(validUser.getUsername());
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		User user=(User)session.getAttribute("user");
		if(user==null){//Unauthorized user
			Error error =new Error(3,"Unauthorized user.. Please Login..");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			user.setOnline(false);
			userDao.update(user);
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}