
public class Solution {
	/*
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 * 
	 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
	 * 
	 * Example:
	 * Input: 4, 14, 2
	 * 
	 * Output: 6
	 * 
	 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just showing the four bits relevant in this case). 
	 * So the answer will be:
	 * 
	 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
	 * 
	 * Note:
	 * 1. Elements of the given array are in the range of 0 to 10^9
	 * 2. Length of the array will not exceed 10^4.
	 */
	
	// 对每一位，计算数组中该位为1的数字的个数。 e.g. 4(0100), 12(1110), 2(0010) 第二位为1的数字个数为2。
	// 则第二位的HammingDistance = 2(1的个数) * (3 - 2)(0的个数) = 2
    public static int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int result = 0;
        int range = 31;

        for (int i = 0; i < range; i++) {
        	int count = 0;
        	
        	for (int j = 0; j < nums.length; j++) {
        		count += (nums[j] >> i) & 1;
        	}
        	
        	result += count * (nums.length - count);
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
    	int[] test1 = {4, 14, 2};
		System.out.println(totalHammingDistance(test1));
    	
	}
}
