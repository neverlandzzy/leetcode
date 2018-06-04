package Solstice;

public class Solution {
	
	public static int maxDifference(int[] a) {
        int min = a[0];
        int result = 0;
        
        for (int i = 1; i < a.length; i++) {
            if (a[i] >= min) {
                result = Math.max(result, a[i] - min);
            }
            
            min = Math.min(min, a[i]);
        }
        
        return result;

    }
	
	public static void main(String[] args) {
		int[] test1 = {1, 1};
		int[] test2 = {0};
		int[] test3 = {0, 1, 3, 2, 5};
		
		
		System.out.println(maxDifference(test1));
		System.out.println(maxDifference(test2));
		System.out.println(maxDifference(test3));
	}
}
