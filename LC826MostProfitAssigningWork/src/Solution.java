import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Solution {
	/*
	 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 
	 * 
	 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with 
	 * difficulty at most worker[i]. 
	 * 
	 * Every worker can be assigned at most one job, but one job can be completed multiple times.
	 * 
	 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, 
	 * his profit is $0.
	 * 
	 * What is the most profit we can make?
	 * 
	 * Example 1:
	 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
	 * Output: 100 
	 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] separately.
	 * 
	 * Notes:
	 * 1 <= difficulty.length = profit.length <= 10000
	 * 1 <= worker.length <= 10000
	 * difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
	 */
	
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Arrays.sort(worker);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < difficulty.length; i++) {
        	list.add(new int[]{difficulty[i], profit[i]});
        }
        
        Collections.sort(list, new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return o2[1] - o1[1];
        	}
        });
        
        int result = 0;
        int index = 0;
        
        for (int i = worker.length - 1; i >= 0; i--) {
        	for (int j = index; j < list.size(); j++) {
        		if (worker[i] >= list.get(j)[0]) {
        			result += list.get(j)[1];
        			index = j;
        			break;
        		}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] difficulty1 = {2, 4, 6, 8, 10};
		int[] profit1 = {10, 20, 30, 40, 50};
		int[] worker1 = {4, 5, 6, 7};
		
		System.out.println(maxProfitAssignment(difficulty1, profit1, worker1));
	}
}
