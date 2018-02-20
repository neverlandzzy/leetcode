
public class Solution {
	/*
	 * There is a fence with n posts, each post can be painted with one of the k colors.
	 * 
	 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
	 * 
	 * Return the total number of ways you can paint the fence.
	 * 
	 * Note:
	 * n and k are non-negative integers.
	 */
	
    public int numWays(int n, int k) {
        if (n == 0) {
        	return 0;
        }
        
        if (n == 1) {
        	return k;
        }
        
        // 以3个posts为例， 前两个颜色相同的方法有sameColor = k种， 前两个颜色不同的方法有diffColor = (k * (k - 1))种, 所以总数有 （sameColor + diffColor）种
        int sameColor = k;
        int diffColor = k * (k - 1); 
        
        // 对于第3个， 第3个与第2个颜色不同的方法有 （sameColor + diffColor）* (k - 1)种； 第3个和第2个颜色相同的方法有diffColor种（要避免1， 1， 1的出现， 
        // 所以要求前两个颜色不同，即diffColor）
        for (int i = 3; i <= n; i++) {
        	int tmp = diffColor;
        	diffColor = (sameColor + diffColor) * (k -1);
        	sameColor = tmp;
        }
        
        return sameColor + diffColor;
    }
    
    public static void main(String[] args) {
    	Solution solution = new Solution();
    	System.out.println(solution.numWays(2, 10));
		//System.out.println(numWays(2, 10));
	}
}	
