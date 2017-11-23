package in.krraghavendra.assignment.atm.commands;

import in.krraghavendra.assignment.atm.Session;

public class BalanceCommand implements ICommand {

	@Override
	public String getName() {
		return "Display Balance";
	}

	@Override
	public void run() {
		System.out.println("Available balance:"+Session.getSession().getAccount().getBalance());
	}

}
