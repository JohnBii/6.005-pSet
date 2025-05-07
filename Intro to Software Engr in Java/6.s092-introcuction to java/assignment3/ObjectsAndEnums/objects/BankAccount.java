package objects;

import java.util.ArrayList;
import enums.AccountType;

public class BankAccount {
	
	/**
	 * A Person object that stores who the owner is
	 */
	private Person owner;
	
	/**
	 * A String object that represents the username for this account.
	 */
	private String username;
	
	/**
	 * A String that stores the owner's password
	 */
	private String password;
	
	
	/**
	 * A double that stores the balance of the account
	 */
	private double balance = 0.0;
	
	/**
	 * An ArrayList of Person objects that stores the authorized users of the account
	 */
	private ArrayList<Person> authorizedPersons = new ArrayList<Person>();
	
	/**
	 * An AccountType enum that stores the type of account 
	 */
	private AccountType at = AccountType.Personal;
	
	
	/**
	 * A long that stores the account number of this bank account
	 */
	private final long accountNumber;
	
	/**
	 * @param p - a Person who is the owner of the account
	 * @param password - a String that is the password to the account
	 * @param userName - A String that is the username of this bank account
	 * @param aN - a long that is the account number
	 */
	public BankAccount(Person p, 
					   String password, 
					   String userName, 
					   long aN) {
		this.authorizedPersons.add(p);
		this.owner = p;
		this.accountNumber = aN;
		this.password = password;
		this.username = userName;
	}
	
	/**
	 * @param p - a Person who is the owner of this account
	 * @param password - a String that is the password to the account
	 * @param userName - A String that is the username of this bank account
	 * @param balance - a double that is the balance 
	 * @param at - an AccountType enum that is the account type
	 * @param aN - a long that is the account number
	 * @param auth - an ArrayList of person that has the list of authorized users
	 */
	public BankAccount(Person p, 
					   String password, 
					   String userName,
					   double balance, 
					   AccountType at, 
					   long aN, 
					   ArrayList<Person> auth) {
		this.authorizedPersons = auth;
		this.authorizedPersons.add(p);
		this.owner = p;
		this.accountNumber = aN;
		this.password = password;
		this.at = at;
		this.balance = balance;
		this.username = userName;
		
	}

	/**
	 * @return Person - the owner of this bank account
	 */
	public Person getOwner() {
		return owner.clone();
	}

	/**
	 * @return double - the balance on this account
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return ArrayList<Person> - the authorized persons list
	 */
	public ArrayList<Person> getAuthorizedPersons() {
		ArrayList<Person> newList = new ArrayList<Person>();
		for (Person p : this.authorizedPersons) {
			newList.add(p.clone());
		}
		return newList;
	}

	/**
	 * @return String - the password of this account
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return AccountType - what this account's account type is
	 */
	public AccountType getAt() {
		return at;
	}

	/**
	 * @return long - this account's account number
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return String - this account's username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @param newUsername - a String that is the new username for this account
	 */
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	
	/**
	 * @param ArrayList<Person> - the ArrayList that is the list of all the authorized persons
	 */
	public void setAuthorizedPersons(ArrayList<Person> authorizedPersons) {
		this.authorizedPersons = authorizedPersons;
	}

	/**
	 * @param AccountType - the account type the account will be set to
	 */
	public void setAt(AccountType at) {
		this.at = at;
	}
	
	/**
	 * @param double - the amount to set the balance of this bank account
	 */
	public void setBalance(double d) {
		this.balance = d;	
	}

	/**
	 * @param amount - a double representing the amount of money to be deposited
	 * @return boolean - true - if the amount was added to this account.
	 */
	public boolean deposit(double amount){
		try{
			this.balance += amount;
			return true;
		}
		catch ( Exception e ){
			return false;
		}
	}
	
	/**
	 * @param amount - a double representing the amount to withdraw
	 * @return true - if the withdrawal was successful
	 * 			false - otherwise 
	 */
	public boolean withdraw(double amount) {
		try {
			if ( amount > this.balance )
				return false;
			this.balance -= amount;
			return true;
		}
		catch ( Exception e ){
			return false;
		}
	}
	
	/**
	 * @param newPass - a String that represents the new password for this account
	 * @return true - if the password change was successful
	 * 			false - otherwise
	 */			
	public boolean changePassword(String newPass) {
		try {
			this.password = newPass;
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param p - the Person to be added to the authorized users list
	 * @return true - if the person was successfully added
	 * 			false - otherwise
	 */
	public boolean addPerson(Person p) {
		if ( this.authorizedPersons.contains(p) )
			return true;
		return this.authorizedPersons.add(p);
	}
	
	/**
	 * @param p - the Person to be removed from the authorized users list
	 * @return true - if the removal of the person was successful
	 * 			false- otherwise
	 */
	public boolean removePerson(Person p) {
		return this.authorizedPersons.remove(p);
	}
	
	/**
	 * @param p - the Person who is the new owner
	 * @return true - if the change in owner was successful
	 * 			false - otherwise
	 */
	public boolean changeOwner(Person p) {
		try {
			this.authorizedPersons.remove(this.owner);
			this.owner = p;
			this.authorizedPersons.add(p);
			return true;
		}
		catch(Exception e) {
			// Multiple lines of code
			
			return false;
		}
	}
	
	/**
	 * @return BankAccount - a clone of this bank account that is a new and exact copy
	 */
	public BankAccount clone() {
		return new BankAccount(owner.clone(), 
							   password, 
							   username, 
							   balance, 
							   at, 
							   accountNumber, 
							   getAuthorizedPersons());
	}

	
	
}
