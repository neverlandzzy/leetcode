import java.util.Random;

public class Solution {
	/**
	 * Shuffle a set of numbers without duplicates.
	 * 
	 * Example:
	 * 
	 * // Init an array with set 1, 2, and 3.
	 * int[] nums = {1,2,3};
	 * Solution solution = new Solution(nums);
	 * 
	 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
	 * solution.shuffle();
	 * 
	 * // Resets the array back to its original configuration [1,2,3].
	 * solution.reset();
	 * 
	 * // Returns the random shuffling of array [1,2,3].
	 * solution.shuffle();
	 */

	// https://leetcode.com/problems/shuffle-an-array/solution/
	// Fisher-Yates Algorithm: On each iteration of the algorithm, we generate a random integer between the current
	//                         index and the last index of the array. Then, we swap the elements at the current index and
	//                         the chosen index. One small, yet important detail is that it is possible to swap an element
	//                         with itself

	int[] originalArray;
	Random random;
    public Solution(int[] nums) {
        random = new Random();
        originalArray = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
		return originalArray;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	int[] array = originalArray.clone();

        for (int i = 0; i < array.length; i++) {
        	int randomIndex = getRandomInRange(i, array.length);
			swap(array, i, randomIndex);
		}

        return array;
    }

    private int getRandomInRange(int min, int max) {
    	return random.nextInt(max - min) + min;
	}

	private void swap(int[] array, int i, int j) {
    	int tmp = array[i];
    	array[i] = array[j];
    	array[j] = tmp;
	}

    public static void main(String[] args) {
		int[] test = {1, 2, 3};
		
		Solution solution = new Solution(test);
		
		int[] reset1 = solution.reset();		
		int[] shuffle1 = solution.reset();
		
		int[] reset2 = solution.reset();		
		int[] shuffle2 = solution.reset();
		
		print(reset1);
		print(shuffle1);
		print(reset2);
		print(shuffle2);
	}
    
    private static void print(int[] nums) {
    	for (int i: nums) {
    		System.out.print(i + ", ");
    	}
    	System.out.println();
    }
}
