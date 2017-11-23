package in.krraghavendra.assignment.atm.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import in.krraghavendra.assignment.atm.InputReader;
import in.krraghavendra.assignment.atm.Session;
import in.krraghavendra.assignment.atm.account.InvalidTransactionException;
import in.krraghavendra.assignment.atm.vault.InvalidDenominationException;
import in.krraghavendra.assignment.atm.vault.Vault;

public class DepositCommand implements ICommand{
	
	@Override
	public String getName() {
		return "Deposit";
	}
	
	@Override
	public void run() {
		System.out.println("Enter ccy to deposit terminated by. e.g. 10 20 50 .");
		Scanner sc = InputReader.getScanner();
		
		List<String> inputCurrencies = new ArrayList<String>();
		String currency = sc.next();
		while (!currency.equals(".")) {
			inputCurrencies.add(currency);
			currency = sc.next();
		}
		
		for(Iterator<String> itr = inputCurrencies.iterator();itr.hasNext();){
			String sCurrency = itr.next();
			try{
				int iCurrency = Integer.parseInt(sCurrency);
				
				Vault.getVault().add(iCurrency);
				Session.getSession().getAccount().credit(iCurrency);
				
				System.out.println("Accepted:"+sCurrency);
				
			}catch (NumberFormatException nfe) {
				System.out.println("Invalid denomination:"+sCurrency+"$");
			} catch (InvalidDenominationException e) {
				System.out.println("Invalid denomination:"+sCurrency+"$");
			} catch (InvalidTransactionException e) {
				System.out.println("Invalid denomination:"+sCurrency+"$");
			}
		}
	}
}
