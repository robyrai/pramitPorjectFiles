package com.cerotid.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Account implements Serializable {
	private AccountType accountType;
	private Date accountOpenDate;
	private double accountBalance;
	private String accountNumber;
	private ArrayList<Transaction> transactions;

	public Account() {

	}

	public Account(AccountType accountType, Date accntOpenDate, double openingBalance) {
		this.accountType = accountType;
		this.accountOpenDate = accntOpenDate;
		this.accountBalance = openingBalance;
		if (this.transactions == null)
			this.transactions = new ArrayList<>();

	}
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", accountOpenDate=" + accountOpenDate + ", accountBalance="
				+ accountBalance + ", accountNumber=" + accountNumber + ", transactions=" + transactions + "]";
	}


	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(Date accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	void printAccountInfo() {
		System.out.println(accountType);
	}




}
