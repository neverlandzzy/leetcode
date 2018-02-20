
public class Solution {
	/*
	 * A password is considered strong if below conditions are all met:
	 * 
	 * It has at least 6 characters and at most 20 characters.
	 * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
	 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other 
	 * conditions are met).
	 * Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make 
	 * s a strong password. If s is already strong, return 0.
	 * 
	 * Insertion, deletion or replace of any one character are all considered as one change.
	 */
	
	// https://discuss.leetcode.com/topic/63854/o-n-java-solution-by-analyzing-changes-allowed-to-fix-each-problem
	// http://bookshadow.com/weblog/2016/10/21/leetcode-strong-password-checker/
	
	/*
	 * 贪心算法：尽量通过一个fix能解决多个问题
	 * 
	 * 修复问题1：
	 * 		若s.length() < 6: (insert, any position)
	 *		若s.length() > 20: (delete, any position)
	 * 修复问题2：
	 *      (insert, any position) or (replace, any position)
	 * 修复问题3：
	 *      (replace, repeating characters) or (insert, repeating characters) or (delete, repeating characters). 
	 *      对于修复问题3的方法中：
	 *      replace 一次可以修复最多5个重复字符
	 *      insert  一次可以修复最多4个重复字符
	 *      delete  一次可以修复最多3个重复字符
	 *      因此优先选择： replace > insert > delete
	 *      
	 * 因此，优先选择replace，可以同时修复问题2和3， 当问题1，3同时存在时，当s.length() < 6时，才考虑选择insert, 当s.length() > 20时，才考虑delete
	 * 
	 * 过程：
	 * 1. 统计s的问题：
	 *    length: 字符长度
	 *    missingTypeCount:字符类型数目，lower-case, upper-case, digit 各算一种
	 *    repeat[]: 重复字符数组：repeat[i] = k -> 从i起，有k个重复字符
	 * 2. 当length < 6时：
	 *    先通过missingTypeCount步的insert操作修复问题2， 此时新的length = missingTypeCount + n, 如果还不够6，再补上6 - (missingTypeCount + n)即可
	 *    这种情况中，问题3不会对结果造成影响，因为若有5个重复字符，则missingTypeCount = 2， 通过2步insert操作可解决重复问题，若有4个或4个以下重复，则通过1步
	 *    insert操作可以解决重复问题
	 * 3. 否则(length >= 6)：
	 *    需要删除 overLength = max(length - 20, 0) 个字符
	 *    若有需要删除字符时，
  	 *    （1）先将repeat[]中大于2的元素通过一步或两步的删除操作转化成3m + 2的形式，例如：25 -> 23 (3 * 7 + 2), 24 -> 23
  	 *    （2）然后若overLength依然大于0再将这些元素通过m步一直删除到2 (因为对于删除操作，每次最多可以修复3个重复字符)
	 *                             
	 */
    public static int strongPasswordChecker(String s) {
    	int n = s.length();
        int result = 0; // the minimal change needed;
        int lowerCase = 1, upperCase = 1, digit = 1; // lower-case, upper-case, digit
        int[] repeat = new int[n]; // 重复字符数组：repeat[i] = k -> 从i起，有k个重复字符
        
        // 统计s 的问题2和3
        for (int i = 0; i < n;) {
        	if (Character.isUpperCase(s.charAt(i))) lowerCase = 0;
        	if (Character.isLowerCase(s.charAt(i))) upperCase = 0;
        	if (Character.isDigit(s.charAt(i))) digit = 0;
        	
        	int j = i;
        	
        	while (i < n && s.charAt(i) == s.charAt(j)) {
        		i++;
        	}
        	repeat[j] = i - j;
        }
        /*
        for (int k: repeat) {
        	System.out.print(k + ", ");
        }
        
        System.out.println();
        */
        int missingTypeCount = lowerCase + upperCase + digit;
        
        if (n < 6) {
        	result += missingTypeCount + Math.max(0, 6 - n - missingTypeCount);
        } else {
        	// overLength: 超过20个字符的长度 -- 至少需要这些步的删除操作
        	int overLength = Math.max(0,  n - 20);
        	result += overLength;
        	
        	//先将repeat[]中大于2的元素通过一步或两步的删除操作转化成3m + 2的形式，例如：25 -> 23 (3 * 7 + 2), 24 -> 23
        	for (int k = 1; k < 3; k++) {
        		for (int i = 0; i < n && overLength > 0; i++) {
        			if (repeat[i] < 3 || repeat[i] % 3 != (k - 1)) {
        				
        				continue;
        			}
        			
        			repeat[i] -= Math.min(k, overLength);
        			overLength -= k;
        		}
        	}
        	
        	int leftOver = 0;
        	// 然后若overLength依然大于0再将这些元素通过m步一直删除到2 (因为对于删除操作，每次最多可以修复3个重复字符)
        	for (int i = 0; i < n; i++) {
        		if (repeat[i] >= 3 && overLength > 0) {
        			//当前repeat[i] = 3m + 2，因此需要m步delete
        			int delete = repeat[i] - 2;
        			repeat[i] -= overLength;
        			overLength -= delete;
        		}
        		
        		// 如果overLength == 0时，repeat[i]依然大于等于3， 则需要额外leftOver步，来消除repeat
        		if (repeat[i] >= 3) {
        			leftOver += repeat[i] / 3;
        		}
        	}
        	
        	result += Math.max(missingTypeCount, leftOver);
        }

        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(strongPasswordChecker("AAAAAAAAAAAAAAAAAAAAAAAAAA"));
	}
}
