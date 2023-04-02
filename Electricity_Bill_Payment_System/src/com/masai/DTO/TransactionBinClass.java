package com.masai.DTO;

import java.time.LocalDate;

public class TransactionBinClass {

	int transactionId;
	double amount;
	LocalDate date;
	public TransactionBinClass(int transactionId, double amount, LocalDate date) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.date = date;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	@Override
	public String toString() {
		return "Transaction transactionId=" + transactionId + ", amount=" + amount + ", date=" + date ;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
