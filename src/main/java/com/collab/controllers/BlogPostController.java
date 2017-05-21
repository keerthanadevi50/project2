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

import com.collab.dao.BlogDao;
import com.collab.dao.JobDao;
import com.collab.model.BlogPost;
import com.collab.model.User;
import com.collab.model.Error;

@RestController
public class BlogPostController {
	@Autowired
	private BlogDao blogDao;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ResponseEntity<?> getBlogList(HttpSession session) {
		/*User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthroized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}*/
		List<BlogPost> blogPosts = blogDao.getBlogPosts();
		return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
	}
	@RequestMapping(value = "/saveBlogPost", method = RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			blogPost.setCreatedBy(user);
			 blogDao.saveBlogPost(blogPost);

			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Error error = new Error(2,
					"Couldnt insert user details. Cannot have null/duplicate values " + e.getMessage());
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPost(@PathVariable(value = "id") int id, HttpSession session) {
	/*	User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthroized user");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}*/
		// select * from blogpost where id=33
		BlogPost blogPost = blogDao.getBlogPost(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);
	}

}
