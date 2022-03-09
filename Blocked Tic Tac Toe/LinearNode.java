/** 
 * CS2210 Assignment 1
 * @author Fengyi Zhu (Emily)
 * Student #: 250903071
 * Date: Oct 19, 2017
 * Class description: 	Represents a linear node storing TTTRecord objects.
 */
public class LinearNode<TTTRecord> {
	private TTTRecord element;
	private LinearNode<TTTRecord> next;
	
	/**
	 * Constructor creates a node with TTTRecord object specified through arguments.
	 */
	public LinearNode(TTTRecord data) {
		element = data;
		next = null;
	}
	
	/**
	 * Default constructor creates an empty node.
	 */
	public LinearNode() {
		element = null;
		next = null;
	}
	
	/**
	 * Gets TTTRecord in node.
	 * @return element 	object in node (T)
	 */
	public TTTRecord getElement() {
		return element;
	}
	
	/**
	 * Gets next node in the linked list
	 * @return next 	next node in linked list (LinearNode<TTTRecord>)
	 */
	public LinearNode<TTTRecord> getNext() {
		return next;
	}
	
	/**
	 * Sets link to next node in linked list
	 * @param data 	next node (LinearNode<TTTRecord>)
	 */
	public void setNext(LinearNode<TTTRecord> data) {
		next = data;
	}
}
