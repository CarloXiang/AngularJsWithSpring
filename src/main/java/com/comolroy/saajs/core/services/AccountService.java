package com.comolroy.saajs.core.services;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.services.util.AccountList;
import com.comolroy.saajs.core.services.util.BlogList;

public interface AccountService {
	public Account findAccount(Long id);

	public AccountList findAllAccounts();

	public Account findByAccountName(String name);
	/*
	 * Will throw AccountExixt exception if account exist.
	 */
	public Account createAccount(Account data);

	/*
	 * Find all the blogs of given account. Will throw AccountDoesNotFound exception
	 * if account not found.
	 */
	public BlogList findBlogsByAccount(Long accountId);

	/*
	 * @param accountId is the user account where the new Blog will create
	 * 
	 * @data is the information to create the Blog If the blog already exist, it
	 * will throw BlogExistsException and AccountDoesNotFound Exception.
	 */
	public Blog createBlog(Long accountId, Blog data);
}
