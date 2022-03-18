import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


public class LFUCache {
	/**
	 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
	 * 
	 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
	 * 
	 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate 
	 * the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more 
	 * keys that have the same frequency), the least recently used key would be evicted.
	 * 
	 * Follow up:
	 * Could you do both operations in O(1) time complexity?
	 * 
	 * Example:
	 * 
	 * LFUCache cache = new LFUCache(2); // capacity
	 * cache.put(1, 1);
	 * cache.put(2, 2);
	 * cache.get(1);       // returns 1
	 * cache.put(3, 3);    // evicts key 2
	 * cache.get(2);       // returns -1 (not found)
	 * cache.get(3);       // returns 3.
	 * cache.put(4, 4);    // evicts key 1.
	 * cache.get(1);       // returns -1 (not found)
	 * cache.get(3);       // returns 3
	 * cache.get(4);       // returns 4
	 */
	
	/* http://bookshadow.com/weblog/2016/11/22/leetcode-lfu-cache/
	 * 
	 * 数据结构设计如下：
	 * 1. 每次从head加入新的pair， 因此head的count(frequency) 最小；
	 * 2. 需要删除的时候，从head删，每个node中的 LinkedHashSet是按插入顺序存储的，因此，iterator.next()的就是least recently used key
	 * 
	 * head --- FreqNode1 ---- FreqNode2 ---- ... ---- FreqNodeN
	 *   			|               |                       |               
	 * 			  first           first                   first             
	 *      		|               |                       |               
	 * 			  KeyNodeA        KeyNodeE                KeyNodeG           
	 *      		|               |                       |               
	 *			  KeyNodeB        KeyNodeF                KeyNodeH           
	 *      		|               |                       |               
	 * 			  KeyNodeC         last                   KeyNodeI           
	 *      		|                                       |      
	 * 			  KeyNodeD                                 last
	 *      		|
	 * 			  last
	 */
	
	// 每一个node 是frequency(count) 相同的pair的集合(上图中的FreqNode)
	private class Node {
		public int count;
		public LinkedHashSet<Integer> keys;
		public Node prev;
		public Node next;
		
		public Node(int count) {
			this.count = count;
			keys = new LinkedHashSet<>();
			prev = null;
			next = null;
		}
	}
	
	private Node head;
	private int capacity;
	private Map<Integer, Integer> keyMap; //存输入的(key, value)
	private Map<Integer, Node> freqMap;  //从key 到 FreqNode的映射
	
	public LFUCache(int cap) {
		this.head = null;
		this.capacity = cap;
		this.keyMap = new HashMap<>();
		this.freqMap = new HashMap<>();
	}
	
	public int get(int key) {
		if (keyMap.containsKey(key)) {
			increaseCount(key);
			return keyMap.get(key);
		}
		return -1;
	}
	
	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
		
		if (keyMap.containsKey(key)) {
			keyMap.put(key, value);
		} else {
			if (keyMap.size() < capacity) {
				keyMap.put(key, value);
			} else {
				removeOldNode();
				keyMap.put(key, value);
			}
			addToHead(key);
		}
		increaseCount(key);
	}
	
	private void increaseCount(int key) {
		Node node = freqMap.get(key); // 由于get()里检查过，所以node肯定存在在nodeHash中
		node.keys.remove(key);
		
		if (node.next == null) {
			node.next = new Node(node.count + 1);  // 若node后面没有点，则创建一个
			node.next.prev = node;
			node.next.keys.add(key);
		} else if (node.next.count == node.count + 1) { // 若node后面刚好有freq 比它大1的点，则在这些点里加上node 
			node.next.keys.add(key);
		} else {
			Node tmp = new Node(node.count + 1);  // 若node后面没有有freq 比它大1的点，则在node后面插入一个 
			tmp.keys.add(key);
			tmp.prev = node;
			tmp.next = node.next;
			node.next.prev = tmp;
			node.next = tmp;
		}
		freqMap.put(key, node.next);  // 更新nodeHash
		
		if (node.keys.size() == 0) {
			removeNode(node);
		}
	}
	
	private void removeNode(Node node) {
		if (node.prev == null) { 
			// node is head
			head = node.next;
		} else {
			node.prev.next = node.next;
		}
		
		if (node.next != null) {
			node.next.prev = node.prev;
		}
	}
	
	private void addToHead(int key) {
		if (head == null) {				// 若head为空，则node设为head；
			head = new Node(0);
			head.keys.add(key);
		} else if (head.count > 0) {    // 若已有head，但head的count > 0， 则node设为新head（因为node的count == 0）。
			Node node = new Node(0);
			node.keys.add(key);
			node.next = head;
			head.prev = node;
			head = node;
		} else {
			head.keys.add(key);         // 否则，在head（count == 0）的keys中加上当前node
		}
		
		freqMap.put(key, head);
	}
	
	private void removeOldNode() {
		if (head == null) {
			return;
		} 
		
		int old = head.keys.iterator().next();  // head的count最低，所以从head中找要删除的点。head.key是LinkedHashSet, 
												// 因此iterator按照插入的先后顺序，iterator.next()返回的是最早插入的，也就是应该被删的LRU元素。
		head.keys.remove(old);
		if (head.keys.size() == 0) {
			removeNode(head);
		}
		freqMap.remove(old);
		keyMap.remove(old);
	}
}
