
public class RemoveContinuousSameChars {
	
	public static String removeSameChars(String s) {
		for (int i = 0, j = 0; j <= s.length(); j++) {
			if (j < s.length() && s.charAt(i) == s.charAt(j)) {
				continue;
			}
			
			if (j - i >= 3) {
				return removeSameChars(s.substring(0,  i) + s.substring(j));
			} else {
				i = j;
			}
		}
		
		return s;
	}	
	
	public static void main(String[] args) {
		String s1 = "AABBBBCCDDDDLS";
		String s2 = "AABBBB";
		String s3 = "CCC";
		System.out.println(removeSameChars(s1));
		System.out.println(removeSameChars(s2));
		System.out.println(removeSameChars(s3));
	}
}
