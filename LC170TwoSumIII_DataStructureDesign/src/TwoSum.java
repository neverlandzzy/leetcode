import java.util.HashMap;
import java.util.Map;


public class TwoSum {

	// HashSet 会超时
	//HashSet<Integer> set = new HashSet<Integer>();
	
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
    // Add the number to an internal data structure.
	public void add(int number) {

		if (!map.containsKey(number)) {
			map.put(number, 1);
		} else {
			map.put(number, map.get(number) + 1);
		}
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
		if(map.isEmpty()) {
			return false;
		}
		
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			int num1 = entry.getKey();
			int num2 = value - num1;
			
			if (num2 == num1) {
				if (map.get(num1) >= 2) {
					return true;
				}
			} else {
				if (map.containsKey(num2)) {
					return true;
				}
			}
		}
		
		return false;
	}
}