package com.comolroy.saajs.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class BlogListResource extends ResourceSupport {
	private List<BlogResource> blogResources = new ArrayList<BlogResource>();

	public List<BlogResource> getBlogResources() {
		return blogResources;
	}

	public void setBlogResources(List<BlogResource> blogResources) {
		this.blogResources = blogResources;
	}

	
	

	
}
