import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> numMap = new HashMap<>();
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        int result = arr.length;
        for (int a: arr) {
            numMap.put(a, numMap.getOrDefault(a, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: numMap.entrySet()) {
            int count = entry.getValue();
            countMap.put(count, countMap.getOrDefault(count, 0) + 1);
        }

        while (!countMap.isEmpty()) {
            Map.Entry<Integer, Integer> entry = countMap.firstEntry();
            int key = entry.getKey();
            int value = entry.getValue();
            int count = key * value;

            if (k <= count) {
                result = value - k / key;
                countMap.remove(key);
                break;
            } else {
                k -= count;
                countMap.remove(key);
            }

        }

        result += getCount(countMap);
        return result;
    }

    private static int getCount(TreeMap<Integer, Integer> countMap) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }

    public static int minDays(int[] bloomDay, int m, int k) {

    }

    public static void main(String[] args) {
//        int[] test21 = {5,5,4};
//        int[] test22 = {4,3,1,1,3,3,2};
//        int[] test23 = {2,4,1,8,3,5,1,3};
//        int[] test24 = {1,1,3,3};
//        int[] test25 = {1,2,2,2,2};
//
//        System.out.println(findLeastNumOfUniqueInts(test21, 1));
//        System.out.println(findLeastNumOfUniqueInts(test22, 3));
//        System.out.println(findLeastNumOfUniqueInts(test23, 3));
//        System.out.println(findLeastNumOfUniqueInts(test24, 0));
//        System.out.println(findLeastNumOfUniqueInts(test25, 2));

        int[] test31 = {1,10,3,10,2};
        int[] test32 = {1,10,3,10,2};
        int[] test33 = {7,7,7,7,12,7,7};
        int[] test34 = {1000000000,1000000000};
        int[] test35 = {1,10,2,9,3,8,4,7,5,6};

        System.out.println(minDays(test31, 3, 1));
        System.out.println(minDays(test32, 3, 2));
        System.out.println(minDays(test33, 2, 3));
        System.out.println(minDays(test34, 1, 1));
        System.out.println(minDays(test35, 4, 2));
    }

}
