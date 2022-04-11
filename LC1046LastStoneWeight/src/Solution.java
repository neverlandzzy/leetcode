import java.util.PriorityQueue;

public class Solution {
    /**
     * You are given an array of integers stones where stones[i] is the weight of the ith stone.
     *
     * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose
     * the heaviest two stones have weights x and y with x <= y. The result of this smash is:
     *
     * If x == y, both stones are destroyed, and
     * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
     * At the end of the game, there is at most one stone left.
     *
     * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
     *
     * Example 1:
     *
     * Input: stones = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
     *
     * Example 2:
     *
     * Input: stones = [1]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     */

    // Solution 1: bucket sort, Time O(n + w), w: range of stones (1 ~ 1000)
    public static int lastStoneWeight(int[] stones) {
        int maxWeight = 0;
        for (int s: stones) {
            maxWeight = Math.max(s, maxWeight);
        }

        int[] buckets = new int[maxWeight + 1];

        for (int s: stones) {
            buckets[s]++;
        }

        int currentWeight = maxWeight;
        maxWeight = 0;

        while (currentWeight > 0) {
            if (buckets[currentWeight] == 0) {
                currentWeight--;
            } else if (maxWeight == 0) {
                buckets[currentWeight] %= 2;
                if (buckets[currentWeight] == 1) {
                    maxWeight = currentWeight;
                }
                currentWeight--;
            } else {
                buckets[currentWeight]--;
                if (maxWeight - currentWeight <= currentWeight) {
                    buckets[maxWeight - currentWeight]++;
                    maxWeight = 0;
                } else {
                    maxWeight -= currentWeight;
                }
            }
        }

        return maxWeight;
    }

    // Solution 2: O(n*logn)
//    public static int lastStoneWeight(int[] stones) {
//        PriorityQueue<Integer> heap = new PriorityQueue<>((a1, a2) -> (a2 - a1));
//
//        for (int s: stones) {
//            heap.offer(s);
//        }
//
//        while (heap.size() > 1) {
//            int a = heap.poll();
//            int b = heap.poll();
//
//            if (a != b) {
//                heap.offer(a - b);
//            }
//        }
//
//        return heap.isEmpty() ? 0 : heap.poll();
//    }

    public static void main(String[] args) {
        int[] test1 = {2, 7, 4, 1, 8, 1};
        int[] test2 = {1};

        System.out.println(lastStoneWeight(test1));
        System.out.println(lastStoneWeight(test2));
    }
}
