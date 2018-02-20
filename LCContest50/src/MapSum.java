import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class MapSum {
	
	public class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        public TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
	}
	
	TrieNode root;
	public HashMap<String, Integer> map;
	
    /** Initialize your data structure here. */
    public MapSum() {
    	root = new TrieNode();
    	map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        map.put(key, val);
        
		TrieNode cur = root;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (cur.children[c - 'a'] == null) {
				cur.children[c - 'a'] = new TrieNode();
			}
			cur = cur.children[c - 'a'];
			cur.startWith.add(key);
		}
    }
    
	public List<String> findByPrefix(String prefix) {
		List<String> result = new ArrayList<>();
		TrieNode cur = root;
		
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (cur.children[c - 'a'] == null) {
				return result;
			}
			cur = cur.children[c - 'a'];
		}
		result.addAll(cur.startWith);
		return result;
	}
	
    public int sum(String prefix) {
        List<String> keys = findByPrefix(prefix);
        HashSet<String> keyset = new HashSet<>(keys);
        int sum = 0;

        for (String s: keyset) {
        	sum += map.get(s);
        }
        
        return sum;
    }
}
