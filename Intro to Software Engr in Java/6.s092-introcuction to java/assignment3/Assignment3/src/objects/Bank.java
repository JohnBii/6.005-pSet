package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import enums.AccountType;

/**
 * @author Hau Lian
 * @version Friday, January 23rd, 2015 2317 EST
 */
public class Bank {
	
	/**
	 * A String that stores the name of the bank
	 */
	private String name;
	
	/**
	 * A boolean that stores if the bank is a member of FDIC
	 */
	private boolean memFDIC;
	
	/**
	 * A double that stores the balance of the bank
	 */
	private double balance;
	
	/**
	 * A HashMap of BankAccount objects that maps bank accounts by the account number
	 */
	private HashMap<Integer, BankAccount> accounts = new HashMap<Integer, BankAccount>();

	/**
	 * Constructs a new Bank object with a name and a balance with 
	 * 	FDIC defaulted to true.
	 * 
	 * @param name - a String that represents the name of the bank
	 * @param bal - a double that represents the initial balance 
	 * 					of the bank
	 */
	public Bank(String name, 
				double bal) {
		this.name = name;
		this.balance = bal;
		this.memFDIC = true;
	}
	
	/**
	 * Constructs a new Bank object with a name a balance and 
	 * 	if they are a member of FDIC
	 * 
	 * @param name - a String representing the name of the bank
	 * @param bal - a double representing the initial balance of the bank
	 * @param fdic - a boolean true if the bank is a member 
	 * 							false otherwise
	 */
	public Bank(String name, 
				double bal, 
				boolean fdic) {
		this.name = name;
		this.balance = bal;
		this.memFDIC = fdic;
	}
	
	/**
	 * Constructs a new Bank object with a name a balance and 
	 * 	if they are a member of FDIC
	 * 
	 * @param name - a String representing the name of the bank
	 * @param bal - a double representing the initial balance of the bank
	 * @param fdic - a boolean true if the bank is a member 
	 * 							false otherwise
	 * @param b - an ArrayList of BankAccount that represents 
	 * 				the bank accounts already in the bank
	 */
	public Bank(String name, 
				double bal, 
				boolean fdic,
				HashMap<Integer, BankAccount> b) {
		this.name = name;
		this.balance = bal;
		this.memFDIC = fdic;
		this.accounts = b;
	}

	/**
	 * @return the name of the Bank
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return boolean - true if the bank is a member of FDIC 
	 * 						false otherwise
	 */
	public boolean isMemFDIC() {
		return memFDIC;
	}
	
	public void setFDIC(boolean fdic) {
		this.memFDIC = fdic;
	}

	/**
	 * @return the balance of the bank (the amount of money 
	 * 				the bank currently has)
	 */
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return a copy of the ArrayList of BankAccounts at this Bank
	 */
	public HashMap<Integer, BankAccount> getBankAccounts() {
		return accounts;
	}
	
	/**
	 * @param firstName - a String that is the first name of the owner
	 * @param lastName - a String that is the last name of the owner
	 * @param birthday - a Date that is the date of birth of the owner
	 * @param username - a String that is the username to the account
	 * @param password - a String that is the password to the account
	 * @param initDeposit - a double that is the initial deposit 
	 * 							into the account
	 * @param at - an AccountType specifying the bank account type
	 * @param auth - an ArrayList of Person object(s) of persons 
	 * 					who are authorized to use this account.
	 * 
	 * @return boolean - true if the account creation was successful
	 * 						false otherwise
	 */
	public boolean openAccount(String firstName, 
							   String lastName,
							   Date birthday,
							   String username,
							   String password,
							   double initDeposit,
							   AccountType at,
							   ArrayList<Person> auth) {
		try {
			int accNum = genAccountNumber(accounts);
			this.accounts.put( accNum,
							   new BankAccount( new Person(firstName, lastName, birthday), 
												password, 
												username, 
												initDeposit, 
												at, 
												accNum, 
												auth ));
			return true; 
		}
		catch ( Exception e ){
			System.out.println("OOPS, we could not create an account for you.\n" +  
								"There was an error.");
			return false;
		}
	}
	
