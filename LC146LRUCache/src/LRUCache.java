import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	/*
	 * Design and implement a data structure for Least Recently Used (LRU) cache. 
	 * It should support the following operations: get and set.
	 * 
	 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
	 * otherwise return -1.
	 * 
	 * set(key, value) - Set or insert the value if the key is not already present. 
	 * When the cache reached its capacity, it should invalidate the least recently used item 
	 * before inserting a new item.
	 */
	
	private class Node {
		int key;
		int val;
		Node prev;
		Node next;
		
		private Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
	
	private int capacity;
	private Map<Integer, Node> map;
    Node head;
    Node tail;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
        	return -1;
        } else {
        	// remove key node
        	Node current = map.get(key);
        	current.prev.next = current.next;
        	current.next.prev = current.prev;
        	// move this node to tail
        	moveToTail(current);
        	
        	return map.get(key).val;
        }
    }
    
    public void set(int key, int value) {
        if (get(key) != -1) {
        	map.get(key).val = value;
        	return;
        }	
        
        if (map.size() == capacity) {
    		map.remove(head.next.key);
    		head.next = head.next.next;
    		head.next.prev = head;
        }
        
    	Node newNode = new Node(key, value);
    	map.put(key, newNode);
    	moveToTail(newNode);       	
         
    }
    
    private void moveToTail(Node node) {
    	node.prev = tail.prev;
    	tail.prev.next = node;
    	tail.prev = node;
    	node.next = tail;
    	
    }
}
