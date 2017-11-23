package in.krraghavendra.assignment.atm.commands;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import in.krraghavendra.assignment.atm.Session;
import in.krraghavendra.assignment.atm.account.Transaction;

public class MiniStatementCommand implements ICommand {

	@Override
	public String getName() {
		return "Mini Statement";
	}

	@Override
	public void run() {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Date Time              Transaction      Amount     Closing Balance");
		System.out.println("-------------------------------------------------------------------");
		
		List<Transaction> transactions = Session.getSession().getAccount().getTransactions();
		
		for(Iterator<Transaction> itr = transactions.iterator();itr.hasNext();){
			Transaction transaction = itr.next();
			
			String statementRow = "";
			SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyyy hh:mm:ss"); 
			statementRow = appendAtPosition(statementRow, 0, dt.format(transaction.getDate()));
			statementRow = appendAtPosition(statementRow, 23, transaction.isCredit()?"Credit":"Debit");
			statementRow = appendAtPosition(statementRow, 39, ""+transaction.getAmount());
			statementRow = appendAtPosition(statementRow, 50, ""+transaction.getBalance());
			
			System.out.println(statementRow);

		}
		System.out.println("-------------------------------------------------------------------");
	}
	
	
	private String appendAtPosition(String orig,int pos,String newStr){
		while (orig.length() < pos) {
			orig += " ";
		}
		return orig+newStr;
	}

}
