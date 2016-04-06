package com.comolroy.saajs.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.comolroy.saajs.core.entities.Blog;

public class BlogList {
	private List<Blog> blogs = new ArrayList<Blog>();
	
	public BlogList(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "BlogList [blogs=" + blogs + "]";
	}
	
	
}
