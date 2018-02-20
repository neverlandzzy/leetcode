
public class Solution {
	public static void main(String[] args) {
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("mad");
		wordDictionary.addWord("dad");
		System.out.println(wordDictionary.search("pad"));
		System.out.println(wordDictionary.search("mad"));
		System.out.println(wordDictionary.search("bad"));
		System.out.println(wordDictionary.search(".ad"));
		System.out.println(wordDictionary.search("..d"));
		
	}
}
