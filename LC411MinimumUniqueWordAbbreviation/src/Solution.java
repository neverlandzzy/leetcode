import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * A string such as "word" contains the following abbreviations:
	 * 
	 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", 
	 * "2r1", "3d", "w3", "4"]
	 * 
	 * Given a target string and a set of strings in a dictionary, find an abbreviation of this 
	 * target string with the smallest possible length such that it does not conflict with 
	 * abbreviations of the strings in the dictionary.
	 * 
	 * Each number or letter in the abbreviation is considered length = 1. For example, the 
	 * abbreviation "a32bc" has length = 4.
	 * 
	 * Note:
	 * In the case of multiple answers as shown in the second example below, you may return any 
	 * one of them.
	 * 
	 * Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, 
	 * and log2(n) + m ≤ 20.
	 * 
	 * Examples:
	 * 
	 * "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
	 * 
	 * "apple", ["plain", "amber", "blade"] -> "1p3" 
	 * (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
	 */
    
	// https://discuss.leetcode.com/topic/61799/java-bit-mask-dfs-with-pruning/2
	// https://discuss.leetcode.com/topic/75571/java-bit-mask-backtrack-16ms-with-detailed-explanation
	
    private static int minL;//缩写的最短长度
    private static int result;//缩写结果的整数形式，每一位1代表保留字符，0代表缩写
    private static Set<Integer> set; //存放字典里与target长度相同的字符串
      
    public static String minAbbreviation(String target, String[] dictionary) {
        set = new HashSet<Integer>();
        
        //字典里只有和目标字符串长度相同的字符串才有conflict的可能，所以只在set里加入相同长度字符串的mask 
        for(String s: dictionary) {
            if (s.length() == target.length()) {
                set.add(getBitMask(s, target));
            }
        }
        
        if (set.size() == 0) {
            return String.valueOf(target.length());
        }
        
        minL = target.length() + 1;
        result = -1;
        
        dfs(0, target.length(), 0, 0);
        
        //根据result，生成缩写字符串
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = target.length() - 1; i >= 0; i--) {
            if ((result & 1) == 0) {
                counter++;
            } else {
                if (counter > 0) {
                    sb.insert(0, counter);
                    counter = 0;
                }
                sb.insert(0,target.charAt(i));
            }
            result = result >> 1;
        }
        
        if (counter > 0) {
            sb.insert(0, counter);
        }
        
        return sb.toString();
    }
   
    //curL: 当前缩写的长度
    //res: target的缩写
    //index: dfs深度
    //用dfs遍历所有可能的缩写组合
    private static void dfs(int index, int length, int res, int curL) {
        
        if (index == length) {
        	//剪枝：只检查小于minL的情况
            if (curL < minL) {
                for (int n: set) {
                	// 检查res是否会和set里的字符串发生conflict，若不发生则更新minL和result
                    if ((res & n) == 0) {
                        return;
                    }
                }
                minL = curL;
                result = res;
            }
            return;
        } else {
        	// 情况1：在target中用数字替换字母，但不允许word => w11d这种用数字代替，因此用res << 1使res末位加个0
            if ((res & 1) == 1 || index == 0) {
            	// 情况1.1: (res & 1) == 1 -- res的末位是字母，因此可以用数字替代
                dfs(index + 1, length, res << 1, curL + 1);
            } else {
            	// 情况1.2: res的末位是数字，因此不能再加一位数字，所以curL不变
                dfs(index + 1, length, res << 1, curL);
            }
            // 情况2： 在target中不替换字母，因此用（res << 1）+ 1使res末位加个1
            dfs(index + 1, length, (res << 1) + 1, curL + 1);
        }
    }
    // 0 代表字符相同， 1 代表字符不同
    private static int getBitMask(String s, String t) {
        int mask = 0;
        
        for (int i = 0; i < s.length(); i++) {
            mask = mask << 1;
            if (s.charAt(i) != t.charAt(i)) {
                mask += 1;
            }
        }
        
        return mask;
    }
    
    public static void main(String[] args) {
		String test = "aabadaa";
		String[] dict1 = {"blade"};
		String[] dict2 = {"plain", "amber", "blade"};
		String[] dict3 = {"aabaxaa","aaxadaa"};
		
		System.out.println(minAbbreviation(test, dict3));
		
		System.out.println(getBitMask("apple", "abble"));  // --> 1100
	}
}
