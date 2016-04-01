package com.comolroy.saajs.core.services;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;

public interface AccountService {
	public Account findAccount(Long id);
	public Account createAccount(Account data);
	/*
	 * @param accountId is the user account where the new Blog will create
	 * @data is the information to create the Blog
	 * If the blog already exist, it will throw BlogExistsException
	 */
	public Blog createBlog(Long accountId, Blog data);
}
