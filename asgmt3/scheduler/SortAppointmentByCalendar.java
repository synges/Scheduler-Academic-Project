package cst8284.asgmt3.scheduler;

import java.util.Comparator;

/**
 * The SortAppointmentByCalendar class is used to sort the Appointment ArrayList
 * <p>
 * The class provides a compare method that compares two Appointment objects
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {

	/**
	 * default no argument constructor used to instantiate a new sort object
	 */
	public SortAppointmentByCalendar() {
	}

	/**
	 * Used to compare two appointments based on the calendar time and returning a
	 * value that indicates which comes first
	 *
	 * @return int The value is either positive if the first appointment comes
	 *         first, negative if the second one comes first or zero if both
	 *         appointments have the same time
	 *
	 */
	@Override
	public int compare(Appointment a1, Appointment a2) {
		return a1.getAptDate().compareTo(a2.getAptDate()); // https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
	}

}
