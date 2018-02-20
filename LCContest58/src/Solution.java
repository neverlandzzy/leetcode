import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class Solution {
	
	static class Atoms {
		String name;
		int count;
		
		Atoms() {
			this.name = null;
			this.count = 0;
		}
	}
	
    public static String minWindow(String S, String T) {
    	return null;
    }
    
    public static String countOfAtoms(String formula) {
        // Input: 
    	// formula = "K4(ON(SO3)12)2"
    	//		Output: "K4N2O14S4"
    	
    	//"Mg(OH)2";
    	
    	Map<String, Integer> map = new HashMap<>();
    	int open = 0;
    	int i = 0;
    	int n = formula.length();
    	Stack<Atoms> stack = new Stack<>();
    	
    	while (i < n) {
    		StringBuilder sb = new StringBuilder();
    		int counter = 0;
    		boolean flag = false;
    		
    		while (i < n && Character.isAlphabetic(formula.charAt(i))) {

    			sb.append(formula.charAt(i));
    			if (Character.isUpperCase(formula.charAt(i))) {
    				if (i + 1 < n && Character.isLowerCase(formula.charAt(i + 1))) {
    					i++;
    					continue;
    				}
    			}
    			map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
    			Atoms atoms = new Atoms();
    			atoms.name = sb.toString();
    			stack.push(atoms);
    			sb.setLength(0);
    			i++;
    		}
    		
    		while (i < n && Character.isDigit(formula.charAt(i))) {
    			counter = counter * 10 + (formula.charAt(i) - '0');
    			flag = true;
    			i++;
    		}
    		
    		if (flag) {
    			
    		}
    		i++;
    	}
    	
    	return "";
    	
    }
    
    public static ListNode[] splitListToParts(ListNode root, int k) {
    	ListNode[] result = new ListNode[k];
    	
    	if (root == null) {
    		return result;
    	}
    	
    	ListNode head = root;
        int length = 0; 
        
        while (head != null) {
        	length++;
        	head = head.next;
        }
        
        int avg = length / k;
        
        int rest = length % k;
        
        head = root;
        
        if (avg > 0) {
	        for (int i = 0; i < k; i++) {
	        	int counter = avg;
		        ListNode cur = head;
		        result[i] = cur;
		        	
	        	while (counter > 1 && cur != null) {
	        		cur = cur.next;
	        		counter--;
	        	}
	        	if (rest > 0 && cur != null) {
	        		cur = cur.next;
	        		rest--;
	        	}
	        	
	        	if (cur != null) {
	        		head = cur.next;
	            	cur.next = null;
	        	}
	        }
	     } else {
	    	
	    	int i = 0;
	    	
	    	while (head != null) {
	    		result[i] = head;
	    		ListNode cur = head;
	    		head = cur.next;
	    		cur.next = null;
	    		i++;
	    	}
	     }
        return result;
    }
    
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length <= 2) {
        	return -1;
        }
        
        int sum = 0;
        int preSum = 0;
        
        for (int i: nums) {
        	sum += i;
        }
        
        
        for (int i = 0; i < nums.length; i++) {
        	if (preSum + nums[i] + preSum == sum) {
        		return i;
        	}
        	preSum += nums[i];
        }
        
        return -1;
    }
    
    private static  void printList(ListNode root) {
    	while (root != null) {
    		System.out.print(root.val + " -> ");
    		root = root.next;
    	}
    	
    	System.out.println();
    }
    
	public static void main(String[] args) {
		int[] test11 = {1, 7, 3, 6, 5, 6};
		int[] test12 = {1, 2, 3};
		int[] test13 = {-1,-1,-1,0,1,1};
		
		//System.out.println(pivotIndex(test11));
		//System.out.println(pivotIndex(test12));
		//System.out.println(pivotIndex(test13));
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		ListNode node9 = new ListNode(9);
		ListNode node10 = new ListNode(10);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		node8.next = node9;
		node9.next = node10;
		/*
		printList(node1);
		ListNode[] result = splitListToParts(node1, 12);
		
		for (ListNode n: result) {
			if (n != null) {
				printList(n);
			} else {
				System.out.println("null");
			}
		}
		*/
		
		
		String test31 = "H2O";
		String test32 = "Mg(OH)2";
		String test33 = "K4(ON(SO3)2)2";
		//System.out.println(countOfAtoms(test31));
		System.out.println(countOfAtoms(test32));
		//System.out.println(countOfAtoms(test33));
		
		/*
		String test411 = "abcdebdde";
		String test412 = "bde";
		
		System.out.println(minWindow(test411, test412));
		*/
	}
}
