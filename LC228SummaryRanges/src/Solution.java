import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a sorted integer array without duplicates, return the summary of its ranges.
	 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 */


    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        int i = 0;
        int n = nums.length;

        while (i < n) {
            int j = i + 1;

            while (j < n && nums[j] == nums[j - 1] + 1) {
                j++;
            }

            StringBuilder sb = new StringBuilder();
            if (j - i > 1) {
                sb.append(nums[i]).append("->").append(nums[j - 1]);
            } else {
                sb.append(nums[i]);

            }
            result.add(sb.toString());

            i = j;
        }

        return result;
    }

    // Another solution
    /*
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
                j++;
            } else {
                if (i == j - 1) {
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
        if (i == j - 1) {
            sb.append(nums[i]);
        } else {
            sb.append(nums[i]).append("->").append(nums[j - 1]);
        }
        result.add(sb.toString());
        
        return result;
    }
    */

    
    public static void main(String[] args) {
		int[] test1 = {0,1,2,4,5,7};
		int[] test2 = {0,1,2,4,5,6};
		int[] test3 = {1,2,3,4, 7, 9, 10};
		int[] test4 = {-2147483648,-2147483647,2147483647};
		
		System.out.println(summaryRanges(test1));
		System.out.println(summaryRanges(test2));
		System.out.println(summaryRanges(test3));
		System.out.println(summaryRanges(test4));

	}
}
