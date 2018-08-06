import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
	/*
	 * Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 
	 * 
	 * Person A will NOT friend request person B (B != A) if any of the following conditions are true:
	 * 
	 * age[B] <= 0.5 * age[A] + 7
	 * age[B] > age[A]
	 * age[B] > 100 && age[A] < 100
	 * Otherwise, A will friend request B.
	 * 
	 * Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
	 * 
	 * How many total friend requests are made?
	 * 
	 * Example 1:
	 * 
	 * Input: [16,16]
	 * Output: 2
	 * Explanation: 2 people friend request each other.
	 * Example 2:
	 * 
	 * Input: [16,17,18]
	 * Output: 2
	 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
	 * 
	 * Example 3:
	 * 
	 * Input: [20,30,100,110,120]
	 * Output: 
	 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100. 
	 * 
	 * Notes:
	 * 
	 * 1 <= ages.length <= 20000.
	 * 1 <= ages[i] <= 120.
	 */
	
	// Solution 2: Instead of processing all 20000 people, we can process pairs of (age, count) 
	// representing how many people are that age. Since there are only 120 possible ages, this is a much faster loop.
	
	public static int numFriendRequests(int[] ages) {
		int[] count = new int[121];
		for (int i: ages) {
			count[i]++;
		}
		
		int result = 0;
		
		for (int i = 0; i < count.length; i++) {
			int countA = count[i];
			for (int j = 0; j <= i; j++) {
				int countB = count[j];

				if (j <= i / 2 + 7) {
					continue;
				}
				
				result += countA * countB;
				
				// 对于年龄相等的人：e.g.[16, 16]  --> expected result = 2
				if (i == j) {
					result -= countA;
				}
			}
		}
		
		return result;
	}
	
	
	// Solution 1: Burte-force TLE
	/*
    public static int numFriendRequests(int[] ages) {
    	
        int result = 0;
        int n = ages.length;
        
        Arrays.sort(ages);
        
        for (int i = 1; i < n; i++) {
        	for (int j = 0; j < i;j++) { 
        		
    			if (valid(ages[i], ages[j])) {
    				result++;
        		}
    			if (ages[i] == ages[j] && valid(ages[i], ages[j])) {
    				
    				result++;
    			}
        	}
        }
        
        return result;
        
    }
    
    private static boolean valid(int a, int b) {
    	if (b > 100 && a < 100) {
    		return false;
    	}
    	
    	if (b > a) {
    		return false;
    	}
    	
    	if (b <= a / 2 + 7) {
    		return false;
    	}
    	
    	return true;
    }    
    */
	
	
    public static void main(String[] args) {
		int[] test1 = {16, 16};
		int[] test2 = {16, 17, 18};
		int[] test3 = {20,30,100,110,120};
		int[] test4 = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};
		System.out.println(numFriendRequests(test1));
		System.out.println(numFriendRequests(test2));
		System.out.println(numFriendRequests(test3));
		System.out.println(numFriendRequests(test4));
	}
}
