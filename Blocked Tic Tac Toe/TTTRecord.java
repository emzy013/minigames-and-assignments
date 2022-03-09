/** 
 * CS2210 Assignment 1
 * @author Fengyi Zhu (Emily)
 * Student #: 250903071
 * Date: Oct 19, 2017
 * Class description: 	Represents a record of a tic-tac-toe gameboard configuration.
 */
public class TTTRecord {
	String config;
	int score, level;
	
	/**
	 * Constructor that creates a record.
	 * @param config	configuration of gameboard (String)
	 * @param score		score attributed to the configuration (int)
	 * @param level		number of moves into the game (int)
	 */
	public TTTRecord(String config, int score, int level) {
		this.config = config;
		this.score = score;
		this.level = level;
	}
	
	/**
	 * Gets the configuration.
	 * @return config	configuration of record (String)
	 */
	public String getConfiguration() {
		return config;
	}
	
	/**
	 * Gets the score.
	 * @return score	score of configuration (int)
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the level.
	 * @return level	number of moves on record (int)
	 */
	public int getLevel() {
		return level;
	}
}
