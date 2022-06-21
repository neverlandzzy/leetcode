import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution {
	/**
	 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
	 * The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
	 * 
	 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions 
	 * like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) 
	 * by asking as few questions as possible (in the asymptotic sense).
	 * 
	 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), 
	 * your function should minimize the number of calls to knows.
	 * 
	 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. 
	 * If there is no celebrity, return -1.
	 */
	
	
	// 2 pass: Time: O(n), Space: O(1)
	// https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass
	public static int findCelebrity(int n) {
		int candidate = 0;
		
		// 第一遍扫，如果knows(candidate, i) == true，说明当前candidate不合格，换成i继续检查
		// i之前的数同样不合格，因为candidate不认识他们
		for (int i = 1; i < n; i++) {
			if (knows(candidate, i)) {
				candidate = i;
			}
		}
		
		// 第二遍扫描，检查candidate是否合格
		for (int i = 0; i < n; i++) {
			if (candidate != i) {
				if (knows(candidate, i) || !knows(i, candidate)) {
					return -1;
				}
			}
		}
		
		return candidate;
	}
	
	
	// brute-force
	/*
    public static int findCelebrity(int n) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	map.put(i, new HashSet<>());
        }
            
        for (int i = 0; i < n; i++) {
            
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                if (knows(i, j)) {
                    map.get(i).add(j);
                }
            }
        }

        int result = -1;
        int counter = 0;
        for (int key: map.keySet()) {
            if (map.get(key).isEmpty()) {
                result = key;
                counter++;
                if (counter > 1) {
                    return -1;
                }
            }
        }
        
        for (int key: map.keySet()) {
            if (result != key) {
                if (!map.get(key).contains(result)) {
                    return -1;
                }
            }
        }      
        return result;
    }
    */

}
