import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Author: Roshan Subudhi
 * Purpose: Elavon Interview 
 * Function: This class provides a menu to play either TicTacToe or ConnectFour. 
 * Date: 05/06/2013
 * License: Creative Commons 3.0 License 
 */


public class GridGames {

	public static void main(String args[]) throws NumberFormatException, IOException{
		int choice = 0;
		
		while (!(choice == 1 || choice == 2 || choice == 3)){
		System.out.println("Elavon, welcome to Grid-based games!");
		System.out.println("Which game would you like to play now: Type");
		System.out.println("'1' for Tic-Tac-Toe");
		System.out.println("'2' for ConnectFour");
		System.out.println("'3' to Exit");
		
		BufferedReader input = new BufferedReader(new InputStreamReader (System.in));
		try {
			choice = Integer.parseInt(input.readLine());
		} catch (Exception e) {
			System.out.print("No characters allowed.");
			choice = 0;
		} 
		
		switch(choice){
		case 1: System.out.println("\n\n---------------------------------------------------\n\n");
				TicTacToe.playTicTacToe();
			break;
		case 2: System.out.println("\n\n---------------------------------------------------\n\n");
				ConnectFour.playConnectFour();
			break;
		case 3: System.out.println("Option 3");
				System.exit(0);
		default:
			System.out.println("\nWrong input. Try again.\n\n");
			
		}
		
		}
		
		
	}
	
}
