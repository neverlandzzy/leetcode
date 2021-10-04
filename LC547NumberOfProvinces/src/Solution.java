import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/**
	 * There are n cities. Some of them are connected, while some are not. If city a is connected directly
	 * with city b, and city b is connected directly with city c, then city a is connected indirectly with
	 * city c.
	 *
	 * A province is a group of directly or indirectly connected cities and no other cities outside of the
	 * group.
	 *
	 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth
	 * city are directly connected, and isConnected[i][j] = 0 otherwise.
	 *
	 * Return the total number of provinces.
	 *
	 * Example 1:
	 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
	 * Output: 2
	 *
	 * Example 2:
	 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
	 * Output: 3
	 *
	 * 1 <= n <= 200
	 * n == isConnected.length
	 * n == isConnected[i].length
	 * isConnected[i][j] is 1 or 0.
	 * isConnected[i][i] == 1
	 * isConnected[i][j] == isConnected[j][i]
	 *
	 * ===============================================
	 * 原来的题目名称和描述，问题和解法完全一样
	 * [Friend Circles]
	 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. 
	 * For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend 
	 * circle is a group of students who are direct or indirect friends.
	 * 
	 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are 
	 * direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
	 * 
	 * Example 1:
	 * Input: 
	 * [[1,1,0],
	 *  [1,1,0],
	 *  [0,0,1]]
	 * Output: 2
	 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
	 * The 2nd student himself is in a friend circle. So return 2.
	 * 
	 * Example 2:
	 * Input: 
	 * [[1,1,0],
	 *  [1,1,1],
	 *  [0,1,1]]
	 * Output: 1
	 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
	 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
	 * 
	 * Note:
	 * 1. N is in range [1,200].
	 * 2. M[i][i] = 1 for all students.
	 * 3. If M[i][j] = 1, then M[j][i] = 1.
	 */
	
	// Time: O(n ^ 2), Space: O(n)
	// BFS and DFS
    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0 || isConnected[0] == null
				|| isConnected[0].length == 0) {
        	return 0;
        }

        boolean[] visited = new boolean[isConnected.length];
        
        int counter = 0;
        
        for (int i = 0; i < isConnected.length; i++) {
        	if (!visited[i]) {
        		//bfs(M, i, visited);
        		dfs(isConnected, i, visited);
        		counter++;
        	}
        }
        
        return counter;
    }
    
    private static void bfs(int[][] isConnected, int i, boolean[] visited) {
    	Queue<Integer> queue = new LinkedList<>();
    	
    	queue.offer(i);
    	
    	while (!queue.isEmpty()) {
			int x = queue.poll();

			for (int y = 0; y < isConnected.length; y++) {
				if (!visited[y] && isConnected[x][y] == 1) {
					queue.offer(y);
					visited[y] = true;
				}
			}
    	}
    }
    
    private static void dfs(int[][] isConnected, int i, boolean[] visited) {
    	visited[i] = true;
    	for (int j = 0; j < isConnected.length; j++) {
    		if (!visited[j] && isConnected[i][j] == 1) {
    			dfs(isConnected, j, visited);
    		}
    	}
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		int[][] test2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
		
		System.out.println(findCircleNum(test1));
		System.out.println(findCircleNum(test2));
	}
}
