package com.cerotid.bank.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Transaction implements serializable {

	private String receiverFirstName;
	private String receiverLastName;
	private double amount;
	private double fee;
	private Date timestamp;

	public Transaction(String receiverFirstName, String receiverLastName, double amount, double fee, Date timestamp) {
		super();
		this.amount = amount;
		this.receiverFirstName = receiverFirstName;
		this.receiverLastName = receiverLastName;
		this.fee = fee;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"receiverFirstName='" + receiverFirstName + '\'' +
				", receiverLastName='" + receiverLastName + '\'' +
				", amount=" + amount +
				", fee=" + fee +
				", timestamp=" + timestamp +
				'}';
	}

	public Transaction() {

	}

	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the receiverFirstName
	 */
	public String getReceiverFirstName() {
		return receiverFirstName;
	}

	/**
	 * @param receiverFirstName the receiverFirstName to set
	 */
	public void setReceiverFirstName(String receiverFirstName) {
		this.receiverFirstName = receiverFirstName;
	}

	/**
	 * @return the timestamp
	 */
//	public Date getTimestamp() {
//		return timestamp;
//	}

	/**
	 * @param timestamp the timestamp to set
	 */
//	public void setTimestamp(Date timestamp) {
//		this.timestamp = timestamp;
//	}

	/**
	 * @return the fee
	 */
	public double getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(double fee) {
		this.fee = fee;
	}

	/**
	 * @return the receiverLastName
	 */
	public String getReceiverLastName() {
		return receiverLastName;
	}

	public void setReceiverLastName(String receiveLastName) {
		this.receiverLastName = receiveLastName;
	}

	public void deductAccountBalance() {

	}

	public void createTransaction() {

	}


}