package com.comolroy.saajs.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comolroy.saajs.core.services.util.BlogList;
import com.comolroy.saajs.rest.controller.BlogController;
import com.comolroy.saajs.rest.resources.BlogListResource;
import com.comolroy.saajs.rest.resources.BlogResource;

public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

	public BlogListResourceAsm() {
		super(BlogController.class, BlogListResource.class);
	}

	/*
	 * Convert BlogList to BlogListResource Get each blog form blogList and
	 * pass it to BlogResourceAsm to get blogResource. The itterate fucntion
	 * of toResources fucntion return the List<BlogResource>
	 */
	@Override
	public BlogListResource toResource(BlogList blogList) {
		BlogListResource blogListResource = new BlogListResource();
		List<BlogResource> blogResources = new BlogResourceAsm().toResources(blogList.getBlogs());
		blogListResource.setBlogResources(blogResources);
		return blogListResource;
	}

}
