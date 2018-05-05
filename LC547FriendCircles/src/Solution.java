import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
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
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) {
        	return 0;
        }

        boolean[] visited = new boolean[M.length];
        
        int counter = 0;
        
        for (int i = 0; i < M.length; i++) {
        	if (!visited[i]) {
        		//bfs(M, i, visited);
        		dfs(M, i, visited);
        		counter++;
        	}
        }
        
        return counter;
    }
    
    private static void bfs(int[][] M, int i, boolean[] visited) {
    	Queue<Integer> queue = new LinkedList<>();
    	
    	queue.offer(i);
    	
    	while (!queue.isEmpty()) {
			int x = queue.poll();
			visited[x] = true;			
			for (int y = 0; y < M.length; y++) {
				if (!visited[y] && M[x][y] == 1) {
					queue.offer(y);
				}
			}
    	}
    }
    
    private static void dfs(int[][] M, int i, boolean[] visited) {
    	visited[i] = true;
    	for (int j = 0; j < M.length; j++) {
    		if (!visited[j] && M[i][j] == 1) {
    			dfs(M, j, visited);
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
