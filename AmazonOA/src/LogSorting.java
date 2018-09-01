import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class LogSorting {
	
	/*
	 * 1. Every line is a space-delimited list of strings;
	 * 2. All lines begin with an alphanumeric identifier;
	 * 3. There will be no lines consisting only of an identifier;
	 * 4. After the alphanumeric identifier, a line will consist of either:
	 *    a list of words using only English letters;
	 *    or list of only integers.
	 * 
	 * Rules:
	 * 1. Reorder the data such that all of the lines with words are at the top of the log file. the lines with words are ordered lexicographically, 
	 *    ignoring the identifier except in the case of ties. 
	 * 2. In the case of ties, the identifier is used to order lexicographically. 
	 * 3. Alphanumeric should be sorted in ASCII order(numbers come before letters)
	 * 4. The identifier must still be part of the lines in the output Strings. 
	 * 5. Lines with integers must be left in the original order they were in the file.
	 * 
	 * Input:
	 * logFileSize, an integer representing the number of log lines;
	 * logLines, a list of strings representing the log file.
	 * 
	 * Example:
	 * 1. 
	 * Input:
	 * logFileSize = 5;
	 * logLines = 
	 * [a1 9 2 3 1]
	 * [g1 Act car]
	 * [zo4 4 7]
	 * [ab1 off KEY dog]
	 * [a8 act zoo]
	 * 
	 * Output
	 * [g1 Act car]
	 * [a8 act zoo]
	 * [ab1 off KEY dog]
	 * [a1 9 2 3 1]
	 * [zo4 4 7]
	 * 
	 * 2. 
	 * Input:
	 * "fhie 1df8 sfds"
	 * "fdsf 2def sees"
	 * "efe2 br9o fjsd"
	 * “asd1 awer jik9"
	 * 
	 * output是：
	 * “asd1 awer jik9"
	 * "efe2 br9o fjsd"
	 * "fhie 1df8 sfds"
	 * "fdsf 2def sees"
	 */
	public static List<String> reorderLines(int logFileSize, List<String> logLines) {
		Collections.sort(logLines, new Comparator<String>() {
			public int compare(String s1, String s2) {
				String[] str1 = s1.split(" ");
				String[] str2 = s2.split(" ");
				
				if (Character.isDigit(str1[1].charAt(0)) && Character.isDigit(str2[1].charAt(0))) {
					return 1;
				} else if (Character.isDigit(str1[1].charAt(0))) {
					return 1;
				} else if (Character.isDigit(str2[1].charAt(0))) {
					return -1;
				} else {
					int min = Math.min(str1.length, str2.length);
					for (int i = 1; i < min; i++) {
						if (!str1[i].equals(str2[i])) {
							return str1[i].compareTo(str2[i]);
						}
					}
					
					if (str1.length < str2.length) {
						return -1;
					} else if (str1.length > str2.length) {
						return 1;
					}
					
					return str1[0].compareTo(str2[0]);
				} 
			}
		});
		
		return logLines;
	}
	
	public static void main(String[] args) {
		List<String> test1 = new ArrayList<>(Arrays.asList("a1 9 2 3 1", "g1 Act car", "zo4 4 7", "ab1 off KEY dog", "a8 act zoo"));
		System.out.println(reorderLines(5, test1));
		
		List<String> test2 = new ArrayList<>(Arrays.asList("fhie 1df8 sfds", "fdsf 2def sees", "efe2 br9o fjsd", "asd1 awer jik9"));
		System.out.println(reorderLines(4, test2));
		
		List<String> test3 = new ArrayList<>(Arrays.asList("a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off KEY dog", "a8 act zoo", "g2 act car day"));
		System.out.println(reorderLines(6, test3));
	}
}
