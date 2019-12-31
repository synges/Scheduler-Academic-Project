/* Course Name:CST8284		
Student Name: Ahmed Aziz
Class name: SchedulerLauncher
Date: 26 October 2019
*/

package cst8284.asgmt3.scheduler;

import cst8284.asgmt3.employee.*;

/**
 * The SchedulerLauncher class is used to launch the program
 * <p>
 * The class contains the main method and when run will instatiate a new
 * Scheduler object
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class SchedulerLauncher {

	/**
	 * The main method used as the point to start the program
	 * 
	 * @param args An array of strings that stores command line arguments
	 */
	public static void main(String[] args) {
		(new Scheduler(new Dentist("Dr. Andrews"))).launch(); // initiating a new scheduler object for a new dentist
																// class parameter called Dr.Andrews and calling the
																// launch method of the Scheduler Object
	}

}
