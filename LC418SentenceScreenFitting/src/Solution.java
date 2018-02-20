
public class Solution {
	/*
	 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence 
	 * can be fitted on the screen.
	 * 
	 * Note:
	 * 
	 * A word cannot be split into two lines.
	 * The order of words in the sentence must remain unchanged.
	 * Two consecutive words in a line must be separated by a single space.
	 * Total words in the sentence won't exceed 100.
	 * Length of each word is greater than 0 and won't exceed 10.
	 * 1 ≤ rows, cols ≤ 20,000.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * rows = 2, cols = 8, sentence = ["hello", "world"]
	 * 
	 * Output: 
	 * 1
	 * 
	 * Explanation:
	 * hello---
	 * world---

	 * The character '-' signifies an empty space on the screen.
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
	 * 
	 * Output: 
	 * 2
	 * 
	 * Explanation:
	 * a-bcd- 
	 * e-a---
	 * bcd-e-
	 * 
	 * The character '-' signifies an empty space on the screen.
	 * 
	 * Example 3:
	 * 
	 * Input:
	 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
	 * 
	 * Output: 
	 * 1
	 * 
	 * Explanation:
	 * I-had
	 * apple
	 * pie-I
	 * had--
	 * 
	 * The character '-' signifies an empty space on the screen.
	 */
	
	// https://discuss.leetcode.com/topic/62455/21ms-18-lines-java-solution?page=1
	
    public static int wordsTyping(String[] sentence, int rows, int cols) {
        StringBuilder sb = new StringBuilder();
        for (String s: sentence) {
        	sb.append(s).append(" ");
        }
        
        int len = sb.length();
        int start = 0; // 最后一行(row)的下一行起始位置 --> 也就是screen里能放下的有效字符
       // System.out.println(len);
        for (int i = 0; i < rows; i++) {
        	
        	start += cols;
        	
        	//当本行最后一个字符的下一个字符为' '时，可以省略掉' '， 因此有效字符数目 +1， 当其不为' '时，要向前移，直到为' '，
        	//每移一次，有效字符数目 -1。
        	
        	while (start > 0 && sb.charAt(start % len) != ' ') {
        		start--;
        	}
        	start++;
        	//System.out.println(start);
        }
        
        
        return start/len;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"hello", "world"};
		String[] test2 = {"a", "bcd", "e"};
		String[] test3 = {"I", "had", "apple", "pie"};
		
		System.out.println(wordsTyping(test1, 2, 8));
		//System.out.println(wordsTyping(test2, 3, 6));
		//System.out.println(wordsTyping(test3, 4, 5));
	}
}
