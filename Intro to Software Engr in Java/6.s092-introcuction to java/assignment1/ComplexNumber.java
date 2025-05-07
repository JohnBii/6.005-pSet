package assignment1;

/**
 * 
 * @version Tuesday, January 6th, 2015 1038 // CHANGE THIS last after you have edited the code and are satisfied.  Then save.
 * @author 	Hau Lian, YOUR NAME 
 *
 */
public class ComplexNumber {
	
	/* 
	 * create a variable to keep track of the real value
	 * it should only be accessible by this class 
	 * it should be a constant and should NEVER change its value
	 * name the variable real
	 * 
	 * TODO: Implement this variable.  The function of this variable is in blue right below. 
	 */
	/**
	 * A constant double value that stores the real part of a complex number
	 */
	
	/* 
	 * create a variable to keep track of the imaginary value
	 * it should only be accessible by this class 
	 * it should be a constant and should NEVER change its value
	 * name the variable real
	 * 
	 * * TODO: Implement this variable.  The function of this variable is in blue right below. 
	 */
	/**
	 * A constant double value that stores the imaginary part of a complex number
	 */
	
		
	/**
	 * Constructor - Creates a new ComplexNumber object 
	 * 					that has a real and imaginary part
	 * 
	 * @param real - a double value representing the value of the real part of a complex number
	 * @param img - a double value representing the value of the imaginary part of a complex number
	 */
	public ComplexNumber(double real, double img){
		/*
		 * TODO: Assign the two instance field that you created above their respective values
		 */
	}
	
	/**
	 * This method returns the sum of this object with the argument.
	 * 
	 * @param other - a complex number that is added to this object 
	 * @return ComplexNumber - an instance of ComplexNumber that represents 
	 * 							the sum of this object and the argument
	 */
	public ComplexNumber add(ComplexNumber other){
		return ComplexNumber.add(this, other);
	}
	
	/**
	 * This method returns the difference of this object with the argument in that order
	 * 
	 * @param other - a complex number that is subtracted from this object
	 * @return ComplexNumber - an instance of ComplexNumber that represents
	 * 							the difference of this object and the argument 
	 */
	public ComplexNumber subtract(ComplexNumber other){
		return ComplexNumber.subtract(this, other);
	}
	
	/**
	 * This method returns the product of this object with the argument
	 * 
	 * @param other - a complex Number that is multiplied with this object
	 * @return ComplexNumber - an instance of ComplexNumber that represents 
	 * 							the product of this object with the argument
	 */
	public ComplexNumber multiply(ComplexNumber other){
		return ComplexNumber.multiply(this, other);
	}
	
	/*
	 * GETTER METHODS
	 */
	
	/**
	 * This method returns the double value of the real part of the complex number
	 * 
	 * @return double - the value of the real part of the complex number
	 */
	public double getRealVal(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return 0.0;
	}
	
	/**
	 * This method returns the double value of the imaginary part of the complex number
	 * 
	 * @return double - the value of the imaginary part of the complex number
	 */
	public double getImgVal(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return 0.0;
	}
	
	/**
	 * This method returns the polar form of this complex number
	 * 
	 * @return double [] - a size-2 array of double that has
	 * 						the real value at index 0
	 * 						the imaginary value at index 1
	 */
	public double[] polarForm(){
		return getPolarForm(this);
	}
	
	/**
	 * This method returns the complex conjugate of the complex number
	 * 
	 * @return ComplexNumber - an instance of ComplexNumber that represents 
	 * 							the complex conjugate of this object
	 */
	public ComplexNumber complexConjugate(){
		return ComplexNumber.complexConjugate(this);
	}
	
	/**
	 * This method returns the String representation of the polar form of this complex number
	 * 
	 * @return String - the complex number in polar form
	 */
	public String getPolarForm(){
		double [] ans = ComplexNumber.getPolarForm(this);
		return "(" + ans[0] + ", " + ans[1] + ")";
	}
	
	/*
	 * END OF GETTER METHODS
	 */
	
	/*
	 * STATIC METHODS
	 */
	
	/**
	 * This method returns the polar form of the argument
	 * 
	 * @param c - an instance of ComplexNumber
	 * @return double [] - a size-2 array of double 
	 * 						r is at index 0
	 * 						theta is at index 1
	 */	
	public static double[] getPolarForm(ComplexNumber c) {
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns the complex conjugate of the argument
	 * 
	 * @param c - an instance of ComplexNumber
	 * 
	 * @return ComplexNumber - an instance representing the complex conjugate of the argument
	 */
	public static ComplexNumber complexConjugate(ComplexNumber c){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}

	/**
	 * This method returns the sum of the two complex number.
	 * 
	 * @param a - an instance of ComplexNumber
	 * @param b - an instance of ComplexNumber
	 * 
	 * @return ComplexNumber - an instance representing the sum of a and b
	 */
	public static ComplexNumber add(ComplexNumber a, ComplexNumber b){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns the difference of the two complex number.
	 * 
	 * @param a - an instance of ComplexNumber
	 * @param b - an instance of COmplexNumber
	 * 
	 * @return ComplexNumber - an instance representing the difference of b from a in that order
	 */
	public static ComplexNumber subtract(ComplexNumber a, ComplexNumber b){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns the product of two complex numbers
	 * 
	 * @param a - an instance of ComplexNumber
	 * @param b - an instance of COmplexNumber
	 * 
	 * @return ComplexNumber - an instance representing the product of a and b
	 */
	public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/*
	 * END OF STATIC METHODS
	 */
	
	/*
	 * OVERRIDING METHODS
	 */
	
	/**
	 * This method checks if another object is equal to this object.
	 * 
	 * Another object is equal to this object if:
	 * 	the real values AND the imaginary values of this and the other object are equal respectively. 
	 */
	public boolean equals(Object o){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return false;
	}
	
	/**
	 * This method returns a new copy of this object
	 */
	public ComplexNumber clone(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;
	}
	
	/**
	 * This method returns the string representation of a ComplexNumber
	 */
	public String toString(){
		/*
		 * TODO: Implement this method.  The function of this method is in blue right above the method signature.
		 */
		return null;		
	}
	
	/*
	 * END OF OVERRIDING METHODS
	 */

}
