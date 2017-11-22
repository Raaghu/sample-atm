package in.krraghavendra.asignment.atm.tests.account;

import java.util.List;

import org.junit.Test;

import in.krraghavendra.assignment.atm.account.Account;
import in.krraghavendra.assignment.atm.account.InvalidTransactionException;
import in.krraghavendra.assignment.atm.account.Transaction;
import junit.framework.TestCase;

public class TestAccount extends TestCase{
	
	@Test
	public void testGetAccountNo(){
		Account acc = new Account("123");
		assertEquals("123", acc.getAccountNo());
	}
	
	@Test
	public void testCredit() throws InvalidTransactionException{
		Account acc = new Account("123");
		Account result = acc.credit(1000);
		assertEquals(acc, result);
		assertEquals(1000.0, result.getBalance());
		
		result = acc.credit(50.50);
		assertEquals(1050.50, result.getBalance());
	}
	
	@Test
	public void testDebit() throws InvalidTransactionException{
		Account acc = new Account("123");
		acc.credit(1000);
		
		Account result = acc.debit(400.00);
		assertEquals(acc, result);
		assertEquals(600.0, result.getBalance());
		
		acc.debit(200);
		assertEquals(400.0, acc.getBalance());
		
		try{
			acc.debit(500);
		}catch (InvalidTransactionException e) {
			assertEquals("Insufficient funds to debit", e.getMessage());
		}
		
		// test for debit with empty account
		acc = new Account("456");
		try{
			acc.debit(500);
		}catch (InvalidTransactionException e) {
			assertEquals("Insufficient funds to debit", e.getMessage());
		}
	}
	
	@Test
	public void testGetTransactions() throws InvalidTransactionException{
		Account acc = new Account("123");
		List<Transaction> transactions = acc.getTransactions();
		assertEquals(0, transactions.size());
		
		acc.credit(1000);
		transactions = acc.getTransactions();
		assertEquals(1, transactions.size());
		assertEquals(1000.0, transactions.get(0).getAmount());
		assertEquals(true, transactions.get(0).isCredit());
		assertEquals(1000.0, transactions.get(0).getBalance());
		
		acc.debit(400);
		transactions = acc.getTransactions();
		assertEquals(2, transactions.size());
		assertEquals(1000.0, transactions.get(0).getAmount());
		assertEquals(true, transactions.get(0).isCredit());
		assertEquals(1000.0, transactions.get(0).getBalance());
		assertEquals(400.0, transactions.get(1).getAmount());
		assertEquals(false, transactions.get(1).isCredit());
		assertEquals(600.0, transactions.get(1).getBalance());
		
		try{
			acc.debit(601);
		}catch (InvalidTransactionException e) {
		}
		
		transactions = acc.getTransactions();
		assertEquals(2, transactions.size());
		assertEquals(1000.0, transactions.get(0).getAmount());
		assertEquals(true, transactions.get(0).isCredit());
		assertEquals(1000.0, transactions.get(0).getBalance());
		assertEquals(400.0, transactions.get(1).getAmount());
		assertEquals(false, transactions.get(1).isCredit());
		assertEquals(600.0, transactions.get(1).getBalance());
		
	}

}
