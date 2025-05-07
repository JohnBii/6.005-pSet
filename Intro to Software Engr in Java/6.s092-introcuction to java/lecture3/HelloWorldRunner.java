package hello;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class HelloWorldRunner {
	public static void main(String[] args) {
		Cat a = new Cat("Garfield");
		a.bark();
		a.meow();
		
		int [] ans = {1,2,3};
		System.out.println(ans); // prints address
		System.out.println(ans.toString()); // prints address
		System.out.println(Arrays.toString(ans)); // prints the actual array
		
		
		BankAccountType t;
		double initialDeposit;
		int passcode;
		
		Scanner in = new Scanner(System.in);
		System.out.println("[1] Personal \n[2] Business");
		int userChoice = in.nextInt();
		
		while ( userChoice != 1 && userChoice != 2 )
			userChoice = askAgain();
		
		if ( userChoice == 1 )
			t = BankAccountType.PERSONAL;
		else
			t = BankAccountType.BUSINESS;
		
		System.out.println("Inital deposit");
		initialDeposit = in.nextDouble();
		
		System.out.println("Desired PIN");
		passcode = in.nextInt();
		
		
		
		
		BankAccount myBank = new BankAccount(t, initialDeposit, passcode);
		boolean goAgain = false;
		do{
			System.out.println("What would you like to do?");
			System.out.println("[1] Withdraw \n[2] Deposit\n[3]Change Passcode");
			System.out.println("ANY OTHER KEY TO QUIT");
			int inChoice = 0;
			try {
				inChoice = in.nextInt();
				if (inChoice == 1 || inChoice == 2){
					goAgain = true;
				}
				if (inChoice == 1)
					myBank.withdraw(5);
				System.out.println(myBank.getBalance());
			}
			catch ( InputMismatchException ime ){
				goAgain = false;
			}
		}while(goAgain);
		
	}
	
	private static int askAgain() {
		Scanner in = new Scanner(System.in);
		System.out.println("[1] Personal \n[2] Business");
		return in.nextInt();
	}

	//Make a method that adds two numbers (int)
	// from user
	// input
	public static int add(int num1, int num2){
		return num1 + num2;
	}
	
}
