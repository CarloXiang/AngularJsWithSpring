package com.comolroy.saajs.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.repositories.BlogEntryRepo;
import com.comolroy.saajs.core.services.BlogEntryService;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {
	
	@Autowired
	private BlogEntryRepo blogEntryRepo;

	@Override
	public BlogEntry findBlogEntry(Long id) {
		
		return blogEntryRepo.findBlogEntry(id);
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
		return blogEntryRepo.updateBlogEntry(id, data);
	}

	@Override
	public BlogEntry deleteBlogEntry(Long id) {
		
		return blogEntryRepo.deleteBlogEntry(id);
	}

}
