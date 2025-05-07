package objects;

import java.util.Calendar;

import enums.Day;
import enums.Month;

/**
 * This class represents a date in a Gregorian calendar
 * @author 	Hau Lian
 * @version 01/12/2015
 */
public class Date {
	
	/**
	 * 
	 */
	private final int YEAR;
	
	/**
	 * 
	 */
	private final Month MONTH;
	
	/**
	 * 
	 */
	private final int DAY_NUM;
	
	/**
	 * 
	 */
	private final Day DAY;
	
	/**
	 * Constructor for a Date object
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
	 * Constructor for a Date object
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
	 * @return the year 
	 */
	public int getYEAR() {
		return this.YEAR;
	}

	/**
	 * @return the month
	 */
	public Month getMONTH() {
		return this.MONTH;
	}

	/**
	 * @return the day of month
	 */
	public int getDAY_NUM() {
		return this.DAY_NUM;
	}

	/**
	 * @return the day of week
	 */
	public Day getDAY() {
		return this.DAY;
	}
	
	/**
	 * @return a String that represent the day as
	 * 			"(DayOfWeek), (Month) (DayNumber), (Year)"
	 */
	public String getDayAndDate() {
		return this.DAY + ", " + this.MONTH + " " + 
				this.DAY_NUM + ", " + this.YEAR;
	}
	
	/**
	 * @return a new and exact copy 
	 */
	public Date clone() {
		return new Date(this.YEAR, this.MONTH, this.DAY_NUM);
	}
	
	/**
	 * @return true if the dates are equal
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
	 * @return mm/dd/yyyy
	 */
	public String toString() {
		return (this.MONTH.ordinal()+1) + "/" + 
				this.DAY_NUM + "/" + this.YEAR;
	}
	
	public static Month getMonth(int month) {
		switch(month){
			case 1: return Month.JANUARY;
			case 2: return Month.FEBRUARY;
			case 3: return Month.MARCH;
			case 4: return Month.APRIL;
			case 5: return Month.MAY;
			case 6: return Month.JUNE;
			case 7: return Month.JULY;
			case 8: return Month.AUGUST;
			case 9: return Month.SEPTEMBER;
			case 10: return Month.OCTOBER;
			case 11: return Month.NOVEMBER;
			case 12: return Month.DECEMBER;
			default: return null;
		}
	}
	
	public static Day getDay(int year, int month, int day) {
		return getDay(year, getMonth(month), day);
	}
	
	public static Day getDay(int year, Month month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month.ordinal()+1, day);
		switch(c.get(Calendar.DAY_OF_WEEK)){
			case 1: return Day.SUNDAY;
			case 2: return Day.MONDAY;
			case 3: return Day.TUESDAY;
			case 4: return Day.WEDNESDAY;
			case 5: return Day.THURSDAY;
			case 6: return Day.FRIDAY;
			case 7: return Day.SATURDAY;
			default: return null;
		}		
	}

}
