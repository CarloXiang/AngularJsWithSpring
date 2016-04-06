package com.comolroy.saajs.core.repositories;

import java.util.List;

import com.comolroy.saajs.core.entities.BlogEntry;

public interface BlogEntryRepo {
	// Return the BlogEntry or null if not found.
	public BlogEntry findBlogEntry(Long id); 
	// Return all the BlogEntry of a given Blog
	public List<BlogEntry> findByBlogId(Long blogId);
	// Create a BlogEntry with given data
	public BlogEntry createBlogEntry(BlogEntry data); 
	// Update specified BlogEntry and return it or return null if BlogEntry not found.
	public BlogEntry updateBlogEntry(Long id, BlogEntry data); 
	// Delete specified BlogEntry or throw exception if can't delete.
	public BlogEntry deleteBlogEntry(Long id); 
}
