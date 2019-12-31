/* Course Name:CST8284	
Student Name: Ahmed Aziz
Class name: Employee
Date: 26 October 2019
*/

package cst8284.asgmt3.employee;

import java.util.Scanner;

/**
 * The Employee class is an abstract class that provides the methods implemented for any kind of employee
 * <p>
 * The class can hold the name of the employee, the activity type that is done by the employee, a scanner to scan the activity is subclasses and a to string method to print the employee name 
 * 
 * @author Ahmed Aziz
 * @version 3.1
 */
public abstract class Employee {
	
	/**
	 * A private string field to hold the full name of the employee
	 */
	private String fullName;
	
	/**
	 * A protected no argument constructor with the default full name unknown that chains to the one argument constructor
	 */
	protected Employee() {this("unknown");}
	
	/**
	 * A protected one argument constructor that takes a string representing the full name and use the setName method to set the fullName private field
	 * 
	 * @param fullName the string containing the full name of the employee 
	 */
	protected Employee(String fullName) {setName(fullName);}
	
	/**
	 * A protected scanner object used to read input from the user
	 */
	protected static Scanner scan = new Scanner(System.in);
	
	/**
	 * A method used to modify the fullName field with the full name of the employee
	 * 
	 * @param fullName the full name of the employee
	 */
	public void setName(String fullName) {this.fullName = fullName;}
	
	/**
	 * A method used to access the fullName field 
	 * 
	 * @return String full name of the employee
	 */
	public String getName() {return fullName;}
	
	/**
	 * An abstract method used to store the Activity of any employee
	 * 
	 * @return String the Activity the employee is doing
	 */
	public abstract String getActivityType();
	
	/**
	 * An abstract to string method used the output the name of the employee
	 *
	 *@return String the name of the employee
	 */
	@Override
	public String toString() {return getName();}
	
}