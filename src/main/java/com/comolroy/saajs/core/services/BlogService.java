package com.comolroy.saajs.core.services;

import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.services.util.BlogEntryList;
import com.comolroy.saajs.core.services.util.BlogList;

public interface BlogService {
	public Blog findBlog(Long blogId);

	public BlogList findAllBlogs();

	// Find all BlogEntry of a given blog. Will throw BlogNotFound Exception if Blog does not exist.
	public BlogEntryList findAllBlogEntries(Long blogId);

	/*
	 * @param blogId is the id of the blog to add this BlogEntry to
	 * 
	 * @param data the BlogEntry containing the data to be used for creating the
	 * new entity
	 * 
	 * @return the created BlogEntry with a generated ID
	 * 
	 * @throws BlogNotFoundException if the blog to add to cannot be found
	 */
	public BlogEntry createBlogEntry(Long blogId, BlogEntry data);
	// find a blog with given blog id
}
