
public class Solution {
	/*
	 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. 
	 * The time complexity must be in O(n).
	 * 
	 * Example 1:
	 * Input: [3, 2, 1]
	 * Output: 1
	 * Explanation: The third maximum is 1.
	 * 
	 * Example 2:
	 * Input: [1, 2]
	 * Output: 2
	 * Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
	 * 
	 * Example 3:
	 * Input: [2, 2, 3, 1]
	 * Output: 1
	 * Explanation: Note that the third maximum here means the third maximum distinct number.
	 * 
	 * Both numbers with value 2 are both considered as second maximum.
	 */
	
    public static int thirdMax(int[] nums) {
    	// 如果用int， 不能pass[1,2,-2147483648]， 所以用Integer  
    	/*
        int first  = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third  = Integer.MIN_VALUE;
        */
        
    	Integer first = null;
    	Integer second = null;
    	Integer third = null;
    	
        for (Integer n: nums) {
        	
        	if (n.equals(first) || n.equals(second) || n.equals(third)) {
        		continue;
        	}
        	
            if (first == null || n > first) {
                third = second;
                second = first;
                first = n;
            } else if (second == null || n > second) {
                third = second;
                second = n;
            } else if (third == null || n > third) {
                third = n;
            }
        }
        
        return third == null ? first : third;
    }
    
    public static void main(String[] args) {
		int[] test1 = {3, 2, 1};
		int[] test2 = {1, 2};
		int[] test3 = {2, 2, 3, 1};
		
		System.out.println(thirdMax(test1));
		System.out.println(thirdMax(test2));
		System.out.println(thirdMax(test3));
	}
}
