package objects;

import java.util.ArrayList;

/**
 * These helper function may be used if desired but it is NOT necessary to use these.
 * 
 * @author Hau Lian
 * @version Friday, January 23rd, 2015 2323 EST
 */
public class BankHelperMethods {

	/**
	 * @param index - an integer that is the index of the BankAccount in the ArrayList of BankAccounts
	 * @param person - the Person who is to be authenticated
	 * @param b - the ArrayList of BankAccounts
	 * 
	 * @return boolean - true if the Person is authorized to access the given account
	 * 						false otherwise
	 */
	public static boolean checkIfPersonIsAuthorized(int index, Person person, ArrayList<BankAccount> b) {
		for ( Person p : b.get(index).getAuthorizedPersons() ) {
			if ( p.equals(person) )
				return true;
		}
		return false;
	}

	/**
	 * @param index - an integer that is the index of the BankAccount in the ArrayList of BankAccounts
	 * @param password - a String that is the password to be checked
	 * @param b - an ArrayList of Bank accounts
	 * 
	 * @return boolean - true if the password at the given index bank account in the ArrayList matches
	 * 						false otherwise
	 */
	public static boolean checkPassword(int index, String password, ArrayList<BankAccount> b) {
		return b.get(index).getPassword().equals(password);
	}

	/**
	 * @param index - an integer representing the index of the BankAccount to access
	 * @param person - the Person to be checked
	 * @param b - the ArrayList of BankAccounts
	 * 
	 * @return boolean - true if the person is the owner of the BankAccount at index
	 * 						false otherwise
	 */
	public static boolean checkIfPersonIsOwner(int index, Person person, ArrayList<BankAccount> b) {
		return b.get(index).getOwner().equals(person);
	}
}
