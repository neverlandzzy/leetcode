
public class Solution {
	public static void main(String[] args) {
		TicTacToe toe = new TicTacToe(3);
		
		System.out.println(toe.move(0, 0, 1));
		System.out.println(toe.move(0, 2, 2));
		System.out.println(toe.move(2, 2, 1));
		System.out.println(toe.move(1, 1, 2));
		System.out.println(toe.move(2, 0, 1));
		System.out.println(toe.move(1, 0, 2));
		System.out.println(toe.move(2, 1, 1));
	}
}
