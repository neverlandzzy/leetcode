import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. 
	 * Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. 
	 * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be 
	 * represented as [[0, 1, 10], [2, 0, 5]].
	 * 
	 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
	 * 
	 * Note:
	 * 
	 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
	 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * [[0,1,10], [2,0,5]]
	 * 
	 * Output:
	 * 2
	 * 
	 * Explanation:
	 * Person #0 gave person #1 $10.
	 * Person #2 gave person #0 $5.
	 * 
	 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
	 * 
	 * Output:
	 * 1
	 * 
	 * Explanation:
	 * Person #0 gave person #1 $10.
	 * Person #1 gave person #0 $1.
	 * Person #1 gave person #2 $5.
	 * Person #2 gave person #0 $5.
	 * 
	 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
	 */
	
    public static int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0 || transactions[0] == null || transactions[0].length == 0) {
        	return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] t: transactions) {
        	map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
        	map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        
        List<Integer> list = new ArrayList<>();
        for (int val: map.values()) {
        	if (val != 0) {
        		list.add(val);
        	}
        }
        
        int[] debts = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
        	debts[i] = list.get(i);
        }
        
        return helper(debts, 0, 0);
    }
    
    private static int helper(int[] debts, int pos, int count) {
    	while (pos < debts.length && debts[pos] == 0) {
    		pos++;
    	}
    	
    	if (pos == debts.length) {
    		return count;
    	}
    	
    	int result = Integer.MAX_VALUE;
    	
    	for (int i = pos + 1; i < debts.length; i++) {
    		if (debts[i] * debts[pos] < 0) {
    			debts[i] = debts[i] + debts[pos];
    			result = Math.min(result, helper(debts, pos + 1, count + 1));
    			debts[i] = debts[i] - debts[pos];
    		}
    	}
    	
    	return result == Integer.MAX_VALUE ? count : result;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{0,1,10}, {2,0,5}};
		int[][] test2 = {{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
		
		System.out.println(minTransfers(test1));
		System.out.println(minTransfers(test2));
	}
}
