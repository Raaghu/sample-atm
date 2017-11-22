package in.krraghavendra.assignment.atm.account;

public class InvalidTransactionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidTransactionException() {
		super();
	}
	
	public InvalidTransactionException(String message) {
		super(message);
	}

}
