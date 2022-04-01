import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * There is a broken calculator that has the integer startValue on its display initially. In
     * one operation, you can:
     *
     * multiply the number on display by 2, or
     * subtract 1 from the number on display.
     * Given two integers startValue and target, return the minimum number of operations needed to
     * display target on the calculator.
     *
     * Example 1:
     *
     * Input: startValue = 2, target = 3
     * Output: 2
     * Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
     *
     * Example 2:
     *
     * Input: startValue = 5, target = 8
     * Output: 2
     * Explanation: Use decrement and then double {5 -> 4 -> 8}.
     *
     * Example 3:
     *
     * Input: startValue = 3, target = 10
     * Output: 3
     * Explanation: Use double, decrement and double {3 -> 6 -> 5 -> 10}.
     *
     *
     * Constraints:
     *
     * 1 <= x, y <= 10^9
     */

    // 从target出发，到达startValue，有两种方式：target + 1或者target / 2
    // 当target > startValue时
    // 如果target是奇数: target不可能通过上一步*2得到，所以上一步一定是做了-1，因此上一步的数字一定是target+1
    // 如果target是偶数：target既可以 *2得到上一步数字，也可以 +1。但如果是+1，则target+1为奇数，为了减小target
    //                 到达startValue，我们还要再+1使其变为偶数再/2，即(target+1+1)/2 (三步操作) =
    //                 target/2 + 1(两步操作)。所以target应该直接/2.
    public static int brokenCalc(int startValue, int target) {
        int result = 0;

        while (target > startValue) {
            result++;
            if (target % 2 == 0) {
                target /= 2;
            } else {
                target++;
            }
        }

        return result + startValue - target;
    }

//    // BFS solution: TLE
//    public static int brokenCalc(int startValue, int target) {
//        int step = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(startValue);
//
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                int curValue = queue.poll();
//                if (curValue == target) {
//                    return step;
//                }
//
//                queue.offer(curValue * 2);
//                queue.offer(curValue - 1);
//            }
//            step++;
//        }
//
//        return step;
//    }

    public static void main(String[] args) {
        System.out.println(brokenCalc(2, 3));
        System.out.println(brokenCalc(5, 8));
        System.out.println(brokenCalc(3, 10));
    }
}
