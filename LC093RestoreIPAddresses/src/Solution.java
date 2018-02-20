import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string containing only digits, restore it by returning all possible 
	 * valid IP address combinations.
	 * 
	 * For example:
	 * Given "25525511135",
	 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	 */
	
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        StringBuilder address = new StringBuilder();
        
        if (s.length() > 12) {
            return result;
        }
        
        dfs(result, s, address, 0);
        
        return result;
    }
    
    private static void dfs(List<String> result, String s, StringBuilder sb, int counter) {
        if (counter == 3 && isValidIP(s)) {
            sb.append(s);
            result.add(sb.toString());
            return;
        } 
        
        if (counter > 4) {
            return;
        }
        
        int len = sb.length();
        
        for (int i = 1; i <= 3 && i < s.length(); i++) {
            String tmp = s.substring(0, i);
            if (isValidIP(tmp)) {
                sb.append(tmp).append(".");
                dfs(result, s.substring(i), sb, counter + 1);
                sb.delete(len, sb.length());
            }
        }
        
    }
   
    
    private static boolean isValidIP(String s) {
    	
    	if (s == null || s.length() == 0 ||s.length() > 3) {
    		return false;
    	}
    	
    	if (s.charAt(0) == '0') {
    		if (s.length() == 1) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	if (Integer.parseInt(s) > 0 && Integer.parseInt(s) <= 255) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static void main(String[] args) {
		String test = "25525511135";
		String test2 = "0279245587303";
		String test3 = "11111111111111111111111111111";
		System.out.println(restoreIpAddresses(test));

	}
}
