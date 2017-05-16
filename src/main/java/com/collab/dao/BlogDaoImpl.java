package com.collab.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collab.model.BlogPost;
import com.collab.model.User;


@Repository
public class BlogDaoImpl implements BlogDao{
@Autowired
private SessionFactory sessionFactory;
	
		
	@Transactional
	public void saveBlogPost(BlogPost blogPost)
	{
		Session session=sessionFactory.openSession();
	
	session.save(blogPost);
	session.flush();
	session.close();
	
}


	@SuppressWarnings("unchecked")
	@Transactional
public List<BlogPost> getBlogPosts() {
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from BlogPost");
			List<BlogPost> blogPosts=query.list();
			session.close();
			return blogPosts;
}


	@Transactional
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.openSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		session.close();
		return blogPost;
	}
}