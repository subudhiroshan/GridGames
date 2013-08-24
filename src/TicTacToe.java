import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Author: Roshan Subudhi
 * Purpose: Elavon Interview 
 * Function: This class starts off the Tic-Tac-Toe game.
 * Date: 05/06/2013
 * License: Creative Commons 3.0 License 
 */

public class TicTacToe {

	static String[][] grid = new String[3][3]; // a 2-D array to represent the game
	static String winningCombinations[] = {"012", "345", "678", "036", "147", "258", "048", "246"};
	// all possible winning combinations to check for
	
	//This function initializes the grid to be null.
	static void initTicTacToe(){
		
		for (int i=0; i < grid.length; i++){
			for (int j=0; j < grid[i].length; j++){
				grid[i][j] = " ";
			}
		}
	}

	
	//This function takes the player's input, updates the grid after performing input validations
	static void userInput(int player) throws NumberFormatException, IOException{
		int rowNum = 0 , colNum = 0;
		String flag;
		if (player == 0){
			flag = "X";
		}
		else{
			flag = "O";
		}
		System.out.println("Player " + (player+1) + ": Place your '" + flag + "' -> ");
		try{
//			while(rowNum<1 && rowNum>3){
				System.out.print("Row:");
				BufferedReader row = new BufferedReader(new InputStreamReader (System.in));
				rowNum = Integer.parseInt(row.readLine());
				if ((rowNum > grid.length + 1) || (rowNum < 1)){
					System.out.println("Wrong Row Input. End.");
					System.exit(0);
				}
//			}
//			while(rowNum<1 && rowNum>3){
				System.out.print("Column:");
				BufferedReader col = new BufferedReader(new InputStreamReader (System.in));
				colNum = Integer.parseInt(col.readLine());
				if ((colNum > grid.length + 1) || (colNum < 1)){
					System.out.println("Wrong Column Input. End.");
					System.exit(0);
				}
//			}
		}catch(NumberFormatException nfe){
			System.out.println("Please enter numbers only! End.");
			System.exit(0);
		}
		
		if (grid[rowNum-1][colNum-1].equals(" ")){
			grid[rowNum-1][colNum-1] = flag;
		}else{
			System.out.println("That cell is already occupied! End.");
			System.exit(0);
		}
	}
	
	//This function checks whether any player has won the game after every move.
	static boolean performCheck(int player){
		String flag;
		StringBuilder current = new StringBuilder();//a string to hold the current status of a given player
		
		if (player == 0){
			flag = "X";
		}
		else{
			flag = "O";
		}
		
		for (int i=0; i < grid.length; i++){
			for (int j=0; j < grid[i].length; j++){
				if (grid[i][j].equals(flag)){
					current.append(i*3+j);
				}
			}
		}
		//System.out.println("Player's current is " + current.toString());
		int count = 0; // a counter to check if 3 X's or O's form a line
		for (int i=0; i< winningCombinations.length; i++){
			count = 0;
			for (int j=0; j<winningCombinations[i].length() ; j++){
				//System.out.println("WC character is " + winningCombinations[i].substring(j, j+1));
				if (current.indexOf(winningCombinations[i].substring(j, j+1)) == -1){
					continue;
				}else{
					count++;
				}
			}
			//System.out.println("Count is " + count);
			if (count == 3) break;
		}
		if (count == 3){
			return true;
		}else{
			return false;
		}
	}
	
	//This function renders the grid for the game
	static void drawGrid(){
		System.out.println(" -------------");
		for (int i=0; i < grid.length; i++){
			System.out.print(" | ");
			for (int j=0; j < grid[i].length; j++){
				System.out.print(grid[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.println(" -------------");
		}
	}
	
	//The main function which starts and ends the game
	public static void playTicTacToe() throws NumberFormatException, IOException{
		
		int i;
		
		System.out.println("Hello Elavon, Welcome to a game of Tic-Tac-Toe.");
		System.out.println("Player 1 would be using crosses(X)");
		System.out.println("Player 2 would be using naughts(O)");
		System.out.println("Note: Rows and Columns are numbered from 1.");
		initTicTacToe();
		drawGrid();
		
		for (i=0; i<9; i++){
			int temp = i%2;
			userInput(temp);
			if (performCheck(temp)){
				System.out.println("\n========================================");
				System.out.println("Player " + (temp+1) + " wins the game. End.");
				break;
			}
			drawGrid();
		}
		
		if (i == 9){
			System.out.println("\n========================================");
			System.out.println("The game ends in a tie! End.");
		}
		drawGrid();

	}
}
