import java.util.Arrays;

public class Solution {
    /**
     * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and
     * arr[i] + arr[j] + arr[k] == target.
     *
     * As the answer can be very large, return it modulo 10^9 + 7.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
     * Output: 20
     * Explanation:
     * Enumerating by the values (arr[i], arr[j], arr[k]):
     * (1, 2, 5) occurs 8 times;
     * (1, 3, 4) occurs 8 times;
     * (2, 2, 4) occurs 2 times;
     * (2, 3, 3) occurs 2 times.
     *
     *
     * Example 2:
     *
     * Input: arr = [1,1,2,2,2,2], target = 5
     * Output: 12
     * Explanation:
     * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
     * We choose one 1 from [1,1] in 2 ways,
     * and two 2s from [2,2,2,2] in 6 ways.
     *
     *
     * Constraints:
     *
     * 3 <= arr.length <= 3000
     * 0 <= arr[i] <= 100
     * 0 <= target <= 300
     */

    // Solution 1: Time: O(n + m^2), Space: O(m)  m = min(n, w).  in this case, m = w: range of arr (0 ~ 100)
    public static int threeSumMulti(int[] arr, int target) {
        Arrays.sort(arr);
        int MOD = 1_000_000_007;
        long result = 0;

        long[] count = new long[101]; // since 0 <= arr[i] <= 100
        int keyCount = 0;

        for (int a: arr) {
            count[a]++;
            if (count[a] == 1) {
                keyCount++;
            }
        }

        int[] keys = new int[keyCount];
        int index = 0;

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                keys[index++] = i;
            }
        }

        for (int i = 0; i < keys.length; i++) {
            int x = keys[i];
            int t = target - x;

            int j = i;
            int k = keys.length - 1;

            while (j <= k) {
               int y = keys[j];
               int z = keys[k];

               if (y + z < t) {
                   j++;
               } else if (y + z > t) {
                   k--;
               } else {
                   if (i < j && j < k) {
                       result += count[x] * count[y] * count[z];
                   } else if (i == j && j < k) {
                       result += count[x] * (count[x] - 1) / 2 * count[z];
                   } else if (i < j && j == k) {
                       result += count[x] * count[y] * (count[y] - 1) / 2;
                   } else {
                       result += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                   }

                   result %= MOD;
                   j++;
                   k--;
               }
            }
        }

        return (int)result;
    }

    // Solution 2: Time: O(n^2), Space: O(1)
//    public static int threeSumMulti(int[] arr, int target) {
//        Arrays.sort(arr);
//        int MOD = 1_000_000_007;
//        int result = 0;
//
//        for (int i = 0; i < arr.length; i++) {
//            int t = target - arr[i];
//
//            int j = i + 1;
//            int k = arr.length - 1;
//
//            while (j < k) {
//                if (arr[j] + arr[k] < t) {
//                    j++;
//                } else if (arr[j] + arr[k] > t) {
//                    k--;
//                } else {
//                    if (arr[j] != arr[k]) {
//                        int left = 1;
//                        int right = 1;
//
//                        while (j + 1 < k && arr[j + 1] == arr[j]) {
//                            left++;
//                            j++;
//                        }
//
//                        while (k - 1 > j && arr[k - 1] == arr[k]) {
//                            right++;
//                            k--;
//                        }
//
//                        result += left * right;
//                        result %= MOD;
//                        j++;
//                        k--;
//                    } else {
//                        int count = k - j + 1;
//                        result += count * (count - 1) / 2;
//                        result %= MOD;
//                        break;
//                    }
//                }
//            }
//        }
//
//        return  result;
//    }

    public static void main(String[] args) {
        int[] test1 = {1,1,2,2,3,3,4,4,5,5};
        int[] test2 = {1,1,2,2,2,2};

        System.out.println(threeSumMulti(test1, 8));
        System.out.println(threeSumMulti(test2, 5));
    }
}
