package OA;

public class Pairs {
	
	
	public static int pairs(int[] nums, int diff) {
		int i = 0, j = 1, pairs = 0;
		int len = nums.length;
		
		while (i < len) {
			for (j = i + 1; j < len; j++) {
				if (nums[j] == nums[i] + diff) {
					pairs++;
				}
			}
			
			i++;
		}
		
		return pairs;
	}
	
	public static void main(String[] args) {
		int[] test = {2, 3, 5, 6, 8, 9, 10};
		
		System.out.println(pairs(test, 1));
	}
}
