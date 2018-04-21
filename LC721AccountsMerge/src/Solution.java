import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	
	/*
	 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest 
	 * of the elements are emails representing emails of the account.
	 * 
	 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to 
	 * both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
	 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
	 * 
	 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the 
	 * elements are emails in sorted order. The accounts themselves can be returned in any order.
	 * 
	 * Example 1:
	 * Input: 
	 * accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", 
	 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
	 * 
	 * Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", 
	 * "mary@mail.com"]]
	 * 
	 * Explanation: 
	 * The first and third John's are the same person as they have the common email "johnsmith@mail.com".
	 * The second John and Mary are different people as none of their email addresses are used by other accounts.
	 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
	 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
	 */
	
	// https://leetcode.com/problems/accounts-merge/discuss/109160/HashMap-plus-union-found-solution-using-Java-programming!
	
	static class UnionFind {
		Map<Integer, Integer> map;
		
		public UnionFind(int n) {
			map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				map.put(i, i);
			}
		}
		
		public int find(int x) {
			if (map.get(x) == x) {
				return x;
			}
			int parent = find(map.get(x));
			map.put(x, parent);
			return parent;
		}
		
		public void union(int x, int y) {
			int pX = find(x);
			int pY = find(y);
            if(pX != pY) {
                map.put(pX, pY);
            }
		}
	}
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) {
        	return result;
        }
        int n = accounts.size();
        
        UnionFind uf = new UnionFind(n);
        // emailMap中记录的是email和用户index的映射。因为用户可能重名，所以不能用名字做key而改用accounts中的index做为key
        // emailMap.valueSet()中的(o ~ n-1)对应了accounts中的(0 ~ n-1)个用户。遍历邮箱，发现相同邮箱对应不同id时，说明这两个
        // id是同一个人，于是在union-find中将两个id union起来
        Map<String,Integer> emailMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	List<String> account = accounts.get(i);
        	for (int j = 1; j < account.size(); j++) {
        		String email = account.get(j);
        		if (emailMap.containsKey(email)) {
        			// 当前遍历到的email已经在map中，说明之前遍历到的i和当前preId是同一个用户，所以union起来
        			int preId = emailMap.get(email);
        			uf.union(preId, i);
        		} else {
        			emailMap.put(email, i);
        		}
        	}
        }
        
        Map<Integer, Set<String>> userMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	int parentI = uf.find(i);
        	if (!userMap.containsKey(parentI)) {
        		userMap.put(parentI, new HashSet<>());
        	}
        	
        	for (int j = 1; j < accounts.get(i).size(); j++) {
        		userMap.get(parentI).add(accounts.get(i).get(j));
        	}
        }
        
        for (Map.Entry<Integer, Set<String>> entry: userMap.entrySet()) {
        	List<String> list = new ArrayList<>();
        	
        	
        	for (String emails: entry.getValue()) {
        		list.add(emails);
        	}
        	
        	Collections.sort(list);
        	
        	list.add(0, accounts.get(entry.getKey()).get(0));
        	result.add(list);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
		accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
		accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
		accounts.add(Arrays.asList("Mary", "mary@mail.com"));
		
		System.out.println(accountsMerge(accounts));
	}
}
