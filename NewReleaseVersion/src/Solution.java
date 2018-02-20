
public class Solution {
	public static String newVersion(String release1, String release2) {
		int m = release1.length();
		int n = release2.length();
		
		int i = 0;
		int j = 0;
		
		while (i < m && j < n) {
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			
			while (i < m && release1.charAt(i) != '.') {
				sb1.append(release1.charAt(i));
				i++;
			}
			
			while (j < n && release2.charAt(j) != '.') {
				sb2.append(release2.charAt(j));
				j++;
			}
			
			if (Integer.valueOf(sb1.toString()) > Integer.valueOf(sb2.toString())) {
				return release1;
			} else if (Integer.valueOf(sb1.toString()) < Integer.valueOf(sb2.toString())){
				return release2;
			} else {				
				i++;
				j++;
			}

		}
		
		if (m > n) {
			return release1;
		} else {
			return release2;
		}
	}
	
	
	public static void main(String[] args) {
		String rel1 = "1.01.34.21.100";
		String rel2 = "1.2.00";
		String rel3 = "10.0.0.1";
		String rel4 = "10.0.0.1.1";

		
		System.out.println(newVersion(rel1, rel2));
		System.out.println(newVersion(rel2, rel3));
		System.out.println(newVersion(rel1, rel3));
		System.out.println(newVersion(rel3, rel4));
	}
}
