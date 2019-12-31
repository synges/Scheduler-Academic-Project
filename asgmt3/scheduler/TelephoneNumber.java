/* Course Name:CST8284	
Student Name: Ahmed Aziz
Class name: TelephoneNumber
Date: 26 October 2019
*/

package cst8284.asgmt3.scheduler;

import java.io.Serializable;

/**
 * The TelephoneNumber class is used to store the phone number of each patient
 * <p>
 * The class store the breaks down the phone number to are code, prefix and line
 * number and validates and store them into their relative variables
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class TelephoneNumber implements Serializable {

	/**
	 * field to hold the area code part of the phone number
	 */
	private int areaCode;

	/**
	 * field to hold the prefix part of the phone number
	 */
	private int prefix;

	/**
	 * field to hold the line number part of the phone number
	 */
	private int lineNumber;

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
	 * One argument constructor used to take the phone number as a string and split
	 * it into the three components of area code, prefix and line number converting
	 * them into integers and checking if each field have the appropriate length
	 * 
	 * @param phoneNumber the phone number typed by the user
	 * @throws BadAppointmentDataException exception is thrown when there is missing
	 *                                     digits in the phone number, the phone
	 *                                     number format is wrong or the phone
	 *                                     number contain characters that aren't
	 *                                     number or the character '-'
	 */
	public TelephoneNumber(String phoneNumber) {

		for (char c : phoneNumber.toCharArray()) { // for loop to check each character of the telephone string if it's a
													// bad character
			if (c != '-' && c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7'
					&& c != '8' && c != '9') {
				throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character ‘-‘",
						"Bad character(s) in input string");
			}
		}

		try {
			String sAreaCode = phoneNumber.split("-")[0]; // https://beginnersbook.com/2013/12/java-string-split-method-example/
			String sPrefix = phoneNumber.split("-")[1]; // splits the phone number based on the location of the dash
			String sLineNumber = phoneNumber.split("-")[2];

			if (sAreaCode.length() == 3 && sPrefix.length() == 3 && sLineNumber.length() == 4) { // checks if each part
																									// of the The number
																									// has the correct
																									// length
				setAreaCode(Integer.valueOf(sAreaCode)); // converts the string to int and sets each field with the
															// correct value
				setPrefix(Integer.valueOf(sPrefix));
				setLineNumber(Integer.valueOf(sLineNumber));
			} else
				throw new BadAppointmentDataException(
						"Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number",
						"Incorrect format");
		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new BadAppointmentDataException(
					"Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number",
					"Incorrect format");
		}
	}

	/**
	 * Used to access the area code part of the phone number
	 * 
	 * @return int the areaCode field value
	 */
	public int getAreaCode() {
		return areaCode;
	}

	/**
	 * Used to check if the area code is correct and modify the value of the
	 * areaCode field
	 * 
	 * @param areaCode the area code part of the phone number the user inputed
	 * @throws BadAppointmentDataException Exception is thrown if the Area code is
	 *                                     invalid and starts with '0' or '1'
	 */
	public void setAreaCode(int areaCode) {
		if (areaCode < 200)
			throw new BadAppointmentDataException("Area code can’t start with a ‘0’ or a ‘1’", "Invalid number");
		this.areaCode = areaCode;
	}

	/**
	 * Used to access the prefix part of the phone number
	 * 
	 * @return int the prefix part of the phone number
	 */
	public int getPrefix() {
		return prefix;
	}

	/**
	 * Used to modify the value of the prefix field
	 * 
	 * @param prefix the prefix part of the phone number the user inputed
	 */
	public void setPrefix(int prefix) {
		this.prefix = prefix;
	}

	/**
	 * Used to access the value of the LineNumber field of the class
	 * 
	 * @return int the line number part of the phone number
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Used to modify the value of the LineNumber field
	 * 
	 * @param lineNumber the line number part of the phone number the user inputed
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Used to output the phone number in string format using all three components
	 * of the number
	 * 
	 * @return String the phone number in string format
	 */
	public String toString() {
		return ("(" + getAreaCode() + ") " + getPrefix() + "-" + getLineNumber());
	}
}
