package com.alzoharbank.webservice.model;

import java.util.Date;

public class Account {

	public int id;
	public int accId;
	public String userName;
	public String email;
	public String password;
	public int cardPin;
	public long cardNo;
	public double balance;
	public Date createdAt;

	public Account(int id, int accId, String userName, String email, String password, int cardPin, long cardNo,
			double balance, Date createdAt) {
		super();
		this.id = id;
		this.accId = accId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.cardPin = cardPin;
		this.cardNo = cardNo;
		this.balance = balance;
		this.createdAt = createdAt;
	}

	public Account() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCardPin() {
		return cardPin;
	}

	public void setCardPin(int cardPin) {
		this.cardPin = cardPin;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
