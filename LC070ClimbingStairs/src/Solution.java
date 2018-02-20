
public class Solution {
	/*
	 * You are climbing a stair case. It takes n steps to reach to the top.
	 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	 */
	
    public static int climbStairs(int n) {
    	
        if (n <= 1) {
            return 1;
        }
        
        int pre = 1;
        int cur = 1;
        int step = 0;
        
        for (int i = 2; i <= n; i++) {
            step = pre + cur;
            pre = cur;
            cur = step;
        }
        
        return step;
        
    	/*
        
        if (n <= 1) {
            return 1;
        }
        
        int cur = 1;
        int pre = cur;
        
        for (int i = 2; i <= n; i++) {
            int tmp = cur;
            cur = pre + cur;
            pre = tmp;
        }
        
        return cur;
        */
    }
    
    public static void main(String[] args) {
    	System.out.println(climbStairs(2));
	}
}
