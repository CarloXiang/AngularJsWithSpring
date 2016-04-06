package com.comolroy.saajs.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.repositories.BlogEntryRepo;
import com.comolroy.saajs.core.repositories.BlogRepo;
import com.comolroy.saajs.core.services.BlogService;
import com.comolroy.saajs.core.services.exceptions.BlogNotFoundException;
import com.comolroy.saajs.core.services.util.BlogEntryList;
import com.comolroy.saajs.core.services.util.BlogList;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogRepo blogRepo;
	@Autowired
	private BlogEntryRepo blogEntryRepo;

	@Override
	public Blog findBlog(Long blogId) {
		
		return blogRepo.findBlog(blogId);
	}

	@Override
	public BlogList findAllBlogs() {
		
		return new BlogList(blogRepo.findAllBlogs());
	}

	@Override
	public BlogEntryList findAllBlogEntries(Long blogId) {
		Blog blog= blogRepo.findBlog(blogId);
		if(blog==null){
			throw new BlogNotFoundException();
		}
		return new BlogEntryList(blogEntryRepo.findByBlogId(blogId), blogId);
	}

	@Override
	public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
		Blog blog= blogRepo.findBlog(blogId);
		if(blog==null){
			throw new BlogNotFoundException();
		}
		BlogEntry blogEntry= blogEntryRepo.createBlogEntry(data);
		blogEntry.setBlog(blog);
		return blogEntry;
	}

}
