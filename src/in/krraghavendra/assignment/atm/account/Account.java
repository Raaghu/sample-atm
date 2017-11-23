package in.krraghavendra.assignment.atm.account;

import java.util.ArrayList;
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
		lastTransaction = new Transaction(true, amount, lastTransaction);
		return this;
	}
	
	public Account debit(double amount) throws InvalidTransactionException {
		lastTransaction = new Transaction(false, amount, lastTransaction);
		return this;
	}
	
	public double getBalance(){
		if(lastTransaction == null){
			return 0;
		}else{
			return lastTransaction.getBalance();
		}
	}
	
	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = lastTransaction;
		while(transaction != null){
			transactions.add(0,transaction);
			transaction = transaction.getPreviousTransaction();
		}
		return transactions;
	}

}
