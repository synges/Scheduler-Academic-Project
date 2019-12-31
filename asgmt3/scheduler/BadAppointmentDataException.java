package cst8284.asgmt3.scheduler;

/**
 * The BadAppointmentDataException class is used the handle exception with data
 * input
 * <p>
 * The class provides the description of the exceptions thrown when bad data is
 * entered
 * 
 * @author Ahmed Aziz
 * @version 3.1
 *
 */
public class BadAppointmentDataException extends RuntimeException {

	/**
	 * Used during deserialization to verify that the sender and receiver of a
	 * serialized object have loaded classes for that object that are compatible
	 * with respect to serialization
	 * 
	 * {@value #serialVersionUID} Value to be consistent with the professor's computer
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds a description of the output message stating an explanation as why the
	 * exception thrown for
	 */
	private String description;

	/**
	 * No argument constructor that chains to the two argument constructor and pass
	 * to it two default values
	 */
	public BadAppointmentDataException() {
		this("Please try again", "Bad data entered");
	}

	/**
	 * Two argument constructor that takes in two messages the first one is set as
	 * the out put of the getMessage method in the super class and the other is set
	 * to the description of the error
	 * 
	 * @param s1 The message outputed to the user when a certain exception is thrown
	 * @param s2 The description of the message stating an explanation as why the
	 *           exception thrown for
	 */
	public BadAppointmentDataException(String s1, String s2) {
		super(s1);
		setDescription(s2);
	}

	/**
	 * A method used to modify the description field with the value provided
	 * 
	 * @param description The description of the message stating an explanation as
	 *                    why the exception thrown for
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Used to access the value of the description field
	 * 
	 * @return String the description of the exception thrown
	 */
	public String getDescription() {
		return description;
	}

}
