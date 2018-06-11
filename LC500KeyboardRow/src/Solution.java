import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
	 * 
	 * Example 1:
	 * Input: ["Hello", "Alaska", "Dad", "Peace"]
	 * Output: ["Alaska", "Dad"]
	 * Note:
	 * 	1. You may use one character in the keyboard more than once.
	 * 	2. You may assume the input string will only contain letters of alphabet.
	 */
	
    public static String[] findWords(String[] words) {
        Set<Character> row1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> row2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
        Set<Character> row3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));
        
        List<String> list = new ArrayList<>();
        
        for (String word: words) {
        	boolean flag1 = false;
        	boolean flag2 = false;
        	boolean flag3 = false;
        	boolean valid = true;
        	
        	if (row1.contains(Character.toLowerCase(word.charAt(0)))) {
        		flag1 = true;
        	} else if (row2.contains(Character.toLowerCase(word.charAt(0)))){
        		flag2 = true;
        	} else {
        		flag3 = true;
        	}
        	
        	for (int i = 1; i < word.length(); i++) {
        		if (flag1 && !row1.contains(Character.toLowerCase(word.charAt(i)))) {
        			valid = false;
        			break;
        		}
        		
        		if (flag2 && !row2.contains(Character.toLowerCase(word.charAt(i)))) {
        			valid = false;
        			break;
        		}
        		
        		if (flag3 && !row3.contains(Character.toLowerCase(word.charAt(i)))) {
        			valid = false;
        			break;
        		}
        	}
        	
        	if (valid) {
        		list.add(word);
        	}
        }
        
        String[] result = new String[list.size()];
        
        for (int i = 0; i < result.length; i++) {
        	result[i] = list.get(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"Hello", "Alaska", "Dad", "Peace"};
		String[] result1 = findWords(test1);
		
		for (String s: result1) {
			System.out.print(s + ", ");
		}
		
		System.out.println();
	}
}
