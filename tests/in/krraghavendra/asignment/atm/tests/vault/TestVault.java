package in.krraghavendra.asignment.atm.tests.vault;

import java.util.List;

import org.junit.Test;

import in.krraghavendra.assignment.atm.vault.InsufficientFundsException;
import in.krraghavendra.assignment.atm.vault.InvalidDenominationException;
import in.krraghavendra.assignment.atm.vault.Vault;
import junit.framework.TestCase;

public class TestVault extends TestCase {
	
	@Test
	public void testAdd() throws InvalidDenominationException {
		Vault vault = new Vault(new Integer[]{50,20,10});
		Vault result = vault.add(50);
		assertEquals(vault, result);
		
		boolean isException = false;
		try{
			vault.add(100);
		}catch (InvalidDenominationException e) {
			isException = true;
		}
		assertTrue(isException);
	}
	
	@Test
	public void testRemove() throws InvalidDenominationException, InsufficientFundsException {
		Vault vault = new Vault(new Integer[]{50,20,10});
		vault.add(50).add(20).add(20).add(10).add(10).add(10);
		
		List<Integer> removedCurrency = vault.remove(30);
		assertEquals(2, removedCurrency.size());
		assertEquals(new Integer(20), removedCurrency.get(0));
		assertEquals(new Integer(10), removedCurrency.get(1));
		
		removedCurrency = vault.remove(40);
		assertEquals(3, removedCurrency.size());
		assertEquals(new Integer(20), removedCurrency.get(0));
		assertEquals(new Integer(10), removedCurrency.get(1));
		assertEquals(new Integer(10), removedCurrency.get(2));
		
		boolean isException = false;
		try{
			vault.remove(20);
		}catch (InsufficientFundsException e) {
			isException = true;
		}
		assertTrue(isException);
		
		isException = false;
		try{
			vault.remove(30);
		}catch (InsufficientFundsException e) {
			isException = true;
		}
		assertTrue(isException);
		
		removedCurrency = vault.remove(50);
		assertEquals(1, removedCurrency.size());
		assertEquals(new Integer(50), removedCurrency.get(0));
		
	}

}
