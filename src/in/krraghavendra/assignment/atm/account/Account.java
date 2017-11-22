package in.krraghavendra.assignment.atm.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
	
	private String accountNo;
	private Transaction lastTransaction = null;
	
	public Account(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public Account credit(double amount) throws InvalidTransactionException {
		this.lastTransaction = new Transaction(new Date(), true, amount, this.lastTransaction);
		return this;
	}
	
	public Account debit(double amount) throws InvalidTransactionException {
		this.lastTransaction = new Transaction(new Date(), false, amount, this.lastTransaction);
		return this;
	}
	
	public double getBalance(){
		return this.lastTransaction.getBalance();
	}
	
	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = this.lastTransaction;
		while(transaction != null){
			transactions.add(0,transaction);
			transaction = transaction.getPreviousTransaction();
		}
		return transactions;
	}

}
