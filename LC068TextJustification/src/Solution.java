import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given an array of words and a length L, format the text such that each line has exactly L 
	 * characters and is fully (left and right) justified.
	 * 
	 * You should pack your words in a greedy approach; that is, pack as many words as you can in 
	 * each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
	 * 
	 * Extra spaces between words should be distributed as evenly as possible. If the number of 
	 * spaces on a line do not divide evenly between words, the empty slots on the left will 
	 * be assigned more spaces than the slots on the right.
	 * 
	 * For the last line of text, it should be left justified and no extra space is inserted between 
	 * words.
	 * 
	 * For example,
	 * 
	 * words: ["This", "is", "an", "example", "of", "text", "justification."]
	 * L: 16.
	 * 
	 * Return the formatted lines as:
	 * 
	 * [
	 *    "This    is    an",
	 *    "example  of text",
	 *    "justification.  "
	 * ]
	 * Note: Each word is guaranteed not to exceed L in length.
	 */

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>(); 
        
        if (maxWidth == 0 || words == null || words.length == 0) {
            result.add("");
            return result;
        }
        
        int length = 0;
    
        for (int i = 0; i < words.length;) {
            
            if (length + words[i].length() <= maxWidth) {
                list.add(words[i]);
                length += words[i].length() + 1;
                i++;
            } else {
                // 本行里所有的剩余空间(extraSpace)：L - 已经加入的每个word和word后面space的长度(length), 最后要加回最后一个word后面的space（+1）
                int extraSpace = maxWidth - length + 1;
                // 本行里有多少个单词间padding的空格（也就是单词数 - 1）
                int intervals = list.size() - 1;
                // 本行里将空格均摊到每个interval后，剩下的不能均摊的空格
                int extra = 0;
                if (intervals != 0) {
                    extra = extraSpace % intervals;
                }
                
                StringBuilder sb = new StringBuilder();
                
                for (String s: list) {
                    sb.append(s);
                    if (list.size() > 1) {
                        if (s != list.get(list.size() - 1)) {
                            // extraSpace/intervals + 1： 每个单词后面至少要加一个空格， 长度已经计算过
                            for (int j = 0; j < extraSpace/intervals + 1; j++) {
                                sb.append(' ');
                            }
                            
                            // 从左开始加不能均摊的空格，直到加完为止
                            if (extra > 0) {
                                sb.append(' ');
                                extra--;
                            }
                        }
                    } else {
                        // corner case: 当一行里只有一个单词的时候，左对齐
                        for (int j = 0; j < extraSpace; j++) {
                            sb.append(' ');
                        }
                    }
                }
                result.add(sb.toString());
                list.clear();
                length = 0;
            }
        }
        
        StringBuilder lastLine = new StringBuilder();
        length = 0;
        
        for (String s: list) {
            lastLine.append(s);
            if (s != list.get(list.size() - 1)) {
                lastLine.append(' ');
                length += s.length() + 1;
            }
        }
        
        length += list.get(list.size() - 1).length();
        
        for (int j = 0; j < maxWidth - length; j++) {
            lastLine.append(' ');
        }
        
        result.add(lastLine.toString());
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		//System.out.println(fullJustify(words, 16));
		
		String[] words2 = {""};
		//System.out.println(fullJustify(words2, 2));
		
		String[] words3 = {"a", "b"};
		//System.out.println(fullJustify(words3, 1));
		
		String[] words4 = {"Listen","to","many,","speak","to","a","few."};
		//System.out.println(fullJustify(words4, 6));
		
		String[] words5 = {"What","must","be","shall","be."};
		//System.out.println(fullJustify(words5, 13));
		
		String[] word6 = {"Give","me","my","Romeo;","and,","when","he","shall","die,"};
		System.out.println(fullJustify(word6, 25));
	}
    
}
