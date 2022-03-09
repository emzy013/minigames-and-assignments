/**
 * This exception is thrown by TTTDictionary when trying to remove an object not stored in the dictionary.
 * 
 * @author Fengyi Zhu
 * Student #: 250903071
 * Date: Oct 19, 2017
 * CS 2210 Assignment 1
 */
public class InexistentKeyException extends Exception {
	/**
	 * Sets up this exception with an appropriate message.
	 * @param config	configuration key of object that is trying to be removed (String)
	 */
	public InexistentKeyException (String config){
		super("Error: Configuration " + config + " is not in dictionary. ");
	}
}
