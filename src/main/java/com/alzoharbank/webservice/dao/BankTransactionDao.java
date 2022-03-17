package com.alzoharbank.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.exception.BankTransactionFailed;
import com.alzoharbank.webservice.exception.InvalidTransaction;
import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.AccountDTO;
import com.alzoharbank.webservice.model.TransactionDTO;

@Repository
public class BankTransactionDao {

	@Autowired
	JdbcTemplate template;

	class transactionMapper implements RowMapper<Account> {

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account account = new Account();
			account.setId(rs.getInt(1));
			account.setAccId(rs.getInt(2));
			account.setUserName(rs.getString(3));
			account.setEmail(rs.getString(4));
			account.setPassword(rs.getString(5));
			account.setCardPin(rs.getInt(6));
			account.setCardNo(rs.getLong(7));
			account.setBalance(rs.getDouble(8));
			account.setCreatedAt(rs.getDate(9));

			return account;
		}
	}

	class BalanceMapper implements RowMapper<AccountDTO> {

		@Override
		public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AccountDTO balance = new AccountDTO();
			balance.setAccId(rs.getInt(1));
			balance.setBalance(rs.getDouble(2));
			return balance;
		}

	}

	class DepositeMapper implements RowMapper<TransactionDTO> {

		@Override
		public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionDTO balance = new TransactionDTO();
			balance.setAccId(rs.getInt(1));
			balance.setBalance(rs.getDouble(2));
			balance.setUserName(rs.getString(3));
			return balance;
		}

	}

	public AccountDTO showBalanceById(int id) {
//		AccountDTO balance = new AccountDTO();
		return template.queryForObject("select accId,balance,userName from accounts where accId = ?",
				new BalanceMapper(), id);
	}

	public Account showAccountId(int id) {
		return template.queryForObject("select * from accounts where accId=? ", new transactionMapper(), id);
	}

	public Account showAccountByEmail(String email) {
		return template.queryForObject("select * from accounts where email=?", new transactionMapper(), email);
	}

	// deposite Amount
	public Map<String, String> deposite(TransactionDTO tx) {
		Map<String, String> response = new HashMap<String, String>();
		if (tx.getAmount() > 0) {
			int rowAffected = template.update(
					"update accounts set balance= balance + " + tx.getAmount() + " where accId=?",
					new Object[] { tx.getAccId() });
			response.put("message", "Amount has been deposited successfully");
			response.put("rowAffected", String.valueOf(rowAffected));
		} else {
			throw new BankTransactionFailed("Invalid Amount !" + tx.getAmount());
		}
		return response;
	}

	// withdraw amount
	public Map<String, String> withdraw(TransactionDTO tx) {
		Map<String, String> response = new HashMap<String, String>();
		if (tx.getAmount() > 0) {
//			double newBalance = tx.getBalance() - tx.getAmount();
//			tx.setBalance(newBalance);
			int rowAffected = template.update(
					"update accounts set balance=balance  - " + tx.getAmount() + " where accId=?",
					new Object[] { tx.getAccId() });
			response.put("message", "Amount has been withdraw successfully");
			response.put("rowAffected", String.valueOf(rowAffected));
		} else {
			throw new InvalidTransaction("Invalid amount for withdraw ! " + tx.getAmount());
		}
		return response;
	}

}
