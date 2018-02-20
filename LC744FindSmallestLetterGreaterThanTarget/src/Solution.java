
public class Solution {
	/*
	 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.
	 * 
	 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
	 * 
	 * Examples:
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "a"
	 * Output: "c"
	 * 
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "c"
	 * Output: "f"
	 * 
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "d"
	 * Output: "f"
	 * 
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "g"
	 * Output: "j"
	 * 
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "j"
	 * Output: "c"
	 * 
	 * Input:
	 * letters = ["c", "f", "j"]
	 * target = "k"
	 * Output: "c"
	 * 
	 * Note:
	 * letters has a length in range [2, 10000].
	 * letters consists of lowercase letters, and contains at least 2 unique letters.
	 * target is a lowercase letter.
	 */
	
    public static char nextGreatestLetter(char[] letters, char target) {
        int[] map = new int[26];
        for (int i = 0; i < letters.length; i++) {
        	map[letters[i] - 'a']++;
        }

        
        for (int i = 1; i <= 26; i++) {
        	int next = i + target;
        	if (next > 'z') {
        		next -= 26;
        	}
        	
        	if (map[next - 'a'] != 0) {
        		
        		return (char) (next);
        	}
        }
        

        return '*';
    }
    
    public static void main(String[] args) {
		char[] test1 = {'c', 'f','j'};
		System.out.println(nextGreatestLetter(test1, 'a'));
		System.out.println(nextGreatestLetter(test1, 'c'));
		System.out.println(nextGreatestLetter(test1, 'd'));
		System.out.println(nextGreatestLetter(test1, 'g'));
		System.out.println(nextGreatestLetter(test1, 'j'));
		System.out.println(nextGreatestLetter(test1, 'k'));
		
	}
}
