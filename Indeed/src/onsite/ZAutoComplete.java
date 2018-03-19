package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZAutoComplete {
	
	/*
	static class TrieNode {
		TrieNode[] children;
		List<String> prefix;
		
		public TrieNode() {
			children = new TrieNode[26];
			prefix = new ArrayList<>();	
		}
	}
	
	static class Trie {
		TrieNode root; 
		
		public Trie(String[] dicts) {
			root = new TrieNode();
			
			for (String word: dicts) {
				TrieNode node = root;
				for (int i = 0; i < word.length(); i++) {
					char c = word.charAt(i);
					if (node.children[c - 'a'] == null) {
						node.children[c - 'a'] = new TrieNode();
					}
					node.prefix.add(word);
					node = node.children[c - 'a'];
				}
				node.prefix.add(word);
			}
			
		}
		
		public List<String> search(String prefix) {
			TrieNode node = root;
			for (int i = 0; i < prefix.length(); i++) {
				char c = prefix.charAt(i);
				
				if (node.children[c - 'a'] == null) {
					return new ArrayList<>();
				}
				node = node.children[c - 'a'];
			}
			
			return node.prefix;
			
		}
	}
	*/
	
	static class TrieNode {
		TrieNode[] children;
		String word;
		
		public TrieNode() {
			children = new TrieNode[26];
			word = null;
		}
	}
	
	static class Trie {

	}
	
	public static List<String> search3(String prefix, String[] dicts) {

	}
	
	public static void main(String[] args) {
		String[] dicts = {"apple", "application", "app", "act", "boy", "bad", "body", "back"};
		Trie ac = new Trie(dicts);
		
		System.out.println(ac.search("a"));
		System.out.println(ac.search("ap"));
		System.out.println(ac.search("app"));
		System.out.println(ac.search("ab"));
		System.out.println(ac.search("b"));
		System.out.println(ac.search("bo"));
		
		
		System.out.println("========================");
		System.out.println(search3("a", dicts));
		System.out.println(search3("ap", dicts));
		System.out.println(search3("app", dicts));
		System.out.println(search3("ab", dicts));
		System.out.println(search3("b", dicts));
		System.out.println(search3("bo", dicts));
		
	}
}
