import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	
	public static class TrieNode {
		TrieNode[] children;
		String word;
		
		public TrieNode() {
			children = new TrieNode[26];
			word = null;
		}
	}
	
    public static String longestWord(String[] words) {
    	
    	if (words == null || words.length == 0) {
    		return null;
    	}
    	
        TrieNode root = new TrieNode();
        Arrays.sort(words);
        
        String result = null;
        int length = 0;
        
        for (String w: words) {
        	TrieNode cur = root;
        	for (int i = 0; i < w.length(); i++) {
        		char c = w.charAt(i);
        		if (cur.children[c - 'a'] == null) {
        			cur.children[c - 'a'] = new TrieNode();
        		}
        		
        		cur = cur.children[c - 'a'];
        	}
        	
        	cur.word = w;
        }
        
        for (String w: words) {
        	TrieNode cur = root;
        	int i = 0;
        	for (i = 0; i < w.length(); i++) {
        		char c = w.charAt(i);
        		cur = cur.children[c - 'a'];
        		if (cur.word == null) {
        			break;
        		} 
        	}
        	
        	if (i == w.length()) {
        		if (w.length() > length) {
        			length = w.length();
        			result = w;
        		}
        	}
        }
        
        return result;
        
    }
	
    
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, List<List<String>>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        
        if (accounts == null || accounts.size() == 0) {
        	return result;
        }
        
        for (int i = 0; i < accounts.size(); i++) {
        	List<String> account = accounts.get(i);
        	String key = account.get(0);
        	if (!map.containsKey(key)) {
        		map.put(key, new ArrayList<>());
        		map.get(key).add(new ArrayList<>());
        		for (int j = 1; j < account.size(); j++) {
        			if (!map.get(key).get(0).contains(account.get(j))) {
        				map.get(key).get(0).add(account.get(j));
        			}
        		}
        	} else {
        		List<List<String>> entry = map.get(key);
        		int size = entry.size();
        		//int index = -1;
        		List<Integer> index = new ArrayList<>();
        		for (int j = 1; j < account.size(); j++) {
        			for (int k = 0; k < entry.size(); k++) {
        				if (entry.get(k).contains(account.get(j))) {
        					index.add(k);
        					break;
        				}
        			}
        		}
        		
        		if (index.size() == 1) {
        			for (int j = 1; j < account.size(); j++) {
        				if (!entry.get(index.get(0)).contains(account.get(j))) {
        					entry.get(index.get(0)).add(account.get(j));
        				}
        			}
        		} else if (index.size() == 0){
        			map.get(key).add(new ArrayList<>());
        			for (int j = 1; j < account.size(); j++) {
        				if (!map.get(key).get(size).contains(account.get(j))) {
        					map.get(key).get(size).add(account.get(j));
        				}
        			}
        			
        		} else {
        			Collections.sort(index);
        			for (int k = 1; k < index.size(); k++) {
        				entry.get(index.get(0)).addAll(entry.get(index.get(k)));  
        				entry.remove(index.get(k));
        			}
        			System.out.println(index);
        			entry.remove(index.get(1));
        			System.out.println(entry);
        		}
        	}
        }
        
        System.out.println(map);
        for (Map.Entry<String, List<List<String>>> entry: map.entrySet()) {
        	String key = entry.getKey();
        	List<List<String>> emails = entry.getValue();
        	
        	for (List<String> email: emails) {
        		Collections.sort(email);
        		List<String> list = new ArrayList<>();
        		list.add(key);
        		list.addAll(email);
        		result.add(list);
        	}
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		String[] test11 = {"w","wo","wor","worl", "worlde"};
		String[] test12 = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
		
		System.out.println(longestWord(test11));
		System.out.println(longestWord(test12));
		
		List<List<String>> test21 = new ArrayList<>();
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.add(new ArrayList<>());
		test21.get(0).add("John");
		test21.get(0).add("johnsmith@mail.com");
		test21.get(0).add("john00@mail.com");
		test21.get(1).add("John");
		test21.get(1).add("johnnybravo@mail.com");
		test21.get(2).add("John");
		test21.get(2).add("johnsmith@mail.com");
		test21.get(2).add("john_newyork@mail.com");
		test21.get(3).add("Mary");
		test21.get(3).add("mary@mail.com");
		test21.get(4).add("Kevin");
		test21.get(4).add("Kevin4@m.co");
		test21.get(4).add("Kevin2@m.co");
		test21.get(4).add("Kevin2@m.co");
		test21.get(5).add("Kevin");
		test21.get(5).add("Kevin3@m.co");
		test21.get(5).add("Kevin3@m.co");
		test21.get(5).add("Kevin5@m.co");
		test21.get(6).add("Kevin");
		test21.get(6).add("Kevin5@m.co");
		test21.get(6).add("Kevin2@m.co");
		
		
		System.out.println(accountsMerge(test21));
		//System.out.println(test21);
		
	}
	
	
	
}
