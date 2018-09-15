import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	public static void main(String[] args) {
		List<List<Integer>> list = new ArrayList<>();
		
		list.add(Arrays.asList(1, 2));
		list.add(Arrays.asList(3));
		list.add(Arrays.asList(4, 5, 6));
		
		Vector2D vector2D = new Vector2D(list);
		
		while (vector2D.hasNext()) {
			System.out.println(vector2D.next());
		}
	}
}
