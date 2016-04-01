package com.comolroy.saajs.rest.resources;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class BlogEntryListResource extends ResourceSupport {
	
	private List<BlogEntryResource> entries;

	

	public List<BlogEntryResource> getEntires() {
		return entries;
	}

	public void setEntires(List<BlogEntryResource> entries) {
		this.entries = entries;
	}
	
	
}
