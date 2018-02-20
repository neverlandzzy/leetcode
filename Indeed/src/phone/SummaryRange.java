package phone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SummaryRange {
	/*
	 *  基本题，LC 228 有序，无重复
	 *  Time: O(n) 
	 */
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int i = 0;
        int j = 1;
        int n = nums.length;
        
        while (j < n) {
            StringBuilder sb = new StringBuilder();
            if (nums[j] == nums[j - 1] + 1) {
         // Follow up 2: 有序，有重复
         // if (nums[j] == nums[j - 1] + 1 || nums[j] == nums[j - 1]) {
                j++;
            } else {
                if (nums[i] == nums[j - 1]) {
                    sb.append(nums[i]);
                } else {
                    sb.append(nums[i]).append("->").append(nums[j - 1]);
                }
                result.add(sb.toString());
                i = j;
                j++;
            } 
        }
        
        StringBuilder sb = new StringBuilder();
        if (nums[i] == nums[j - 1]) {
            sb.append(nums[i]);
        } else {
            sb.append(nums[i]).append("->").append(nums[j - 1]);
        }
        result.add(sb.toString());
        
        return result;
    }
    
    /*
     *  Follow up 1: 
     *  如果输入为[1,2,3,4,5,.......100000, 300000] 这种情况怎么办
     *  就是输入开始是连续的，直到某个点开始不连续，找到那个连续区间的终止点即可，二分法
     *  e.g. {1, 2, 3, 4, 5, 10000, 20000}; // 连续区间的终点为i = 4
     */
    public static int summaryRanges2(int[] nums) {
    	int start = 0;
    	int end = nums.length - 1;
    	
    	while (start < end) {
    		int mid = start + (end - start) / 2 + 1; // 避免start和mid重合的情况
    		if (nums[mid] - nums[start] == mid - start) {
    			// 即从start 到 mid都连续，断层在mid = end之间，所以区间右移
    			start = mid;
    		} else {
    			end = mid - 1;
    		}
    	}
    	
    	return start;
    }
    
    /*
     *  Follow up 2: 
     *  
     *  有序，有重复, 见基本题注释， one line change
     */
    
    /*
     *  Follow up 3: 
     *  
     *  无序，有重复，用HashSet
     *  http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=292816
     *  
     *  Time: O(n) + O(rlogr) --> r: number of ranges.  worst case canbe O(nlogn)即没有连续的range
     */
    public static List<String> summaryRanges3(int[] nums) {
    	List<String> result = new ArrayList<>();
    	List<int[]> ranges = new ArrayList<>();
    	
    	if (nums == null || nums.length == 0) {
    		return result;
    	}
    	
    	Set<Integer> set = new HashSet<>();
    	// O(n)
    	for (int num: nums) {
    		set.add(num);
    	}
    	
    	// O(n)
    	for (int num: nums) {
    		if (set.contains(num)) {
    			int start = num;
    			int end = num;
    			set.remove(num);
    			
    			//while (start != Integer.MIN_VALUE && set.contains(start - 1)) {
    			while (set.contains(start - 1)) {
    				start--;
    				set.remove(start);
    			}
    			
    			// while (start != Integer.MAX_VALUE && set.contains(end + 1)) {
    			while (set.contains(end + 1)) {
    				end++;
    				set.remove(end);
    			}
    			ranges.add(new int[]{start, end});
    		}
    	}
    	
    	// O(r * log r) -- r: range的个数 
    	Collections.sort(ranges, new Comparator<int[]>() {
    		@Override
    		public int compare(int[] o1, int[] o2) {
    			return o1[0] - o2[0];
    		}
    	});
    	
    	for (int[] range: ranges) {
    		if (range[0] == range[1]) {
    			result.add("" + range[0]);
    		} else {
    			result.add(range[0] + "->" + range[1]);
    		}
    	}
    	
    	return result;
    }
    
    /*
     *  Follow up 4: 
     *  基本解法有负数可以吗？ -- 可以
     */
    
    /*
     * Follow up 5:
     * 输入 Array [1, 3, 5, 8, 12, 16], 
     * 输出步长相等的range--> "1-5/2, 8-16/4" 2 and 4 are distances
     * 
     * 类似于基本题解法，只是加入步长
     * 
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=173601
     * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171888
     */
    
    public static String summaryRanges4(int[] nums) {
    	StringBuilder sb = new StringBuilder();
    	
    	if (nums == null || nums.length == 0) {
    		return sb.toString();
    	}
    	
    	int i = 0;
    	int j = 0;
    	int n = nums.length;
    	int step = 0 ;
    	
    	while (j < n) {
    		if (j + 1 < n) {
    			
    			// 若有重复
    			/*
    			if (nums[j + 1] == nums[j]) {
    				j++;
    				continue;
    			}
    			*/
    			step = nums[j + 1] - nums[j];
    			while (j + 1 < n && nums[j + 1] - nums[j] == step) {
    				j++;
    			}
    		}
    		
    		if (j == n - 1) {
    			// 若有重复
    			/* 
    			if (nums[j] != nums[j - 1]) {
    				sb.append(getRange(nums, i, j, step));
    			}
    			*/
    			sb.append(getRange(nums, i, j, step));
    		} else {
    			sb.append(getRange(nums, i, j, step)).append(", ");
    		}
    		
    		j++;
    		i = j;
    	}
    	
    	return sb.toString();
    }
    
    private static String getRange(int[] nums, int start, int end, int step) {
    	if (start == end) {
    		return "" + nums[start];
    	} 
    	
    	return nums[start] + "-" + nums[end] + "/" + step;
    }
    
    
    public static void main(String[] args) {
    	System.out.println("===== 1. 基本题（LC 282）有序，无重复 + Follow up 2(有序，有重复)======");

		int[] test11 = {0, 1, 2, 5, 7};
		int[] test12 = {0, 1, 2, 4, 5, 6};
		int[] test13 = {1, 2, 3, 4, 7, 9, 10};
		int[] test14 = {-2147483648, -2147483647, 2147483647};
		int[] test15 = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 10, 11, 12, 20};
		
		// Follow up 2:
		int[] test16 = {0, 1, 1, 1, 2, 2, 5, 7, 7};
		int[] test17 = {0, 0, 0};
		int[] test18 = {-3, -3, -2, -1, 0, 1, 2, 10, 10};
		int[] test19 = {-2147483648, -2147483648, -2147483647, 2147483647};
		
		System.out.println(summaryRanges(test11));
		System.out.println(summaryRanges(test12));
		System.out.println(summaryRanges(test13));
		System.out.println(summaryRanges(test14));
		System.out.println(summaryRanges(test15));
		System.out.println();	
		
		System.out.println(summaryRanges(test16));
		System.out.println(summaryRanges(test17));
		System.out.println(summaryRanges(test18));
		System.out.println(summaryRanges(test19));
		System.out.println();
		
    	System.out.println("===== 2. Follow up 1 找连续区间的终点======");

		int[] test21 = {0, 1, 2, 3, 4, 5, 7};   //5
		int[] test22 = {1, 2, 3, 4, 5, 10000, 20000}; // 4
		int[] test23 = {5, 6, 20, 50, 70, 2000, 3000}; // 1
		int[] test24 = {-8, -7, -6, -5, 70, 2000, 3000}; // 3
		
		System.out.println(summaryRanges2(test21));
		System.out.println(summaryRanges2(test22));
		System.out.println(summaryRanges2(test23));
		System.out.println(summaryRanges2(test24));
		System.out.println();
		
		System.out.println("===== 3. Follow up 3 输入无序有重复======");
		int[] test31 = {7, 5, 0, 7, 2, 5, 1};
		int[] test32 = {4, 6, 6, 1, 2, 0, 1, 0, 5};
		int[] test33 = {10, 10, 20, -3, -2, 3, 1, 2, 0, 11, -1};
		
		System.out.println(summaryRanges3(test31));
		System.out.println(summaryRanges3(test32));
		System.out.println(summaryRanges3(test33));
		System.out.println();	
		
		System.out.println("===== 4. Follow up 5 输出连续等差的range和其步长======");
		int[] test41 = {1, 3, 5, 8, 12, 16};
		int[] test42 = {-3, -2, -1, 0, 1, 2, 4, 6, 7, 8};
		System.out.println(summaryRanges4(test41));
		System.out.println(summaryRanges4(test42));
	}
}
