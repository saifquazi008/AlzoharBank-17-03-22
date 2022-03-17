package com.alzoharbank.webservice.dao;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.dao.BankTransactionDao.transactionMapper;
import com.alzoharbank.webservice.exception.BankTransactionFailed;
import com.alzoharbank.webservice.exception.InvalidTransaction;
import com.alzoharbank.webservice.model.Account;
import com.alzoharbank.webservice.model.StatementDTO;
import com.alzoharbank.webservice.model.TransactionDTO;
import com.alzoharbank.webservice.model.TransactionHistory;

@Repository
public class TransactionHistoryDao {

	@Autowired
	JdbcTemplate template;

	class HistoryMapper implements RowMapper<TransactionHistory> {

		@Override
		public TransactionHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionHistory history = new TransactionHistory();
			history.setId(rs.getInt(1));
			history.setAccId(rs.getInt(2));
			history.setTransactionType(rs.getString(3));
			history.setAccUserName(rs.getString(4));
			history.setOldBalance(rs.getDouble(5));
			history.setNewBalance(rs.getDouble(6));
			history.setAmount(rs.getInt(7));
			history.setCreatedAt(rs.getDate(8));
			history.setModifiedAt(rs.getDate(9));
			return history;
		}
	}

	class TransactionMapper implements RowMapper<TransactionDTO> {

		@Override
		public TransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			TransactionDTO balance = new TransactionDTO();
			balance.setAccId(rs.getInt(1));
			balance.setBalance(rs.getDouble(2));
			balance.setUserName(rs.getString(3));
			return balance;
		}

	}
	
	class StatementMapper implements RowMapper<StatementDTO>{

		@Override
		public StatementDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			StatementDTO statement= new StatementDTO();
			statement.setId(rs.getInt(1));
			statement.setAccId(rs.getInt(2));
			statement.setUserName(rs.getString(3));
			statement.setEmail(rs.getString(4));
			statement.setPassword(rs.getString(6));
			statement.setCardPin(rs.getInt(7));
			statement.setCardNo(rs.getLong(8));
			statement.setBalance(rs.getDouble(9));
			statement.setTransactionType(rs.getString(10));
			statement.setAccUserName(rs.getString(11));
			statement.setOldBalance(rs.getShort(12));
			statement.setNewBalance(rs.getDouble(13));
			statement.setAmount(rs.getInt(14));
			statement.setModifiedAt(rs.getDate(15));
			return statement;
		}
		
	}

	// show History by Id
	public List<TransactionHistory> showHistoryById(int accId) {
		return template.query("select * from history where accId=?", new HistoryMapper(), accId);
	}

	// jon history table
	public List<TransactionHistory> joinHistory() {
//		List<TransactionHistory> join = new LinkedList<TransactionHistory>();
		return template.query("select * from accounts inner join history  On accounts.accId =history.accId ",
				new HistoryMapper());
	}

	// show balance by Id
	public TransactionDTO showBalanceById(int accId) {
		return template.queryForObject("select accId,balance,userName from accounts where accId = ?",
				new TransactionMapper(), accId);
	}

	// deposite Amount
	public Map<String, String> depositeHistory(TransactionDTO tx) {
		Map<String, String> response = new HashMap<String, String>();
//			TransactionHistory history = new TransactionHistory();
//			Account acc = new Account();
		if (tx.getAmount() > 0) {
			TransactionDTO details = showBalanceById((int) tx.getAccId());
			double newBalance = details.getBalance() + tx.getBalance();
			String type = "Deposite";
			int rowAffected = template.update(
					"update accounts set balance= balance + " + tx.getAmount() + " where accId=?",
					new Object[] { tx.getAccId() });
			int rowsAffected = template.update(
					"insert into history(accId,transactionType,accUserName,oldBalance,newBalance,amount,createdAt,modifiedAt)"
							+ "values(?,?,?,?,?,?,?,?)",
					new Object[] { tx.getAccId(), type, details.getUserName(), details.getBalance(), newBalance,
							tx.getAmount(), new Date(), new Date() });
			response.put("message", "Amount has been deposited successfully");
			response.put("rowAffected", String.valueOf(rowAffected));
			response.put("message", "history updated successfully");
			response.put("rowsAffected", String.valueOf(rowsAffected));

		} else {
			throw new BankTransactionFailed("Invalid Amount !" + tx.getAmount());
		}
		return response;
	}

	// withdraw amount
	public Map<String, String> withdrawHistory(TransactionDTO tx) {
		Map<String, String> response = new HashMap<String, String>();
		if (tx.getAmount() > 0) {
			TransactionDTO details = showBalanceById((int) tx.getAccId());
			double newBlance = details.getBalance() + tx.getBalance();
			String type = "withdraw";
			int rowAffected = template.update(
					"update accounts set balance=balance - " + tx.getAmount() + " where accId=?",
					new Object[] { tx.getAccId() });

			int rowsAffected = template.update(
					"insert into history (accId,transactionType,accUserName,oldBalance,newBalance,amount,createdAt,modifiedAt)"
							+ "values(?,?,?,?,?,?,?,?)",
					new Object[] { tx.getAccId(), type, details.getUserName(), details.getBalance(), newBlance,
							tx.getAmount(), new Date(), new Date() });
			response.put("message", "Amount has been withdraw successfully");
			response.put("rowAffected", String.valueOf(rowAffected));
			response.put("message", "history updated withdraw successfully");
			response.put("rowAffected", String.valueOf(rowsAffected));
		} else {
			throw new InvalidTransaction("Invalid amount for withdraw !" + tx.getAmount());
		}
		return response;
	}

}
