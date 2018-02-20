package phone;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReverseHtml {
	// 1. LC 344, 345, 151, 186, 541
	
	// 1. LC 344 Reverse String  
	// Given s = "hello", return "olleh".
    public static String reverseString1_1(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));   
        }
        
        return sb.toString();
    }

    public static String reverseString1_2(String s) {
        int i = 0;
        int j = s.length() - 1;
        
        char[] array = s.toCharArray();
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
        
        return new String(array);
    }
    
    // 2. LC 345 Reverse Vowels of a String
    // Given s = "leetcode", return "leotcede". 只reverse 元音
    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        Set<Character> set = new HashSet<>();
        set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        
        int i = 0;
        int j = s.length() - 1;
        char[] charArray = s.toCharArray();
        
        while (i < j) {
            while (i < j && !set.contains(charArray[i])) {
                i++;
            }
            
            while (i < j && !set.contains(charArray[j])) {
                j--;
            }
            
            if (i < j) {
                char tmp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = tmp;
                i++;
                j--;
            }
        } 
        
        return new String(charArray);
        
    }
    
    // 3. LC 151 Reverse Words in a String
    // Given s = "the sky is blue", return "blue is sky the".
    
    public static String reverseWords(String s) {
    	StringBuilder sb = new StringBuilder();
    	
    	int end = s.length();
    	
    	for (int i = s.length() - 1; i >= 0; i--) {
    		if (s.charAt(i) == ' ') {
    			end = i;
    		} else if (i == 0 || s.charAt(i-1) == ' '){
    			if (sb.length() != 0) {
    				sb.append(' ');
    			}
    			sb.append(s.substring(i, end));
    		}
    	}
    	return sb.toString();
    }
    
    // 4. LC 186 Reverse Words in a String II -- in-place without allocating extra space
    // The input string does not contain leading or trailing spaces and the words are always separated by a single space.
    // Given s = "the sky is blue", return "blue is sky the".
    // 两步翻转法
    
    public static void reverseWordsII(char[] str) {
        reverse(str, 0, str.length - 1);
        
        int start = 0;
        
        for (int i = 0; i <= str.length; i++) {
        	if (i == str.length || str[i] == ' ') {
        		reverse(str, start, i - 1);
            	start = i + 1;
        	}
        }
    }

    private static void reverse(char[] str, int start, int end) {
    	while (start < end) {
    		char tmp = str[start];
    		str[start] = str[end];
    		str[end] = tmp;
    		start++;
    		end--;
    	}
    }
    
    // 5. LC 541 Reverse String II
    // Input: s = "abcdefg", k = 2, Output: "bacdfeg"
    
    public static String reverseStr(String s, int k) {
        boolean flag = true;
        
        char[] charArray = s.toCharArray();
        int n = s.length();
        
        int i = 0;
        int j = k - 1;
        
        while (j < n) {
        	if (flag) {
        		reverse(charArray, i, j);
        		i += k;
        		j += k;
        		flag = false;
        	} else {
        		flag = true;
        		i += k;
        		j += k;
        	}
        }
        
        if (flag && i < n) {
        	reverse(charArray, i, n - 1);
        }
        
        return new String(charArray);
    }
    
    // 6. Reverse HTML entity (以&开头;结尾, &;之间的不翻转)
    // e.g. &lt; 代表小于符号, 不能翻转  --> html&lt;123 => 321&lt;lmth
    // 
    // [思路]
    // http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=158403
    // http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=144324
    // 1. 就是遇到一个不是entity的就reverse存到一个list里面，如果是entity就原封不动的存到list，最后在从尾到头扫描这个list，得到结果
    // 2. html entity有一个特性：&开头 ;结尾， 用这个特性来traverse string，碰到&就把前面的substring翻转一下并存到list里面，然后查一下;在不在剩下的string 里面
    // 两步翻转法，类似LC 186 Reverse Words in a String II
    // [代码] http://www.1point3acres.com/bbs/thread-160879-2-1.html
    //
    // [Test Case]
    // e.g.    html&lt;123&yen;
    // list : [lmth,  &lt;  321, &yen;]
    // result:  &yen;321&lt;html.
    // "a&&nbsp;;b" -- 最内部的&;作为整体 --> "b;&nbsp;&a"
    // "1234&euro;" => &euro;4321"
    // "1234&euro" => "orue&4321"
    // "1234&euro;567" => "765&euro;4321"
    // "123&euro;432&euro;" ==> "&euro;234&euro;321"
    
    // Time: O(n) = O(n)(第一步翻转整个s) + O(2n)(第二步，扫描一遍s，最坏情况从0扫描到n-1后发现需要翻转，再翻转一遍，因此是2n)

    public static String reverseHtml(String s){
    	if (s == null || s.length() == 0) {
    		return s;
    	}
    	//s = s.trim();
    	
    	StringBuilder sb = new StringBuilder(s);
    	sb.reverse();
    	
    	int start = 0;
    	
    	for (int i = 0; i < sb.length(); i++) {
    		if (sb.charAt(i) == ';') {
    			start = i;
    		} 
    		
    		if (sb.charAt(i) == '&' && sb.charAt(start) == ';') {
    			reverse(sb, start, i);
        		start = i + 1;
    		}
    	}
    	
    	return sb.toString();
    }
    
    private static void reverse(StringBuilder sb, int start, int end) {    	
    	while (start < end) {
    		char tmp = sb.charAt(start);
    		sb.setCharAt(start, sb.charAt(end));
    		sb.setCharAt(end, tmp);
    		start++;
    		end--;
    	}
    }
    
    public static void main(String[] args) {
    	System.out.println("===== LC 344 ======");
		String test1 = "hello world";
		System.out.println(reverseString1_1(test1));
		System.out.println(reverseString1_2(test1));
		System.out.println();
		
    	System.out.println("===== LC 345 ======");
		String test2 = "hello";
		System.out.println(reverseVowels(test2));
		System.out.println();
		
    	System.out.println("===== LC 151 ======");
		String test3 = " the sky is blue ";
		System.out.println(reverseWords(test3));
		System.out.println();
		
    	System.out.println("===== LC 186 ======");
		char[] str = "the sky is blue".toCharArray();
		System.out.println(new String(str));
		reverseWordsII(str);
		System.out.println(new String(str));
		System.out.println();
		
    	System.out.println("===== LC 541 ======");
    	String test5 = "abcdefg";
		System.out.println(reverseStr(test5, 2));
		System.out.println();
		
    	System.out.println("===== Reverse HTML entity ======");
    	String test61 = "abcdefg";
    	String test62 = "html&lt;123&yen";
    	String test63 = "a&&nbsp;;b";
    	String test64 = "1234&euro;";
    	String test65 = "1234&euro";
    	String test66 = "1234&euro;567";
    	String test67 = "123&euro;432&euro;";
		System.out.println(reverseHtml(test61));
		System.out.println(reverseHtml(test62));
		System.out.println(reverseHtml(test63));
		System.out.println(reverseHtml(test64));
		System.out.println(reverseHtml(test65));
		System.out.println(reverseHtml(test66));
		System.out.println(reverseHtml(test67));
		
		System.out.println();

	}
}
