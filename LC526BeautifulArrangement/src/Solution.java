
public class Solution {
	/*
	 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an 
	 * array that is constructed by these N numbers successfully if one of the following 
	 * is true for the ith position (1 ≤ i ≤ N) in this array:
	 * 
	 * The number at the ith position is divisible by i.
	 * i is divisible by the number at the ith position.
	 * Now given N, how many beautiful arrangements can you construct?
	 * 
	 * Example 1:
	 * 
	 * Input: 2
	 * Output: 2
	 * 
	 * Explanation: 
	 * The first beautiful arrangement is [1, 2]:
	 * 
	 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
	 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
	 * 
	 * The second beautiful arrangement is [2, 1]:
	 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
	 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
	 * 
	 * Note:
	 * N is a positive integer and will not exceed 15.
	 */
	
    public static int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        
        return helper(N, N, visited);
    }
    
    private static int helper(int N, int k, boolean[] visited) {
        if (k == 0) {
            return 1;
        }
        
        int result = 0;
        
        for (int i = 1; i <= N; i++) {
            if (visited[i] || ((i % k != 0) && (k % i != 0))) {
                continue;
            }
            visited[i] = true;
            result += helper(N, k - 1, visited);
            visited[i] = false;
        }
        
        return result;
    }
    
    // k 从小到大遍历，从1开始，到N + 1截止。 从0开始会出现除数为0的exception
    /*     
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        
        return helper(N, 1, visited);
    }
    
    private int helper(int N, int k, boolean[] visited) {
        if (k == N + 1) {
            return 1;
        }
        
        int result = 0;
        
        for (int i = 1; i <= N; i++) {
            if (visited[i] || (i % k != 0 && k % i != 0)) {
                continue;
            }
            visited[i] = true;
            result += helper(N, k + 1, visited);
            visited[i] = false;
        }
        
        return result;
    }
     */
    
    public static void main(String[] args) {
		System.out.println(countArrangement(2));
	}
}
