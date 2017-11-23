package in.krraghavendra.assignment.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import in.krraghavendra.assignment.atm.account.Account;
import in.krraghavendra.assignment.atm.commands.BalanceCommand;
import in.krraghavendra.assignment.atm.commands.DepositCommand;
import in.krraghavendra.assignment.atm.commands.ExitCommand;
import in.krraghavendra.assignment.atm.commands.ICommand;
import in.krraghavendra.assignment.atm.commands.MiniStatementCommand;
import in.krraghavendra.assignment.atm.commands.WithdrawCommand;

public class Application {
	
	private List<ICommand> commands = new ArrayList<ICommand>();
	
	public void addCommand(ICommand command) {
		commands.add(command);
	}
	
	public void run() {
		
		for (int i=0; i< commands.size();i++) {
			System.out.println((i+1)+". "+commands.get(i).getName());
		}
		System.out.print("Select an option : ");

		Scanner sc = InputReader.getScanner();
        int i = sc.nextInt();
        while (i < 1 || i > commands.size()) {
    		System.out.print("Invalid Option , Select a valid Option : ");
        	i = sc.nextInt();
		}
		
        commands.get(i-1).run();
        
        System.out.println("");
        run();
	}
	
	public static void main(String[] args) {
		
		// initialize session
		Session session = Session.getSession();
		session.setAccount(new Account("account1"));
		
		Application app = new Application();
		
		app.addCommand(new DepositCommand());
		app.addCommand(new WithdrawCommand());
		app.addCommand(new BalanceCommand());
		app.addCommand(new MiniStatementCommand());
		app.addCommand(new ExitCommand());
		
		app.run();
		
	}

}
