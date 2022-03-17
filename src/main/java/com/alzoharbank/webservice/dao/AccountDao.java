package com.alzoharbank.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alzoharbank.webservice.model.Account;

@Repository
public class AccountDao {

	@Autowired
	JdbcTemplate template;

	class AccountMapper implements RowMapper<Account> {

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

	public List<Account> findAllAccount() {
		List<Account> account = new LinkedList<Account>();
		account = template.query("select * from accounts", new AccountMapper());
		return account;
	}

	public Account findAccountById(int id) {
		return template.queryForObject("select * from accounts where accId=?", new AccountMapper(), id);
	}

	public Account findAccountByAccountName(String name) {
		return template.queryForObject("select * from accounts where userName=?", new AccountMapper(), name);
	}

	public int insertAccount(Account acc) {
		return template.update(
				"insert into accounts(id,accId,userName,email,password,cardPin,cardNo,balance)"
						+ "values(?,?,?,?,?,?,?,?)",
				new Object[] { acc.getId(), acc.getAccId(), acc.getUserName(), acc.getEmail(), acc.getPassword(),
						acc.getCardPin(), acc.getCardNo(), acc.getBalance() });
	}

	public int updateAccount(Account account) {
		return template.update(
				"update accounts " + "set accId=?, userName=?, email=?,password=?,cardPin=?, cardNo=?,balance=? "
						+ " where id=?",
				new Object[] { account.getAccId(), account.getUserName(), account.getEmail(), account.getPassword(),
						account.getCardPin(), account.getCardNo(), account.getBalance(), account.getId() });
	}

	public int deleteAccount(int id) {
		return template.update("delete from accounts where accId=?", id);
	}

}
