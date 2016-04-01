package com.comolroy.saajs.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.entities.BlogEntry;
import com.comolroy.saajs.core.services.BlogEntryService;
import com.comolroy.saajs.rest.controller.BlogEntryController;

public class BlogEntryControllerTest {

	@InjectMocks
	private BlogEntryController controller;

	@Mock
	private BlogEntryService blogEntryService; // This will create a mock
												// BlogEntryService and will
												// inject to BlogEntryController

	private MockMvc mockMvc;

	@Before
	public void init() {
		// Initialize mock annotation for given class
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
	}

	/*
	 * @Test public void test() throws Exception{
	 * mockMvc.perform(get("/view")).andDo(print()); }
	 * 
	 * @Test public void jsonTest() throws Exception {
	 * mockMvc.perform(get("/jsonTest")).andDo(print()); }
	 * 
	 * @Test public void jsonTestPost() throws Exception {
	 * mockMvc.perform(post("/jsonTestPost")
	 * .contentType(MediaType.APPLICATION_JSON) .content(
	 * "{\"title\":\"This is blog entry title 02\"}"))
	 * .andExpect(jsonPath("$.title", is("This is blog entry title 02"))) //$
	 * sign is for root element and .title is to select title element.
	 * .andDo(print()); }
	 */

	@Test
	public void getExistingBlogEntry() throws Exception {
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setId(1L);
		blogEntry.setTitle("This is blog entry title 03");

		Blog blog = new Blog();
		blog.setId(1L);

		blogEntry.setBlog(blog);

		when(blogEntryService.findBlogEntry(1L)).thenReturn(blogEntry);

		mockMvc.perform(get("/rest/blog-entries/1")).andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
				.andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
				.andExpect(jsonPath("$.links[*].rel", hasItems("self", "blog"))).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void getNonExistingBlogEntry() throws Exception {

		when(blogEntryService.findBlogEntry(1L)).thenReturn(null);

		mockMvc.perform(get("/rest/blog-entries/1")).andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void deleteExistingBlogEntry() throws Exception {
		BlogEntry deletedBlogEntry = new BlogEntry();
		deletedBlogEntry.setId(1L);
		deletedBlogEntry.setTitle("This is blog entry title 04");

		when(blogEntryService.deleteBlogEntry(1L)).thenReturn(deletedBlogEntry);

		mockMvc.perform(delete("/rest/blog-entries/1")).andExpect(jsonPath("$.title", is(deletedBlogEntry.getTitle())))
				.andExpect(jsonPath("links[*].href", hasItem(endsWith("/rest/blog-entries/1"))))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void deleteNotExistingBlogEntry() throws Exception {

		when(blogEntryService.deleteBlogEntry(1L)).thenReturn(null);

		mockMvc.perform(delete("/rest/blog-entries/1")).andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void updateExistingBlogEntry() throws Exception {
		BlogEntry updatedBlogEntry = new BlogEntry();
		updatedBlogEntry.setId(1L);
		updatedBlogEntry.setTitle("This is blog entry title 04");

		Blog blog = new Blog();
		blog.setId(1L);

		updatedBlogEntry.setBlog(blog);

		when(blogEntryService.updateBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(updatedBlogEntry);

		mockMvc.perform(put("/rest/blog-entries/1").content("{\"title\":\"Generic Title\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is(updatedBlogEntry.getTitle())))
				.andExpect(jsonPath("links[*].href", hasItem(endsWith("/rest/blog-entries/1"))))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void updateNotExistingBlogEntry() throws Exception {
		when(blogEntryService.updateBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(null);

		mockMvc.perform(put("/rest/blog-entries/1").content("{\"title\":\"Generic Title\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound());
	}

}
