package com.alzoharbank.webservice.model;

import java.util.Date;

public class TransactionHistory {
	private int id;
	private int accId;
	private String transactionType;
	private String accUserName;
	private double oldBalance;
	private double newBalance;
	private int amount;
	private Date createdAt;
	private Date modifiedAt;

	public TransactionHistory(int id, int accId, String transactionType, String accUserName, double oldBalance,
			double newBalance, int amount, Date createdAt, Date modifiedAt) {
		super();
		this.id = id;
		this.accId = accId;
		this.transactionType = transactionType;
		this.accUserName = accUserName;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
		this.amount = amount;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public TransactionHistory() {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
