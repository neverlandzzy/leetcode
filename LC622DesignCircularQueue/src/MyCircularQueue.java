
public class MyCircularQueue {
	/*
	 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed 
	 * based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. 
	 * It is also called "Ring Buffer".
	 * 
	 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once 
	 * the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, 
	 * we can use the space to store new values.
	 * 
	 * Your implementation should support following operations:
	 * 
	 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
	 * Front: Get the front item from the queue. If the queue is empty, return -1.
	 * Rear: Get the last item from the queue. If the queue is empty, return -1.
	 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
	 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
	 * isEmpty(): Checks whether the circular queue is empty or not.
	 * isFull(): Checks whether the circular queue is full or not. 
	 * 
	 * Example:
	 * 
	 * MyCircularQueue circularQueue = new MycircularQueue(3); // set the size to be 3
	 * circularQueue.enQueue(1);  // return true
	 * circularQueue.enQueue(2);  // return true
	 * circularQueue.enQueue(3);  // return true
	 * circularQueue.enQueue(4);  // return false, the queue is full
	 * circularQueue.Rear();  // return 3
	 * circularQueue.isFull();  // return true
	 * circularQueue.deQueue();  // return true
	 * circularQueue.enQueue(4);  // return true
	 * circularQueue.Rear();  // return 4
	 * 
	 * Note:
	 * All values will be in the range of [0, 1000].
	 * The number of operations will be in the range of [1, 1000].
	 * Please do not use the built-in Queue library.
	 */
	
	class Node {
		int val;
		Node prev;
		Node next;
		
		public Node(int v) {
			this.val = v;
			this.prev = null;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	private int capacity;
	
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head; 
        this.capacity = k;
        this.size = 0;
    }
    
    // 新的点加在tail前
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
        	return false;
        }
        
        this.size++;
        
        Node node = new Node(value);
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
        
        return true;
    }
    
    // 从头删
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
        	return false;
        }
        
        size--;
        head.next.next.prev = head;
        head.next = head.next.next;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
        	return -1;
        }
        
        return head.next.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
        	return -1;
        }
        
        return tail.prev.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return this.capacity == this.size;
    }
}
