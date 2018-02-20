import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * Given two words (beginWord and endWord), and a dictionary's word list, 
	 * find the length of shortest transformation sequence from beginWord to endWord, such that:
	 * 
	 * Only one letter can be changed at a time
	 * Each intermediate word must exist in the word list
	 * 
	 * For example,
	 * 
	 * Given:
	 * beginWord = "hit"
	 * endWord = "cog"
	 * wordList = ["hot","dot","dog","lot","log"]
	 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	 * return its length 5.
	 * 
	 * Note:
	 * Return 0 if there is no such transformation sequence.
	 * All words have the same length.
	 * All words contain only lowercase alphabetic characters.
	 */
	
   public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }
        
        Set<String> set = new HashSet<>();
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        int counter = 1;
        
        set.add(beginWord);
        queue.offer(beginWord);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            counter++;
            
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                
                for (String s: getNextWord(word, wordSet)) {
                    if (set.contains(s)) {
                        continue;
                    }
                    
                    if (s.equals(endWord)) {
                        return counter;
                    }
                    set.add(s);
                    queue.offer(s);
                }    
            }
        }
        
        return 0;
    }
    
    private static List<String> getNextWord(String word, Set<String> set) {
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < word.length(); i++) {
            char[] charArray = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                if (charArray[i] == c) {
                    continue;
                }
                
                charArray[i] = c;
                String s = String.valueOf(charArray);
                if (set.contains(s)) {
                    result.add(s);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String begin = "hit";
		String end = "cog";
		List<String> wordList= new ArrayList<>();
		
		wordList.add("cog");
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("dog");
	
		System.out.println(ladderLength(begin, end, wordList));
	}
}
