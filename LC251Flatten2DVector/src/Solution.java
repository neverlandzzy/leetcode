import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	public static void main(String[] args) {
		int[][] test = {{1, 2}, {3}, {4}};
		Vector2D vector2D = new Vector2D(test);

		System.out.println(vector2D.next());
		System.out.println(vector2D.next());
		System.out.println(vector2D.next());
		System.out.println(vector2D.hasNext());
		System.out.println(vector2D.hasNext());
		System.out.println(vector2D.next());
		System.out.println(vector2D.hasNext());
	}
}
