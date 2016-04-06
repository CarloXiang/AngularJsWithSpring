package com.comolroy.saajs.core.repositories;

import java.util.List;

import com.comolroy.saajs.core.entities.Blog;

public interface BlogRepo {
	// Find Blog with given blogId return the Blog or null it can't find.
	public Blog findBlog(Long id);
	public List<Blog> findAllBlogs();
	public Blog findBlogByTitle(String title);
	// Return a List of Blog of given account. Or return null if it can't find.
	public List<Blog> findBlogsByAccount(Long accountId);
	// Find Blog with given blogTitle return the Blog or null it can't find.
	// Create a Blog within given account and return the Blog. Or throw exception if if can not.
	public Blog createBlog(Blog data);
	// Return a list of all the Blog of the system.
}
