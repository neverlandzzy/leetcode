
public class Solution {
	/**
	 * Given a word, you need to judge whether the usage of capitals in it is right or not.
	 * 
	 * We define the usage of capitals in a word to be right when one of the following cases holds:
	 * 	1. All letters in this word are capitals, like "USA".
	 * 	2. All letters in this word are not capitals, like "leetcode".
	 * 	3. Only the first letter in this word is capital if it has more than one letter, like "Google".
	 * Otherwise, we define that this word doesn't use capitals in a right way.
	 * 
	 * Example 1:
	 * Input: "USA"
	 * Output: True
	 * 
	 * Example 2:
	 * Input: "FlaG"
	 * Output: False
	 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
	 */

    public static boolean detectCapitalUse(String word) {
		if (word == null || word.length() == 0) {
			return true;
		}

		boolean isFirstCharCapital = word.charAt(0) >= 'A' && word.charAt(0) <= 'Z';
		boolean isFollowingCharContainingLowerCase = false;
		boolean isFollowingCharContainingCapital = false;

		for (int i = 1; i < word.length(); i++) {
			char c = word.charAt(i);

			if (c >= 'a' && c <= 'z') {
				if (isFollowingCharContainingCapital) {
					return false;
				}
				isFollowingCharContainingLowerCase = true;
			} else if (c >= 'A' && c <= 'Z') {
				if (!isFirstCharCapital || isFollowingCharContainingLowerCase) {
					return false;
				}

				isFollowingCharContainingCapital = true;
			}
		}

		return true;
    }
    
    public static void main(String[] args) {
		System.out.println(detectCapitalUse("USA"));
		System.out.println(detectCapitalUse("FlaG"));
		System.out.println(detectCapitalUse("FLAg"));
	}
}
