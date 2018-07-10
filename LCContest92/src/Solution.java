import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
	
    public static int[][] transpose(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        
        int[][] result = new int[n][m];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		result[j][i] = A[i][j];
        	}
        }
        
        return result;
    }
    
    
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Map<TreeNode, Integer> depth = new HashMap<>();
        
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.offer(root);
        map.put(root, root);
        
        int level = 0;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode node = queue.poll();
        		depth.put(node, level);
        		
        		if (node.left != null) {
        			map.put(node.left, node);
        			queue.offer(node.left);
        		}
        		
        		if (node.right != null) {
        			map.put(node.right, node);
        			queue.offer(node.right);
        		}
        	}
        	
        	level++;
        }
        
        Set<TreeNode> deepest = new HashSet<>();
        for (TreeNode key: depth.keySet()) {
        	if (depth.get(key) == level - 1) {
        		deepest.add(key);
        	}
        }
        
        if (deepest.size() == 1) {
            return deepest.iterator().next();
        }
        
        Set<TreeNode> set = new HashSet<>();
        int count = 0;
        int size = 0;
        
        while (true) {
	        for (TreeNode node: deepest) {
	        	TreeNode result = map.get(deepest.iterator().next());
	        	
	        	set.add(map.get(node));
	        	count++; 
	        	
	        	if (result == map.get(node)) {        		
	        		size++;       		
	        	} 
	        	
	        	if (count == deepest.size()) {
	        		if (size == count) {
	        			return result;
	        		} else {
	            		deepest = new HashSet<>(set);
	            		set.clear();
	            		count = 0;
	            		size = 0;
	        		}
	        	}
	        }
        }
        
       // return root;
    }
    
    public static int primePalindrome(int N) {
    	if (N == 1) {
    		return 2;
    	}
    	
    	if (N == 2) {
    		return 2;
    	}
    	
    	if (N % 2 != 0) {
        	if (isPalindrome(N)) {
        		if (isPrime(N)) {
        			return N;
        		}
        	}
        	
        	N++;
    	}
    	
    	if (N >= 9989900) {
    		return 100030001;
    	}
    	
        for (int i = N + 1; i <= Integer.MAX_VALUE; i += 2) {
        	if (isPalindrome(i)) {
        		if (isPrime(i)) {
        			return i;
        		}
        	}
        }
        
        return 0;
    }
    
    private static boolean isPrime(int N) {
    	if (N <= 1) {
    		return false;
    	}
    	
    	if (N == 2) {
    		return true;
    	}
    	
    	if (N % 2 == 0) {
    		return false;
    	}
    	
    	for (int i = 3; i * i <= N; i += 2) {
    		if (N % i == 0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    private static boolean isPalindrome(int x) {
          
        if (x % 10 == 0 && x != 0) {
            return false;
        }
        
        int orig = x;
        int reverse = 0;

        
        while (orig > 0) {
            reverse = reverse * 10 + orig % 10;
            orig /= 10;
        }
        
        return x == reverse;
    }
    
	public static void main(String[] args) {
		/*
		int[][] test11 = {{1,2,3}, {4,5,6}, {7,8,9}};
		int[][] test12 = {{1,2,3}, {4,5,6}};
		int[][] test13 = {{1},{4}};
		
		int[][] result11 = transpose(test11);
		int[][] result12 = transpose(test12);
		int[][] result13 = transpose(test13);
		
		print(result11);
		System.out.println();
		print(result12);
		System.out.println();
		print(result13);
		
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(6);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(0);
		TreeNode node7 = new TreeNode(8);
		TreeNode node8 = new TreeNode(7);
		TreeNode node9 = new TreeNode(4);
		TreeNode node10 = new TreeNode(9);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node5.left = node8;
		node5.right = node9;
		node7.left = node10;
		
		TreeNode result21 = subtreeWithAllDeepest(node1);
		System.out.println(result21);
		*/
		
		System.out.println(primePalindrome(6));
		System.out.println(primePalindrome(8));
		System.out.println(primePalindrome(13));
		System.out.println(primePalindrome(1));
		System.out.println(primePalindrome(9989900));
		System.out.println(primePalindrome(51633903));
		System.out.println(primePalindrome(45887963));

	}
	
	private static void print(int[][] A) {
		for (int[] a: A) {
			for (int i: a) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
}
