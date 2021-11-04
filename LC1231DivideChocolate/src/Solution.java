public class Solution {

    /**
     * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
     *
     * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using k cuts,
     * each piece consists of some consecutive chunks.
     *
     * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
     *
     * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
     *
     *
     *
     * Example 1:
     *
     * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
     * Output: 6
     * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
     *
     * Example 2:
     *
     * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
     * Output: 1
     * Explanation: There is only one way to cut the bar into 9 pieces.
     *
     * Example 3:
     *
     * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
     * Output: 5
     * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
     *
     *
     * Constraints:
     *
     * 0 <= k < sweetness.length <= 10^4
     * 1 <= sweetness[i] <= 10^5
     */

    // Similar to 410, 774, 875, 1011
    //
    // 与410的几点不同：
    // 1. 410中m是划分的份数，本题中k是切的次数，切k次相当于划分了k+1份，即m = k+1。因此本题counter从0开始
    // 2. 410是求最大的一份的sum的最小可能值，本题是求最小的一份的sum的最大可能值。因此当total > minSweetness时，total要置为0 (410中total置为i)
    //    因为本题在找最小的sum值，其它的sum都比它大，因此当total刚刚等于或大于minSweetness时，total的值时可以的，total从0开始计算。而410中，因为在找
    //    最大的sum值，当total大于sum时，total的值是不可以的，因此当前i的值要算在下一个区间里
    // 3. valid = true时表示将sweetness切k次可以实现最小的sweetness为mid（即counter > k表示我们要切counter刀才能达到最小值为minSweetness，k刀更能满足），
    //    即mid是一个workable value，因此我们继续找可能更大一些的值，即在[mid, right]中找，即 min = mid
    //    valid = false表示mid不是一个workable value，因此要在[left, mid)中查找，即 sum = mid - 1
    // 4. mid = (left + right + 1) / 2 是为了配合3，防止死循环
    public static int maximizeSweetness(int[] sweetness, int k) {
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i: sweetness) {
            min = Math.min(i, min);
            sum += i;
        }

        while (min < sum) {
            int mid = (min + sum + 1) / 2;

            if (valid(sweetness, k, mid)) {
                min = mid;
            } else {
                sum = mid - 1;
            }
        }

        return min;
    }

    private static boolean valid(int[] sweetness, int k, int minSweetness) {
        int counter = 0;
        int total = 0;

        for (int i: sweetness) {
            total += i;
            if (total >= minSweetness) {
                total = 0;
                counter++;

                if (counter > k) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3,4,5,6,7,8,9};
        int[] test2 = {5,6,7,8,9,1,2,3,4};
        int[] test3 = {1,2,2,1,2,2,1,2,2};

        System.out.println(maximizeSweetness(test1, 5));
        System.out.println(maximizeSweetness(test2, 8));
        System.out.println(maximizeSweetness(test3, 2));
    }
}
