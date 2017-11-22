package in.krraghavendra.assignment.atm.account;

import java.util.Date;

public class Transaction {
	
	private Date date;
	private boolean isCredit;
	private double amount;
	private double balance;
	private Transaction previousTransaction;
		
	public Transaction(Date date, boolean isCredit, double amount, Transaction previousTransaction) throws InvalidTransactionException {
		this.date = date;
		this.isCredit = isCredit;
		this.amount = amount;
		if(previousTransaction == null){
			if(this.isCredit){
				this.balance = this.amount;
			}else{
				throw new InvalidTransactionException("1st Transaction can not be Debit");
			}
		}else{
			this.balance = previousTransaction.balance + this.amount * (this.isCredit?1:-1);
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
