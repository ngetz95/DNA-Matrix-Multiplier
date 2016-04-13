//Noah Getz
//Help with code from http://rosettacode.org/wiki/Longest_common_subsequence#Java
import java.util.ArrayList;

public class MatrixMultiplier {
	
	int ranNum;
	String newString;
	
	public String buildString(){//Randomly builds a string based off the Nucleobases for DNA
		newString = "";
		for(int i = 0; i < 10; i++){
			ranNum = (int) (Math.random() * 4);
			if(ranNum == 0)
				newString = newString + "A";
			else if(ranNum == 1)
				newString = newString + "C";
			else if(ranNum == 2)
				newString = newString + "G";
			else
				newString = newString + "T";
		}
		return newString;
	}
	
	ArrayList<Integer> gridValues = new ArrayList<Integer>();
	String lcsValues;
	public void makeTable(String first, String second){ //Makes a 2D Table of LCS value using a 1D ArrayList
		if(first.length() != second.length()){
			return;
		}
		
		gridValues.clear();
		int k = 0;
		
		for(int i = 0; i < first.length(); i++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... first.length() - 1
			newString = "";
			
			for(int j = 0; j < second.length(); j++){ //INVARIANT: A[i] < A[i + 1], i = 1, 2, ... second.length() - 1
				int left = (i * first.length()) + j - 1; //With the nature of the list, the left item should always have an index of the induced equation
				int diagUp = left - first.length();; //An item diagonally upward should be just above the left item, an item directly up should be just right of that
				int up = diagUp + 1;
				
				if(first.charAt(i) == second.charAt(j) && diagUp >= 0){ //If a match is found, the new value should be one more than the item diagonal from it
					gridValues.add(1 + gridValues.get(diagUp) );
				}
				
				else if(first.charAt(i) == second.charAt(j)){ //If there is no diagonal element above it, the number should be 1
					gridValues.add(1);
				}
				
				else if(left >= 0){
					if(up >= 0 && gridValues.get(up) >= gridValues.get(left)){ //If there is no match, it should copy the largest value of either above or below
						gridValues.add(gridValues.get(up));
					}
					
					else{
						gridValues.add(gridValues.get(left));
					}
					
				}
				
				else{ // if none of the criteria are met (likely towards the beginning) the table should have 0
					gridValues.add(0);
				}
				
				newString = newString + gridValues.get(k) + ", ";
				k++;
			}
			
			System.out.println(newString);
		}

	}
	
	public static String lcs(String a, String b) {
	    int[][] myStrings = new int[a.length()+1][b.length()+1]; //Make a 2 dimensional Array with the two strings
	 
	    // the initial row and column (zero based) are automatically initialized to 0
	 
	    for (int i = 0; i < a.length(); i++) // INVARIANT: A[i] < A[i + 1], i = 1, 2, ... a.length() - 1
	        for (int j = 0; j < b.length(); j++) // INVARIANT: A[i] < A[i + 1], i = 1, 2, ... b.length() - 1
	            if (a.charAt(i) == b.charAt(j)) // Checks through both Strings to find letters in common
	                myStrings[i+1][j+1] = myStrings[i][j] + 1; //Increases the value of the cell when a match is found
	            else
	                myStrings[i+1][j+1] = Math.max(myStrings[i+1][j], myStrings[i][j+1]); //If not it takes the max value from either above or to the left
	 
	    // Gets the substring from the Strings
	    StringBuffer myLCS = new StringBuffer();
	    for (int x = a.length(), y = b.length(); x != 0 && y != 0; ) {
	        if (myStrings[x][y] == myStrings[x-1][y]) //Checks to the left if an identical number is found there
	            x--;
	        else if (myStrings[x][y] == myStrings[x][y-1]) //Checks above if an identical number is found there
	            y--;
	        else {
	            assert a.charAt(x-1) == b.charAt(y-1); //Checks diagonally up and left left if an identical number is found there
	            myLCS.append(a.charAt(x-1)); //Adds character found to the LCS during this, once finished, we should have a complete but backwards LCS
	            x--;
	            y--;
	        }
	    }
	 
	    return myLCS.reverse().toString(); //This flips the LCS back around
	}
}
