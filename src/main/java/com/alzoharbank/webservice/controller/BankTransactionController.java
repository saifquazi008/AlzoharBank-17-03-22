package com.alzoharbank.webservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.dao.BankTransactionDao;
import com.alzoharbank.webservice.dao.TransactionHistoryDao;
import com.alzoharbank.webservice.exception.BankTransactionFailed;
import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.AccountDTO;
import com.alzoharbank.webservice.model.TransactionDTO;

@RestController
public class BankTransactionController {

	@Autowired
	BankTransactionDao banBankTransactionDao;

	@Autowired
	TransactionHistoryDao historyDao;

	@GetMapping("/balance/{id}")
	public AccountDTO showBalanceById(@PathVariable("id") int id) {
		AccountDTO transaction = banBankTransactionDao.showBalanceById(id);
		if (transaction != null) {
			return transaction;

		}
		throw new BankTransactionFailed("Transaction Failed Given Id Not Match !" + id);
	}

	@GetMapping("/bank")
	public Account showAccountByEmail(@RequestParam("email") String email) {
		Account account = banBankTransactionDao.showAccountByEmail(email);
		if (account != null) {
			return account;
		}
		throw new BankTransactionFailed("Transaction Failed With Given Email !" + email);

	}

	@GetMapping("/banks/{id}")
	public Account showAccountById(@PathVariable("id") int id) {
		Account account = banBankTransactionDao.showAccountId(id);
		if (account != null) {
			return account;
		}
		throw new BankTransactionFailed("Transaction Failed With Given id" + id);
	}

	@PutMapping("/deposite")
	public Map<String, String> depositeAmount(@RequestBody TransactionDTO tx) {
		return historyDao.depositeHistory(tx);
	}

	@PutMapping("/withdraw")
	public Map<String, String> withdrawAmount(@RequestBody TransactionDTO tx) {
		return historyDao.withdrawHistory(tx);

	}

}
