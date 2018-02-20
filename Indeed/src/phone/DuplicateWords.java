package phone;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateWords {
	/*
	 * 问题是输入一组word，找出duplicate
	 * 例如输入 "abc def ghi abc"，输出abc即可
	 * 
	 * follow up 1: 输出最早出现的duplicate，
	 * 输入 "abc def ghi jkl ghi abc"，这里应该输出abc
	 * 
	 * follow up 2: 
	 * 如果这是一个real word的function怎么办，比如说你的输入是一本杂志里面的词，找出所有duplicate的词。
	 * 这里的意思是，真实中的输入不一定严格的以一个空格隔开每个单词（不能简单用split(" ")），不一定全部小写等等
	 */
	
	// Time: O(NL), Space: O(NL) N: number of words in s, L: length of words in s
	private static String findDupicate(String s) {
		String[] words = s.split(" ");
		
		Set<String> set = new HashSet<>();
		
		for (String w: words) {
			if (set.contains(w)) {
				return w;
			}
			set.add(w);
		}
		
		return null;
	}
	
	// follow up 1 and 2
	private static String findDupicate2(String s) {
		
		String[] words = s.split(" ");
		//String[] words = s.toLowerCase().split("\\s+");  // -- follow up 2: 不一定严格的以一个空格隔开单词 -- regular expression, 有大小写
		
		int index = words.length;
		
		Map<String, Integer> map = new HashMap<>();
		
		for (int i = 0; i < words.length; i++) {
			if (map.containsKey(words[i])) {
				index = Math.min(index, map.get(words[i]));
			} else {
				map.put(words[i], i);
			}
		}
		return index == words.length ? null : words[index];
	}

	// Another solution using Trie 
	// Time: O(NL) N: number of words in s, L: length of words in s. Space: upper limit O(26^L)
	public static class TrieNode {
		Map<Character, TrieNode> children;
		String word;
		
		public TrieNode() {
			children = new HashMap<>();
			word = null;
		}
	}
	
	private static String findDupicate3(String s) {
		TrieNode root = new TrieNode();
		String[] words = s.split(" ");
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			TrieNode node = root;
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				if (!node.children.containsKey(c)) {
					node.children.put(c, new TrieNode());
				}
				node = node.children.get(c);
			}
			if (node.word != null) {
				return node.word;
			}
			
			node.word = word;
		}
		
		return null;
	}
	
	// follow up 1 and 2, in Trie
	public static class TrieNode2 {
		Map<Character, TrieNode2> children;
		String word;
		Integer index;
		
		public TrieNode2() {
			children = new HashMap<>();
			word = null;
			index = null;
		}
	}
	
	private static String findDupicate4(String s) {
		TrieNode2 root = new TrieNode2();
		String[] words = s.split(" ");
		int index = words.length;
		
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			TrieNode2 node = root;
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				if (!node.children.containsKey(c)) {
					node.children.put(c, new TrieNode2());
				}
				node = node.children.get(c);
			}
			if (node.word != null) {
				index = Math.min(index, node.index);
			} else {			
				node.word = word;
				node.index = i;
			}
		}
		return index == words.length ? null : words[index];
		
	}
	
	public static void main(String[] args) {
		String test1 = "abc def ghi jkl ghi abc";
		String test2 = "dog cat fish cat dog";
		String test3 = "hello world world hello";
		
		System.out.println("===== Solution 1 ======");
		System.out.println(findDupicate(test1));
		System.out.println(findDupicate(test2));
		System.out.println(findDupicate(test3));
		System.out.println("===== Solution 2 ======");
		System.out.println(findDupicate2(test1));
		System.out.println(findDupicate2(test2));
		System.out.println(findDupicate2(test3));
		System.out.println("===== Solution 3 ======");
		System.out.println(findDupicate3(test1));
		System.out.println(findDupicate3(test2));
		System.out.println(findDupicate3(test3));
		System.out.println("===== Solution 4 ======");
		System.out.println(findDupicate4(test1));
		System.out.println(findDupicate4(test2));
		System.out.println(findDupicate4(test3));
	}
}
