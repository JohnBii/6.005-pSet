package objects;

import enums.Month;


public class Person {
	
	/**
	 * 
	 */
	private String firstName;
	
	/**
	 * 
	 */
	private String lastName;
	
	/**
	 * 
	 */
	private final Date birthday;
	
	/**
	 * Constructor for a Person object
	 * 
	 * @param firstName
	 * @param lastName
	 * @param DOB
	 */
	public Person(String first, String last, Date d) {
		this.firstName = first;
		this.lastName = last;
		this.birthday = d;
	}
	
	/**
	 * Constructor for a Person object
	 * 
	 * @param firstName
	 * @param lastName
	 * @param year of birth
	 * @param month of birth
	 * @param day of birth
	 */
	public Person(String first, String last, int year, int month, int day){
		this.firstName = first;
		this.lastName = last;
		this.birthday = new Date(year, month, day);
	}
	
	/**
	 * Constructor for a Person object
	 * 
	 * @param firstName
	 * @param lastName
	 * @param year of birth
	 * @param month of birth
	 * @param day of birth
	 */
	public Person(String first, String last, int year, Month month, int day){
		this.firstName = first;
		this.lastName = last;
		this.birthday = new Date(year, month, day);
	}
	
	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * @return lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * @return DOB
	 */
	public Date getBirthday() {
		return this.birthday;
	}
	
	/**
	 * @return a new and exact copy the Person
	 */
	public Person clone() {
		return new Person(this.firstName, this.lastName, this.birthday.clone());
	}
	
	/**
	 * @return true - if the person has the same name and DOB
	 * 		   false otherwise
	 */
	public boolean equals(Object o) {
		try{
			Person p = (Person) o;
			return this.firstName.equalsIgnoreCase(p.firstName) &&
					this.lastName.equalsIgnoreCase(p.lastName) &&
					this.birthday.equals(p.birthday);
		}
		catch ( Exception e ) {
			return false;
		}
	}
	
	/**
	 * @return the person's name
	 */
	public String toString() {
		return this.lastName + ", " + this.firstName; 
	}
	
}
