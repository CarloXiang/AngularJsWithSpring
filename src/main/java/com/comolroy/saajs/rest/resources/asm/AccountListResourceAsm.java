package com.comolroy.saajs.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.comolroy.saajs.core.services.util.AccountList;
import com.comolroy.saajs.rest.controller.AccountController;
import com.comolroy.saajs.rest.resources.AccountListResource;
import com.comolroy.saajs.rest.resources.AccountResource;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

	public AccountListResourceAsm() {
		super(AccountController.class, AccountListResource.class);
	}

	@Override
	public AccountListResource toResource(AccountList accountList) {
		List<AccountResource> resourceList= new AccountResourceAsm().toResources(accountList.getAccounts());
		AccountListResource accountListResource= new AccountListResource();
		accountListResource.setAccounts(resourceList);
		return accountListResource;
	}
	
	

}
