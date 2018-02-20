
public class Solution {
	public static void main(String[] args) {
		String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
		int[] times = {5, 3, 2, 2};
		
		AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
		System.out.println(obj.input('i'));
		System.out.println(obj.input(' '));
		System.out.println(obj.input('a'));
		System.out.println(obj.input('#'));
	}
}
