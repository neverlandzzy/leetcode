import java.util.HashSet;
import java.util.Set;


public class Solution {
	/**
	 * Given an unsorted array of integers, find the length of the longest 
	 * consecutive elements sequence.
	 * 
	 * For example,
	 * 
	 * Given [100, 4, 200, 1, 3, 2],
	 * 
	 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	 * Your algorithm should run in O(n) complexity.
	 */
	
	
    public static int longestConsecutive(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();

        for (int i: nums) {
            set.add(i);
        }

        int result = 0;

        for (int i: nums) {
            if (!set.contains(i + 1)) {
                int length = 1;
                i -= 1;

                while (set.contains(i)) {
                    length++;
                    i--;
                }

                result = Math.max(result, length);
            }
        }

        return result;
    }
    
    // a little slower
    /*
    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        
        for (int i: nums) {
        	map.put(i, 0);
        }
        
        for (int i: nums) {
        	
        	if (!map.containsKey(i)) {
        		continue;
        	} 
        	
            int length = 1;
            int tmp = i;          
      	    
        	while (map.containsKey(--tmp)) {
	        		length++;
	        		map.remove(tmp);
	        }
        	
        	tmp = i;
        	while (map.containsKey(++tmp)) {
	        		length++;
	        		map.remove(tmp);
	        	}
	        	map.remove(i);
        	
        	max = Math.max(max, length);
    	}
        
        return max;
    }
    */
    public static void main(String[] args) {
		int[] test = {100,4,200,1,3,2};
		int[] test2 = {100};
		//System.out.println(longestConsecutive(test1));
		System.out.println(longestConsecutive(test2));
	}
}
