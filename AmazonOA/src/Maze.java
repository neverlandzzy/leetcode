import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Maze {
	
	/*
	 * 但是求离到原点的最近距离。 这个类似于maze traversal. 给出List<List<Integer>> 的input来代表整个地图, 每个cell的值可能是 0， 1， 9。 
	 * 0表示障碍， 1表示路可走，9表示目的地。 BFS 可以搞定， 但要记得在加adjacent cell 的时候要做boundary check 避免index of overflow.
	 * 
	 * 注意Corner case： 1. 起始点是终点; 2. 起点是墙
	 */
	
	public static int shortpath(int rows, int cols, List<List<Integer>> lots){
		if (rows == 0 || cols == 0 || lots.get(0).get(0) == 0) {
			return -1;
		}
		
		int[][] grid = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				grid[i][j] = lots.get(i).get(j);
			}
		}
		
		int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		Queue <int[]> queue = new LinkedList<>();
		int dist = 0;
		queue.offer(new int[] {0, 0});
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] pos = queue.poll();
				int x = pos[0];
				int y = pos[1];
				
				if (grid[x][y] == 9) {
					return dist;
				}
				grid[x][y] = -1;
				//System.out.println("x = " + x + " y = " + y  + " grid[x][y] = " + grid[x][y]);
				
				for (int[] dir: direction) {
					int nextX = x + dir[0];
					int nextY = y + dir[1];
					
					if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < cols && (grid[nextX][nextY] == 1 || grid[nextX][nextY] == 9)) {
						queue.offer(new int[] {nextX, nextY});						
					}
				}
			}
			dist++;
		}
		
		return -1;
	}
	
	// 参考答案
	/*
    public static int shortpath2(int[][] maze,int[] start,int[] dest){
    	int[][] distance = new int[maze.length][maze[0].length];
    	for (int[] row: distance){
    		Arrays.fill(row, Integer.MAX_VALUE);
    	}
    	distance[start[0]][start[1]] = 0;
    	int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
    	Queue <int[]> q = new LinkedList<>();
    	q.add(start);
    	while(!q.isEmpty()){
    		int[] buff = q.remove();
    		for (int[] dir:dirs){
    			int x = buff[0] + dir[0];
    			int y = buff[1] + dir[1];
    			int sum = 0;
    			while(x>=0 && y>=0 && x<maze.length && y < maze[0].length && maze[x][y] == 0){
    				x +=dir[0];
    				y +=dir[1];
    				sum++;
    			}
    			if (distance[buff[0]][buff[1]] + sum < distance[x-dir[0]][y-dir[1]]){
    				distance[x-dir[0]][y-dir[1]] = distance[buff[0]][buff[1]] + sum;
    				q.add(new int[] {x-dir[0],y-dir[1]});
    			}
    		}
    	}
    	return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1:distance[dest[0]][dest[1]];
    }
    
    public static int shortestBFS(int[][] maze ,int[] start ,int[] dest){
    	int m = maze.length, n = maze[0].length;
    	int[][] path = new int[m][n];
    	int[] dirs = {-1,0,1,0,-1};
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(start);
    	while(!q.isEmpty()){
    		int[] curr = q.poll();
    		for(int d = 0;d < 4;d++){
    			int x = curr[0], y = curr[1], count = 0;
    			while(x+dirs[d] >= 0 && x+dirs[d] < m && y+dirs[d+1] >= 0 && y+dirs[d+1] < n && 
    					maze[x+dirs[d]][y+dirs[d+1]] == 0){
    				x += dirs[d];
    				y += dirs[d+1];
    				count++;
    			}
    			if ((x != start[0] || y!= start[1])&&
    					(path[x][y] == 0 || path[x][y] >path[curr[0]][curr[1]]+count)){
    				path[x][y] = path[curr[0]][curr[1]]+count;
    				q.offer(new int[]{x,y});
    			}
    		}
    		
    				
    	}
    	for (int[] pa: path) {
    		for (int p: pa) {
    			System.out.print(p + ",");
    		}
    		System.out.println();
    	}
    	return path[dest[0]][dest[1]] == 0?
    			-1 : path[dest[0]][dest[1]];
    			
    }
    */
    public static void main (String[] args){
    	/*
    	int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
    	int[] start = {0,0};
    	int[] end = {1,1};
    	System.out.println(shortestBFS(maze,start,end));
    	System.out.println(shortpath2(maze,start,end));
    	*/
    	List<List<Integer>> lots1 = new ArrayList<>();
    	lots1.add(new ArrayList<>(Arrays.asList(1, 1, 0, 1, 1)));
    	lots1.add(new ArrayList<>(Arrays.asList(1, 9, 1, 1, 1)));
    	lots1.add(new ArrayList<>(Arrays.asList(1, 1, 1, 0, 1)));
    	lots1.add(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0)));
    	lots1.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
    	
    	System.out.println(shortpath(5, 5, lots1));
    	
    	List<List<Integer>> lots2 = new ArrayList<>();
    	lots2.add(new ArrayList<>(Arrays.asList(1, 0, 0)));
    	lots2.add(new ArrayList<>(Arrays.asList(1, 0, 0)));
    	lots2.add(new ArrayList<>(Arrays.asList(1, 9, 1)));
    	
    	System.out.println(shortpath(3, 3, lots2));
    }
}
