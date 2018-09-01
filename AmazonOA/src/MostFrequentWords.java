import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MostFrequentWords {
	/*
	 * 给你一个String （I am Jack and my father is Jimmy. I like wearing Jack and Jone's. ）， 一个exclude list， 让你给出出现频率最高或者并列高的词
	 * (不Case sensitive, Jack和jack算一个词，都出现的话等于算jack出现两次). (而不是大家之前说的给一个k，然后求出出现频率最高的k个）, 比如Jack和Jone都是频率最高且出现三次，
	 * 那么return [Jack, Jone]。这里有个很tricky的地方就是Jone's是两个词，Jone和s。 
	 * 
	 * Words that have a different case are counted as the same word.
	 * The order of words does not matter in the output list.
	 * Punctuation should be treated as white space.
	 * 
	 * 本题不同版本要求不一样，有的是输入没有其它字符，只有whitespace，大小写算不一样的词
	 */
	
	public static List<String> mostFrequentWords (String paragraph, List<String> banned) {
		List<String> result = new ArrayList<>();
		Set<String> set = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		int max = 0;
		
		for (String b: banned) {
			set.add(b.toLowerCase());
		}
		
		for (String s: paragraph.split("\\W+")) {
			String word = s.toLowerCase();
			if (!set.contains(word)) {
				map.put(word, map.getOrDefault(word, 0) + 1);
				max = Math.max(max, map.get(word));
			}
		}
		
		for (String key: map.keySet()) {
			if (map.get(key) == max) {
				result.add(key);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String paragraph = "Jack and Jill went to the !!! market : ? to buy bread and cheese. Cheese is Jack's and Jill's favorite food.";
		List<String> banned = new ArrayList<>(Arrays.asList("and", "he", "the", "to", "is", "Jack", "Jill"));
		
		System.out.println(mostFrequentWords(paragraph, banned)); // [cheese, s]
	}
	
	// 参考答案
	/*
	static public List<String> commonWords(String paragraph, List<String> banned) {
		List<String> ans = new ArrayList();
		
		if (paragraph == null || paragraph.length() == 0) return ans;
		// split all words
		String[] words = paragraph.toLowerCase().split("\\W+");
		int max = Integer.MIN_VALUE;
		Map<String, Integer> map = new HashMap<>();
		for (String word : words) {
			if (!banned.contains(word)) {
				int frequent = map.getOrDefault(word, 0) + 1;
				map.put(word, frequent);
				max = Math.max(max, frequent);
			}
		}
		
		// get the most common words
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max)
				ans.add(entry.getKey());
		}
		return ans;
	}

	static public boolean isBanned(List<String> banned, String word) {
		for (String w : banned) {
			if (w.toLowerCase().equals(word))
				return true;
		}
		return false;
	}
	*/
}
