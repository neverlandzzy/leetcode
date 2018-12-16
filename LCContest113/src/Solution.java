import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	
    public static String largestTimeFromDigits(int[] A) {

    	StringBuilder hour = new StringBuilder();
    	StringBuilder min = new StringBuilder();
    	String time = "";
    	List<Integer> list = new ArrayList<>();
    	
    	for (int a: A) {
    		list.add(a);
    	}
    	
    	for (int i = 0; i < list.size(); i++) {
    		for (int j = 0; j < list.size(); j++) {
    			if (i == j) {
    				continue;
    			}
    			hour.append(list.get(i));
    			hour.append(list.get(j));
    			list.remove(i);
    			list.remove(j);
    			
    			for (int m = 0; m < list.size(); m++) {
    				for (int n = 0; n < list.size(); n++) {
    	    			if (i == j) {
    	    				continue;
    	    			}    	    			
    	    			min.append(list.get(m));
    	    			min.append(list.get(n));
    	    			list.remove(m);
    	    			list.remove(n);
    				}
    			}
    			
    			list.clear();
    	    	for (int a: A) {
    	    		list.add(a);
    	    	}
    	    	
    	    	System.out.println(hour.toString());
    	    	System.out.println(min.toString());
    		}
    	}
 
        return "";
    }
    
    private static boolean validTime(String hour, String min) {
    	if (Integer.valueOf(s) >= 24) {
    		return false;
    	}
    	
    	return true;
    }

    
	public static void main(String[] args) {
		int[] test11 = {1, 2, 3, 4};
		int[] test12 = {5, 5, 5, 5};
		int[] test13 = {4, 1, 0, 0};
		
		System.out.println(largestTimeFromDigits(test11));
		System.out.println(largestTimeFromDigits(test12));
		System.out.println(largestTimeFromDigits(test12));
	}
}
