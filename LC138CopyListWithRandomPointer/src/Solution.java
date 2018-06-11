import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * A linked list is given such that each node contains an additional 
	 * random pointer which could point to any node in the list or null.
	 * 
	 * Return a deep copy of the list.
	 */
	
	
	// Solution 1: HashMap
	// straightforward
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(head, newHead);
        
        RandomListNode node = head;
        
        while (node != null) {
            if (node.random != null) {
                if (!map.containsKey(node.random)) {
                    map.put(node.random, new RandomListNode(node.random.label));
                }
                
                map.get(node).random = map.get(node.random);
            }
            
            if (node.next != null) {
                if (!map.containsKey(node.next)) {
                    map.put(node.next, new RandomListNode(node.next.label));
                }
                map.get(node).next = map.get(node.next);
            }
            node = node.next;
        }
        
        return map.get(head);
    }
    
    // another way
    /*
	public static RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        
        while (head != null) {
            
            RandomListNode newNode;
            
            if (map.containsKey(head)) {
                newNode = map.get(head);
            } else {
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }
            
            cur.next = newNode;
            
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            
            cur = newNode;
            head = head.next;
        }
        
        return dummy.next;
	}
	*/
	// Solution 2: no hashmap
	/*
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        
        RandomListNode cur = head;
        
        // 复制每个点
        while (cur != null) {
            RandomListNode newNode = new RandomListNode(cur.label);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        
        // 复制random指针
        cur = head;
        
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        
        // 分离两个链表
        cur = head;
        RandomListNode newHead = cur.next;
        
        while (cur != null) {
            RandomListNode newNode = cur.next;
            cur.next = newNode.next;
            cur = cur.next;
            if (cur != null) {
                newNode.next = cur.next;
            }
        }
        
        return newHead;
    }
    */
    
    public static void main(String[] args) {
    	RandomListNode node1 = new RandomListNode(1);
    	RandomListNode node2 = new RandomListNode(2);
    	RandomListNode node3 = new RandomListNode(3);
    	RandomListNode node4 = new RandomListNode(4);
    	RandomListNode node5 = new RandomListNode(5);
		

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		RandomListNode head = node1;
		
		RandomListNode newhead = copyRandomList(head);
		
		while (newhead != null) {
			System.out.print(newhead.label + "->");
			newhead = newhead.next;
		}
		
		
	}
}
