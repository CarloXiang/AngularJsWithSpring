package com.comolroy.saajs.controller;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.services.AccountService;
import com.comolroy.saajs.core.services.exceptions.AccountDoesNotExistException;
import com.comolroy.saajs.core.services.exceptions.AccountExistsException;
import com.comolroy.saajs.core.services.exceptions.BlogExistsException;
import com.comolroy.saajs.rest.controller.AccountController;

public class AccountControllerTest {

	@InjectMocks
	private AccountController controller;

	@Mock
	private AccountService service;

	private MockMvc mockMvc;

	/*
	 * AccountCaptor captor the instance of call pass to any method.
	 */
	private ArgumentCaptor<Account> accountCaptor;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
		accountCaptor = ArgumentCaptor.forClass(Account.class);
	}

	@Test
	public void createBlogExistingAccount() throws Exception {
		Blog createdBlog = new Blog();
		createdBlog.setId(1L);
		createdBlog.setTitle("Blog Title 01");

		when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(createdBlog);

		mockMvc.perform(post("/rest/accounts/1/blogs").content("{\"title\":\"Blog Title 01\"}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(jsonPath("$.title", is("Blog Title 01")))
				.andExpect(header().string("Location", endsWith("/blogs/1"))).andExpect(status().isCreated());
	}

	@Test
	public void createBlogNotExistingAccount() throws Exception {
		when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());

		mockMvc.perform(post("/rest/accounts/1/blogs").content("{\"title\":\"Blog Title 02\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void createBlogExistingBlogName() throws Exception {
		when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

		mockMvc.perform(post("/rest/accounts/1/blogs").content("{\"title\":\"Blog Title 03\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict()).andDo(print());
	}

	@Test
	public void createAccountNonExistingUsername() throws Exception {
		Account createdAccount = new Account();
		createdAccount.setId(1L);
		createdAccount.setName("test");
		createdAccount.setPassword("test");

		when(service.createAccount(any(Account.class))).thenReturn(createdAccount);

		mockMvc.perform(post("/rest/accounts").content("{\"name\":\"test\",\"password\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(header().string("Location", endsWith("/rest/accounts/1")))
				.andExpect(jsonPath("$.name", is(createdAccount.getName()))).andExpect(status().isCreated())
				.andDo(print());

		/*
		 * Capture the instance of account class pass to service.createAccount method.
		 */
		verify(service).createAccount(accountCaptor.capture());

		String password = accountCaptor.getValue().getPassword();
		assertEquals("test", password);
	}

	@Test
	public void createAccountExistingUsername() throws Exception {
		when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());

		mockMvc.perform(post("/rest/accounts").content("{\"name\":\"test\",\"password\":\"test\"}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isConflict()).andDo(print());
	}

	@Test
	public void getExistingAccount() throws Exception {
		Account foundAccount = new Account();
		foundAccount.setId(1L);
		foundAccount.setName("test");
		foundAccount.setPassword("test");

		when(service.findAccount(1L)).thenReturn(foundAccount);

		mockMvc.perform(get("/rest/accounts/1")).andDo(print()).andExpect(jsonPath("$.password", isEmptyOrNullString()))
				.andExpect(jsonPath("$.name", is(foundAccount.getName()))).andExpect(status().isOk());

	}

	@Test
	public void getNotExistingAccount() throws Exception {
		when(service.findAccount(1L)).thenReturn(null);

		mockMvc.perform(get("/rest/accounts/1")).andDo(print()).andExpect(status().isNotFound());
	}

}
