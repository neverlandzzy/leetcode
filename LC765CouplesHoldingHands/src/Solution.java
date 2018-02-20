import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum number of swaps so that every couple 
	 * is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
	 * 
	 * The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in order, the first couple being (0, 1), 
	 * the second couple being (2, 3), and so on with the last couple being (2N-2, 2N-1).
	 * 
	 * The couples' initial seating is given by row[i] being the value of the person who is initially sitting in the i-th seat.
	 * 
	 * Example 1:
	 * 
	 * Input: row = [0, 2, 1, 3]
	 * Output: 1
	 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
	 * 
	 * Example 2:
	 * 
	 * Input: row = [3, 2, 0, 1]
	 * Output: 0
	 * Explanation: All couples are already seated side by side.
	 * 
	 * Note:
	 * 1. len(row) is even and in the range of [4, 60].
	 * 2. row is guaranteed to be a permutation of 0...len(row)-1.
	 */
	
	
    public static int minSwapsCouples(int[] row) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
        	map.put(row[i], i);
        }
        
        int result = 0;
        
        for (int i = 0; i < row.length; i += 2) {
        	if (row[i] % 2 == 0) {
        		if (row[i + 1] == row[i] + 1) {
        			continue;
        		}
        		int index = map.get(row[i] + 1);
        		int tmp = row[i + 1];
        		row[i + 1] = row[index];
        		row[index] = tmp; 
        		map.put(tmp, index);
        		map.put(row[i + 1], i + 1);
        		result++;
        	} else {
        		if (row[i + 1] == row[i] - 1) {
        			continue;
        		}
        		int index = map.get(row[i] - 1);
        		int tmp = row[i + 1];
        		row[i + 1] = row[index];
        		row[index] = tmp; 
        		map.put(tmp, index);
        		map.put(row[i + 1], i + 1);
        		result++;
        	}
        }      
        return result;
    }
    
    
    public static void main(String[] args) {
		int[] test1 = {0, 2, 1, 3};
		int[] test2 = {3, 2, 0, 1};
		int[] test3 = {0,2,4,6,7,1,3,5};
		
		//System.out.println(minSwapsCouples(test1));
		//System.out.println(minSwapsCouples(test2));
		System.out.println(minSwapsCouples(test3));
	}
}
