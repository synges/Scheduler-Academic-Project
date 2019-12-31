/* Course Name:CST8284	
Student Name: Ahmed Aziz
Class name: Scheduler
Date: 26 October 2019
*/

package cst8284.asgmt3.scheduler;

import java.util.Scanner;

import cst8284.asgmt3.employee.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * The Scheduler class holds all the methods that can manipulate and change the
 * whole schedule
 * <p>
 * The class holds all the menu options that the user can interact with and all
 * the methods that can make, change or delete an appointment as well as the
 * methods that can display the schedule, save it to a file or load it from a
 * file
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class Scheduler {

	/**
	 * A private scanner object used to read input from the user
	 */
	private static Scanner input = new Scanner(System.in);

	/**
	 * An Appointment ArrayList that is used to store all the appointments created
	 * in the scheduler
	 */
	private ArrayList<Appointment> appointments = new ArrayList<>();

	/**
	 * The employee that the Schedule is for
	 */
	private Employee employee;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * SAVE_APPOINTMENT option in the menu, it's value is
	 * {@value #SAVE_APPOINTMENT}.
	 */
	private static final int SAVE_APPOINTMENT = 1;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * DELETE_APPOINTMENT option in the menu, it's value is
	 * {@value #DELETE_APPOINTMENT}.
	 */
	private static final int DELETE_APPOINTMENT = 2;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * CHANGE_APPOINTMENT option in the menu, it's value is
	 * {@value #CHANGE_APPOINTMENT}.
	 */
	private static final int CHANGE_APPOINTMENT = 3;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * DISPLAY_APPOINTMENT option in the menu, it's value is
	 * {@value #DISPLAY_APPOINTMENT}.
	 */
	private static final int DISPLAY_APPOINTMENT = 4;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * DISPLAY_SCHEDULE option in the menu, it's value is
	 * {@value #DISPLAY_SCHEDULE}.
	 */
	private static final int DISPLAY_SCHEDULE = 5;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * SAVE_APPOINTMENTS_TO_FILE option in the menu, it's value is
	 * {@value #SAVE_APPOINTMENTS_TO_FILE}.
	 */
	private static final int SAVE_APPOINTMENTS_TO_FILE = 6;

	/**
	 * A final field that holds the number needed as an input to choose the
	 * LOAD_APPOINTMENTS_FROM_FILE option in the menu, it's value is
	 * {@value #LOAD_APPOINTMENTS_FROM_FILE}.
	 */
	private static final int LOAD_APPOINTMENTS_FROM_FILE = 7;

	/**
	 * A final field that holds the number needed as an input to choose the EXIT
	 * option in the menu, it's value is {@value #EXIT}.
	 */
	private static final int EXIT = 0;

	/**
	 * One argument constructor used to instantiate a scheduler for a certain
	 * Employee
	 * 
	 * @param emp The Employee that the schedule is done for
	 */
	public Scheduler(Employee emp) {
		loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments()); // load the Array list of Appointments
																					// from the file if one already
																					// exists
		setEmployee(emp);
	}

	/**
	 * The launch method is the first method called from the scheduler object to
	 * display the menu and start taking inputs from the user by calling the
	 * displayMenu method, then it calls the executeMenuItem with an input from the
	 * return value from the displayMenu method to execute the choice of the user.
	 * All this is repeated by a do while loop until the user chooses to EXIT
	 */
	public void launch() {
		int choiceRepeat; // used choiceRepeat to hold the value returned from displayMenu so I don't have
							// to call display menu 2 times (1 inside the loop and 1 in the while condition)
		System.out.println("Scheduling appointments for " + getEmployee().getName());
		do { // do while loop to keep repeating the menu until the user chooses EXIT
			choiceRepeat = displayMenu();
			System.out.println("");
			try {
				executeMenuItem(choiceRepeat);
			} catch (BadAppointmentDataException ex) { // to catch all the BadAppointmentDataExceptionthrown during data
														// input
				System.out.println("\n" + ex.getDescription() + ": " + ex.getMessage() + "\n"); //
			}

		} while (choiceRepeat != EXIT);
		System.out.println("Exiting Scheduler");
	}

	/**
	 * Used to modify the value of the employee field of the Scheduler
	 * 
	 * @param employee the new employee that the Schedule is for
	 */
	private void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Used to access the value of the employee field of the Scheduler
	 * 
	 * @return Employee the employee that the Schedule is for
	 */
	private Employee getEmployee() {
		return employee;
	}

	/**
	 * Displays all the menu options to the user and reads the input from the
	 * keyboard and return its value
	 * 
	 * @return int The value corresponding to the menu option the user chose
	 */
	private int displayMenu() {
		System.out.println("Enter a selection from the following menu:\n" + SAVE_APPOINTMENT + ". Save appointment\n"
				+ DELETE_APPOINTMENT + ". Remove appointment\n" + CHANGE_APPOINTMENT + ". Change appointment\n"
				+ DISPLAY_APPOINTMENT + ". Get appointment\n" + DISPLAY_SCHEDULE + ". Display schedule\n"
				+ SAVE_APPOINTMENTS_TO_FILE + ". Backup appointments to file\n" + LOAD_APPOINTMENTS_FROM_FILE
				+ ". Load appointments from file\n" + EXIT + ". Exit program");

		return Integer.valueOf(input.nextLine().trim()); // to avoid scanner bug we take the feedback from the user as a
															// string and convert it to an int
	}

	/**
	 * The method is used to execute the option that the user chose, it takes a int
	 * value corresponding to which menu item was chosen and using a switch
	 * statement it can determine the code needed to start executing that option
	 * 
	 * @param choice The number inputed by the user in the displayMenu method which
	 *               is used to determine which menu item gets executed
	 */
	private void executeMenuItem(int choice) {

		switch (choice) { // the choice variable holding the user input from the menu is passed to the
							// switch case
		case SAVE_APPOINTMENT:
			if (saveAppointment(makeAppointmentFromUserInput())) // calls the save appointment method with the input as
																	// the Appointment returned from
																	// makeAppointmentFromUserInput method
				System.out.println("\nAppointment saved."); // prints this if the appointment is saved and the
															// saveAppointment returns true

			else
				System.out.println("Cannot save; an appointment at that time already exists");
			System.out.println("");
			break;
		case DELETE_APPOINTMENT:
			if (deleteAppointment(makeCalendarFromUserInput(false))) // deletes an appointment by calling the
																		// deleteAppointment method and by passing a
																		// Calendar object with the date and time, input
																		// for makeCalendarFromUserInput is false so we
																		// can specify the time in addition to the day
				System.out.println("Appointment deleted");
			else
				System.out.println("No Appointment was deleted");
			System.out.println("");
			break;
		case CHANGE_APPOINTMENT:
			if (changeAppointment(makeCalendarFromUserInput(false))) // changes an appointment date and time by calling
																		// the changeAppointment method and by passing a
																		// Calendar object with the new date and time,
																		// input for makeCalendarFromUserInput is false
																		// so we can specify the time in addition to the
																		// day
				System.out.println("Appointment re-booked");
			else
				System.out.println("No Appointment was re-booked");
			System.out.println("");
			break;
		case DISPLAY_APPOINTMENT:
			displayAppointment(makeCalendarFromUserInput(false)); // displays an appointment by calling the
																	// displayAppointment method and by passing a
																	// Calendar object with the date and time, input for
																	// makeCalendarFromUserInput is false so we can
																	// specify the time in addition to the day
			System.out.println("");
			break;
		case DISPLAY_SCHEDULE:
			displayDaySchedule(makeCalendarFromUserInput(true)); // displays the schedule for a whole day by calling the
																	// displayDaySchedule method and by passing a
																	// Calendar object with the date, input for
																	// makeCalendarFromUserInput is true so we specify
																	// the day only with no time
			System.out.println("");
			break;
		case SAVE_APPOINTMENTS_TO_FILE:
			if (saveAppointmentsToFile(getAppointments(), "CurrentAppointments.apts")) // saves the ArrayList of
																						// Appointments to the a file by
																						// calling the
																						// saveAppointmentToFile method
																						// and passing the ArraysList
																						// and file name as parameters
				System.out.println("Appointment data saved to\nCurrentAppointments.apts");
			System.out.println("");
			break;
		case LOAD_APPOINTMENTS_FROM_FILE:
			if (loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments())) // loads the ArrayList of
																							// Appointments from the a
																							// file by calling the
																							// loadAppointmentsFromFile
																							// method and passing file
																							// name and the ArraysList
																							// as parameters
				System.out.println("Appointments successfully loaded from\nCurrentAppointments.apts");
			System.out.println("");
			break;
		case EXIT:
			saveAppointmentsToFile(getAppointments(), "CurrentAppointments.apts"); // save the Appointments on Exit
			break;
		default:
			System.out.println("Invalid input: Please input a number between 1 and 7 or 0 to Exit"); // message to be
																										// displayed
																										// when any
																										// other invalid
																										// option is
																										// inputed
			break;
		}
	}

	/**
	 * The method is used to add the appointment passed as a parameter to the Array
	 * List after checking if the Appointment already exists or not by using the
	 * findAppointment method, then it sorts the Array list using the
	 * SortAppointmentByCalendar class
	 * 
	 * @param apt The appointment to be added to the list
	 * @return boolean returns true if saving the appointment was successful or
	 *         false if it didn't save
	 */
	private boolean saveAppointment(Appointment apt) {
		if (findAppointment(apt.getAptDate()) == null) { // checks if the findAppointment found any Appointment with the
															// same time and if returns null then the time is free
			getAppointments().add(apt); // adds the appointment
			Collections.sort(getAppointments(), new SortAppointmentByCalendar()); // sorts the array list
			return true;
		}
		return false;
	}

	/**
	 * The method is used to delete an appointment from the Array List by passing a
	 * Calendar object that have the date and time of the appointment required to be
	 * deleted
	 * 
	 * @param cal Calendar object with date and time of the Appointment needed to be
	 *            deleted
	 * @return boolean returns true if appointment is successfully deleted and false
	 *         if appointment is not deleted
	 */
	private boolean deleteAppointment(Calendar cal) {
		Appointment am = findAppointment(cal); // findAppointment returns the appointment with the specified date and
												// time passed as a Calendar object
		if (am != null) { // checks that the appointment exists
			displayAppointment(cal);
			if (getResponseTo("Enter 'Yes' to delete this appointment ").trim().equals("Yes")) { // confirmation to
																									// delete the
																									// appointment
				getAppointments().remove(am); // removing the appointment from the Array List
				return true;
			}
		} else
			System.out.println("Coudn't find appointment at that time");
		return false;
	}

	/**
	 * The method is used to change the time of a certain appointment. A calendar
	 * object is passed as a parameter that contains the date and time of the
	 * appointment that want to be changed, the method then checks if an appointment
	 * already exists at that time and then make a new calendar from user input to
	 * take the new date and time
	 * 
	 * @param cal Calendar object containing the date and time of the appointment
	 *            wanted to be changed
	 * @return boolean returns true if the appointment date and time changed
	 *         successfully
	 */
	private boolean changeAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		if (apt != null) { // checks that the appointment exists or not
			displayAppointment(cal); // displays the appointment wanted to be changed
			if (getResponseTo("Enter 'Yes' to change the date and time of this appointment ").trim().equals("Yes")) { // confirmation
																														// to
																														// change
																														// the
																														// appointment
																														// date
																														// or
																														// time
				System.out.println("Enter new date and time");
				Calendar newCal = makeCalendarFromUserInput(false); // making a new calendar from the user with the new
																	// date and time
				if (findAppointment(newCal) == null) { // tests if the new cal date and time taken from user dosen't
					apt.setAptDate(newCal); // already have another appointment at the same time
					return true;
				} else
					System.out.println("An appointment already exists at this time");
			}
		} else
			System.out.println("Coudn't find appointment at that time");
		return false;
	}

	/**
	 * The method is used to display the appointment associated with the date and
	 * time of the calendar object passed as a parameter
	 * 
	 * @param cal Calendar object containing date and time of the appointment to be
	 *            displayed
	 */
	private void displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal); // finding the appointment
		if (apt == null) { // if no appointment to be found
			System.out.println("No appointment scheduled between " + cal.get(Calendar.HOUR_OF_DAY) + ":00 and "
					+ (cal.get(Calendar.HOUR_OF_DAY) + 1) + ":00");
		} else {
			System.out.println("");
			System.out.println(apt.toString()); // if appointment found print it using it's toString method
			System.out.println("");
		}
	}

	/**
	 * The method is used to display the schedule for a whole day
	 * 
	 * @param cal Calendar Object containing only the day wanting to be displayed
	 */
	private void displayDaySchedule(Calendar cal) {
		for (int i = 8; i <= 16; i++) { // using for loop to set the hour of the calendar object to each hour of the day
										// that might have an appointment from 8 to 16
			cal.set(Calendar.HOUR_OF_DAY, i);
			displayAppointment(cal); // displaying the apointments found
		}

	}

	/**
	 * The method is used to save the Appointments Array list to a file, The method
	 * takes the Array list as a parameter and the name of the save file
	 * 
	 * @param apts     The Array list of type Appointments to be save in the file
	 * @param saveFile the name of the file for the Array list to be saved at
	 * @return boolean returns true if the save the the file was successful
	 */
	private static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {

		File myFile = new File(saveFile); // https://stackoverflow.com/questions/32326157/file-not-found-w-fileinputstream
		if (!myFile.exists()) { // checks if the file exists or not
			try {
				myFile.createNewFile(); // creates the file if it didn't exist before
			} catch (IOException e) {
				System.out.println("coudn't create file");
			}
		}

		try (FileOutputStream objectFileStream = new FileOutputStream(myFile);
				ObjectOutputStream oos = new ObjectOutputStream(objectFileStream);) {
			for (Appointment apt : apts) // writes each appointment from the Array list to the file
				oos.writeObject(apt);
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("file not found and can't be created ");
			return false;
		} catch (IOException ex) {
			return false;
		}
	}

	/**
	 * The method is used to load an Array list of Appointments from a file
	 * 
	 * @param sourceFile the name of the file to be loaded from
	 * @param apts       the name of the Arrays list to be filled with the objects
	 *                   read from the file
	 * @return boolean returns true if the load was successful
	 */
	private static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {

		File myFile = new File(sourceFile); // https://stackoverflow.com/questions/32326157/file-not-found-w-fileinputstream
		if (!myFile.exists()) { // checks if the file exist
			try {
				myFile.createNewFile(); // creates the file if dosen't exist
			} catch (IOException e) {
				System.out.println("coudn't create file");
			}
		}

		apts.clear(); // clears the current Array List

		try (FileInputStream objectFileStream = new FileInputStream(myFile);
				ObjectInputStream ois = new ObjectInputStream(objectFileStream);) {

			while (true) {
				apts.add((Appointment) (ois.readObject())); // reading each object from the file and casting it to
															// Appointment before adding it to the Arrays List
			}
		} catch (EOFException ex) { // used to check that all objects were loaded
			return true;
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		} catch (ClassNotFoundException ex) {
		}
		return false;
	}

	/**
	 * The method is used to print a certain prompt and receive the input from the
	 * user afterwards, also a check is done to ensure no empty or null value
	 * provided
	 * 
	 * @param s Prompt to be displayed to the user
	 * @return String The response the user provided
	 * @throws BadAppointmentDataException The exception is thrown when the user
	 *                                     inputs an empty or null value
	 */
	private static String getResponseTo(String s) {
		System.out.print(s);
		String a = input.nextLine();
		if (a == null || a.isEmpty()) // if the input from the user is null or empty then throw the exception
			throw new BadAppointmentDataException("Must enter a value", "Empty or null value entered");
		return a;

	}

	/**
	 * The method is used to create an Appointment from the user input, the method
	 * calls all other responsible method for creating the the Calendar for the date
	 * and time of the appointment, the Activity to be done at the appointment, the
	 * phone number of the customer and the name of the customer to be stored
	 * 
	 * @return Appointment The Appointment created from all the info the user
	 *         provided
	 * @throws BadAppointmentDataException The exception is thrown when the full
	 *                                     name inputed is not in the correct format
	 *                                     or the first or last name exceeds 30
	 *                                     characters
	 */
	private Appointment makeAppointmentFromUserInput() {

		String fullName = getResponseTo("Enter Client Name (as FirstName LastName): "); // prompts and stores the full
																						// name of the customer

		if (fullName.trim().split(" ").length != 2) { // checks if the full name is compromised of two names with the
														// space between or not
			throw new BadAppointmentDataException( // throw the exception if the full name format is wrong
					"Only one name inputed, you need your First and Last name seperated by a space",
					"Missing First or Last Name");
		}

		for (String s : fullName.trim().split(" ")) { // split the first and last name into an array of 2 strings and
														// iterate over them
			for (char c : s.toCharArray()) { // check each character in each string
				if ((c < 'A' && c != '-' && c != '.' && c != '‘') || c > 'z' || (c > 'Z' && c < 'a')) { // if the
																										// character is
																										// one that is
																										// not allowed
																										// throw the
																										// exception
					throw new BadAppointmentDataException(
							"Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (‘)",
							"Illegal characters in name");
				}
			}
			if (s.length() > 30) // if one of the names exceeded 30 characters then throw the exception
				throw new BadAppointmentDataException("Name cannot exceed 30 characters",
						"Name exceeds maximum length");
		}

		TelephoneNumber phone = new TelephoneNumber(getResponseTo("Phone Number (e.g. 613-555-1212): ")); // prompts and
																											// stores
																											// the phone
																											// number of
																											// the
																											// customer

		return (new Appointment(makeCalendarFromUserInput(false), fullName, phone, // make a Calendar Object with the
																					// date and time of the Appointment
				new Activity(getResponseTo("Enter Activity: "), getEmployee().getActivityType()))); // makes a new
																									// Activity and
																									// passes it to the
																									// Appointment
																									// constructor as
																									// one of it's
																									// parameters and
																									// and then the
																									// method returns
																									// the new
																									// appointment made

	}

	/**
	 * The methods creates a calendar object from the date and time inputed by the
	 * user, that is passed as the calendar field of Appointments
	 * 
	 * @param suppressHour used to indicate if the hour field of the calendar needed
	 *                     to be prompted for or not
	 * @return Calendar Object stores the date and time of an appointment
	 * @throws BadAppointmentDataException if the date entered by the user isn't in
	 *                                     the correct format or is not a real date
	 */
	private static Calendar makeCalendarFromUserInput(boolean suppressHour) {

		Calendar cal = Calendar.getInstance(); // instantiate a calendar object
		cal.clear(); // clear the fields of the calendar
		cal.setLenient(false); // used to make inputs to the calendar checked for correctness
								// https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
		String date = getResponseTo("Appointment Date (entered as DDMMYYYY): "); // prompts the user for a date and
																					// stores the string provided
		try {
			cal.set(Integer.valueOf(date.substring(4, 8)), Integer.valueOf(date.substring(2, 4)) - 1, // try catch used
																										// to see if we
																										// can parse the
																										// date
					Integer.valueOf(date.substring(0, 2)));
			cal.getTime(); // used to try to get the time and if the date is not a real date it will throw
							// an exception
		} catch (Exception ex) { // catching all exception associated with the date input
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY",
					"Bad calendar format");
		}

		// https://www.tutorialspoint.com/java/util/calendar_setfield4.htm
		// set year, month, day in that order
		if (!suppressHour)
			cal.set(Calendar.HOUR_OF_DAY, processTimeString(getResponseTo("Appointment Time: "))); // https://stackoverflow.com/questions/460293/how-do-you-set-the-time-and-only-the-time-in-a-calendar-in-java
		else
			cal.set(Calendar.HOUR_OF_DAY, 0);
		return cal;
	}

	/**
	 * The method is used to process the time string entered by the user regardless
	 * of the format inputed in
	 * 
	 * @param t the time input by the user in string
	 * @return int return the time as an integer ranging from 8 to 16 depending on
	 *         the time of the day
	 */
	private static int processTimeString(String t) {
		int x = Integer.valueOf(t.split(" ")[0].split(":")[0]); // splits the string at the space and the at the colon
																// if exists to only get the actual hour
																// https://beginnersbook.com/2013/12/java-string-split-method-example/
		if (x < 8)
			x += 12; // add 12 hours to make it in 24 hour format
		return (x);
	}

	/**
	 * The method is used to search the Array list for appointments with the same
	 * date and time as the Calendar Object passed in as the parameter
	 * 
	 * @param cal Calendar object with the date and time of the appointment that
	 *            need to be found
	 * @return Appointment returns the Appointment with the same date and time as
	 *         the Calendar object passed, if there is no appointment at the
	 *         specified time then the method returns null
	 */
	private Appointment findAppointment(Calendar cal) {
		Collections.sort(getAppointments(), new SortAppointmentByCalendar()); // sorts the array list before searching
																				// using the SortAppointmentByCalendar
																				// class
		int index = Collections.binarySearch(getAppointments(), new Appointment(cal, "firstName lastName", null, null), // binary
																														// search
																														// threw
																														// the
																														// sorted
																														// Array
																														// list
																														// using
																														// a
																														// new
																														// appointment
																														// with
																														// the
																														// calendar
																														// object
																														// passed
																														// to
																														// the
																														// method
				new SortAppointmentByCalendar()); // gets index of appoint if it exists
		if (index < 0) { // if the index of the binary search returns a negative value then appointment
							// dosen't exist
			return null;
		} else
			return getAppointments().get(index);
	}

	/**
	 * Used to access the values of the Appointment Array List field
	 * 
	 * @return ArrayList The Arrays lists that stores all the appointments
	 */
	private ArrayList<Appointment> getAppointments() {
		return appointments;
	}

}
