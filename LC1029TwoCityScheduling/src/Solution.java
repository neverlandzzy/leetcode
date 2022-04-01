import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    /**
     * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti],
     * the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is
     * bCosti.
     *
     * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
     *
     *
     *
     * Example 1:
     *
     * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
     * Output: 110
     * Explanation:
     * The first person goes to city A for a cost of 10.
     * The second person goes to city A for a cost of 30.
     * The third person goes to city B for a cost of 50.
     * The fourth person goes to city B for a cost of 20.
     *
     * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
     *
     * Example 2:
     *
     * Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
     * Output: 1859
     *
     * Example 3:
     *
     * Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
     * Output: 3086
     *
     *
     * Constraints:
     *
     * 2 * n == costs.length
     * 2 <= costs.length <= 100
     * costs.length is even.
     * 1 <= aCosti, bCosti <= 1000
     */

    // 将人送到cityA，花费costs[i][0], 送到cityB，花费costs[i][1]。按照costs[i][0] - costs[i][1]排序
    // 即送到A与送到B的花费之差排序，前n个送到A的花费差最小，因此送到A，后n个送到B
    public static int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, Comparator.comparingInt((int[] c) -> (c[0] - c[1])));

        int result = 0;
        int n = costs.length;

        for (int i = 0; i < n; i++) {
            result += (i < n / 2) ? costs[i][0] : costs[i][1];
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        int[][] test2 = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};

        System.out.println(twoCitySchedCost(test1));
        System.out.println(twoCitySchedCost(test2));
    }
}
