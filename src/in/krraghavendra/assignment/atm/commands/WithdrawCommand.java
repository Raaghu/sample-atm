package in.krraghavendra.assignment.atm.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import in.krraghavendra.assignment.atm.InputReader;
import in.krraghavendra.assignment.atm.Session;
import in.krraghavendra.assignment.atm.account.InvalidTransactionException;
import in.krraghavendra.assignment.atm.vault.InsufficientFundsException;
import in.krraghavendra.assignment.atm.vault.Vault;

public class WithdrawCommand implements ICommand{
	
	@Override
	public String getName() {
		return "Withdraw";
	}
	
	@Override
	public void run() {
		System.out.println("Enter amount to withdraw");
		Scanner sc = InputReader.getScanner();
		
		int amount = sc.nextInt();
		
		try {
			List<Integer> currencies = Vault.getVault().remove(amount);
			Session.getSession().getAccount().debit(amount);
			
			for(Iterator<Integer> itr = currencies.iterator();itr.hasNext();){
				System.out.println("Dispensing "+itr.next()+"$");
			}
			
		} catch (InsufficientFundsException e) {
			System.out.println("Insufficient Funds");
		} catch (InvalidTransactionException e) {
			System.out.println("Insufficient Funds");
		}
		
	}
}
