
public class Solution {
	public static void main(String[] args) {
		
		int[][] food1 = {{1,2}, {0,1}};
		SnakeGame sg = new SnakeGame(3, 2, food1);
		
		System.out.println(sg.move("R"));
		System.out.println(sg.move("D"));
		System.out.println(sg.move("R"));
		System.out.println(sg.move("U"));
		System.out.println(sg.move("L"));
		System.out.println(sg.move("U"));
	}
}
