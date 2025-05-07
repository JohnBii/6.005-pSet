package objects;

import java.util.Calendar;

import enums.Day;
import enums.Month;

/**
 * This class represents a date in a Gregorian calendar. 
 * Objects of this class are immutable.
 * 
 * @author 	Hau Lian
 * @version 1 - 01/21/2015
 */
public class Date {
	
	/**
	 * A private integer variable used to keep track of the year
	 */
	private final int YEAR;
	
	/**
	 * A private Month enum used to keep track of the month
	 */
	private final Month MONTH;
	
	/**
	 * A private integer used to keep track of the day of the month
	 */
	private final int DAY_NUM;
	
	/**
	 * A private Day enum used to keep track of the day of the week
	 */
	private final Day DAY;
	
	/**
	 * Constructs a Date object given the year and day of the month as integers and
	 * 	the month as a Month enum.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(int year, Month month, int day) {
		this.YEAR = year;
		this.MONTH = month;
		this.DAY_NUM = day;
		this.DAY = getDay(year, month, day);
	}
	
	/**
	 * Constructs a Date object given the year, month and day of the month as integers
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(int year, int month, int day) {
		this.YEAR = year;
		this.MONTH = getMonth(month);
		this.DAY_NUM = day;
		this.DAY = getDay(year, month, day);
	}
	
	/**
	 * @return integer - the year of this date
	 */
	public int getYEAR() {
		return this.YEAR;
	}

	/**
	 * @return Month - the month of this date
	 */
	public Month getMONTH() {
		return this.MONTH;
	}

	/**
	 * @return integer - the day of the month
	 */
	public int getDAY_NUM() {
		return this.DAY_NUM;
	}

	/**
	 * @return Day - the day of week
	 */
	public Day getDAY() {
		return this.DAY;
	}
	
	/**
	 * @return String - "(DayOfWeek), (Month) (DayNumber), (Year)"
	 */
	public String getDayAndDate() {
		return this.DAY + ", " + this.MONTH + " " + 
				this.DAY_NUM + ", " + this.YEAR;
	}
	
	/**
	 * @return Date - a new and exact copy 
	 */
	public Date clone() {
		return new Date(this.YEAR, this.MONTH, this.DAY_NUM);
	}
	
	/**
	 * @return true - if the dates are equal
	 * 		   false - otherwise
	 */
	public boolean equals(Object o) {
		try{
			Date d = (Date) o;
			return this.YEAR == d.YEAR &&
					this.MONTH == d.MONTH &&
					this.DAY_NUM == d.DAY_NUM;
		}
		catch ( Exception e ) {
			return false;
		}
	}
	
	/**
	 * @return String - mm/dd/yyyy
	 */
	public String toString() {
		return (this.MONTH.ordinal()+1) + "/" + 
				this.DAY_NUM + "/" + this.YEAR;
	}
	
	/**
	 * @param month - an integer representation of the Month of a year
	 * @return Month - the month of the year as a Month enum
	 */
	public static Month getMonth(int month) {
		switch(month){
			case 1: return Month.January;
			case 2: return Month.February;
			case 3: return Month.March;
			case 4: return Month.April;
			case 5: return Month.May;
			case 6: return Month.June;
			case 7: return Month.July;
			case 8: return Month.August;
			case 9: return Month.September;
			case 10: return Month.October;
			case 11: return Month.November;
			case 12: return Month.December;
			default: return null;
		}
	}
	
	/**
	 * @param year - an integer representing the year
	 * @param month - an integer representing the month
	 * @param day - an integer representing the day of the month
	 * @return Day - the day of the week as a Day enum
	 */
	public static Day getDay(int year, int month, int day) {
		return getDay(year, getMonth(month), day);
	}
	
	/**
	 * @param year - an integer representing the year
	 * @param month - a Month enum representing the month of the year
	 * @param day - an integer representing the day of the month
	 * @return Day - the day of the week as a day enum
	 */
	public static Day getDay(int year, Month month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month.ordinal()+1, day);
		switch(c.get(Calendar.DAY_OF_WEEK)){
			case 1: return Day.Sunday;
			case 2: return Day.Monday;
			case 3: return Day.Tuesday;
			case 4: return Day.Wednesday;
			case 5: return Day.Thursday;
			case 6: return Day.Friday;
			case 7: return Day.Saturday;
			default: return null;
		}		
	}

}
