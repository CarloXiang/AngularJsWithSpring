package com.comolroy.saajs.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.services.BlogService;
import com.comolroy.saajs.core.services.exceptions.BlogNotFoundException;
import com.comolroy.saajs.core.services.util.BlogEntryList;
import com.comolroy.saajs.core.services.util.BlogList;
import com.comolroy.saajs.rest.exceptions.NotFoundException;
import com.comolroy.saajs.rest.resources.BlogEntryListResource;
import com.comolroy.saajs.rest.resources.BlogEntryResource;
import com.comolroy.saajs.rest.resources.BlogListResource;
import com.comolroy.saajs.rest.resources.BlogResource;
import com.comolroy.saajs.rest.resources.asm.BlogEntryListResourceAsm;
import com.comolroy.saajs.rest.resources.asm.BlogEntryResourceAsm;
import com.comolroy.saajs.rest.resources.asm.BlogListResourceAsm;
import com.comolroy.saajs.rest.resources.asm.BlogResourceAsm;

@Controller
@RequestMapping("rest/blogs")
public class BlogController {

	private BlogService blogService;
	
	

	public BlogController() {
		super();
	}
	
	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}
	
	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
		Blog blog = blogService.findBlog(blogId);
		
		if(blog!=null){
			BlogResource blogResource = new BlogResourceAsm().toResource(blog);
			return new ResponseEntity<BlogResource>(blogResource, HttpStatus.OK);
		}else {
			return new ResponseEntity<BlogResource>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs() {
		BlogList blogList = blogService.findAllBlogs();
		BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogList);
		return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
	}

	@RequestMapping(value="/{blogId}/blog-entries")
	public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId) {
		try {
			BlogEntryList blogEntryList = blogService.findAllBlogEntries(blogId);
			BlogEntryListResource blogEntryListResource = new BlogEntryListResourceAsm().toResource(blogEntryList);
			return new ResponseEntity<BlogEntryListResource>(blogEntryListResource, HttpStatus.OK);
		} catch (BlogNotFoundException e) {
			System.out.println("No Blog Found");
			throw new NotFoundException();
		}
	}

	@RequestMapping(value = "/{blogId}/blog-entries", method= RequestMethod.POST)
	public ResponseEntity<BlogEntryResource> createBlogEntry(@PathVariable Long blogId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		try {
			BlogEntry createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
			BlogEntryResource createdBlogEntryResource= new BlogEntryResourceAsm().toResource(createdBlogEntry);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogEntryResource.getLink("self").getHref()));
			return new ResponseEntity<BlogEntryResource>(createdBlogEntryResource, headers, HttpStatus.CREATED);
		} catch (BlogNotFoundException e) {
			throw new NotFoundException();
		}
	}

	

}
