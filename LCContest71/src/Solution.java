import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Solution {
	
	static Integer pre;
	static int min = Integer.MAX_VALUE;
	
    public static int minDiffInBST(TreeNode root) {
        pre = null;
        
        inorder(root);
        
        return min;
    }
    
    private static void inorder(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	inorder(root.left);
    	if (pre == null) {
    		pre = root.val;
    	} else {
    		min = Math.min(min, root.val - pre);
    		pre = root.val;
    	}
    	
    	inorder(root.right);
    }
    
    
    
    public static int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0) {
        	return 0;
        }
        int sum = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: answers) {
        	map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        //System.out.println(map);
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
        	int key = entry.getKey();
        	int value = entry.getValue();
        	
        	sum += (value / (key + 1)) * (key + 1);
        	if (value % (key + 1) != 0) {
        		sum += key + 1;
        	}
        }
        
        return sum;
    }
    
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{tx, ty});
        
        while (!queue.isEmpty()) {
        	int[] point = queue.poll();
        	int x = point[0];
        	int y = point[1];
        	
        	if (x == sx && y == sy) {
        		return true;
        	}      	
        	
        	if (x >= y && y > 0) {
        		queue.offer(new int[]{x - y, y});
        	}

        	if (x <= y && x > 0) {
        		queue.offer(new int[]{x, y - x});
        	}
        }
        
        return false;
    }
    
    public static void main(String[] args) {
    	/*
    	TreeNode node1 = new TreeNode(4);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(6);
    	TreeNode node4 = new TreeNode(1);
    	TreeNode node5 = new TreeNode(3);
    	
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node2.left  = node4;
    	node2.right = node5; 
    	
    	
    	TreeNode node6 = new TreeNode(1);
    	TreeNode node7 = new TreeNode(0);
    	TreeNode node8 = new TreeNode(48);
    	TreeNode node9 = new TreeNode(12);
    	TreeNode node10 = new TreeNode(49);
    	
    	
    	node6.left  = node7;
    	node6.right = node8;
    	node8.left  = node9;
    	node8.right = node10; 
    	
    	System.out.println(minDiffInBST(node6));
		
    	
    	int[] test21 = {1, 1, 2};  // 5
    	int[] test22 = {10, 10, 10}; // 11
    	int[] test23 = {}; // 0
    	int[] test24 = {1, 0, 1, 0, 0}; // 5
    	int[] test25 = {0,0,1,1,1};  // 6
    	
    	System.out.println(numRabbits(test21));
    	System.out.println(numRabbits(test22));
    	System.out.println(numRabbits(test23));
    	System.out.println(numRabbits(test24));
    	System.out.println(numRabbits(test25));
    	*/
    	
    	System.out.println(reachingPoints(1, 1, 3, 5));
    	System.out.println(reachingPoints(1, 1, 2, 2));
    	System.out.println(reachingPoints(1, 1, 1, 1));
    	System.out.println(reachingPoints(35, 13, 455955547, 420098884));
	}
    
}
