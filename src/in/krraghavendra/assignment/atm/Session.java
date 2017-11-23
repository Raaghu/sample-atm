package in.krraghavendra.assignment.atm;

import in.krraghavendra.assignment.atm.account.Account;

public class Session {
	
	private static Session sessionObj = null;
	
	private Account account = null;
	
	private Session() {
		
	}
	
	public static Session getSession() {
		if(sessionObj == null){
			synchronized (Session.class) {
				if(sessionObj == null){
					sessionObj = new Session();
				}
			}
		}
		return sessionObj;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
