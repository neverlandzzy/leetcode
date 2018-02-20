
public class Solution {
	public static void main(String[] args) {
		
		String[] words = {"apple"};
		WordFilter obj = new WordFilter(words);
		
		System.out.println(obj.f("a", "e"));
		System.out.println(obj.f("b", ""));
	}
}
