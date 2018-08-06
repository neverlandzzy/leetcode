import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given a robot cleaner in a room modeled as a grid.
	 * 
	 * Each cell in the grid can be empty or blocked.
	 * 
	 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
	 * 
	 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
	 * 
	 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
	 * 
	 * interface Robot {
	 *   // returns true if next cell is open and robot moves into the cell.
	 *   // returns false if next cell is obstacle and robot stays on the current cell.
	 *   boolean move();
	 * 
	 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
	 *   // Each turn will be 90 degrees.
	 *   void turnLeft();
	 *   void turnRight();
	 * 
	 *   // Clean the current cell.
	 *   void clean();
	 * }
	 * 
	 * Example:
	 * Input:
	 * room = [
	 *   [1,1,1,1,1,0,1,1],
	 *   [1,1,1,1,1,0,1,1],
	 *   [1,0,1,1,1,1,1,1],
	 *   [0,0,0,1,0,0,0,0],
	 *   [1,1,1,1,1,1,1,1]
	 * ],
	 * row = 1,
	 * col = 3
	 * 
	 * Explanation:
	 * All grids in the room are marked by either 0 or 1.
	 * 0 means the cell is blocked, while 1 means the cell is accessible.
	 * The robot initially starts at the position of row=1, col=3.
	 * From the top left corner, its position is one row below and three columns right.
	 * 
	 * Notes:
	 * 
	 * 1. The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". 
	 *    In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
	 * 2. The robot's initial position will always be in an accessible cell.
	 * 3. The initial direction of the robot will be facing up.
	 * 4. All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
	 * 5. Assume all four edges of the grid are all surrounded by wall.
	 */
	
	// https://github.com/Chaseqi/leetcode/blob/master/questions/CleaningRobot.java
	// http://www.1point3acres.com/bbs/thread-403845-1-1.html
	
	// 顺时针
	// up -> right -> down -> left 
	int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
    public void cleanRoom(Robot robot) {
        Set<String> set = new HashSet<>();
        
        dfs(robot, set, 0, 0, 0);
    }
    
    // x, y: 坐标
    // d: 方向 - [0, 3]
    private void dfs(Robot robot, Set<String> set, int x, int y, int d) {
    	robot.clean();
    	set.add(x + "," + y);
    	
    	for (int i = 0; i < directions.length; i++) {
    		int[] dir = directions[(i + d) % 4];
    		int nextX = x + dir[0];
    		int nextY = y + dir[1];
    		
    		if (!set.contains(nextX + "," + nextY) && robot.move()) {
    			dfs(robot, set, nextX, nextY, (i + d) % 4);
    		}
    		
    		// 顺时针 -- 与directions的顺序要一致。因为directions里的方向表示机器人将要前进的方向。确定方向后，要将机器人转到该方向上（by default，机器人是向上的）
    		robot.turnRight();
    	}
    	
    	moveBack(robot);
    }
    
    private void moveBack(Robot robot) {
		robot.turnRight();
        robot.turnRight();
		robot.move();
		robot.turnRight();
        robot.turnRight();
	}
}
