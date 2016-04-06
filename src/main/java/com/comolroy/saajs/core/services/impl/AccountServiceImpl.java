package com.comolroy.saajs.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.repositories.AccountRepo;
import com.comolroy.saajs.core.repositories.BlogRepo;
import com.comolroy.saajs.core.services.AccountService;
import com.comolroy.saajs.core.services.exceptions.AccountDoesNotExistException;
import com.comolroy.saajs.core.services.exceptions.AccountExistsException;
import com.comolroy.saajs.core.services.exceptions.BlogExistsException;
import com.comolroy.saajs.core.services.util.AccountList;
import com.comolroy.saajs.core.services.util.BlogList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private BlogRepo blogRepo;

	@Override
	public Account findAccount(Long id) {
		return accountRepo.findAccount(id);
	}

	@Override
	public AccountList findAllAccounts() {
		AccountList accountList = new AccountList(accountRepo.findAllAccount());
		return accountList;
	}

	@Override
	public Account findByAccountName(String name) {

		return accountRepo.findAccountByName(name);
	}

	@Override
	public Account createAccount(Account data) {
		Account account = accountRepo.findAccountByName(data.getName());
		if (account != null) {
			throw new AccountExistsException();
		}
		return accountRepo.createAccount(data);
	}

	@Override
	public BlogList findBlogsByAccount(Long accountId) {
		Account account = accountRepo.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}
		return new BlogList(blogRepo.findBlogsByAccount(accountId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.comolroy.saajs.core.services.AccountService#createBlog(java.lang.
	 * Long, com.comolroy.saajs.core.entities.Blog) All the method is
	 * transactional. Thus when createBlog.setOnwer(account) is set, it will be
	 * persist on database.
	 */
	@Override
	@Transactional
	public Blog createBlog(Long accountId, Blog data) {
		Blog sameBlogTitle = blogRepo.findBlogByTitle(data.getTitle());
		if (sameBlogTitle != null) {
			new BlogExistsException();
		}

		Account account = accountRepo.findAccount(accountId);
		if (account == null) {
			throw new AccountDoesNotExistException();
		}

		Blog createBlog = blogRepo.createBlog(data);

		createBlog.setOwner(account);

		return createBlog;
	}

}