	/**
	 * @param acctNum - an integer representing the account number 
	 * 						of the account to be closed
	 * 
	 * @return boolean - true if the closing of the account was successful
	 * 						false otherwise
	 */
	public boolean closeAccount(int accNum) {
		return this.accounts.remove(accNum) == null ? false : true;
	}

	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account to withdraw from
	 * @param amount - a double representing the amount of money 
	 * 					to be withdrawn
	 * 
	 * @return boolean - true if the withdrawal was successful
	 * 						false otherwise
	 */
	public boolean withdraw(int accNum, 
							double amount) {
		try{
			if ( amount > this.accounts.get(new Integer(accNum)).getBalance() )
				throw new Exception();
			this.accounts.get(accNum).withdraw(amount);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account to deposit into
	 * @param amount - a double representing the amount of money 
	 * 					to be deposited
	 * 
	 * @return boolean - true if the deposit was successful
	 * 						false otherwise
	 */
	public boolean deposit(int accNum, 
						   double amount) {
		try{
			this.accounts.get(accNum).deposit(amount);
			return true;
		}
		catch (Exception e){
			return false;
		}		
	}

	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account to check the balance of
	 * 
	 * @return double - the balance of the account
	 */
	public double checkBalance(int accNum) {
		try {
			return this.accounts.get(accNum).getBalance();
		}
		catch (Exception e) {
			return 0.0;
		}		
	}

	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account to change the password
	 * @param newPass - a String that represents the new password 
	 * 						for the account
	 * 
	 * @return boolean - true if the password change was successful
	 * 						false otherwise
	 */
	public boolean changePassword(int accNum, 
								  String newPass) {
		try {
			this.accounts.get(accNum).changePassword(newPass);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account the new person will be added to
	 * @param p - the new Person 
	 * 
	 * @return boolean - true if the new person was added or 
	 * 							already exists in the list
	 * 						false otherwise
	 */
	public boolean authorizePerson(int accNum, 
								   Person p) {
		try {
			if (this.accounts.get(accNum).getAuthorizedPersons().contains(p))
				return true;
			this.accounts.get(accNum).addPerson(p);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account the said person will be removed from
	 * @param p - the Person to be removed
	 * 
	 * @return boolean - true if the person was removed successfully
	 * 						false otherwise
	 */
	public boolean unauthorizePerson(int accNum, 
									 Person p) {
		try {
			this.accounts.get(accNum).removePerson(p);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param accNumFrom - an integer representing the account number 
	 * 						of the account from which the funds are coming
	 * @param accNumTo - an integer representing the account number 
	 * 						of the account to which the funds are going
	 * @param amount - a double representing he amount to be transfered
	 * 
	 * @return boolean - true is the transfer was successful
	 * 						false otherwise
	 */
	public boolean transferFunds(int accNumFrom, 
								 int accNumTo, 
								 double amount) {
		try {
			if ( amount > this.accounts.get(accNumFrom).getBalance() )
				throw new Exception();
			this.accounts.get(accNumFrom).withdraw(amount);
			this.accounts.get(accNumTo).deposit(amount);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param accNum - an integer representing the account number 
	 * 					of the account to be transferred 
	 * @param p - the Person to be transferred to 
	 * @param newPass - a String that is the new password to the account
	 * 
	 * @return boolean - true if the transfer was successful
	 * 						false otherwise
	 */
	public boolean transferAccount(int accNum, 
								   Person p, 
								   String newPass) {
		try {
			this.accounts.get(accNum).changeOwner(p);
			this.accounts.get(accNum).changePassword(newPass);
			return true;
			
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param accounts2 - an ArrayList of BankAccounts representing 
	 * 							the BankAccounts in the bank
	 * 
	 * @return int - an integer representing a new BankAccount number 
	 * 					that is unique and different from all 
	 * 					the bank account numbers
	 * 					that exist already in the array
	 */
	private int genAccountNumber(HashMap<Integer, BankAccount> accounts) {
		Random r = new Random(System.currentTimeMillis());
		int accountNumber = r.nextInt(Integer.MAX_VALUE - 10000000) + 10000001;		
		
		while ( accounts.containsKey(accountNumber) )
			accountNumber = r.nextInt(Integer.MAX_VALUE - 10000000) + 10000001;
		
		return accountNumber;
	}

	public boolean openAccount(String firstName, 
							   String lastName, 
							   Date birthday,
							   String username, 
							   String password, 
							   double initDeposit,
							   AccountType at, 
							   ArrayList<Person> auth, 
							   int accNum) {
		try {
			this.accounts.put( accNum,
							   new BankAccount( new Person(firstName, lastName, birthday), 
												password, 
												username, 
												initDeposit, 
												at, 
												accNum, 
												auth ));
			return true; 
		}
		catch ( Exception e ){
			System.out.println("OOPS, we could not create an account for you.\n" +  
								"There was an error.");
			return false;
		}
		
	}
}
