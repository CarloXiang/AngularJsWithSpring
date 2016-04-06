package com.comolroy.saajs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.services.BlogEntryService;
import com.comolroy.saajs.rest.resources.BlogEntryResource;
import com.comolroy.saajs.rest.resources.asm.BlogEntryResourceAsm;

@Controller
@RequestMapping(value = "/rest/blog-entries")
public class BlogEntryController {

	private BlogEntryService blogEntryService;

	public BlogEntryController() {
		super();
	}
	
	@Autowired
	public BlogEntryController(BlogEntryService blogEntryService) {
		this.blogEntryService = blogEntryService;
	}

	/*
	 * Following part is used for JUnit Test
	 */

	@RequestMapping("/view")
	public String showView() {
		return "view";
	}

	@RequestMapping("/jsonTest")
	public ResponseEntity<Object> jsonTest() {
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setTitle("This is blog entry title 01");
		return new ResponseEntity<Object>(blogEntry, HttpStatus.OK);
	}

	@RequestMapping(value = "/jsonTestPost", method = RequestMethod.POST)
	public @ResponseBody BlogEntry jsonTestPost(@RequestBody BlogEntry blogEntry) {
		return blogEntry;
	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.GET)
	public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable("blogEntryId") Long blogEntryId) {

		BlogEntry blogEntry = blogEntryService.findBlogEntry(blogEntryId);
		if (blogEntry != null) {
			BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
			return new ResponseEntity<BlogEntryResource>(blogEntryResource, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.PUT)
	public ResponseEntity<BlogEntryResource> updateBlogEntry(@PathVariable("blogEntryId") Long blogEntryId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		BlogEntry updatedEntry = blogEntryService.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
		if (updatedEntry != null) {
			BlogEntryResource updatedBlogEntryResource = new BlogEntryResourceAsm().toResource(updatedEntry);
			return new ResponseEntity<BlogEntryResource>(updatedBlogEntryResource, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogEntryResource> deleteBlogEntry(@PathVariable Long blogEntryId) {
		BlogEntry blogEntry = blogEntryService.deleteBlogEntry(blogEntryId);
		if (blogEntry != null) {
			BlogEntryResource blogEntryResource = new BlogEntryResourceAsm().toResource(blogEntry);
			return new ResponseEntity<BlogEntryResource>(blogEntryResource, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}

}
