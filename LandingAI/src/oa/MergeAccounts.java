package oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeAccounts {
	/*
	 * http://www.1point3acres.com/bbs/thread-299621-1-1.html
	 * 
	 * 给一组list：(元素代表一个用户的信息）
	 * [（"aaa", "123456", "abc@gmail.com"）, ("bb", "abc@gmail.com"), ("c", "123456")]
	 * 找出所有相同的用户，这里的三个用户都为同一个用户， 因为前两个邮箱相同，最后一个和第一个手机号相同。
	 * 输出答案[[0,1,2]](相同的用户group到一起）。
	 * 
	 * 基本类似LC721。不同之处在于：
	 * 1. 手机号，可以和email一样处理；
	 * 2. 同一个用户可以有不同名字；
	 * 3. 输出用户序号的group;
	 */
	
	
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
    public static List<List<Integer>> accountsMerge(List<List<String>> accounts) {
        List<List<Integer>> result = new ArrayList<>();
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
        	for (int j = 0; j < account.size(); j++) {
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
        
        Map<Integer, List<Integer>> userMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	int parentI = uf.find(i);
        	if (!userMap.containsKey(parentI)) {
        		userMap.put(parentI, new ArrayList<>());
        	}
        	userMap.get(parentI).add(i);
        }
        
        for (Map.Entry<Integer, List<Integer>> entry: userMap.entrySet()) {
        	List<Integer> list = new ArrayList<>();
        	list.addAll(entry.getValue());
        	
        	//Collections.sort(list);
        	result.add(list);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList("aaa", "123456", "abc@gmail.com"));
		accounts.add(Arrays.asList("bb", "abc@gmail.com"));
		accounts.add(Arrays.asList("c", "123456"));
		accounts.add(Arrays.asList("d", "7123456"));
		
		System.out.println(accountsMerge(accounts));
	}
}
