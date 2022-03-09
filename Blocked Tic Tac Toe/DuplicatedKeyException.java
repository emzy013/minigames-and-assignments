/**
 * This exception is thrown by TTTDictionary when trying to store under an already-stored key.
 * 
 * @author Fengyi Zhu
 * Student #: 250903071
 * Date: Oct 19, 2017
 * CS 2210 Assignment 1
 */
public class DuplicatedKeyException extends Exception {
	/**
	 * Sets up this exception with an appropriate message.
	 * @param config	configuration key that is trying to be stored (String)
	 */
	public DuplicatedKeyException (String config) {
		super("Error: " + config + " configuration is already in dictionary. ");
	}
}
