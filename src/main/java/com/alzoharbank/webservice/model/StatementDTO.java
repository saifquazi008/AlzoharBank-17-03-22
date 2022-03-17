package com.alzoharbank.webservice.model;

import java.util.Date;

public class StatementDTO {

	public int id;
	public int accId;
	public String userName;
	public String email;
	public String password;
	public int cardPin;
	public long cardNo;
	public double balance;
	private String transactionType;
	private String accUserName;
	private double oldBalance;
	private double newBalance;
	private int amount;
	private Date modifiedAt;

	public StatementDTO(int id, int accId, String userName, String email, String password, int cardPin, long cardNo,
			double balance, String transactionType, String accUserName, double oldBalance, double newBalance,
			int amount, Date modifiedAt) {
		super();
		this.id = id;
		this.accId = accId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.cardPin = cardPin;
		this.cardNo = cardNo;
		this.balance = balance;
		this.transactionType = transactionType;
		this.accUserName = accUserName;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.amount = amount;
		this.modifiedAt = modifiedAt;
	}

	public StatementDTO() {
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

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAccUserName() {
		return accUserName;
	}

	public void setAccUserName(String accUserName) {
		this.accUserName = accUserName;
	}

	public double getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(double oldBalance) {
		this.oldBalance = oldBalance;
	}

	public double getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
