/** 
 * CS2210 Assignment 1
 * @author Fengyi Zhu (Emily)
 * Student #: 250903071
 * Date: Oct 19, 2017
 * Class description: 	Represents a dictionary that implements a hash table and separate chaining using a linked list 
 * 						to store records of tic-tac-toe gameboard configurations.
 */
public class TTTDictionary {
	int size;
	LinearNode[] hashTable;
	
	/**
	 * Default constructor creates a dictionary of size 4999.
	 */
	public TTTDictionary() {
		size = 4999;
		hashTable = new LinearNode[size];
	}
	
	/**
	 * Constructor creates a dictionary of specified size.
	 * @param size	size of dictionary (int)
	 */
	public TTTDictionary(int size) {
		this.size = size;
		hashTable = new LinearNode[size];
	}
	
	/**
	 * Inserts entry into the dictionary through a hash table and checks for duplicated entries.
	 * @param record	record of gameboard (TTTRecord)
	 * @return 	0	if no collision encountered (int)
	 * @return	1	if collision encounter (int)
	 * @throws DuplicatedKeyException	if entry already in dictionary
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		int collisionVal = 0;
		if(get(record.getConfiguration()) != null)	//attempts to retrieve record to check if already in dictionary
			throw new DuplicatedKeyException(record.getConfiguration());
		else {
			int code = getCode(record.getConfiguration());	//gets hashcode for entry and stores in variable code
			if(hashTable[code] == null)	//no collision encountered
				hashTable[code] = new LinearNode(record);
			else {	//collision encountered
				collisionVal = 1;
				LinearNode pointer = hashTable[code];	//accesses linked list in collision location
				while(pointer.getNext() != null)
					pointer = pointer.getNext();
				pointer.setNext(new LinearNode(record));	//adds entry to end of linked list
			}
		}
		return collisionVal;
	}
	
	/**
	 * Removes entry from the dictionary using a hash table and checks if the entry is inexistent.
	 * @param config	key of entry/configuration of gameboard (String)
	 * @throws InexistentKeyException	if entry is inexistent in dictionary
	 */
	public void remove(String config) throws InexistentKeyException {
		int code = getCode(config);	//gets hashcode for entry
		if(hashTable[code] == null)
			throw new InexistentKeyException(config);
		else {
			LinearNode pointer = hashTable[code];
			if(((TTTRecord)pointer.getElement()).getConfiguration().equals(config))	//removes entry if it is at the head of linked list
				hashTable[code] = pointer.getNext();
			else {
				do {
					if(pointer.getNext() == null)	//throws exception if entry isn't found in the linked list
						throw new InexistentKeyException(config);
					else if(((TTTRecord)pointer.getNext().getElement()).getConfiguration().equals(config)) {	//removes entry if it is in the linked list
						pointer.setNext(pointer.getNext().getNext());
						break;
					}
					else
						pointer = pointer.getNext();
				} while(true);
			}
		}
	}
	
	/**
	 * Gets entry associated with the provided key.
	 * @param config	key of entry/configuration of gameboard (String)
	 * @return 	null			if entry not found
	 * @return	TTTRecord obj	if entry found
	 */
	public TTTRecord get(String config) {
		int code = getCode(config); //gets hashcode for entry
		if(hashTable[code] == null)	//nothing at hashcode location and entry is not found
			return null;
		LinearNode pointer = hashTable[code];	//if hashcode location not empty
		do {	
			if(((TTTRecord)pointer.getElement()).getConfiguration().equals(config))	//entry found
				return (TTTRecord) pointer.getElement();
			else
				pointer = pointer.getNext();
		} while(pointer != null);
		return null;	//if entry not found
	}
	
	/**
	 * Counts number of entries in the dictionary.
	 * @return count	number of entries (int)
	 */
	public int numElements() {
		int count = 0;
		for (int i = 0; i < hashTable.length; i ++)
			if(hashTable[i] != null) {
				LinearNode pointer = hashTable[i];
				count ++;
				while(pointer.getNext() != null) {
					pointer = pointer.getNext();
					count ++;
				}
			}
		return count;
	}
	
	/**
	 * Private method that returns the hashcode of a string.
	 * @param config	key (String)
	 * @return code		hashcode of string (int)
	 */
	private int getCode(String config) {
		int code = config.codePointAt(0);
		for(int i = 1; i < config.length(); i ++)
			code = (code * 10 + config.codePointAt(i)) % size;
		return code;
	}
}