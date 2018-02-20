import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class AllOne {
	/*
	 * Implement a data structure supporting the following operations:
	 * 
	 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
	 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, 
	 *            this function does nothing. Key is guaranteed to be a non-empty string.
	 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
	 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
	 * Challenge: Perform all these in O(1) time complexity.
	 * 
	 */
	
	// (1) 很容易想到的暴力解： min/max heap + hashmap -- O(log n)
	//     https://www.yuanmas.com/info/obynNbx3yK.html
	// 
	// (2) O(1)解法  iaming
	// https://discuss.leetcode.com/topic/65634/java-ac-all-strict-o-1-not-average-o-1-easy-to-read/4
	// 用一个双向链表记录每个key的频率，从小到大，每个频率用一个node表示，node中用set保存相同频率的key。用LinkedHashSet比HashSet 在iterator().next()时更快
	//
	
	class Node {
		Node prev;
		Node next;
		int val;
		Set<String> set;
		
		Node(int val) {
			this.val = val;
			set = new LinkedHashSet<>(); // faster than HashSet in iteration
		}
		
		// insert this before node
		void insertAt(Node node) {
			this.next = node;
			this.prev = node.prev;
			node.prev.next = this;
			node.prev = this;
		}
		
		void remove(String key) {
			set.remove(key);
			if (set.isEmpty()) {
				this.prev.next = this.next;
				this.next.prev = this.prev;
			}
		}
	}
	
	Node head;
	Node tail;
	Map<String, Node> map;

    /** Initialize your data structure here. */
    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Node node = map.getOrDefault(key, head);
        Node next = node.next;
        
        if (next.val != node.val + 1) {
        	next = new Node(node.val + 1);
        	next.insertAt(node.next);
        }
        
        next.set.add(key);
        map.put(key, next);
        if (node != head) {
        	node.remove(key);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Node node = map.get(key);
        if (node == null) {
        	return;
        }
        
        if (node.val == 1) {
        	node.remove(key);
        	map.remove(key);
        	return;
        }
        
        Node prev = node.prev;
        
        if (prev.val != node.val - 1) {
        	prev = new Node(node.val - 1);
        	prev.insertAt(node);
        }
        
        prev.set.add(key);
        map.put(key, prev);
        node.remove(key);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail) {
        	return "";
        }       
        return tail.prev.set.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail) {
        	return "";
        } 
        return head.next.set.iterator().next();
    }
}
