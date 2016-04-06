package com.comolroy.saajs.controller;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.services.BlogService;
import com.comolroy.saajs.core.services.exceptions.BlogNotFoundException;
import com.comolroy.saajs.core.services.util.BlogEntryList;
import com.comolroy.saajs.core.services.util.BlogList;
import com.comolroy.saajs.rest.controller.BlogController;

public class BlogControllerTest {

	@InjectMocks
	private BlogController controller;

	@Mock
	private BlogService service;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void findAllBlogs() throws Exception {
		List<Blog> list = new ArrayList<Blog>();

		Blog blogA = new Blog();
		blogA.setId(1L);
		blogA.setTitle("Blog Title 01");
		list.add(blogA);

		Blog blogB = new Blog();
		blogB.setId(2L);
		blogB.setTitle("Blog Title 02");
		list.add(blogB);

		BlogList blogList = new BlogList(list);

		when(service.findAllBlogs()).thenReturn(blogList);

		mockMvc.perform(get("/rest/blogs")).andDo(print())
				// blogResources is the private variable of BlogList Resource
				// item.
				.andExpect(jsonPath("$.blogResources[*].title",
						hasItems(endsWith("Blog Title 01"), endsWith("Blog Title 02"))))
				.andExpect(status().isOk());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void getExistingBlog() throws Exception {
		Blog blog = new Blog();
		blog.setTitle("Blog Title 03");
		blog.setId(1L);

		Account onwer = new Account();
		onwer.setId(1L);

		blog.setOwner(onwer);

		when(service.findBlog(1L)).thenReturn(blog);

		mockMvc.perform(get("/rest/blogs/1")).andDo(print())
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1"))))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1/entries"))))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/accounts/1"))))
				.andExpect(jsonPath("$.links[*].rel", hasItems("entries", "owner", "self")))
				.andExpect(jsonPath("$.title", is("Blog Title 03"))).andExpect(status().isOk());
	}

	@Test
	public void getNotExistingBlog() throws Exception {
		when(service.findBlog(1L)).thenReturn(null);

		mockMvc.perform(get("/rest/blogs/1")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void createBlogEntryExistingBlog() throws Exception {
		Blog blog = new Blog();
		blog.setId(1L);
		blog.setTitle("Blog Title 04");

		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setId(1L);
		blogEntry.setTitle("BlogEntry Title 04");
		blogEntry.setBlog(blog);

		when(service.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(blogEntry);

		mockMvc.perform(post("/rest/blogs/1/blog-entries")
				// The title can be generic, because above when method alwasy
				// return the blogEntry
				.content("{\"title\":\"Generic Title\"}").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blog-entries/1"))))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blogs/1"))))
				.andExpect(header().string("Location", endsWith("/rest/blog-entries/1")))
				.andExpect(status().isCreated()).andDo(print());
	}

	@Test
	public void createBlogEntryNotExistingBlog() throws Exception {
		when(service.createBlogEntry(eq(1L), any(BlogEntry.class))).thenThrow(new BlogNotFoundException());

		mockMvc.perform(post("/rest/blogs/1/blogs-entries").content("{\"title\":\"Generic Title\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void listBlogEntriesForExistingBlog() throws Exception {
		BlogEntry entryA = new BlogEntry();
		entryA.setId(1L);
		entryA.setTitle("BlogEntry Title 05");

		BlogEntry entryB = new BlogEntry();
		entryB.setId(2L);
		entryB.setTitle("BlogEntry Title 06");

		List<BlogEntry> list = new ArrayList<BlogEntry>();
		list.add(entryA);
		list.add(entryB);

		BlogEntryList blogEntryList = new BlogEntryList(list,1L);

		when(service.findAllBlogEntries(1L)).thenReturn(blogEntryList);

		mockMvc.perform(get("/rest/blogs/1/blog-entries")).andDo(print())
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1/blog-entries"))))
				.andExpect(jsonPath("$.links[*].rel", hasItem("all")))
				.andExpect(jsonPath("$.entires[*].title", hasItems("BlogEntry Title 06", "BlogEntry Title 05")))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void listBlogEntriesForNotExistingBlog() throws Exception {
		when(service.findAllBlogEntries(1L)).thenThrow(new BlogNotFoundException());

		mockMvc.perform(get("/rest/blogs/1/blog-entries")).andDo(print()).andExpect(status().isNotFound());
	}

}
