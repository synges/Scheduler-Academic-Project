/* Course Name:CST8284	
Student Name: Ahmed Aziz
Class name: Activity
Date: 26 October 2019
*/

package cst8284.asgmt3.scheduler;

import java.io.Serializable;

/**
 * The Activity class store the info on the activity done in each appointment
 * <p>
 * The class stores the category of Activity as well as the description
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class Activity implements Serializable {

	/**
	 * Field to hold the description of what type of work is done in this activity
	 */
	private String descriptionOfWork;

	/**
	 * Field to gold the category type of the Activity to be done
	 */
	private String category;

	/**
	 * Used during deserialization to verify that the sender and receiver of a
	 * serialized object have loaded classes for that object that are compatible
	 * with respect to serialization
	 * 
	 * {@value #serialVersionUID} Value to be consistent with the professor's
	 * computer
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Two argument constructor used to instantiate a new Activity and set the
	 * values of the description and category
	 * 
	 * @param description The description of the activity
	 * @param category    The category that the activity belongs to
	 */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}

	/**
	 * Used to access the value of the Description field
	 * 
	 * @return String the value stored inside the description field
	 */
	public String getDescription() {
		return descriptionOfWork;
	}

	/**
	 * Used to modify the value of the Description field
	 * 
	 * @param s The value inputed into the Description field
	 */
	public void setDescription(String s) {
		this.descriptionOfWork = s;
	}

	/**
	 * Used to access the value of the Category field
	 * 
	 * @return String The value stored in the category field
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Used to modify the value of the Category field
	 * 
	 * @param s the value inputed into the category field
	 */
	public void setCategory(String s) {
		this.category = s;
	}

	/**
	 * Used to output the category and the description of the activity in string
	 * format
	 * 
	 * @return String String containing both the category and description of the
	 *         Activity
	 */
	public String toString() {
		return getCategory() + "\n" + getDescription(); // category field not used this Assignment
	}

}
