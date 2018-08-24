package StringCompare;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	/*
	 * Given an String array A ["cat", "bat", "dog", "zip", "face"]
	 * and a char array B ['c','b','d','a','f']
	 * question : is A sorted according to B?
	 * 
	 * e.g.
	 * A ["cat", "bat", "dog", "zip", "face"]
	 * B ['c','b','d','z','f']
	 * return true;
	 * 
	 * A ["cat","cataa", "bat", "dog", "zip", "face"]
	 * B ['c','b','d','z','f']
	 * return true;
	 * 
	 * A ["bat", "cat", "dog", "zip", "face"]
	 * B ['c','b','d','z','f']
	 * return false;
	 * 
	 * Questions asked to clarify:
	 * 1. how big is the in put?  Very big, but you can put them all in memory
	 * 2. what is in B?  Any characters, maybe alian ones.
	 * 3. Is there duplication in B? no
	 * 4. If A is empty or null what shall we return? true;
	 * 5. If B is empty or null what shall we return? false;
	 * 
	 * Questions  forgot to ask.
	 * 1. what if there are character in A which is not in B?
	 */
	public static boolean isSortedArray(String[] words, char[] aphabet) {
        Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < aphabet.length; i++ ) {
			map.put(aphabet[i], i);
		}

		for (int i = 1; i < words.length; i++ ) {
	        String pre = words[i - 1];
	        String cur = words[i];
	        
	        int minLen = Math.min(pre.length(), cur.length());
	        boolean isSorted = false;
	        for (int j = 0; j < minLen; j++ ) {
	        	int preIndex = map.get(pre.charAt(j));
	        	int curIndex = map.get(cur.charAt(j));
	                
	                if (preIndex > curIndex) {
	                	return false;
	                } else if (preIndex < curIndex) {
	                	isSorted = true;
	                    break;
	                }
	        }
	        if (!isSorted && pre.length() > cur.length()) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		String[] test11 = {"cat", "bat", "dog", "zip", "face"};
		char[] test12 = {'c','b','d','z','f'};
		String[] test21 = {"cat","cataa", "bat", "dog", "zip", "face"};
		char[] test22 = {'c','b','d','z','f', 'a', 't'};
		String[] test31 = {"bat", "cat", "dog", "zip", "face"};
		char[] test32 = {'c','b','d','z','f'};
		
		System.out.println(isSortedArray(test11, test12));
		System.out.println(isSortedArray(test21, test22));
		System.out.println(isSortedArray(test31, test32));
	}
	
}
