import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class FigureOutFriendConnection {
	/*
	 * Assume there's such a social network where:
	 * 
	 * A person could know any number of people in the network;
	 * If person A knows person B, and person B knows person C, we consider person A knows 
	 * person C at the distince of 1.
	 * 
	 * Friendship is directional. E.g. It's OK if A knows B but B doesn't know A;
	 * You are given a list of friendship pair, and person A and person B as input, 
	 * return what's the shorted distince from person A to person B?
	 * 
	 * If person A directly knows person B, the distance is 0;
	 * 
	 * If person A indirectly knows person B, return the shorted distance from A to B;
	 * If person A doesn't know B directly/indirectly, return -1;
	 */
	
	
	// read input from stdin, DO NOT change this function
    public static String[]  readInput() {
     Scanner scanner = new Scanner(System.in);
     String[] input = new String[2];
     input[0] = scanner.nextLine();
     input[1] = scanner.nextLine();
     
     scanner.close();
     return input;
   }
    
    public static int friendConnection(String relations, String fromTo) {
    	
    	HashMap<Integer, List<Integer>> relationMap = new HashMap<Integer, List<Integer>>();
    	HashSet<Integer> sorted = new HashSet<Integer>();
    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	String[] pairs = relations.split(", ");
    	int from = Integer.parseInt(fromTo.split(" ")[0]);
    	int to = Integer.parseInt(fromTo.split(" ")[1]);
    	int distance = -1;
    	
    	for (String s: pairs) {

    		int key = Integer.parseInt(s.split(" ")[0]);
    		int value = Integer.parseInt(s.split(" ")[1]);

    		if (!relationMap.containsKey(key)) {
    			List <Integer> friends = new ArrayList<Integer>();
    			friends.add(value);
    			relationMap.put(key, friends);
    		} else {
    			relationMap.get(key).add(value);
    		}
    	}
    	
    	queue.offer(from);
    	sorted.add(from);
    	System.out.println(queue);
    	while (!queue.isEmpty()) {
    		int node = queue.poll();
    		if (node == to) {
    			return distance;
    		} else {
    			if (relationMap.containsKey(node) )
    			for(int n: relationMap.get(node)) {
    				if (!sorted.contains(n)) {
    					queue.offer(n);
    					sorted.add(n);
    				}
    			} 
    			distance++;
    		}
    	}
    	return distance;
    }
    
	public static void main(String[] args) {
	   //String[] input = readInput();
	   //String[] input = {"1 2, 2 3, 3 6, 7 8", "1 6"};
	   String[] input = {"1 2, 2 3, 2 4, 7 8", "1 4"};
	   System.out.println(friendConnection(input[0], input[1]));
	}
}
