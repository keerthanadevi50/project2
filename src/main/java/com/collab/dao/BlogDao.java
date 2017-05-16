package com.collab.dao;

import java.util.List;

import com.collab.model.BlogPost;

public interface BlogDao {
	void saveBlogPost(BlogPost blogPost);

	List<BlogPost> getBlogPosts();

	BlogPost getBlogPost(int id);
}
