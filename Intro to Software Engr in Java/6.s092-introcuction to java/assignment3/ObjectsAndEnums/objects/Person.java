package objects;


/**
 * @author Hau Lian
 */
public class Person {
	
	/**
	 * A String that stores the first name of a person
	 */
	private String firstName;
	
	/**
	 * A String that stores the last name of a person
	 */
	private String lastName;
	
	/**
	 * A Date that stores the date of birth of a person
	 */
	private final Date birthday;
	
	/**
	 * Constructs a Person object given a first name, last name and date of birth
	 * 
	 * @param firstName - a String of the first name of the person
	 * @param lastName - a String of the last name of the person
	 * @param DOB - a Date of the person's date of birth
	 */
	public Person(String first, String last, Date d) {
		this.firstName = first;
		this.lastName = last;
		this.birthday = d;
	}
	
	/**
	 * Constructs a Person object given a name (first last), and a date of birth
	 * 
	 * @param name - a String of the person's full name (first last)
	 * @param DOB - a Date of the person's date of birth
	 */
	public Person(String name, Date d) {
		this.firstName = name.split(" ")[0];
		this.lastName = name.split(" ")[1];
		this.birthday = d;
	}
	
	/**
	 * @return String - the first name of this person
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * @return String - the last name of this person
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * @return Date - the date of birth of this person
	 */
	public Date getBirthday() {
		return this.birthday;
	}
	
	/**
	 * @return Person - a new and exact copy the Person
	 */
	public Person clone() {
		return new Person(this.firstName, this.lastName, this.birthday.clone());
	}
	
	/**
	 * @return true - if the person has the same name and DOB
	 * 		   false - otherwise
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
	 * @return String - the person's name (Last, First)
	 */
	public String toString() {
		return this.lastName + ", " + this.firstName; 
	}
	
}
