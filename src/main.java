//Noah Getz
//Main Method
public class main {

	public static void main(String[] args) {
		
		MatrixMultiplier myFinder = new MatrixMultiplier();
		String firstString = myFinder.buildString();
		String secondString = myFinder.buildString();
		
		System.out.println(firstString);
		System.out.println(secondString);
		System.out.println("");
		
		myFinder.makeTable(firstString, secondString);
		System.out.println(myFinder.lcs(firstString, secondString));
	}

}
