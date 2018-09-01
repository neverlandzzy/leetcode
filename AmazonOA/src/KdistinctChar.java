import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class KdistinctChar {
	
	// 1. 给定一个string 和K值，求包含 k个不同字符的子串集合。
	public static List<String> kdistinctChar(String input, int K){
		List<String> result = new ArrayList<>();
		if (input == null || input.length() == 0 || K <= 0) {
			return result;
		}
		
		Set<Character> map = new HashSet<>();
		Set<String> set = new HashSet<>();
		
		int i = 0;
		int j = 0;
		
		while (j < input.length()) {
			if (map.contains(input.charAt(j)) || j - i + 1 > K) {
				map.remove(input.charAt(i));
				i++;
			} else {
				map.add(input.charAt(j));
				
				if (j - i + 1 == K) {				
					set.add(input.substring(i, j + 1));
				}
				
				j++;
			}
		}
		
		result.addAll(set);
		return result;
	}
	
	// 找出所有size为k的substring并且这个substring里有k-1 unique character.
	public static List<String> kdistinctChar2(String input, int K){
		List<String> result = new ArrayList<>();
		if (input == null || input.length() == 0 || K <= 0) {
			return result;
		}
		
		Map<Character, Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		
		int i = 0;
		int j = 0;
		
		while (j < input.length()) {
			if ((map.containsKey(input.charAt(j)) && map.get(input.charAt(j)) == 2) || j - i + 1 > K) {
				map.put(input.charAt(i), map.get(input.charAt(i)) - 1);
				
				if (map.get(input.charAt(i)) == 0) {
					map.remove(input.charAt(i));
				}
				
				i++;
			} else {
				map.put(input.charAt(j), map.getOrDefault(input.charAt(j), 0) + 1);
				
				if (j - i + 1 == K && map.size() > 2) {				
					set.add(input.substring(i, j + 1));
				}
				
				j++;
			}
		}
		
		result.addAll(set);
		return result;
	}

	public static void main(String[] args) {

		System.out.println(kdistinctChar("awaglknagawunagwkwagl", 4));
		System.out.println(kdistinctChar2("awaglknagawunagwkwagl", 4));
		
		System.out.println(kdistinctChar("awaglknagawunagwwkwagl", 4));
		System.out.println(kdistinctChar2("awaglknagawunagwwkwagl", 4));
		
		System.out.println(kdistinctChar("awagl", 4));
		System.out.println(kdistinctChar2("aabbiefe", 4));
		
	}
}
