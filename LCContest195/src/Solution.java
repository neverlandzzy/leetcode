import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static boolean isPathCrossing(String path) {

        int[] pos = {0, 0};
        Set<String> set = new HashSet<>();
        set.add(indexToString(pos));

        for (char c: path.toCharArray()) {
            if (c == 'N') {
                pos[0]++;
            } else if (c == 'S') {
                pos[0]--;
            } else if (c == 'E') {
                pos[1]++;
            } else {
                pos[1]--;
            }

            String posString = indexToString(pos);

            if (set.contains(posString)) {
                return true;
            }
            set.add(posString);
        }

        return false;
    }

    private static String indexToString(int[] pos) {
        return pos[0] + "," + pos[1];
    }

    public static boolean canArrange(int[] arr, int k) {
        int sum = 0;

        for (int i : arr) {
            sum += i % k;
        }

        return sum % k == 0;
    }

    public static int numSubseq(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = dp[i][i] * 2 <= target ? 1 : 0;
        }

        for (int len = 1; len < n; len++) {
            for (int from = 0; from + len < n; from++) {
                int to = from + len;

                int max = nums[from];
                int min = nums[from];
                for (int k = from; k <= to; k++) {
                    max = Math.max(max, nums[k]);
                    min = Math.min(min, nums[k]);

                    if (max + min > target) {
                        dp[from][to] = from == 0 ? 0 : dp[from - 1][to] + dp[from][to - 1];
                        break;
                    }

                    dp[from][to] = 2 ^ len - 1;
                    System.out.println("from = " + from +  " to = " + to);
                }
            }
        }

        return dp[0][n -1];
    }

    public static void main(String[] args) {
        String test11 = "NES";
        String test12 = "NESWW";

//        System.out.println(isPathCrossing(test11));
//        System.out.println(isPathCrossing(test12));

//        int[] test21 = {1,2,3,4,5,10,6,7,8,9};
//        System.out.println(canArrange(test21, 5));
//
//        int[] test22 = {1,2,3,4,5,6};
//        System.out.println(canArrange(test22, 7));
//
//        int[] test23 = {1,2,3,4,5,6};
//        System.out.println(canArrange(test23, 10));
//
//        int[] test24 = {-10, 10};
//        System.out.println(canArrange(test24, 2));
//
//        int[] test25 = {-1,1,-2,2,-3,3,-4,4};
//        System.out.println(canArrange(test25, 3));

        int[] test31 = {3,5,6,7};
        System.out.println(numSubseq(test31, 9));
//        int[] test32 = {3,3,6,8};
//        System.out.println(numSubseq(test32, 10));
//        int[] test33 = {2,3,3,4,6,7};
//        System.out.println(numSubseq(test33, 12));
//        int[] test34 = {5,2,4,1,7,6,8};
//        System.out.println(numSubseq(test34, 16));
    }
}
