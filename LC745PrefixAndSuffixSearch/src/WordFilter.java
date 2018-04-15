

public class WordFilter {
	/*
	 * Given many words, words[i] has weight i.
	 * 
	 * Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with 
	 * given prefix and suffix with maximum weight. If no word exists, return -1.
	 * 
	 * Examples:
	 * Input:
	 * WordFilter(["apple"])
	 * WordFilter.f("a", "e") // returns 0
	 * WordFilter.f("b", "") // returns -1
	 * 
	 * Note:
	 * 1. words has length in range [1, 15000].
	 * 2. For each test case, up to words.length queries WordFilter.f may be made.
	 * 3. words[i] has length in range [1, 10].
	 * 4. prefix, suffix have lengths in range [0, 10].
	 * 5. words[i] and prefix, suffix queries consist of lowercase letters only.
	 */

	// Solution 1: WordFilter: O(n * L^3) (L: maximum length of a word) --> the time complexity of map.put/get is O(Key.Length)
	//             f         : O(L)
	//             Space     : O(n * L^2)
	// 适合f()多的系统
	/*
	Map<String, Integer> map;
    
	public WordFilter(String[] words) {
		map = new HashMap<>();
		for (int w = 0; w < words.length; w++) {
			for (int i = 0; i <= words[w].length(); i++) {
				for (int j = 0; j <= words[w].length(); j++) {
					map.put(words[w].substring(0, i) + "#" + words[w].substring(j), w);
				}
			}
		}
    }
    
    public int f(String prefix, String suffix) {
    	return (map.containsKey(prefix + "#" + suffix))? map.get(prefix + "#" + suffix) : -1;
    }
    */
	
	
	// Solution 2: Trie
	// 类似于解法1， 将apple存成 '{apple', 'e{apple', 'le{apple', 'ple{apple', 'pple{apple', 'apple{apple'...存入Trie
	// 查找时候找 后缀 + { + 前缀  // 用'{'是因为在ASCII表中 '{'在'z'后面，因此用一个children = new TrieNode[27]即可
	// 时间复杂度：WordFilter() -> O(n * L^2), f(L) ->  O(L) (L: maximum length of a word) 
	// 空间复杂度：O(n * L^2)

	class TrieNode {
		TrieNode[] children;
		int weight;
		
		TrieNode() {
			children = new TrieNode[27];
			weight = 0;
		}
	}
	
	TrieNode root;
	
    public WordFilter(String[] words) {
        root = new TrieNode();
        
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            
            
            for (int j = w.length(); j >= 0; j--) {
                TrieNode node = root;
                String s = w.substring(j) + "{" + w;
                
                for (int k = 0; k < s.length(); k++) {
                    char c = s.charAt(k);
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new TrieNode();
                    }
                    node = node.children[c - 'a'];
                    node.weight = i;
                }
            }
        }
    }
    /*
	public WordFilter(String[] words) {
		root = new TrieNode();
		
		for (int w = 0; w < words.length; w++) {
			String word = words[w] + "{";
			for (int i = 0; i < word.length(); i++) {
				TrieNode node = root;
				node.weight = w;
				
				for (int j = i; j < word.length() * 2 - 1; j++) {
					int key = word.charAt(j % word.length()) - 'a';
					if (node.children[key] == null) {
						node.children[key] = new TrieNode();
					}
					node = node.children[key];
					node.weight = w;
				}
			}
		}
	}
	*/
	public int f(String prefix, String suffix) {
		TrieNode node = root;
		
		String s = suffix + "{" + prefix;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (node.children[c - 'a'] == null) {
				return -1;
			}
			node = node.children[c -'a'];
		}
		
		return node.weight;
	}
	
	/*
	// Solution 3: WordFilter: O(1)
	//             f         : O(N*L)
	//             Space     : O(1)
	// 适合input多变的系统，LC会TLE
	String[] input;
    public WordFilter(String[] words) {
        input = words;
    }
    
    public int f(String prefix, String suffix) {
        for (int i = input.length - 1; i >= 0; i--) {
        	if (input[i].startsWith(prefix) && input[i].endsWith(suffix)) {
        		return i;
        	}
        }
        return -1;
    }
    */
}

