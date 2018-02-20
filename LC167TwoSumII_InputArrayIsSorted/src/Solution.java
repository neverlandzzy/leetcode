
public class Solution {
	/*
	 * Given an array of integers that is already sorted in ascending order, 
	 * find two numbers such that they add up to a specific target number.
	 * 
	 * The function twoSum should return indices of the two numbers such that 
	 * they add up to the target, where index1 must be less than index2. 
	 * Please note that your returned answers (both index1 and index2) are not zero-based.
	 * 
	 * You may assume that each input would have exactly one solution.
	 * 
	 * Input: numbers={2, 7, 11, 15}, target=9
	 * Output: index1=1, index2=2
	 */
	
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        
        int pre = 0;
        int post = numbers.length - 1;
        
        
        while (pre < post) {
        	if (numbers[pre] + numbers[post] > target) {
        		post--;
        	} else  if (numbers[pre] + numbers[post] < target) {
        		pre++;
        	} else {
        		result[0] = pre + 1;
        		result[1] = post + 1;
        		break;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {2,7,11,15};
		int[] result = new int[2];
		
		result = twoSum(test, 9);
		
		for (int n: result) {
			System.out.println(n);
		}
	}
}
