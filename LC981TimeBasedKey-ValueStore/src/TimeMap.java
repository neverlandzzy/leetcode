import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
 */

public class TimeMap {

}