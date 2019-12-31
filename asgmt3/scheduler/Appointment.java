/* Course Name:CST8284
Student Name: Ahmed Aziz
Class name: Appointment
Date: 26 October 2019
*/

package cst8284.asgmt3.scheduler;

import java.util.Calendar;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * The Appointment class is used to instantiate objects which store each
 * appointment info
 * <p>
 * The class stores the time and date of the appointment, the first and last
 * name of the patient, the phone number of the patient and the activity to be
 * done on the patient
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class Appointment implements Serializable {

	/**
	 * Field holds a calendar object that has the date and time of the appointment
	 */
	private Calendar aptDate;

	/**
	 * Field holds the first name for the person who have the appointment
	 */
	/**
	 * Field holds the last name for the person who have the appointment
	 */
	private String firstName, lastName;

	/**
	 * Field holds the telephone number of the person who have the appointment
	 */
	private TelephoneNumber phone;

	/**
	 * Field to hold the activity that is going to be conducted during the
	 * appointment
	 */
	private Activity activity;

	/**
	 * Used during deserialization to verify that the sender and receiver of a
	 * serialized object have loaded classes for that object that are compatible
	 * with respect to serialization
	 * 
	 * {@value #serialVersionUID} Value to be consistent with the professor's computer
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * 
	 * Four arguments constructor that takes in a Calendar object, the full name of
	 * the customer, the phone number of the customer and the Activity to be
	 * conducted during the appointment then the constructor chains to the five
	 * argument constructor after splitting the full name into first and last name
	 * 
	 * @param cal      Calendar object containing the date and time of the
	 *                 appointment
	 * @param fullName Full name of the customer
	 * @param phone    Phone number of the customer
	 * @param activity Activity to be conducted during the appointment
	 */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity activity) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, activity); // https://beginnersbook.com/2013/12/java-string-split-method-example
		// full name is split at the space to a string array containing the first name
		// and last name
	}

	/**
	 * Five argument constructor used to instantiate an Appointment that takes in a
	 * Calendar object, First and last name of the customer, the phone number of the
	 * customer and the activity to be conducted during the appointment
	 * 
	 * @param cal       Calendar object containing the date and time of the
	 *                  appointment
	 * @param firstName First name of the customer
	 * @param lastName  Last name of the customer
	 * @param phone     Phone number of the customer
	 * @param activity  Activity to be conducted during the appointment
	 */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity) {
		setAptDate(cal);
		setFirstName(firstName);
		setLastName(lastName);
		setPhone(phone);
		setActivity(activity);
	}

	/**
	 * Used to access the value of the Calendar field of the Appointment
	 * 
	 * @return Calendar the calendar field that contain the time of the appointment
	 */
	public Calendar getAptDate() {
		return aptDate;
	}

	/**
	 * Used to modify the value of the Calendar field of the Appointment
	 * 
	 * @param aptDate the new Calendar to be set for the Appointment
	 */
	public void setAptDate(Calendar aptDate) {
		this.aptDate = aptDate;
	}

	/**
	 * Used to access the value of the firstName field of the Appointment
	 * 
	 * @return String the first name of the customer
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Used to modify the value of the firstName field of the Appointment
	 * 
	 * @param firstName The modified first name of the customer
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Used to access the value of the lastName field of the Appointment
	 * 
	 * @return String the last name of the customer
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Used to modify the value of the lastName field of the Appointment
	 * 
	 * @param lastName The modified last name of the customer
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Used to access the value of the TelephoneNumber field of the Appointment
	 * 
	 * @return TelephoneNumber An object of type TelephoneNumber that contains the
	 *         phone number of the customer
	 */
	public TelephoneNumber getPhone() {
		return phone;
	}

	/**
	 * Used to modify the value of the TelephoneNumber field of the Appointment
	 * 
	 * @param phone the modified TelephoneNumber of the customer
	 */
	public void setPhone(TelephoneNumber phone) {
		this.phone = phone;
	}

	/**
	 * Used to access the value of the Activity field of the Appointment
	 * 
	 * @return Activity The Activity field associated with the Appointment
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * Used to modify the value of the Activity field of the Appointment
	 * 
	 * @param activity the new modified Activity for the Appointment
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Method that returns the string that displays the customer name and phone
	 * number, appointment date and the Activity to be conducted
	 * 
	 * @return String The String to be displayed with the Appointment info
	 */
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm"); // https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
		return (df.format(getAptDate().getTime()) + "\n" + getFirstName() + " " + getLastName() + "\n"
				+ getPhone().toString() + "\n" + getActivity().toString());
	}

}
