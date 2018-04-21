import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
    public static String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.split(" ");
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        int counter = 0;
        
        for (String w: words) {
        	w = w.toLowerCase();
        	w = w.replace("?", "").replace("!", "").replace("'", "").replace(";", "").replace(".", "").replace(",", "");
        	if (!bannedSet.contains(w)) {
        		map.put(w, map.getOrDefault(w, 0) + 1);
        	}
        }
        
        for (String w: map.keySet()) {
        	counter = Math.max(counter, map.get(w));
        }
        
        for (String w: map.keySet()) {
        	if (map.get(w) == counter) {
        		return w;
        	}
        }
        
        return "";
    }
    
    public static int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int g: G) {
        	set.add(g);
        }
        
        int counter = 1;
        boolean flag = false;
        
        ListNode node = head;
        
        while (node != null && !set.isEmpty()) {
        	if (!set.contains(node.val)) {
        		if (flag) {
        			counter++;
        			flag = false;
        		}
        	} else {
        		set.remove(node.val);
        		if (set.isEmpty()) {
        			break;
        		}
        		flag = true;
        	}
        	node = node.next;
        }
        
        return counter;

    }
    
    public static List<String> ambiguousCoordinates(String S) {
        List<String> result = new ArrayList<>();
        //String input = S.substring(1, S.length() - 1);
        
        for (int i = 1; i < S.length() - 2; i++) {
        	
    		String left = S.substring(1, i + 1);
    		String right = S.substring(i + 1, S.length() - 1);
    		boolean leftFlag = false;
    		boolean rightFlag = false;
    		
    		if (left.startsWith("0") && (left.length() > 1 && left.charAt(1) != '.')) {
    			leftFlag = true;;
    		}
    		
    		if (right.startsWith("0") && (right.length() > 1 && right.charAt(1) != '.')) {
    			rightFlag = true;
    		}
    		

    		if (!(leftFlag || rightFlag)) {
    			result.add("(" + left + ", " + right + ")");
    		}
    		
    		List<String> leftList = new ArrayList<>();
    		List<String> rightList = new ArrayList<>();
    		
    		if (left .length() > 1) {   			
    			for (int j = 1; j < left.length(); j++) {
    				if (left.substring(0, j).length() > 1 && (left.substring(0, j).startsWith("0"))) {
    					continue;
    				}
    				
    				if (left.substring(j).length() > 1 && left.substring(j).charAt(left.substring(j).length() - 1) == '0') {
    					continue;
    				}
    				
    				if (Integer.valueOf(left.substring(j)) != 0) {
    					leftList.add(left.substring(0, j) + "." + left.substring(j));
    				}
    			}
    		}
    		
    		if (right.length() > 1) {
    			for (int j = 1; j < right.length(); j++) {
    				if (right.substring(0, j).length() > 1 && (right.substring(0, j).startsWith("0"))) {
    					continue;
    				}
    				
    				if (right.substring(j).length() > 1 && right.substring(j).charAt(right.substring(j).length() - 1) == '0') {
    					continue;
    				}
    				
    				if (Integer.valueOf(right.substring(j)) != 0) {
    					rightList.add(right.substring(0, j) + "." + right.substring(j));
    				}
    			}
    		}
    		
    		
    		for (String l: leftList) {
    			for (String r: rightList) {
    				result.add("(" + l + ", " + r + ")");
    			}   			
    		}
    		
    		if (!rightFlag) {
	    		for (String l: leftList) {
	    			result.add("(" + l + ", " + right + ")");
	    		}
    		}
    		
    		if (!leftFlag) {
	    		for (String r: rightList) {
	    			result.add("(" + left + ", " + r + ")");
	    		}
    		}
    		
    		
        }
        
        return result;
    }

    
    public static void main(String[] args) {
    	/*
		String test11 = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned11 = {"hit"};
		
		System.out.println(mostCommonWord(test11, banned11));

    	
    	ListNode node0 = new ListNode(0);
    	ListNode node1 = new ListNode(1);
    	ListNode node2 = new ListNode(2);
    	ListNode node3 = new ListNode(3);
    	node0.next = node1;
    	node1.next = node2;
    	node2.next = node3;
    	int[] test21 = {0, 1, 3};
    	System.out.println(numComponents(node0, test21));
    	
    	ListNode node10 = new ListNode(0);
    	ListNode node11 = new ListNode(1);
    	ListNode node12 = new ListNode(2);
    	ListNode node13 = new ListNode(3);
    	ListNode node14 = new ListNode(4);

    	node13.next = node14;
    	node14.next = node10;
    	node10.next = node12;
    	node12.next = node11;
    	int[] test22 = {4};
    	
    	System.out.println(numComponents(node13, test22));
    	*/
    	
    	String test31 = "(123)";
    	String test32 = "(00011)";
    	String test33 = "(0123)";
    	String test34 = "(100)";
    	String test35 = "(0010)";

    	System.out.println(ambiguousCoordinates(test31));
    	System.out.println(ambiguousCoordinates(test32));
    	System.out.println(ambiguousCoordinates(test33));
    	System.out.println(ambiguousCoordinates(test34));
    	System.out.println(ambiguousCoordinates(test35));
	}
}
