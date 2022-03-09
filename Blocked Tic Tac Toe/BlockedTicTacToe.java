/** 
 * CS2210 Assignment 1
 * @author Fengyi Zhu (Emily)
 * Student #: 250903071
 * Date: Oct 19, 2017
 * Class description: 	Represents a gameboard object where blocked-tic-tac-toe is played.
 */
public class BlockedTicTacToe {
	final char HUMAN = 'x', COMPUTER = 'o';
	int board_size, inline, max_levels;
	char[][] gameBoard;
	
	/**
	 * Constructor creates a BlockedTicTacToe object.
	 * @param board_size	size of gameboard (int)
	 * @param inline		how many connected in a row needed to win (int)
	 * @param max_levels	maximum moves computer allowed to predict (int)
	 */
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameBoard = new char[board_size][board_size];
		for(int i = 0; i < board_size; i ++)
			for(int j = 0; j < board_size; j ++)
				gameBoard[i][j] = ' ';
	}
	
	/**
	 * Creates a dictionary for the game to store possible configurations.
	 * @return TTTDictionary obj	empty dictionary
	 */
	public TTTDictionary createDictionary() {
		return new TTTDictionary();
	}
	
	/**
	 * Checks if the current configuration has been stored in the dictionary and returns the score of the configuration.
	 * @param configurations	dictionary of configurations (TTTDictionary)
	 * @return 	-1			if configuration not already stored in dictionary (int)
	 * @return	int type	score if configuration found in dictionary
	 */
	public int repeatedConfig(TTTDictionary configurations) {
		TTTRecord found = configurations.get(getConfig());	//calls getConfig method to obtain current configuration in string format
		if(found == null)
			return -1;
		else
			return(found.getScore());
	}
	
	/**
	 * Records the configuration into the dictionary.
	 * @param configurations	dictionary storing configurations (TTTDictionary)
	 * @param score				score of configuration (int)
	 * @param level				number of moves into the game (int)
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		try {
			configurations.put(new TTTRecord(getConfig(), score, level));
		}
		catch(DuplicatedKeyException e) {	//catches exception if the configuration is already in the dictionary
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Makes a play on the gameboard and modifies gameboard.
	 * @param row		row played (int)
	 * @param col		column played (int)
	 * @param symbol	symbol played (char)
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * Checks if square is empty.
	 * @param row	row played (int)
	 * @param col	column played (int)
	 * @return true		if square is empty (boolean)
	 * @return false	if square isn't empty (boolean)
	 */
	public boolean squareIsEmpty(int row, int col) {
		return gameBoard[row][col] == ' ';
	}
	
	/**
	 * Checks if the symbol has won the game.
	 * @param symbol	symbol being checked (char)
	 * @return true		if game is won (boolean)
	 * @return false	if game is not won (boolean)
	 */
	public boolean wins(char symbol) {
		String winningStr = "";
		for(int i = 0; i < inline; i ++)	//creates the string required to win
			winningStr = winningStr.concat(Character.toString(symbol));
		for(int i = 0; i < board_size; i ++) {	//creates strings of the configuration of separate rows and columns on the gameboard
			String s1 = "", s2 = "";
			for(int j = 0; j < board_size; j ++) {
				s1 = s1.concat(Character.toString(gameBoard[i][j]));
				s2 = s2.concat(Character.toString(gameBoard[j][i]));
			}
			if(s1.contains(winningStr) || s2.contains(winningStr))	//checks if symbol has won in any rows or columns
				return true;
		}
		for(int i = 0; i < board_size - inline + 1; i ++) {	//creates string of the configuration of separate downwards diagnols on the gameboard
			int j = i, k = 0;
			String s1 = "", s2 = "";
			while(j < board_size) {
				s1 = s1.concat(Character.toString(gameBoard[j][k]));
				s2 = s2.concat(Character.toString(gameBoard[k][j]));
				j ++;
				k ++;
			}
			if(s1.contains(winningStr) || s2.contains(winningStr))	//checks if symbol has won in any downwards diagnols
				return true;
		}
		for(int i = inline - 1; i < board_size; i ++) {	//creates string of the configuration of separate upwards diagnols on the gameboard
			int j = i, k = 0;
			String s1 = "", s2 = "";
			while(j >= 0) {
				s1 = s1.concat(Character.toString(gameBoard[j][k]));
				s2 = s2.concat(Character.toString(gameBoard[board_size-1-k][board_size-1-j]));
				j --;
				k ++;
			}
			if(s1.contains(winningStr) || s2.contains(winningStr))	//checks if symbol has won in any upwards diagnols
				return true;
		}
		return false;	//symbol has not won in any of the above configurations
	}
	
	/**
	 * Checks if the game has come to a draw.
	 * @return true		game has come to a draw (boolean)
	 * @return false	game hasn't come to a draw (boolean)
	 */
	public boolean isDraw() {
		for(int i = 0; i < board_size; i ++)
			for(int j = 0; j < board_size; j ++)
				if(squareIsEmpty(i, j))	//game is not a draw if still exists empty spaces
					return false;
		if(wins(HUMAN) || wins(COMPUTER))	//game not a draw if one side wins
			return false;
		else
			return true;
	}
	
	/**
	 * Returns the score of the current configuration.
	 * @return 0	human wins (int)
	 * @return 1	game has drawn (int)
	 * @return 2	game is still continuing (int)
	 * @return 3	computer wins (int)
	 */
	public int evalBoard() {
		if(wins(COMPUTER))
			return 3;
		else if(wins(HUMAN))
			return 0;
		else if(isDraw())
			return 1;
		else
			return 2;
	}
	
	/**
	 * Returns the string representation of the configuration of the gameboard.
	 * @return config	configuration of gameboard (String)
	 */
	private String getConfig() {
		String config = "";
		for(int i = 0; i < board_size; i ++)
			for(int j = 0; j < board_size; j ++)
				config = config.concat(Character.toString(gameBoard[i][j]));
		return config;
	}
}
