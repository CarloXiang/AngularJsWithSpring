package com.comolroy.saajs.controller.repositories;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.comolroy.saajs.SpringAndAngularJsApplication;
import com.comolroy.saajs.core.entities.Account;
import com.comolroy.saajs.core.repositories.AccountRepo;

import static org.junit.Assert.*;

/*
 * @RunWith Use for Custom Class Test Runner which will allow 
 * to inject spring bean into test class and also configure them.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringAndAngularJsApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class AccountRepoTest {

	Account account;
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Before
	@Transactional
	@Rollback(false)
	public void init(){
		account = new Account();
		account.setName("test");
		account.setPassword("test");
		accountRepo.createAccount(account);
	}

	@Test
	@Transactional
	public void atest() {
			assertNotNull(accountRepo.findAccount(account.getId()));
	}
}
