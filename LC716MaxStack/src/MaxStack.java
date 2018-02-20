import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class MaxStack {
	
	/*
	 * Design a max stack that supports push, pop, top, peekMax and popMax.
	 * 
	 * push(x) -- Push element x onto stack.
	 * pop() -- Remove the element on top of the stack and return it.
	 * top() -- Get the element on the top.
	 * peekMax() -- Retrieve the maximum element in the stack.
	 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the 
	 *             top-most one.
	 *             
	 * Example 1:
	 * MaxStack stack = new MaxStack();
	 * stack.push(5); 
	 * stack.push(1);
	 * stack.push(5);
	 * stack.top(); -> 5
	 * stack.popMax(); -> 5
	 * stack.top(); -> 1
	 * stack.peekMax(); -> 5
	 * stack.pop(); -> 1
	 * stack.top(); -> 5
	 * 
	 * Note:
	 * 1. -1e7 <= x <= 1e7
	 * 2. Number of operations won't exceed 10000.
	 * 3. The last four operations won't be called when stack is empty.
	 */
	
	// Solution 1: O(n) for popMax(), O(1) for others
	// 用maxStack记录当前遇到的最大值，
	// 用stack记录所有输入的元素
	// e.g. stack -> [2, 1, 5, 3, 9] 则maxStack -> [2, 2, 5, 5, 9]
	// LinkedIn面试中，这种方法会被fail 要求所有操作O(logn)以内 --> Solution 2
	

    /** initialize your data structure here. */
	/*
	Stack<Integer> stack;
	Stack<Integer> maxStack;
	
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
    	int max = x;
        if (!maxStack.isEmpty()) {
        	max = Math.max(maxStack.peek(), x);
        }
        
        stack.push(x);
        maxStack.push(max);
        //System.out.println(stack);
    }
    
    public int pop() {
       maxStack.pop();
       return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max = maxStack.pop();
        Stack<Integer> buffer = new Stack<>();
        while (stack.peek() != max) {
        	// buffer.push(stack.pop()); --- 错误，必须把maxStack也pop出来，再重新push，
        	//                               否则，例如(5, 1, -5), popMax()第一次pop出5之后，maxStack离剩下的是(5, 5) stack中剩下的是(1, -5) 错误
        	buffer.push(pop());
        }

        stack.pop();
        while (!buffer.isEmpty()) {
        	push(buffer.pop());
        }

        return max;
    }

    */
	
	// Solution 2: TreeMap + Doubly Linked List
	// top: O(1), 其余: O(log n) (TreeMap的get, containsKey, next 都是O(log n))
	class ListNode {
		ListNode prev;
		ListNode next;
		int val;
		
		ListNode(int val) {
			this.prev = null;
			this.next = null;
			this.val = val;
		}
	}
	
	ListNode head;
	ListNode tail;
	TreeMap<Integer, List<ListNode>> map;
	
    public MaxStack() {
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
        map = new TreeMap<>();
    }
    
    public void push(int x) {
    	// 生成一个新的node，加入到doubly linked list的尾部
    	ListNode node = new ListNode(x);
    	node.prev = tail.prev;
    	node.next = tail;
    	tail.prev.next = node;
    	tail.prev = node;
    	
    	// 将node加入到TreeMap中
    	if (!map.containsKey(x)) {
    		map.put(x, new ArrayList<ListNode>());
    	} 
    	map.get(x).add(node);
    }
    
    public int pop() {
    	// doubly linked list尾部的节点总是最新的，每次pop出这个节点
        ListNode node = tail.prev;
        tail.prev = node.prev;
        node.prev.next = tail;
        //Java GC, 不需要下面两行
        //node.prev = null;
        //node.next = null;
        int val = node.val;
        
        // 通过这个节点的val，找到其在TreeMap里的位置，删掉
        List<ListNode> list = map.get(val);
        list.remove(list.size() - 1);
        
        if (list.size() == 0) {
        	map.remove(val);
        }
        
        return val;
    }
    
    public int top() {
        return tail.prev.val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
    	// 通过TreeMap找到最大值的key，删掉其对应的一个节点
        int val = map.lastKey();
        List<ListNode> list = map.get(val);
        ListNode node = list.remove(list.size() - 1);
        if (list.size() == 0) {
        	map.remove(val);
        }
        
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //Java GC, 不需要下面两行
        //node.prev = null;
        //node.next = null;
        return val;
    }
}
