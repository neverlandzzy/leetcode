import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Solution {
	
    public static String toGoatLatin(String S) {
    	if (S == null || S.length() == 0) {
    		return S;
    	}
        Set<Character> vowel = new HashSet<>();
        vowel.add('a');
        vowel.add('e');
        vowel.add('i');
        vowel.add('o');
        vowel.add('u');
        
        String result = " ";
        String[] array = S.split(" ");
        
        for (int i = 0; i < array.length; i++) {
        	StringBuilder sb = new StringBuilder(array[i]);
        	if (!vowel.contains(Character.toLowerCase(sb.charAt(0)))) {
        		char c = sb.charAt(0);
        		sb.deleteCharAt(0);
        		sb.append(c);
        	} 
        	
        	sb.append("ma");
        	for (int k = 0; k <= i; k++) {
        		sb.append("a");
        	}
        	
        	result += sb.toString();
        	
        	result += " ";
        	
        }
        
        return result.trim();
    }
	
    /*
     * age[B] <= 0.5 * age[A] + 7
     * age[B] > age[A]
     * age[B] > 100 && age[A] < 100
     */
    
    public static int numFriendRequests(int[] ages) {
    	
    	Map<Integer, Integer> dup = new HashMap<>();
    	TreeMap<Integer, Integer> map = new TreeMap<>();
    	Arrays.sort(ages);
    	int n = ages.length;
    	int result = 0;
    	for (int i = 0; i < n; i++) {   		
    		map.put(ages[i], i + 1);
    		dup.put(ages[i], dup.getOrDefault(ages[i], 0) + 1);
    	}
    	
    	System.out.println(map);
    	
    	for (int i = 0; i < n; i++) {
    		int upper = map.floorKey(ages[i]);
    		int lower = map.ceilingKey(ages[i] / 2 + 8);
    		
    		result += map.get(upper) - map.get(lower);

    	}
    	
    	for (int key: dup.keySet()) {
    		if (dup.get(key) > 1) {
    			int val = dup.get(key);
    			if (key > key / 2 + 7) {
    				result += val;
    			}
    		}
    	}
    	//System.out.println(dup);
    	return result;
    	/*
        int result = 0;
        int n = ages.length;
        
        Arrays.sort(ages);
        
        for (int i = 1; i < n; i++) {
        	for (int j = 0; j < i;j++) { 
        		
    			if (valid(ages[i], ages[j])) {
    				result++;
        		}
    			if (ages[i] == ages[j] && valid(ages[i], ages[j])) {
    				
    				result++;
    			}
        	}
        }
        
        return result;
        */
    }
    
    private static boolean valid(int a, int b) {
    	if (b > 100 && a < 100) {
    		return false;
    	}
    	
    	if (b > a) {
    		return false;
    	}
    	
    	if (b <= a / 2 + 7) {
    		return false;
    	}
    	
    	return true;
    }
    
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        return 0;
    }
    
	public static void main(String[] args) {
		/*
		String test11 = "I speak Goat Latin";
		
		String test12 = "The quick brown fox jumped over the lazy dog";
		
		String test13 = "";
		System.out.println(toGoatLatin(test11));
		System.out.println(toGoatLatin(test12));
		System.out.println(toGoatLatin(test13));
		*/
		
		int[] test21 = {16, 16};
		int[] test22 = {16, 17, 18};
		int[] test23 = {20,30,100,110,120};
		int[] test24 = {73,106,39,6,26,15,30,100,71,35,46,112,6,60,110};
		//System.out.println(numFriendRequests(test21));
		//System.out.println(numFriendRequests(test22));
		//System.out.println(numFriendRequests(test23));
		System.out.println(numFriendRequests(test24));
		
	}
}
