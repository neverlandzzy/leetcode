
public class Solution {
	/*
	 * There are N children standing in a line. Each child is assigned a rating value.
	 * You are giving candies to these children subjected to the following requirements:
	 * 
	 * 	Each child must have at least one candy.
	 * 	Children with a higher rating get more candies than their neighbors.
	 * 
	 * What is the minimum candies you must give?
	 */
	
	
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
        	return 0;
        }
        
        int n = ratings.length;        
        int[] count = new int[n];
        int sum = n;
        
        for (int i = 1; i < n; i++) {
        	if (ratings[i-1] < ratings[i]) {
        		count[i] = count[i-1] + 1;
        	}
        }
        
        for (int i = n - 2; i >= 0; i--) {
        	if ((ratings[i] > ratings[i+1]) &&(count[i] <= count[i+1])) {
        		count[i] = count[i+1] + 1;
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	sum += count[i];
        }
       
        return sum;
    }
    
    public static void main(String[] args) {
		int [] test = {4,3,5,6,1,2,9};
		
		System.out.println(candy(test));
	}
}
