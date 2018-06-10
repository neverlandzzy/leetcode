package scalyr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	public static List<String> subStringCom(String s) {
		List<String> result = new ArrayList<>();
		
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				result.add(s.substring(i, j));
			}
		}
		
		return result;
	}

	public static void recursiveForLoop(int start, int n) {
		if (start == n) {
			return;
		}
		
		System.out.print(start + ", ");
		start++;
		recursiveForLoop(start, n);
	}

	
	public static void main(String[] args) throws IOException {
		
		//System.out.println(subStringCom("abcd"));
		
		for (int i = 0; i < 10; i++) {
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println("======");
		
		recursiveForLoop(0, 10);
		
	}
	
	
}
