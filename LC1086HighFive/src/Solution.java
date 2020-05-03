import java.util.*;

public class Solution {

    /**
     * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
     *
     * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
     *
     *
     *
     * Example 1:
     *
     * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
     * Output: [[1,87],[2,88]]
     * Explanation:
     * The average of the student with id = 1 is 87.
     * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
     *
     *
     * Note:
     *
     * 1 <= items.length <= 1000
     * items[i].length == 2
     * The IDs of the students is between 1 to 1000
     * The score of the students is between 1 to 100
     * For each student, there are at least 5 scores
     */

    public static void main(String[] args) {
        int[][] test1 = {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        int[][] result1 = highFive(test1);

        for (int[] res: result1) {
            printArray(res);
        }
    }

    // My Solution
//    public static int[][] highFive(int[][] items) {
//        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
//
//        for (int[] item: items) {
//            int id = item[0];
//            int score = item[1];
//
//            if (!map.containsKey(id)) {
//                map.put(id, new PriorityQueue<>(Collections.reverseOrder()));
//            }
//            map.get(id).offer(score);
//        }
//
//        int n = map.size();
//        int[][]result = new int[n][2];
//        int index = 0;
//
//        for (Map.Entry<Integer, PriorityQueue<Integer>> entry: map.entrySet()) {
//            int avg = 0;
//            for (int i = 0; i < 5; i++) {
//                avg += entry.getValue().poll();
//            }
//
//            avg /= 5;
//
//            result[index][0] = entry.getKey();
//            result[index][1] = avg;
//            index++;
//        }
//
//        return result;
//    }

    // Better solution
    public static int[][] highFive(int[][] items) {
        if (items == null || items.length == 0 || items[0] == null || items[0].length == 0) {
            return items;
        }

        Arrays.sort(items, (i1, i2) -> {
            if (i1[0] == i2[0]) {
                return i2[1] - i1[1];
            } else {
                return i1[0] - i2[0];
            }
        });

        List<int[]> scores = new ArrayList<>();
        int n = items.length;
        int i = 0;

        while (i < n) {
            int id = items[i][0];
            int sum = 0;
            int count = 0;

            while (i < n && count < 5) {
                sum += items[i][1];
                i++;
                count++;
            }

            scores.add(new int[] {id, sum / 5});
            while (i < n && items[i][0] == id) {
                i++;
            }
        }

//            int[][] result = new int[scores.size()][2];
//            for (int j = 0; j < scores.size(); j++) {
//                result[j] = scores.get(j);
//            }
        return scores.stream()
                .map(a -> new int[]{a[0], a[1]})
                .toArray(int[][]::new);
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
