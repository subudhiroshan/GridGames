import java.util.ArrayList;

/* Author: Roshan Subudhi
 * Purpose: Elavon Interview 
 * Function: This class provides various basic Checks used by the Connect4 game. 
 * Date: 05/06/2013
 * License: Creative Commons 3.0 License 
 */

public class Checks {

	static ArrayList<String> combs = new ArrayList<String>();
	
	// This function determines if input string has consecutive numbers (difference of 1)
	static boolean isConsecutive(StringBuilder con){
		int x = 0;
		for (x=0; x<con.length()-1; x++){
			if (!(Math.abs(Integer.parseInt(con.substring(x, x+1)) - Integer.parseInt(con.substring(x+1, x+2))) == 1)){
				break;
			}
		}
		if (x == con.length()-1){ 
			return true; // a consecutive series is found
		}else{
			return false;
		}
	}
	
	// This function determines if input string has consecutive numbers in a circular fashion
	static boolean isCircConsecutive(StringBuilder circStr){
		char[] orig = circStr.toString().toCharArray();
		StringBuilder temp = new StringBuilder();
		for (int i=0; i<circStr.length(); i++){
			for (int j=0; j<4; j++){
				temp.append(orig[(i+j)%circStr.length()]);
			}
			if (isConsecutive(temp)) return true;
			temp.delete(0, 4);
		}
		return false;
	}
	
	//This function determines if input string has same numbers (equality). Note: Could have used isConsecutive(), with difference 0, but string comparison is quicker. 
	static boolean isSame(StringBuilder set){
		int x = 0;
		for (x=0; x<set.length(); x++){
			if (!set.substring(0,1).equals(set.substring(x,x+1))){
				break;
			}
		}
		if (x == set.length()){ 
			return true; // a set contains same elements
		}else{
			return false;
		}
	}
	
	//This function determines if input strings lie on the downward diagonal (difference between consecutive sum of coordinates is 2)
	static boolean isDownwardDiagonal(StringBuilder X, StringBuilder Y){
		int x = 0;
		if (isSame(X) || isSame(Y)) return false;
		if (!isConsecutive(X) || !isConsecutive(Y)) return false;
		for (x=0; x<Y.length()-1; x++){// X.length() == Y.length()
			if (!((Integer.parseInt(X.substring(x, x+1)) + Integer.parseInt(Y.substring(x, x+1))) - (Integer.parseInt(X.substring(x+1, x+2)) + Integer.parseInt(Y.substring(x+1, x+2))) == (Integer.parseInt(X.substring(0, 1)) + Integer.parseInt(Y.substring(0, 1))) - (Integer.parseInt(X.substring(1, 2)) + Integer.parseInt(Y.substring(1, 2))))){
				break;
			}
		}
		if (x == X.length()-1){ 
			return true; // a downward diagonal is matched
		}else{
			return false;
		}
	}

	//This function determines if input strings lie on the upward diagonal (sum of coordinates is same)
	static boolean isUpwardDiagonal(StringBuilder X, StringBuilder Y){
		int x = 0;
		if (isSame(X) || isSame(Y)) return false;
		if (!isConsecutive(X) || !isConsecutive(Y)) return false;
		int sum = Integer.parseInt(X.substring(0, 1)) + Integer.parseInt(Y.substring(0, 1));
		for (x=0; x<Y.length(); x++){ // X.length() == Y.length()
			if (!((Integer.parseInt(X.substring(x,x+1)) + Integer.parseInt(Y.substring(x,x+1))) == sum)){
				break;
			}
		}
		if (x == Y.length()) { 
			return true; // an upward diagonal is matched
		}else{
			return false;
		}		
	}

	//This function generates all the possible 4-letter permutations for a string
	static ArrayList<String> permutations(StringBuilder str){
		ArrayList<String> possPerm = new ArrayList<String>();
		char[] orig = str.toString().toCharArray();
		StringBuilder temp = new StringBuilder();
		for (int i=0; i<str.length(); i++){
			for (int j=0; j<4; j++){
				temp.append(orig[(i+j)%str.length()]);
			}
			possPerm.add(temp.toString());
			temp.delete(0, 4);
		}
		return possPerm;
	}
	
	//Code borrowed from http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
	//Modified it slightly to generate necessary possible combinations
	//This function generates all the possible 4-letter combinations for a string
	static void combination(String pre, String str) {
	    int n = str.length();
	    if (n == 0) combs.add(pre.substring(0,4)); 
	    else {
	        for (int i = 0; i < n; i++)
	           combination(pre + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}	
}
