
public class App {
	
	/**
	 * Compare two version numbers version1 and version2.
	 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
	 * 
	 * You may assume that the version strings are non-empty and contain only digits and the '.' character.
	 * The '.' character does not represent a decimal point and is used to separate number sequences.
	 * 
	 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level 
	 * revision of the second first-level revision.
	 * 
	 * Here is an example of version numbers ordering:
	 * 0.1 < 1.1 < 1.2 < 13.37
	 */
	
    public static int compareVersion(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        
        int n1 = str1.length;
        int n2 = str2.length;
        int i = 0;
        int j = 0;
        
        while (i < n1 || j < n2) {
            int a1 = i >= n1 ? 0 : Integer.parseInt(str1[i]);
            int a2 = j >= n2 ? 0 : Integer.parseInt(str2[j]);
            
            if (a1 > a2) {
                return 1;
            } else if (a1 < a2) {
                return -1;
            }
            
            i++;
            j++;
        }
        
        return 0;
    }
    
	public static void main(String[] args) {
		String test1 = "01";
		String test2 = "1.1";
		String test3 = "1.3";
		String test4 = "1.10";
		
		System.out.println(compareVersion(test1,test2));
		System.out.println(compareVersion(test2,test3));
		System.out.println(compareVersion(test2,test4));
	}
}
