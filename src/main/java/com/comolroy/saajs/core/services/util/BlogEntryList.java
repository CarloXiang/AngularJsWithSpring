package com.comolroy.saajs.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.comolroy.saajs.core.entities.BlogEntry;

public class BlogEntryList {
	private List<BlogEntry> entries = new ArrayList<BlogEntry>();
	private Long blogId;
	
	

	public BlogEntryList(List<BlogEntry> entries, Long blogId) {
		this.entries = entries;
		this.blogId = blogId;
	}

	public List<BlogEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "BlogEntryList [entries=" + entries + ", blogId=" + blogId + "]";
	}

	
}
