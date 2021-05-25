import java.util.Random;

public class Solution {

    int[] preSum;
    int sum;
    Random random;

    public Solution(int[] w) {
        preSum = new int[w.length];
        random = new Random();

        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            preSum[i] = sum;
        }
    }

    public int pickIndex() {
        int pick = random.nextInt(sum);
        int start = 0;
        int end = preSum.length - 1;

        while (start + 1 < end) {
            int mid = start + (end -  start) / 2;
            if (preSum[mid] <= pick) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (preSum[start] > pick) {
            return start;
        }

        return end;
    }

    public static void main(String[] args) {
        int[] w1 = {1};
        Solution solution1 = new Solution(w1);
        System.out.println(solution1.pickIndex());

        int[] w2 = {1, 3};
        Solution solution2 = new Solution(w2);
        System.out.println(solution2.pickIndex());
        System.out.println(solution2.pickIndex());
        System.out.println(solution2.pickIndex());
        System.out.println(solution2.pickIndex());
        System.out.println(solution2.pickIndex());
    }
}
