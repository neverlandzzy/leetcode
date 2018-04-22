import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	
    public static int[] shortestToChar(String S, char C) {
        int n = S.length();
        int[] result = new int[n];
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
        	char c = S.charAt(i);
        	if (c == C) {
        		list.add(i);
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	int dis = Integer.MAX_VALUE;
        	
        	for (int l: list) {
        		dis = Math.min(dis, Math.abs(i - l));
        	}
        	
        	result[i] = dis;
        }
        
        return result;
    }
	
    public static int flipgame(int[] fronts, int[] backs) {
    	
    	Set<Integer> set = new HashSet<>();
    	Set<Integer> same = new HashSet<>();
    	
    	for (int i = 0; i < fronts.length; i++) {
    		set.add(fronts[i]);
    		set.add(backs[i]);
    		if (fronts[i] == backs[i]) {
    			same.add(fronts[i]); 
    		}
    	}
    	
    	for (int i = 1; i <= 2000; i++) {
    		if (set.contains(i) && !same.contains(i)) {
    			return i;
    		}
    	}
    	
    	return 0;
    }
 
    public static int minimumLengthEncoding(String[] words) {
    	Set<String> set = new HashSet<>();
    	int counter = 0;
    	
    	for (String w: words) {
    		boolean flag = false;
    		
    		if (set.isEmpty()) {
    			set.add(w);
    			counter += w.length();
    			continue;
    		}   		

			for (String s: set) {
				int wL = w.length();
				int sL = s.length();
				if (wL >= sL) {
					if (w.substring(wL - sL).equals(s)) {
						counter -= s.length();
						counter += w.length();
						set.remove(s);
						set.add(w);
						flag = true;
						break;
					}
				} else {
					if (s.substring(sL - wL).equals(w)) {
						flag = true;
						break;
					}
				}
			}
			
			if (!flag) {
				counter += w.length();
				set.add(w);
			}
    		
    	}

    	return counter + set.size();
    }

    public static int numFactoredBinaryTrees(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        int result = 0;
        
        int M = 100000007;
        
        Arrays.sort(A);
        Arrays.fill(dp, 1);
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
        	map.put(A[i], i);
        }
        
        for (int i = 0; i < n; i++) {
        	Set<Integer> set = new HashSet<>();
        	for (int j = 0; j < i; j++) {
        		if (set.contains(A[j])) {
        			continue;
        		}
        		
        		if (map.containsKey(A[i] / A[j]) && A[i] % A[j] == 0) {
        			set.add(A[j]);
        			set.add(A[i]/A[j]);

        			dp[i] = dp[i] % M + ((dp[j] % M) * (dp[map.get(A[i]/A[j])] % M) * 2) % M;
        			
        		}
        	}
        }
        
        for (int d: dp) {
        	//System.out.print(d + ", ");
        	result += d;
        }
        //System.out.println();
        
        return result;
    }
    
	public static void main(String[] args) {
		/*
		String test11 = "loveleetcode";
		int[] result11 = shortestToChar(test11, 'e');
		print(result11);
		
		
		int[] test211 = {1, 2, 4, 4, 7};
		int[] test212 = {1, 3, 4, 1, 3};
		System.out.println(flipgame(test211, test212));
		int[] test221 = {1, 1};
		int[] test222 = {1, 2};
		System.out.println(flipgame(test221, test222));

		
		
		String[] test31 = {"time", "me", "bell"};
		System.out.println(minimumLengthEncoding(test31));
		
		String[] test32 = {"t"};
		System.out.println(minimumLengthEncoding(test32));
		
		String[] test33 = {"me", "time"};
		System.out.println(minimumLengthEncoding(test33));
		*/
		
		int[] test41 = {2, 4, 5, 10};
		int[] test42 = {2, 3, 6, 18};
		System.out.println(numFactoredBinaryTrees(test41));
		System.out.println(numFactoredBinaryTrees(test42));
		
	}
	
	private static void print(int[] array) {
		for (int i: array) {
			System.out.print(i + ", ");
		}
		
		System.out.println();
	}
}
