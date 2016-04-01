package com.comolroy.saajs.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comolroy.saajs.core.services.util.BlogEntryList;
import com.comolroy.saajs.rest.controller.BlogController;
import com.comolroy.saajs.rest.resources.BlogEntryListResource;
import com.comolroy.saajs.rest.resources.BlogEntryResource;

public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {

	
	/*
	 * Get all entry of a blog, this function is called form blog controller
	 * Thus here BlogController.class is used
	 */
	public BlogEntryListResourceAsm() {
		super(BlogController.class, BlogEntryListResource.class);
	}

	/*
	 *Convert the BlogEntryList to BlogEntryListResource
	 *After getting each BlogEntry from BlogEntryList, pass it to BlogEntryResourceAsm to get BlogEntryResource
	 *The iterate function convert to List<BlogEntryResource> and return it. 
	 */
	@Override
	public BlogEntryListResource toResource(BlogEntryList list) {
		List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(list.getEntries());
		BlogEntryListResource listResource = new BlogEntryListResource();
		listResource.setEntires(resources);
		
		listResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
		// Following list is to get the link for all blogs
		listResource.add(linkTo(methodOn(BlogController.class).findAllBlogs()).withRel("all"));
		return listResource;
	}
	
}
