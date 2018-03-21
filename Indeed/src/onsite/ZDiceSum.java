package onsite;

public class ZDiceSum {
	
	// Time: O(6 * dice)
	public static float sumPossibility1(int dice, int target) {

	}

	
	public static float sumPossibility2(int dice, int target) {

	}

	
	public static float sumPossibility3(int dice, int target) {

	}
	
	
	public static void main(String[] args) {
		System.out.println("Solution 1: Brute Force");
		System.out.println(sumPossibility1(2, 2));
		System.out.println(sumPossibility1(10, 40));
		System.out.println(sumPossibility1(10, 50));
		System.out.println();
		
		
		System.out.println("Solution 2: DFS + mem");
		System.out.println(sumPossibility2(2, 2));
		System.out.println(sumPossibility2(10, 40));
		System.out.println(sumPossibility2(10, 50));
		System.out.println();
		
		
		System.out.println("Solution 3: DP");
		System.out.println(sumPossibility3(2, 2));
		System.out.println(sumPossibility3(10, 40));
		System.out.println(sumPossibility3(10, 50));
		System.out.println();
		
	}
}
