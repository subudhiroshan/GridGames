import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Author: Roshan Subudhi
 * Purpose: Elavon Interview 
 * Function: This class starts off the Connect4 game. 
 * Date: 05/06/2013
 * License: Creative Commons 3.0 License 
 */

public class ConnectFour {
	
	static String winningReason = null;
	static String[][] grid = new String[6][7]; // a 2-D array to represent the game

	//This function initializes the grid to be null. REUSED from TIC-TAC-TOE.
	static void initConnectFour(){
		for (int i=0; i < grid.length; i++){
			for (int j=0; j < grid[i].length; j++){
				grid[i][j] = "-";
			}
		}
	}

	//This function takes the player's input, updates the grid after performing input validations
	static void userInput(int player) throws NumberFormatException, IOException{
		int colNum = 0;
		int rowNum = 0;

		String flag;
		if (player == 0){
			flag = "R";
		}
		else{
			flag = "B";
		}
		
		System.out.println("Player " + flag + ": Choose your column -> ");
		try{
			System.out.print("Column:");
			BufferedReader col = new BufferedReader(new InputStreamReader (System.in));
			colNum = Integer.parseInt(col.readLine());
			if ((colNum > grid[0].length) || (colNum < 1)){
				System.out.println("Wrong Column Input. End.");
				System.exit(0);
			}
		}catch(NumberFormatException nfe){
			System.out.println("Please enter numbers only! End.");
			System.exit(0);
		}

		for (rowNum=grid.length-1; rowNum>-1; rowNum--){
			if (grid[rowNum][colNum - 1].equals("-")){
				break;
			}else{
				continue;
			}
		}

		if (rowNum < 0){
			System.out.println("That column is fully occupied! End.");
			System.exit(0);
		}else{
			grid[rowNum][colNum-1] = flag;
		}
	}

	//This function renders the grid for the game. REUSED from TIC-TAC-TOE
	static void drawGrid(){
		System.out.print(" | ");
		for(int i = 0; i< grid[0].length; i++){
			System.out.print((i+1) + " | ");
			
		}
		System.out.println(" \n-----------------------------");
		for (int i=0; i < grid.length; i++){
			System.out.print(" | ");
			for (int j=0; j < grid[i].length; j++){
				System.out.print(grid[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
			System.out.println(" -----------------------------");
		}
	}

	//This function calculates the player's current status after every move.
	static boolean currentStatus(int player){
		String flag;
		StringBuilder currentX = new StringBuilder();//a string to hold the status of current player, X coordinates
		StringBuilder currentY = new StringBuilder();//a string to hold the status of current player, Y coordinates

		if (player == 0){
			flag = "R";
		}
		else{
			flag = "B";
		}

		for (int i=0; i < grid.length; i++){
			for (int j=0; j < grid[i].length; j++){
				if (grid[i][j].equals(flag)){
					currentX.append(i);
					currentY.append(j);
				}
			}
		}

		if (currentX.length()>3){
			return verifyWinner(currentX, currentY);
		}
		else{
			return false; // if any player has played less than 4 moves, that player cannot win
		}
	}

	//This function checks whether the player's current status is a winning move. 
	static boolean verifyWinner(StringBuilder X, StringBuilder Y){

		ArrayList<String> temp1 = Checks.permutations(X);
		ArrayList<String> temp2 = Checks.permutations(Y);

		for(int i=0; i<temp1.size(); i++){//temp1.size() == temp2.size()
			if (Checks.isSame(new StringBuilder(temp1.get(i))) && Checks.isCircConsecutive(new StringBuilder(temp2.get(i)))){
				winningReason = "4 moves in a Row!";
				return true; // row found
			}
			if (Checks.isSame(new StringBuilder(temp2.get(i))) && Checks.isCircConsecutive(new StringBuilder(temp1.get(i)))){
				winningReason = "4 moves in a Column!";
				return true; // column found
			}
		}
		
		Checks.combination("", X.toString());
		ArrayList<String> combsX = new ArrayList<String>(Checks.combs);
		Checks.combs.clear();
		Checks.combination("", Y.toString());
		ArrayList<String> combsY = new ArrayList<String>(Checks.combs);
		Checks.combs.clear();
		
		for(int i=0; i<combsX.size(); i++){//combsX.size() == combsY.size()
			if (Checks.isUpwardDiagonal(new StringBuilder(combsX.get(i)), new StringBuilder(combsY.get(i)))){
				winningReason = "4 moves on the Upward Diagonal";
				return true;
			}
			if (Checks.isDownwardDiagonal(new StringBuilder(combsX.get(i)), new StringBuilder(combsY.get(i)))){
				winningReason = "4 moves on the Downward Diagonal";
				return true;
			}
		}
		
		return false;
	}
	
	//The main function which starts and ends the game. REUSED from TIC-TAC-TOE
	public static void playConnectFour() throws NumberFormatException, IOException{

		int i=0;
		String flag;
		
		System.out.println("Hello Elavon, Welcome to an electronic game of Connect4.");
		System.out.println("Player 1 is Red(R)");
		System.out.println("Player 2 is Black(B)\n");

		initConnectFour();
		drawGrid();

		for (i=0; i<42; i++){
			int temp = i%2;
			if (temp == 0){
				flag = "R";
			}
			else{
				flag = "B";
			}
			userInput(temp);
			if (currentStatus(temp)){
				System.out.println("\n========================================");
				System.out.println("Player " + flag + " wins the game with " + winningReason + ". End.\n");
				break;
			}
			drawGrid();
		}
		
		if (i == 42){
			System.out.println("\n========================================");
			System.out.println("The game ends in a tie! End.");
		}
		drawGrid();
	}
}
