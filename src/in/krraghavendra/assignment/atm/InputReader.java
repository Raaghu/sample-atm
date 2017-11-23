package in.krraghavendra.assignment.atm;

import java.util.Scanner;

public class InputReader {
	
	private static Scanner scanner = null;
	
	public static Scanner getScanner() {
		if(scanner == null){
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
	
	public static void closeScanner() {
		scanner.close();
		scanner = null;
	}

}
