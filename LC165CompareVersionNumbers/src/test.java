
public class test {
	public static void main(String[] args) {
		
		String test1 = "William Shakespeare";
		String test2 = "I am a weakish speller";
		
		String test3 = "Slient";
		String test4 = " li sten";
		String test5 = "sliet";
		
		System.out.println(isAnagram(test1, test2));
		System.out.println(isAnagram(test3, test4));
		System.out.println(isAnagram(test3, test5));
		

		
	}
	
	public static boolean isAnagram (String str1, String str2) {
		
		char[] charArray1 = new char[26];
		char[] charArray2 = new char[26];
		int k;
		
		for (int i = 0; i < str1.replaceAll("\\s+", "").length(); i++) {
			k = str1.replaceAll("\\s+", "").toLowerCase().charAt(i) - 97;
			charArray1[k]++;
		}
		
		for (int i = 0; i < str2.replaceAll("\\s+", "").length(); i++) {
			k = str2.replaceAll("\\s+", "").toLowerCase().charAt(i) - 97;
			charArray2[k]++;
		}

		for (int i = 0; i < 26; i++) {
			if (charArray1[i] != charArray2[i]) {
				return false;
			}
		}
		
		return true;
	}
}
