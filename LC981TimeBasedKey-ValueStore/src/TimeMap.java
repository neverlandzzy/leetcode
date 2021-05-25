import java.util.*;

/**
 * Solution 1: TreeMap - Set: O(log n), Get: O(log n)
 */
//public class TimeMap {
//    /** Initialize your data structure here. */
//
//    Map<String, TreeMap<Integer, String>> map;
//
//    public TimeMap() {
//        map = new HashMap<>();
//    }
//
//    public void set(String key, String value, int timestamp) {
//        if (!map.containsKey(key)) {
//            map.put(key, new TreeMap<>());
//        }
//
//        map.get(key).put(timestamp, value);
//    }
//
//    public String get(String key, int timestamp) {
//        if (!map.containsKey(key)) {
//            return "";
//        }
//
//        TreeMap<Integer, String> treeMap = map.get(key);
//        Integer treemapKey = treeMap.floorKey(timestamp);
//
//        if (treemapKey == null) {
//            return "";
//        } else {
//            return treeMap.get(treemapKey);
//        }
//    }
//}

/**
 * Solution 2: Binary Search - Set: O(1), Get: O(log n)
 *
 * *Use this condition: The timestamps for all TimeMap.set operations are strictly increasing.
 */

public class TimeMap {

    class Node {
        Integer timestamp;
        String val;

        public Node(Integer timestamp, String val) {
            this.timestamp = timestamp;
            this.val = val;
        }
    }

    Map<String, List<Node>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }

        map.get(key).add(new Node(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<Node> list = map.get(key);

        if (list.get(0).timestamp > timestamp) {
            return "";
        }

        int start = 0;
        int end = list.size() - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid).timestamp == timestamp) {
                return list.get(mid).val;
            }

            if (list.get(mid).timestamp > timestamp) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return list.get(end).timestamp <= timestamp ? list.get(end).val : list.get(start).val;
    }
}