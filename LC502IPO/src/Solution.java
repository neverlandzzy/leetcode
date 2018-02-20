import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would 
	 * like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most 
	 * k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k 
	 * distinct projects.
	 * 
	 * You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the 
	 * corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit 
	 * will be added to your total capital.
	 * 
	 * To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final 
	 * maximized capital.
	 * 
	 * Example 1:
	 * Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].
	 * 
	 * Output: 4
	 * 
	 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
	 *              After finishing it you will obtain profit 1 and your capital becomes 1.
	 *              With capital 1, you can either start the project indexed 1 or the project indexed 2.
	 *              Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
	 *              Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
	 * Note:
	 * You may assume all numbers in the input are non-negative integers.
	 * The length of Profits array and Capital array will not exceed 50,000.
	 * The answer is guaranteed to fit in a 32-bit signed integer.
	 */
	
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        
    	// 按capital的升序排
        PriorityQueue<int[]> capQueue = new PriorityQueue<>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return o1[0] - o2[0];
        	}
        });
        
        // 按profit的降序排
        PriorityQueue<int[]> profitQueue = new PriorityQueue<>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return o2[1] - o1[1];
        	}
        });
        
        for (int i = 0; i < Profits.length; i++) {
        	capQueue.offer(new int[]{Capital[i], Profits[i]});
        }
        
        // capQueue 按capital的升序排列，每一步，把capQueue中小于W元素取出来，放入profitQueue中，也就是每一步都把可以执行的操作取出来放入profitQueue
        // profitQueue按profit的降序排列，每一步取出一个值，即为当前步骤所能达到的最大收益。
        // 重复k次，或者profitQueue空
        for (int i = 0; i < k; i++) {
        	while (!capQueue.isEmpty() && capQueue.peek()[0] <= W) {
        		profitQueue.offer(capQueue.poll());
        	}
        	
        	if (profitQueue.isEmpty()) {
        		break;
        	}
        	
        	W += profitQueue.poll()[1];
        }
        
        return W;
    }
    
    public static void main(String[] args) {
		int[] profits = {1, 2, 3};
		int[] capital = {0, 1, 1};
		
		System.out.println(findMaximizedCapital(2, 0, profits, capital));
	}
}
