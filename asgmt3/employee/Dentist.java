/* Course Name:CST8284	
Student Name: Ahmed Aziz
Class name: Dentist
Date: 26 October 2019
*/

package cst8284.asgmt3.employee;

/**
 * The Dentist class is used to hold information for every dentist that works in
 * the clinic
 * <p>
 * The class holds the name and the four activities a dentist can perform on any
 * patient and can print the selection of activites to choose from
 * 
 * @author Ahmed Aziz
 * @version 3.1
 */
public class Dentist extends Employee {

	/**
	 * A private String Array that hold the different Activity a Dentist can perform
	 */
	private static String[] workDescription = { "Assessment", "Filling", "Crown", "Cosmetic Repair" };

	/**
	 * One argument constructor that sets the full name of the dentist using the
	 * super constructor from the employee superclass
	 * 
	 * @param fullName Full name of the employee created
	 */
	public Dentist(String fullName) {
		super(fullName);
	}

	/**
	 * Overriding the getActibityType method from the superclass employee so it
	 * displays the four activity of dentists and takes the input of which activity
	 * he's going to perform
	 *
	 * @return String the element from the workDescription array chosen based on
	 *         user input
	 *
	 */
	@Override
	public String getActivityType() {
		System.out.println("Enter a selection from the following menu:");
		int i = 1;
		for (String description : workDescription) // for loop the go threw each element of the worddescription array to
													// print them out
			System.out.println(i++ + "." + description);
		int ch = Integer.valueOf(scan.nextLine().trim()); // get user input and parse it to an Int
		return workDescription[ch - 1]; // substract 1 from user input to get the index of the element based on the user
										// input
	}
}
