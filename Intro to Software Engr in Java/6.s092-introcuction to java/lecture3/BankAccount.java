package hello;

public class BankAccount {
	private BankAccountType type;
	private double balance;
	private int passcode;
	
	public BankAccount(BankAccountType t){
		this.balance = 0;
		this.passcode = 1111;
		this.type = t;
	}
	
	public BankAccount(BankAccountType t, double inital, int passcode){
		this.balance = inital;
		this.passcode = passcode;
		this.type = t;
	}
	
	
	public void withdraw(double x){
		this.balance = this.balance - x;
		// this.balance -= x;
		// print the balance
		System.out.println(this.balance);
	}
	
	public void deposit(double x){
		this.balance += x;
		System.out.println(this.balance);
	}
	
	public void changePasscode(int newPasscode){
		this.passcode = newPasscode;
		System.out.println("Changed Successfully");
	}
	
	public double getBalance(){
		return this.balance;
	}

}
