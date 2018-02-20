import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class RandomizedCollection {
	
	/*
	 * Design a data structure that supports all following operations in average O(1) time.
	 * 
	 * Note: Duplicate elements are allowed.
	 * insert(val): Inserts an item val to the collection.
	 * remove(val): Removes an item val from the collection if present.
	 * getRandom: Returns a random element from current collection of elements. The probability of each element being returned is 
	 * linearly related to the number of same value the collection contains.
	 * 
	 * Example:
	 * 
	 * // Init an empty collection.
	 * RandomizedCollection collection = new RandomizedCollection();
	 * 
	 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
	 * collection.insert(1);
	 * 
	 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
	 * collection.insert(1);
	 * 
	 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
	 * collection.insert(2);
	 * 
	 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
	 * collection.getRandom();
	 * 
	 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
	 * collection.remove(1);
	 * 
	 * // getRandom should return 1 and 2 both equally likely.
	 * collection.getRandom();
	 */

	//https://discuss.leetcode.com/topic/53688/java-haspmap-linkedhashset-arraylist-155-ms?page=1
	
    /** Initialize your data structure here. */
	private List<Integer> list;
	// 这里用LinkedHashSet而不用HashSet， 为了提高iterator().next()的performance
	//
	// [HashSet]
	// https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
	//
	// This class offers constant time performance for the basic operations (add, remove, contains and size), assuming the hash 
	// function disperses the elements properly among the buckets. Iterating over this set requires time proportional to the sum of 
	// the HashSet instance's size (the number of elements) plus the "capacity" of the backing HashMap instance (the number of buckets). 
	// Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
    // https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashSet.html
	//
	// [LinkedHashSet]
	// https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashSet.html
	// Iteration over a LinkedHashSet requires time proportional to the size of the set, regardless of its capacity. Iteration over a 
	// HashSet is likely to be more expensive, requiring time proportional to its capacity.

	private Map<Integer, Set<Integer>> map;
	private Random random;
	
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean result = map.containsKey(val);
        
        if (!result) {
        	map.put(val, new LinkedHashSet<Integer>());
        }
        
        map.get(val).add(list.size());
        list.add(val);
        return !result;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
        	return false;
        }
        
        int index = map.get(val).iterator().next();
        map.get(val).remove(index);
        if (index != list.size() - 1) {
        	int tail = list.get(list.size() - 1);
        	list.set(index, tail);
        	map.get(tail).remove(list.size() - 1);
        	map.get(tail).add(index);
        }
        list.remove(list.size() - 1);
       
        
        if (map.get(val).isEmpty()) {
        	map.remove(val);
        }
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
