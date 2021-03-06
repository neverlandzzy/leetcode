

public class Solution {
	/*
	 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. 
	 * Return the maximum valued number you could get.
	 * 
	 * Example 1:
	 * Input: 2736
	 * Output: 7236
	 * Explanation: Swap the number 2 and the number 7.
	 * 
	 * Example 2:
	 * Input: 9973
	 * Output: 9973
	 * Explanation: No swap.
	 * Note:
	 * The given number is in the range [0, 10^8]
	 */
	
	// https://leetcode.com/problems/maximum-swap/solution/
	public static int maximumSwap(int num) {
        char[] str = String.valueOf(num).toCharArray();
        int[] last = new int[10];
            
        for (int i = 0; i < str.length; i++) {
            last[str[i] - '0'] = i;
        }
        
        for (int i = 0; i < str.length; i++) {
            for (int j = 9; j > str[i] - '0'; j--) {
                if (last[j] > i) {
                    char c = str[i];
                    str[i] = str[last[j]];
                    str[last[j]] = c;
                    return Integer.parseInt(String.valueOf(str));
                }
            }
        }
        
        return num;
	}
	
	// My solution
	/*
    public static int maximumSwap(int num) {
        List<Integer> digits = new ArrayList<>();
        
        while (num != 0) {
        	digits.add(0, num % 10);
        	num /= 10;
        }
        
        List<Integer> sorted = new ArrayList<>(digits);
        Collections.sort(sorted, Collections.reverseOrder());
        int smaller = -1;
        int larger = -1;

        for (int i = 0; i < digits.size(); i++) {
        	if (digits.get(i) != sorted.get(i)) {
        		smaller = digits.get(i);
        		larger = sorted.get(i);
        		digits.set(i, sorted.get(i));
        		break;
        	}
        }

        if (smaller != -1) {
        	for (int i = digits.size() - 1; i>= 0; i--) {
        		if (digits.get(i) == larger) {
        			digits.set(i, smaller);
        			break;
        		}
        	}
        }
        
        int result = 0;
        
        for (int i = 0; i < digits.size(); i++) {
        	result += digits.get(i);
        	result *= 10;
        }
        
        return result / 10;
    }
    */
    public static void main(String[] args) {
		System.out.println(maximumSwap(2736));
		System.out.println(maximumSwap(9973));
	}
}
