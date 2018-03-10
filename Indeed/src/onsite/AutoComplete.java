package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoComplete {
	/*
	 *  Question Description
	 *  Say I'm typing on a phone. Given a prefix String,and a dictionary.
	 *  Find all auto-complete word for the given prefix string
	 * 
	 * 	没有follow up，但可能考多种解法
	 * 
	 * 【解法1】Trie， 每个点存prefix的list -- 查找快，费空间
	 * 【解法2】Trie， 查找的时候做dfs  -- 查找慢，省空间
	 * 【解法3】binary search
	 */
	
	// 【解法1】
	
	public static class TrieNode {
		TrieNode[] children;
		String word;
		List<String> prefix;
		
		public TrieNode() {
			this.children = new TrieNode[26];
			this.word = null;
			this.prefix = new ArrayList<>();
		}
	}
	
	TrieNode root;
	
	public AutoComplete(String[] dicts) {
		root = new TrieNode();
		
		for (String s: dicts) {
			TrieNode node = root;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (node.children[c - 'a'] == null) {
					node.children[c - 'a'] = new TrieNode();
				}
				node.prefix.add(s);
				node = node.children[c - 'a'];
			}
			node.word = s;
			node.prefix.add(s);
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
	
	
	/*
	// 解法2：
	public static class TrieNode {
		TrieNode[] children;
		String word;
		
		public TrieNode() {
			this.children = new TrieNode[26];
			this.word = null;
		}
	}
	
	TrieNode root;
	
	public AutoComplete(String[] dicts) {
		root = new TrieNode();
		
		for (String s: dicts) {
			TrieNode node = root;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (node.children[c - 'a'] == null) {
					node.children[c - 'a'] = new TrieNode();
				}
				node = node.children[c - 'a'];
			}
			node.word = s;
		}
	}
	
	public List<String> search(String prefix) {
		List<String> result = new ArrayList<>();
		TrieNode node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (node.children[c - 'a'] == null) {
				return new ArrayList<>();
			}
			node = node.children[c - 'a'];
		}
		
		helper(result, node);
		return result;
	}
	
	private void helper(List<String> result, TrieNode node) {
		if (node.word != null) {
			result.add(node.word);
		}
		
		for (int i = 0; i < 26; i++) {
			if (node.children[i] != null) {
				helper(result, node.children[i]);
			}
		}
	}
	*/
	
	// 解法3 binary search
	public static List<String> search3(String prefix, String[] dicts) {
		List<String> result = new ArrayList<>();
		
		Arrays.sort(dicts);
		
		int start = 0;
		int end = dicts.length - 1;
		int len = prefix.length();
		
		while (start < end) {
			int mid = start + (end - start) / 2;
			
			String s = dicts[mid].substring(0, Math.min(len, dicts[mid].length()));
			
			if (s.equals(prefix)) {
				int i = mid;
				while (i >= start && dicts[i].substring(0, Math.min(len, dicts[mid].length())).equals(prefix)) {
					result.add(dicts[i]);
					i--;
				}
				i = mid + 1;
				while (i <= end && dicts[i].substring(0, Math.min(len, dicts[mid].length())).equals(prefix)) {
					result.add(dicts[i]);
					i++;
				}
				
				return result;
			} else if (s.compareTo(prefix) < 0) {
				// [compareTo] return the value 0 if the argument string is equal to this string; 
				// a value less than 0 if this string is lexicographically less than the string argument; 
				// and a value greater than 0 if this string is lexicographically greater than the string argument.
				start = mid;
			} else {
				end = mid;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] dicts = {"apple", "application", "app", "act", "boy", "bad", "body", "back"};
		AutoComplete ac = new AutoComplete(dicts);
		
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
