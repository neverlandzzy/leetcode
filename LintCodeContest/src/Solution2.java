import java.util.Arrays;


public class Solution2 {
	
	
    public static String getMinString(String s, int k) {
        int n = s.length();
        String result = s;
        
        for (int len1 = 1; len1 <= k; len1++) {
        	for (int i = 0; i + len1 <= n; i++) {
        		for (int len2 = 1; len2 <= k; len2++) {
        			for (int j = i + len1; j + len2 <= n; j++) {  
        				if (s.substring(i, i + len1).equals(s.substring(j, j + len2))) {
        					continue;
        				}
        				
        				if (s.substring(j, j + len2).compareTo(s.substring(i, i + len1)) > 0) {
        					continue;
        				}
        				
        				String tmp = s.substring(0, i) + s.substring(j, j + len2) + s.substring(i + len1, j) + s.substring(i, i + len1) + s.substring(j + len2);
        				if (result.compareTo(tmp.toString()) > 0) {
        					result = tmp.toString();
        				}
    				}
    			}
    		}
    		
    	}
    	
    	return result;
    }
   
	
    public static void main(String[] args) {
		System.out.println(getMinString("cab", 2));
		//System.out.println(getMinString("ddccbbaa", 4));
		//System.out.println(getMinString("ajzynfwp", 4));
		
		
		//System.out.println(getMinString("sakaagaaaaaaaaaaaiaaeaaahaaaaaeaaaqaaaaaatyoayakaaaasaaaaaaaaaaauaaaaasaaaoanaaaaaacaafagaaaaavayoaaaaaaaanawhaaauaazaaaxaaaaaaaaaaordaaaaaaxjaaaaaamajaaaapaafitaaaaahvaakaaogbaaaaaaaaqadaagaawaxuaaaaaamyaoaa", 10));
	}
    
    
}
