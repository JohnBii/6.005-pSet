package hello;

import java.util.Scanner;

public class HelloWorldRunner {
	public static void main(String[] args){
		//printing out things to the console
		System.out.println("Hello World!");
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.MIN_VALUE);
		
		// Accept user input in main
		// Scanner is a class that allows us to accept user input
		Scanner userInput = new Scanner(System.in); // Declaration and Initialization
		System.out.println("Enter an integer.");//message to user
		int firstNum = userInput.nextInt(); // storing user's response
		System.out.println("Enter another integer."); // message to user
		int secondNum = userInput.nextInt(); //storing user's response
		
		// Print out the sum of user's inputs
		// add(firstNum, secondNum) calls the add function below
		// 		and prints whatever is returned
		System.out.println(add(firstNum, secondNum));
		
		
		
	}
	
	//Make a method that adds two numbers (int)
	// from user
	// input
	public static int add(int num1, int num2){
		return num1 + num2;
	}
	
}
