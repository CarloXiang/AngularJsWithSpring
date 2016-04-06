package com.comolroy.saajs.core.repositories;

import java.util.List;

import com.comolroy.saajs.core.entities.Account;

public interface AccountRepo {
	// Find a return a Account or null if not found
	public Account findAccount(Long id);
	// Return a list of all the account of the system
	public List<Account> findAllAccount();
	// Find a account by name and return it. Or Return null if account not found.
	public Account findAccountByName(String name);
	// Create an account with given data and return it. Or return null if it can not.
	public Account createAccount(Account data);
}
