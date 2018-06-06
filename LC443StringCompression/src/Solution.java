
public class Solution {
	/*
	 * Given an array of characters, compress it in-place.
	 * 
	 * The length after compression must always be smaller than or equal to the original array.
	 * 
	 * Every element of the array should be a character (not int) of length 1.
	 * 
	 * After you are done modifying the input array in-place, return the new length of the array.
	 * 
	 * Follow up:
	 * Could you solve it using only O(1) extra space?
	 * 
	 * Example 1:
	 * Input:
	 * ["a","a","b","b","c","c","c"]
	 * 
	 * Output:
	 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
	 * 
	 * Explanation:
	 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
	 * Example 2:
	 * Input:
	 * ["a"]
	 * 
	 * Output:
	 * Return 1, and the first 1 characters of the input array should be: ["a"]
	 * 
	 * Explanation:
	 * Nothing is replaced.
	 * 
	 * Example 3:
	 * Input:
	 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
	 * 
	 * Output:
	 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
	 * 
	 * Explanation:
	 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
	 * Notice each digit has it's own entry in the array.
	 * 
	 * Note:
	 * 1. All characters have an ASCII value in [35, 126].
	 * 2. 1 <= len(chars) <= 1000.
	 */
	
	// Time:O(n), Space: O(1)
	public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }
        
        int index = 0;
        int i = 0;
        int j = 0;
        
        int counter = 0;
        int n = chars.length;
        
        while (j < n) {
            while (j < n && chars[i] == chars[j]) {
                j++;
                counter++;
            }
            
            chars[index] = chars[i];
            index++;
            
            if (counter > 1) {
                String s = String.valueOf(counter);
                for (int k = 0; k < s.length(); k++) {
                    chars[index++] = s.charAt(k);
                }
            }
            
            i = j;
            counter = 0;
            if (j >= n) {
                break;
            }
        }
        
        return index;
    }
	
	// another solution, more straightforward
	/*
    public static int compress(char[] chars) {
        int index = 0;
        int i = 0;
        int j = 1;
        
        int n = chars.length;
        
        while (j < n) {
            if (chars[j] == chars[i]) {
                j++;
            } else {
                if (j - i == 1) {
                    chars[index] = chars[i];
                    index++;
                } else {
                    
                    chars[index] = chars[i];
                    index++;
                    String s = String.valueOf(j - i);
                    
                    for (int k = 0; k < s.length(); k++) {
                        chars[index] = s.charAt(k);
                        index++;
                    }
                }
                i = j;
                j++;
            }
        }
        
        if (j - i == 1) {
            chars[index] = chars[i];
            return index + 1;
        } else {
            chars[index] = chars[i];
            index++;
            String s = String.valueOf(j - i);
            for (int k = 0; k < s.length(); k++) {
                chars[index] = s.charAt(k);
                index++;
            }
        }
        return index;
    }
    */
	
	public static void main(String[] args) {
		char[] test1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
		char[] test2 = {'a'};
		char[] test3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
		
		System.out.println(compress(test1));
		print(test1);
		
		System.out.println(compress(test2));
		print(test2);
		
		System.out.println(compress(test3));
		print(test3);		
	}
	
	private static void print(char[] chars) {
		for (char c: chars) {
			System.out.print(c + ", ");
		}
		System.out.println();
	}
}
