package in.krraghavendra.asignment.atm.tests.account;

import org.junit.Test;

import in.krraghavendra.assignment.atm.account.Account;
import in.krraghavendra.assignment.atm.account.InvalidTransactionException;
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
		
	}

}
