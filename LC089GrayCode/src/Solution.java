import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * The gray code is a binary numeral system where two successive values differ in only one bit.
	 * 
	 * Given a non-negative integer n representing the total number of bits in the code, print the 
	 * sequence of gray code. A gray code sequence must begin with 0.
	 * 
	 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	 * 
	 * 00 - 0
	 * 01 - 1
	 * 11 - 3
	 * 10 - 2
	 * 
	 * Note:
	 * 
	 * For a given n, a gray code sequence is not uniquely defined.
	 * 
	 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
	 * 
	 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
	 */
	
	// Time: O(n^2)
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        
        result.add(0);
        
        for(int i = 0 ; i < n; i++) {
        	int flipper = 1 << i;
        	
        	for(int j = result.size() - 1; j >= 0; j--) {  // 因为下一行会令result.size()增加，所以不能用 for(int j = 0; j < result.size(); j++)， 
        												   // 否则会死循环。
        		
        		result.add(result.get(j) | flipper);
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		
    	int t1 = 2;
		int t2 = 4;
		int t3 = 5;
		
		System.out.println(grayCode(t1));
		System.out.println(grayCode(t2));
		System.out.println(grayCode(t3));
		
    	/*
    	for (int i = 0; i < 4; i++) {
    		int flipper = 1 << i;
    		System.out.println(i + " --- > " + flipper);
    	}
		*/
	}
}
