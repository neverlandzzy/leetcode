
public class Solution {
	/*
	 * We are playing the Guess Game. The game is as follows:
	 * 
	 * I pick a number from 1 to n. You have to guess which number I picked.
	 * 
	 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
	 * 
	 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
	 * 
	 * -1 : My number is lower
	 *  1 : My number is higher
	 *  0 : Congrats! You got it!
	 * Example:
	 * n = 10, I pick 6.
	 * 
	 * Return 6.
	 */
	
	private static int guess(int n) {
		int target = 1702766719;
		
		if (n == target) {
			return 0;
		} else if (n > target) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public static int guessNumber(int n) {
        int start = 1;
        int end = n;
        
        while (start + 1 < end) {
        	int mid = start + (end - start) / 2;
            
            if (guess(mid) == 0) {
                return mid;
            }
            
            if (guess(mid) == -1) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (guess(start) == 0) {
            return start;
        } else {
            return end;
        }
	}
	
	/*
    public static int guessNumber(int n) {
        int start = 1; 
        int end = n;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) == 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start;
    }
    */
    public static void main(String[] args) {
		System.out.println(guessNumber(2126753390));
	}
}
