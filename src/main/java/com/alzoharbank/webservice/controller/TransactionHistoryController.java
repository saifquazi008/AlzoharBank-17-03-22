package com.alzoharbank.webservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alzoharbank.webservice.dao.TransactionHistoryDao;
import com.alzoharbank.webservice.exception.BankTransactionFailed;
import com.alzoharbank.webservice.model.TransactionHistory;

@RestController
public class TransactionHistoryController {

	@Autowired
	TransactionHistoryDao historyDao;

	@GetMapping("/history/{accId}")
	public List<TransactionHistory> showHistory(@PathVariable("accId") int accId) {
		List<TransactionHistory> history = historyDao.showHistoryById(accId);
		if (history != null) {
			return history;
		}
		throw new BankTransactionFailed("Failed Given id Not match !" + accId);
	}

	@GetMapping("join")
	public List<TransactionHistory> showJoinHistory() {
		List<TransactionHistory> list = historyDao.joinHistory();
		if (list.isEmpty()) {
			throw new BankTransactionFailed("Failed Given id Not match !");
		}
		return list;
	}

}
