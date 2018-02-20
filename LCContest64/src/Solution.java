import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Solution {
	
	
    public static int reachNumber(int target) {
    	Queue<Integer> queue = new LinkedList<>();

    	Set<Integer> set = new HashSet<>();
    	
    	int step = 1;
    	queue.offer(0);
    	set.add(0);
    	
    	while(!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			
    			int pos = queue.poll();
    			System.out.println(pos);
    			if (pos == target) {
    				return step - 1;
    			}
    			queue.offer(pos + step);
    			queue.offer(pos - step);
    		}
    		step++;
    	}
    	
    	return -1;
    }
    
    
    public static int[] pourWater(int[] heights, int V, int K) {
        int n = heights.length;
        
        while (V > 0) {
        	int left = K;
        	int right = K;
        	int flag = 0;

    		int leftPoint = left;
    		int rightPoint = right;
        	while (left >= 1) {
        		if (heights[left - 1] <= heights[left]) {
        			if (heights[left - 1] < heights[left]) {
        				leftPoint = left - 1;
        			}
        			left--;
        		} else if (heights[left - 1] > heights[left]){ 
        			break;
        		} 
        	}
        	
        	if (leftPoint != K) {
    			heights[leftPoint]++;
    			V--;
    			flag = 1;
        	}
        	
        	if (flag == 1) {
        		continue;
        	}
        	
        	while (right < n - 1) {
        		if (heights[right + 1] <= heights[right]) {
        			if (heights[right + 1] < heights[right]) {
        				rightPoint = right + 1;
        			}
        			right++;
        		} else if (heights[right + 1] > heights[right]) {
        			break;
        	    } 
        	}
        	
        	if (rightPoint != K) {
    			heights[rightPoint]++;
    			V--;
    			flag = 1;
        	}
        	
        	if (flag == 0) {
        		heights[K]++;
        		V--;
        	}

        }
        
        return heights;
    }
    
	public static void main(String[] args) {
		//System.out.println(reachNumber(3));
		//System.out.println(reachNumber(2));
		//System.out.println(reachNumber(1000));
		//System.out.println(reachNumber(-1000000000));
		
		/*
		int[] test21 = {2,1,1,2,1,2,2};
		int[] result21 = pourWater(test21, 4, 3);
		for (int k: result21) {
			System.out.print(k + ", ");
		}
		System.out.println();
		
		
		int[] test22 = {1, 2, 3, 4};
		int[] result22 = pourWater(test22, 2, 2);
		for (int k: result22) {
			System.out.print(k + ", ");
		}
		System.out.println();
		
		
		int[] test23 = {3, 1, 3};
		int[] result23 = pourWater(test23, 5, 2);
		for (int k: result23) {
			System.out.print(k + ", ");
		}
		System.out.println();
		
*/
		int[] test24 = {1,2,3,4,3,2,1,2,3,4,3,2,1};
		int[] result24 = pourWater(test24, 10, 2);
		for (int k: result24) {
			System.out.print(k + ", ");
		}
		System.out.println();
	}
}
