import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Solution {
    public static String shiftingLetters(String S, int[] shifts) {
    	for (int i = shifts.length - 1; i >= 0; i--) {
    		shifts[i] = shifts[i] % 26;
    		
    		if (i < shifts.length - 1) {
    			shifts[i] += shifts[i + 1];
    			shifts[i] = shifts[i] % 26;
    		}
    	}
    	

    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < S.length(); i++) {
    		sb.append(shift(S.charAt(i), shifts[i]));
    	}
    	
    	return sb.toString();
    }
    
    private static char shift(char c, int i) {
    	int k = i % 26;
    	
    	if (c + k > 'z') {
    		return (char)(c + k - 26);
    	}
    	
    	return (char)(c + k);
    }
    
    
    public static int maxDistToClosest(int[] seats) {
        int max = 0;
        int count = 0;
        
        int n = seats.length;
        
        for (int i = 0; i < n; i++) {
        	if (seats[i] == 0) {
        		count++;
        	} else {
        		count = 0;
        	}
        	
        	if (count == i + 1 || (i == n - 1 && count > 0)) {
        		max = Math.max(max, count);
        	} else {
        		max = Math.max(max, (count - 1) / 2 + 1);
        	}
        }
       
        return max;
    }
    

    
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        
        int n = quiet.length;
        
        for (int i = 0; i < n; i++) {
        	map.put(i, new TreeSet<Integer>(new Comparator<Integer>(){
        		public int compare(Integer i1, Integer i2) {
        			return quiet[i2] - quiet[i1];
        		}
        	}));
        	map.get(i).add(i);
        }

        for (int[] r: richer) {
        	map.get(r[1]).add(r[0]);
        }
        
        for (int key: map.keySet()) {
        	
        }
        
        int[] result = new int[n];
        return result;
    }
    
    public static void main(String[] args) {
    	/*
    	String s11 = "abc";
    	int[] shifts11 = {3, 5, 9};
    	System.out.println(shiftingLetters(s11, shifts11));

    	
    	int[] test21 = {1,0,0,0,1,0,1};
    	int[] test22 = {1, 0, 0, 0};
    	int[] test23 = {0, 0, 0, 1};
    	int[] test24 = {0, 0, 0, 1, 0, 0, 0, 1};
    	int[] test25 = {0, 1};
    	int[] test26 = {1, 0, 1};
    	int[] test27 = {0,1,0,0,0,0};
    	int[] test28 = {0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,0,1};
    
    	System.out.println(maxDistToClosest(test21));
    	System.out.println(maxDistToClosest(test22));
    	System.out.println(maxDistToClosest(test23));
    	System.out.println(maxDistToClosest(test24));
    	
    	System.out.println(maxDistToClosest(test25));
    	System.out.println(maxDistToClosest(test26));
    	
    	System.out.println(maxDistToClosest(test27));
    	System.out.println(maxDistToClosest(test28));
    	*/
    	int[][] richer1 = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
    	int[] quiet1 = {3,2,5,4,6,1,7,0};
    	
    	int[] result31 = loudAndRich(richer1, quiet1);
    	print(result31);
    	
	}
    
    private static void print(int[] nums) {
    	for (int i: nums) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    }
}
