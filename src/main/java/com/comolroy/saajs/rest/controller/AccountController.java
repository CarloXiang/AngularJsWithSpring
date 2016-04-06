package com.comolroy.saajs.rest.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.entities.Blog;
import com.comolroy.saajs.core.services.AccountService;
import com.comolroy.saajs.core.services.exceptions.AccountDoesNotExistException;
import com.comolroy.saajs.core.services.exceptions.AccountExistsException;
import com.comolroy.saajs.core.services.exceptions.BlogExistsException;
import com.comolroy.saajs.core.services.util.AccountList;
import com.comolroy.saajs.core.services.util.BlogList;
import com.comolroy.saajs.rest.exceptions.BadRequestException;
import com.comolroy.saajs.rest.exceptions.ConflictException;
import com.comolroy.saajs.rest.exceptions.NotFoundException;
import com.comolroy.saajs.rest.resources.AccountListResource;
import com.comolroy.saajs.rest.resources.AccountResource;
import com.comolroy.saajs.rest.resources.BlogListResource;
import com.comolroy.saajs.rest.resources.BlogResource;
import com.comolroy.saajs.rest.resources.asm.AccountListResourceAsm;
import com.comolroy.saajs.rest.resources.asm.AccountResourceAsm;
import com.comolroy.saajs.rest.resources.asm.BlogListResourceAsm;
import com.comolroy.saajs.rest.resources.asm.BlogResourceAsm;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

	public AccountController() {
		super();
	}
	
	private AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
		Account account = accountService.findAccount(accountId);
		if (account != null) {
			AccountResource accountResource = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(accountResource, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);

		}
	}

	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required=false) String name){
		AccountList list = null;
		if(name == null){
			list= accountService.findAllAccounts();
		} else {
			Account account = accountService.findByAccountName(name);
			if(account == null) {
				list = new AccountList(new ArrayList<Account>());
			}else {
				list = new AccountList(Arrays.asList(account));
			}
		}
		
		AccountListResource accountListResource = new AccountListResourceAsm().toResource(list);
		return new ResponseEntity<AccountListResource>(accountListResource, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
		try {
			Account createdAccount = accountService.createAccount(sentAccount.toAccount());
			AccountResource accountResource = new AccountResourceAsm().toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(accountResource.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(accountResource, headers, HttpStatus.CREATED);
		} catch (AccountExistsException e) {
			throw new ConflictException(e);
		}
	}
	
	@RequestMapping(value="/{accountId}/blogs", method= RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs(@PathVariable Long accountId){
		try{
			BlogList blogList = accountService.findBlogsByAccount(accountId);
			BlogListResource blogListResource= new BlogListResourceAsm().toResource(blogList);
			return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
		}catch(AccountDoesNotExistException exception){
			throw new NotFoundException(exception);
		}
	}

	@RequestMapping(value = "/{accountId}/blogs", method = RequestMethod.POST)
	public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId,
			@RequestBody BlogResource blogResource) {
		try {
			Blog createdBlog = accountService.createBlog(accountId, blogResource.toBlog());
			BlogResource createdBlogResource = new BlogResourceAsm().toResource(createdBlog);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdBlogResource.getLink("self").getHref()));
			return new ResponseEntity<BlogResource>(createdBlogResource, headers, HttpStatus.CREATED);
		} catch (AccountDoesNotExistException exception) {
			throw new BadRequestException(exception);
		} catch (BlogExistsException exception) {
			throw new ConflictException(exception);
		}
	}

}
