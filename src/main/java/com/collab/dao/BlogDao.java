package com.collab.dao;

import java.util.List;

import com.collab.model.BlogComment;
import com.collab.model.BlogPost;

public interface BlogDao {
	void saveBlogPost(BlogPost blogPost);

	List<BlogPost> getBlogPosts();
	List<BlogPost> approvedBlogPosts();
	List<BlogPost> notapprovedBlogPosts();
	BlogPost getBlogPost(int id);
	void addBlogComment(BlogComment blogComment);
	List<BlogComment> getBlogComments(int blogPostId);
	void update(BlogPost blogPost);
}
