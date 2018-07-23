import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	
	
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        
        helper(root1, list1);
        helper(root2, list2);
        
        if (list1.size() != list2.size()) {
        	return false;
        }
        
        for (int i = 0; i < list1.size(); i++) {
        	if (list1.get(i) != list2.get(i)) {
        		return false;
        	}
        }
        
        return true;
    }
    
    private static void helper(TreeNode root, List<Integer> list) {
    	if (root == null) {
    		return;
    	}
    	
    	helper(root.left, list);
    	if (root.left == null && root.right == null) {
    		list.add(root.val);
    	}
    	
    	helper(root.right, list);
    }
    
    
    public static int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for (int[] obstacle: obstacles) {
        	set.add(obstacle[0] + "," + obstacle[1]);
        }
        
        char[] directions = {'u', 'r', 'd', 'l'};
        int index = 0;
        int x = 0;
        int y = 0;
        
        int result = 0;
        
        for (int c: commands) {
        	if (c < 0) {
        		if (c == -1) {
        			index++;
        			if (index == 4) {
        				index = 0;
        			}
        		} else {
        			index--;
        			if (index < 0) {
        				index = 3;
        			}
        		}
        	} else {
        		char dir = directions[index];
        		int nextX = x;
        		int nextY = y;
        		
        		for (int k = 0; k < c; k++) {
        			switch (dir) {
        				case 'u':
        					nextY++;
        					break;
        				case 'r':
        					nextX++;
        					break;
        				case 'd':
        					nextY--;
        					break;
        				case 'l':
        					nextX--;
        					break;
        			}
        			
        			if (!set.contains(nextX + "," + nextY)) {
        				x = nextX;
        				y = nextY;
        			} else {
        				break;
        			}
        		}
        	}
            
            result = Math.max(result, x * x  + y * y);
        }

        return result; 
    }
    
    public static int minEatingSpeed(int[] piles, int H) {
    	Arrays.sort(piles);
    	int n = piles.length;
    	int start = piles[0];
    	int end = piles[n - 1];
    	
    	while (start < end) {
    		int mid = start + (end - start) / 2;
    		
    		if (valid(piles, H, mid)) {
    			end = mid;
    		} else {
    			start = mid + 1;
    		}
    	}

    	return start;
    }
    
    private static boolean valid(int[] piles, int H, int speed) {
    	int total = 0;
    	for (int p: piles) {
    		total += p / speed;
    		
    		if (p % speed != 0) {
    			total +=1;
    		}
    		
    		if (total > H) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
	public static void main(String[] args) {
		/*
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(7);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(4);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		
		TreeNode node6 = new TreeNode(8);
		TreeNode node7 = new TreeNode(1);
		TreeNode node8 = new TreeNode(3);
		TreeNode node9 = new TreeNode(4);
		TreeNode node10 = new TreeNode(7);
		
		node6.left = node7;
		node6.right = node8;
		node8.left = node9;
		node8.right = node10;
		
		System.out.println(leafSimilar(node1, node6));

		
		int[] command1 = {4, -1, 3};
		int[][] obstacles1 = {{0, 2}, {-2, 1}};
		
		System.out.println(robotSim(command1, obstacles1));
		
		
		int[] command2 = {4,-1,4,-2,4};
		int[][] obstacles2 = {{2, 4}};
		System.out.println(robotSim(command2, obstacles2));
		
		int[] command3 = {-2,-2, 4, -1, 6};
		int[][] obstacles3 = {{0, -3}};
		System.out.println(robotSim(command3, obstacles3));
		*/
		
		int[] p1 = {3, 6, 7, 11};
		int[] p2 = {30,11,23,4,20};
		int[] p3 = {30,11,23,4,20};
		int[] p4 = {332484035, 524908576, 855865114, 632922376, 222257295, 690155293, 112677673, 679580077, 337406589, 290818316, 877337160, 901728858, 679284947, 688210097, 692137887, 718203285, 629455728, 941802184};
		
		System.out.println(minEatingSpeed(p1, 8));
		System.out.println(minEatingSpeed(p2, 5));
		System.out.println(minEatingSpeed(p3, 6));
		System.out.println(minEatingSpeed(p4, 823855818));
	}

			
}
