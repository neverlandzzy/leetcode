
public class App {
	
	/*
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
	public static void main(String[] args) {
		String test1 = "01";
		String test2 = "1.1";
		String test3 = "1.3";
		String test4 = "1.10";
		
		System.out.println(compareVersion(test1,test2));
		System.out.println(compareVersion(test2,test3));
		System.out.println(compareVersion(test2,test4));
	}
	
	

	public static int compareVersion(String version1, String version2) {
		int count1 = 0;
		int count2 = 0;
		
		String[] v1 = version1.split("\\.");
		String[] v2 = version2.split("\\.");


		
		int k = 0;
		while(v1[0].charAt(k) == '0' && (k < v1[0].length()-1)) {
			k++;
		}
		
		if (v1[0].length()-k == 0) {
			k = 0;
		}
		char[] v1L = new char[v1[0].length()-k];
		

		for (int j = 0; j < v1[0].length()-k;j++) {
			v1L[j] = v1[0].charAt(k);
		}
	
		k = 0;
		while((v2[0].charAt(k) == '0') && (k < v2[0].length()-1)) {
			k++;
		}
		if (v2[0].length()-k == 0) {
			k = 0;
		}
		char[] v2L = new char[v2[0].length()-k];
		
		for (int j = 0; j < v2[0].length()-k;j++) {
			v2L[j] = v2[0].charAt(k);
		}

		char[] v1R;
		char[] v2R;
		char[] zero = {'0'};
		
		if(v1.length == 2) {
			v1R = v1[1].toCharArray();
		} else {
			v1R = zero;
		}

		if(v2.length ==2) {
			v2R = v2[1].toCharArray();
		} else {
			v2R = zero;
		}
		
			
		if (v1L.length > v2L.length) {
			return 1;
		} else if (v1L.length < v2L.length) {
			return -1;
		} else {
			for (int i = 0; i < v1L.length; i++) {
				count1 = count1*10 + (int)v1L[i]; 
				count2 = count2*10 + (int)v2L[i];
			}
		}
		
		if (count1 > count2) {
			return 1;
		} else if (count1 < count2) {
			return -1;
		} else {
			for (int i = 0; i < Math.min(v1R.length, v2R.length); i++) {
				if((int)v1R[i] > (int)v2R[i]) {
					return 1;
				} else if((int)v1R[i] < (int)v2R[i]) {
					return -1;
				}
			}
			
			if (v1R.length > v2R.length) {
				return 1;
			} else {
				return 0;
			}
		}


	}

}
