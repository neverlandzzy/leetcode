import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
	 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
	 * 
	 * Each 0 marks an empty land which you can pass by freely.
	 * Each 1 marks a building which you cannot pass through.
	 * Each 2 marks an obstacle which you cannot pass through.
	 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
	 * 
	 * 1 - 0 - 2 - 0 - 1
	 * |   |   |   |   |
	 * 0 - 0 - 0 - 0 - 0
	 * |   |   |   |   |
	 * 0 - 0 - 1 - 0 - 0
	 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
	 * 
	 * Note:
	 * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
	 */
	
	// http://blog.csdn.net/magicbean2/article/details/76188432
	// https://discuss.leetcode.com/topic/32391/share-a-java-implement
	// 思路：对于每一个building (grid[i][j] == 1)，用BFS计算其到每一个空白处的距离， 记录在dist[][]中， 每次计算dist时，更新result。 当所有点（本例中3个）都计算完毕后，
	//      result即为最后结果，也就是dist[][]数组中的最小值。对于grid上的每个点，我们要记录其坐标值，以及在本轮BFS中，其到达building的距离distance，因此，需要构建一个新
	//      的类，来记录这些信息。 --> 也可以利用坐标转换，将2D转为1D，避免构建新的类，而是将坐标(1d)和距离作为数组，存入queue, 类似于第一个link中的做法
	//      优化：每次BFS只遍历之前building全部能够到达的点：第一次只遍历grid[i][j] == 0的点，之后将其置为-1, 第二次只遍历grid[i][j] == -1的点，之后将其置为-2 ... 
	//      以此类推。用flag来记录每次应该遍历的点(0 -> -1 -> -2 -> ...)     
	
	
	// 思路一样，只是避免了创建新的class
	public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        
        int result = 0;
        int flag = 0;
        int[][] count = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }
                
                // 因为要求所有的building都能到达。如果有不满足，则result 为Integer.MAX_VALUE;
                result = Integer.MAX_VALUE;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, j});
                int level = 0;
                
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    level++;
                    
                    for (int k = 0; k < size; k++) {
                        int[] point = queue.poll();
                        for (int[] dir: direction) {
                            int nextI = point[0] + dir[0];
                            int nextJ = point[1] + dir[1];
                            
                            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || grid[nextI][nextJ] != flag) {
                                continue;
                            } 
                            
                            count[nextI][nextJ] += level;
                            grid[nextI][nextJ]--;
                            result = Math.min(result, count[nextI][nextJ]);
                            queue.offer(new int[]{nextI, nextJ});
                        }
                    }
                }
                
                flag--;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
	}
	
	
	/*
	static class Point {
		private int x;
		private int y;
		private int distance;
		
		Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.distance = dist;
		}
	}
	
    public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        
        int result = 0;
        int flag = 0;
        
        int[][] count = new int[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] != 1) {
        			continue;
        		}
        		
        		result = Integer.MAX_VALUE;
        		Queue<Point> queue = new LinkedList<>();
        		queue.offer(new Point(i, j, 0));
        		
        		while (!queue.isEmpty()) {
        			Point point = queue.poll();
        			
        			for (int[] dir: direction) {
        				int x = point.x + dir[0];
        				int y = point.y + dir[1];
        				
        				if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != flag) {
        					continue;
        				}
        				count[x][y] += point.distance + 1;
        				grid[x][y]--;
        				result = Math.min(result, count[x][y]);
        				queue.offer(new Point(x, y, point.distance + 1));
        			}
        		}
        	
        		flag--;
        	}
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    */
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
		int[][] test2 = {{0, 2, 1}, {0, 2, 0}, {0, 2, 2}, {1, 0, 0}};
		//System.out.println(shortestDistance(test1));
		System.out.println(shortestDistance(test2));
	}
}
