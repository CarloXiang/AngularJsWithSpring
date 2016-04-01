package com.comolroy.saajs.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.rest.controller.BlogController;
import com.comolroy.saajs.rest.controller.BlogEntryController;
import com.comolroy.saajs.rest.resources.BlogEntryResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResource> {
	public BlogEntryResourceAsm() {
		super(BlogEntryController.class, BlogEntryResource.class);
	}

	@Override
	public BlogEntryResource toResource(BlogEntry blogEntry) {
		BlogEntryResource blogEntryResource = new BlogEntryResource();
		blogEntryResource.setTitle(blogEntry.getTitle());

		// Link link=
		// linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel();

		// This will only work when @RequestMapping is done on class level
		Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
		blogEntryResource.add(link.withSelfRel());

		if (blogEntry.getBlog() != null) {
			blogEntryResource.add(linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog"));
		}

		return blogEntryResource;
	}

}
