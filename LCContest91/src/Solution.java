import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {

    public static boolean lemonadeChange(int[] bills) {
    	int five = 0;
    	int ten = 0;
    	
    	int sum = 0;
    	
    	for (int i = 0; i < bills.length; i++) {
    		int change = bills[i] - 5;
    		
    		if (change > sum) {
    			return false;
    		}
    		
    		while (change > 0) {
    			while (change >= 10 && ten > 0) {
    				change -= 10;
    				ten--;
    			}
    			
    			while (change >= 5 && five > 0) {
    				change -= 5;
    				five--;
    			}
    			
    			
        		if (change > 0) {
        			return false;
        		}
    		}
		
    		if (bills[i] == 5) {
    			five++;
    		}
    		
    		if (bills[i] == 10) {
    			ten++;
    		}
    		
    		sum += 5;
    	}
    	
    	return true;
    }
    
    
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
        	return result;
        }
        
        if (K == 0) {
        	result.add(target.val);
        	return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	TreeNode node = queue.poll();
        	
        	if (!map.containsKey(node)) {
        		map.put(node, new ArrayList<>());
        	}
        	
        	if (node.left != null) {
        		if (!map.containsKey(node.left)) {
        			map.put(node.left, new ArrayList<>());
        		}
        		
        		map.get(node).add(node.left);
        		map.get(node.left).add(node);
        		queue.offer(node.left);
        	}
        	
        	if (node.right != null) {
        		if (!map.containsKey(node.right)) {
        			map.put(node.right, new ArrayList<>());
        		}
        		
        		map.get(node).add(node.right);
        		map.get(node.right).add(node);
        		queue.offer(node.right);
        	}
        }

        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int level = 1;
        boolean flag = false;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	
        	if (level == K) {
        		flag = true;
        	}
        	
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		for (TreeNode next: map.get(node)) {
        			if (!visited.contains(next)) {
        				if (!flag) {
	        				queue.offer(next);
	        				visited.add(next);
        				} else {
        					result.add(next.val);
        				}
        			}
        		}
        	}
        	
        	if (flag) {
        		break;
        	}
        	level++;
        }
        
        return result;
    }
        
    public static int matrixScore(int[][] A) {
        int m = A.length; 
        int n = A[0].length;
        
        //print(A);
        for (int i = 0; i < m; i++) {
            if (A[i][0] == 0) {
                toggleRow(A, i);
            }
        }

        //print(A);
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                count += A[j][i];
            }
            
            if (count <= m / 2) {
                toggleCol(A, i);
            }           
        }
        
        //print(A);
        
        return calBinary(A);
    }
    
    
    private static int calBinary(int[][] A) {
    	int result = 0;
    	for (int i = 0; i < A.length; i++) {
    		int base = 0;
    		for (int j = A[0].length - 1; j >= 0; j--) {
    			result += (A[i][j] << base);
    			base++;
    		} 
    	}
    	
    	return result;
    }

    
    private static void toggleRow(int[][] A, int r) {
    	for (int i = 0; i < A[r].length; i++) {
    		A[r][i] ^= 1; 
    	}
    }
    
    private static void toggleCol(int[][] A, int c) {
    	for (int i = 0; i < A.length; i++) {
    		A[i][c] ^= 1; 
    	}
    }
    
    
    
	public static void main(String[] args) {
		/*
		int[] test11 = {5,5,5,10,20};
		int[] test12 = {5,5,10};
		int[] test13 = {10,10};
		int[] test14 = {5,5,10,10,20};
		int[] test15 = {5,5,5,10,5,5,10,20,20,20};
		
		
		System.out.println(lemonadeChange(test11));
		
		System.out.println(lemonadeChange(test12));
		System.out.println(lemonadeChange(test13));
		System.out.println(lemonadeChange(test14));
		System.out.println(lemonadeChange(test15));

		
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(6);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(0);
		TreeNode node7 = new TreeNode(8);
		TreeNode node8 = new TreeNode(7);
		TreeNode node9 = new TreeNode(4);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node5.left = node8;
		node5.right = node9;	
		
		System.out.println(distanceK(node1, node2, 0));
		*/
			
		int[][] test31 = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
		int[][] test32 = {{0},{1}};
		System.out.println(matrixScore(test31));
		//System.out.println(matrixScore(test32));
	}
	
	private static void print(int[][] A) {
		for (int[] a: A) {
			for (int k: a) {
				System.out.print(k + ", ");
				
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
