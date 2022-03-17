package com.alzoharbank.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.dao.AccountDao;
import com.alzoharbank.webservice.exception.AccountNotFound;
import com.alzoharbank.webservice.model.Account;

@RestController
public class AccountController {

	@Autowired
	AccountDao accountDao;

	@GetMapping("/account")
	public Account getAccountByName(@RequestParam("name") String name) {
		Account account = accountDao.findAccountByAccountName(name);
		if (account != null) {
			return account;
		}
		throw new AccountNotFound("Account Not Found With Given Name " + name);
	}

	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		List<Account> list = accountDao.findAllAccount();
		if (list.isEmpty()) {
			throw new AccountNotFound("Account is empty ,Zero Records Found !");
		}
		return list;
	}

	@GetMapping("/accounts/{id}")
	public Account getAccountById(@PathVariable("id") int id) {
		Account account = accountDao.findAccountById(id);
		if (account != null) {
			return account;
		}
		throw new AccountNotFound("Account Not Found With Given Id " + id);
	}

	@PostMapping("/accounts")
	public Map<String, String> addAccount(@RequestBody Account account) {
		int rowsAffected = accountDao.insertAccount(account);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account created Successfully !");
		response.put("rowsAffected", String.valueOf(rowsAffected));
		return response;

	}

	@PutMapping("/accounts/{id}")
	public Map<String, String> updateAccountById(@PathVariable("id") int id, @RequestBody Account account) {
		int rowAffected = accountDao.updateAccount(account);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account is updated successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

	@DeleteMapping("/account/{id}")
	public Map<String, String> deleteAccountById(@PathVariable("id") int id) {
		int rowAffected = accountDao.deleteAccount(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Account is deleted successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;

	}

}
