package com.comolroy.saajs.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.rest.controller.AccountController;
import com.comolroy.saajs.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

	
	
	public AccountResourceAsm() {
		super(AccountController.class, AccountResource.class);
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource accountResource = new AccountResource();
		accountResource.setName(account.getName());
		accountResource.setPassword(account.getPassword());
		accountResource.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
		return accountResource;
		
	}

}
