package com.comolroy.saajs.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class BlogListResource extends ResourceSupport {
	private List<BlogResource> blogs = new ArrayList<BlogResource>();

	public List<BlogResource> getBlogResources() {
		return blogs;
	}

	public void setBlogResources(List<BlogResource> blogResources) {
		this.blogs = blogResources;
	}

	
	

	
}
