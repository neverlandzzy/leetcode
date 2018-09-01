import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution1 {
	
	public static int evenSubarray(List<Integer> numbers, int k) {
		int n = numbers.size();
		Set<List<Integer>> set = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				List<Integer> list = numbers.subList(i, j);
				if (valid(list, k)) {
					set.add(list);
				}
			}
		}

		return set.size();
		
	}
	
	private static boolean valid(List<Integer> list, int k) {
		int counter = 0;
		
		for (int i: list) {
			if ((i & 1) != 0) {
				counter++;
			}
			
			if (counter > k) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(2, 1, 2, 1, 3));
		System.out.println(evenSubarray(list1, 2));  //10
		
		List<Integer> list2 = new ArrayList<>(Arrays.asList(6, 3, 5, 8));
		System.out.println(evenSubarray(list2, 1));  //6
	}
}
