import java.util.Arrays;
import java.util.Comparator;


public class Solution {
	/**
	 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
	 * One envelope can fit into another if and only if both the width and height of one envelope 
	 * is greater than the width and height of the other envelope.
	 * 
	 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
	 * 
	 *Example:
	 *Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian 
	 *doll is 3 ([2,3] => [5,4] => [6,7]).
	 */
	
    public static int maxEnvelopes(int[][] envelopes) {
    	/*
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
        	return 0;
        }
        
        //[3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it 
        //will be counted as an increasing number if the order is [3, 3], [3, 4]
        
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] array1, int[] array2){
            	if (array1[0] == array2[0]) {
            		return array2[1] - array1[1];
            	} else {
            		return array1[0] - array2[0];
            	}
           } 
        });
        
        // LC 300
        int[] dp = new int[envelopes.length];
        int len = 0;
        
        for (int[] envelope: envelopes) {
        	int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
        	if (index < 0) {
        		index = -(index + 1);
        	}
        	dp[index] = envelope[1];
        	if (index == len) {
        		len++;
        	}
        }
        
        return len;
        */
    	
    	
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        
        //按第一个元素升序排列，若第一个元素相等，则按第二个元素降序排列
        //e.g [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it 
        //will be counted as an increasing number if the order is [3, 3], [3, 4]
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] array1, int[] array2){
                if (array1[0] == array2[0]) {
                    return array2[1] - array1[1];
                } else {
                    return array1[0] - array2[0];
                }
            }
        });
        
        // LC 300
        int[] dp = new int[envelopes.length];
        int len = 0;
        
        for (int[] envelope: envelopes) {
            int i = 0;
            int j = len;
            
            while (i < j) {
                int mid = i + (j - i) / 2;
                if (dp[mid] < envelope[1]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            
            dp[i] = envelope[1];
            if (i == len) {
                len++;
            }
        }
        
        return len;
    }
    
    
    
    public static void main(String[] args) {
		int[][] envs = {{5,4},{6,4},{6,7},{2,3}};
		
		System.out.println(maxEnvelopes(envs));
	}
}
