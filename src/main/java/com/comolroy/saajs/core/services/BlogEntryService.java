package com.comolroy.saajs.core.services;

import com.comolroy.saajs.core.entities.BlogEntry;

public interface BlogEntryService {
	//This method will return the BlogEntry or return null if not found
	public BlogEntry findBlogEntry(Long id);
	//This method will delete the BlogEntry or return null if not fount.
	public BlogEntry deleteBlogEntry(Long id);
	/*
	 * This method will update the BlogEntry having id with @param id
	 * Selected BlogEntry will be updated with @param data value
	 * @return the updated BlogEntry or null if the BlogEntry with the id cannot be found
	 */
	public BlogEntry updateBlogEntry(Long id, BlogEntry data);
}
