package assignment1;

/**
 * 
 * @author YOUR NAME
 *
 */
public class Stringx {
	
	/* 
	 * create a variable to keep track of the string
	 * it should only be accessible by this class 
	 * it should be a constant and should NEVER change its value
	 * name the variable s
	 * 
	 * TODO: Implement this variable.  The function of this variable is in blue right below. 
	 */
	/**
	 * A constant String object that stores the String
	 */
	
	/**
	 * Constructor - Creates a new Stringx object that has added funtionality 
	 *  				to the String class.
	 * @param in
	 */
	public Stringx(String in){
		/*
		 * TODO: Assign the instance field that you created above to the instance field
		 */
	}
	
	/**
	 * This method returns a substring from the given index until the end of the string.
	 * 	if index < 0 then position is counted from the right
	 * 	if index is >= string length or index < -(stringLength + 1) return an empty string
	 * 
	 * @param index - an int representing the index of the desired substring
	 * @return a String representing the substring
	 */
	public String substring(int index){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns a substring between the given beginning index (inclusive) and ending index (exclusive)
	 * 	beginIndex > 0 and endingIndex > 0 and begin < end  OR
	 * 	beginIndex < 0 and endingIndex < 0 and begin > end
	 * 
	 * @param beginIndex - an int representing the beginning of the substring
	 * @param endIndex - an int representing the end of the substring
	 * @return a String that represents the substring at specified indices
	 */
	public String substring(int beginIndex, int endIndex){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method creates a new and exact copy of this object
	 * 
	 * @return a Stringx object that is the same as this object
	 */
	public Stringx clone(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns true iff this object and the comparing object 
	 * 	have the same values in their instance fields
	 * 
	 * @return a boolean - true if this object equals comparing object
	 * 					   false otherwise
	 */
	public boolean equals(Object o){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return false;
	}
	
	/**
	 * This method returns the string representation of this object.
	 * 
	 * @return a String representation of this object.
	 */
	public String toString(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}

}
