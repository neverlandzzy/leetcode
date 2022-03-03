
public class Solution {
	/**
	 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's 
	 * in their binary representation and return them as an array.
	 * 
	 * Example:
	 * 
	 * For num = 5 you should return [0,1,1,2,1,2].
	 * 
	 * Follow up:
	 * 
	 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear 
	 * time O(n) /possibly in a single pass?
	 * 
	 * Space complexity should be O(n).
	 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
	 */
	
    public static int[] countBits(int num) {
        //f[i] = f[i / 2] + i % 2;
    	
    	int[] result = new int[num + 1];
    	result[0] = 0;
    	
    	for (int i = 1; i <= num; i++) {
    		result[i] = result[i >> 1] + (i & 1);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		int[] result = countBits(5);
		
		for (int n: result) {
			System.out.print(n + ", ");
		}
		System.out.println();
	}
}
