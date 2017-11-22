package in.krraghavendra.assignment.atm.account;

import java.util.Date;

public class Transaction {
	
	private Date date;
	private boolean isCredit;
	private double amount;
	private double balance;
	private Transaction previousTransaction;
		
	public Transaction(boolean isCredit, double amount, Transaction previousTransaction) throws InvalidTransactionException {
		this.date = new Date();
		this.isCredit = isCredit;
		this.amount = amount;
		
		double previousBalance = previousTransaction != null ? previousTransaction.balance:0;
		
		if(previousBalance < 0){
			throw new InvalidTransactionException("balance in previousTransaction can not be negative");
		}
		
		this.balance = previousBalance + this.amount * (this.isCredit?1:-1);
		if(this.balance < 0){
			throw new InvalidTransactionException("Insufficient funds to debit");
		}
		this.previousTransaction = previousTransaction;
	}

	public Date getDate() {
		return date;
	}
	public boolean isCredit() {
		return isCredit;
	}
	public double getAmount() {
		return amount;
	}
	public double getBalance() {
		return balance;
	}
	public Transaction getPreviousTransaction() {
		return previousTransaction;
	}

}
