
public class Solution {
	/*
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 * 
	 * Given two integers x and y, calculate the Hamming distance.
	 * 
	 * Note:
	 * 0 ≤ x, y < 2^31.
	 * 
	 * Example:
	 * 
	 * Input: x = 1, y = 4
	 * 
	 * Output: 2
	 * 
	 * Explanation:
	 * 1   (0 0 0 1)
	 * 4   (0 1 0 0)
	 *        ↑   ↑
	 * 
	 * The above arrows point to positions where the corresponding bits are different.
	 */
	
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        
        int counter = 0;
        while (xor != 0) {
        	if ((xor & 1) == 1) {
        		counter++;
        	}
        	xor = xor >> 1;
        }
        
        return counter;
    }
    
    /*
    public static int hammingDistance(int x, int y) {
        int counter = 0;
        
        for (int i = 0; i < 32; i++) {
            int bitX = x & 1;
            int bitY = y & 1;
            
            if ((bitX ^ bitY) == 1) {
                counter++;
            }
            
            x = x >> 1;
            y = y >> 1;
            
            if (x == 0 && y == 0) {
                break;
            }
        }
        
        return counter;
    }
    */
    public static void main(String[] args) {
    	System.out.println(hammingDistance(1, 4));
    	System.out.println(hammingDistance(5, 6));
	}
}
