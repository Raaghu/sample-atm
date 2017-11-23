package in.krraghavendra.assignment.atm.commands;

import in.krraghavendra.assignment.atm.InputReader;

public class ExitCommand implements ICommand {

	@Override
	public String getName() {
		return "Exit";
	}

	@Override
	public void run() {
		System.out.println("Thank you for using our ATM , Please Visit Again !!");
		InputReader.closeScanner();
		System.exit(0);
	}

}
