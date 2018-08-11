import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	public static void main(String[] args) {
		
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4, 5, 6));
		
		ZigzagIterator i = new ZigzagIterator(list1, list2);
		
		while (i.hasNext()) {
			System.out.println(i.next());
		}
	}
}
