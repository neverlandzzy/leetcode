
public class Solution {
	/**
	 * Reverse bits of a given 32 bits unsigned integer.
	 * 
	 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
	 * return 964176192 (represented in binary as 00111001011110000010100101000000).
	 * 
	 * Follow up:
	 * If this function is called many times, how would you optimize it?
	 */
	
    public static int reverseBits(int n) {
        int val = 0;
        
        for (int i = 0; i < 32; i++) {
        	val = (val << 1) | (n & 1);
        	n = n >> 1;
        }
        
        return val;
    }
    
    public static void main(String[] args) {
    	
    	int result = reverseBits(43261596);
    	System.out.println(Integer.toBinaryString(43261596));
		System.out.println(Integer.toBinaryString(result));
		System.out.println(result);
	}
}
