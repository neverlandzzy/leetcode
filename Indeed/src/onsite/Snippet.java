package onsite;

public class Snippet {
	/*
	 * 就是给你一段String[] = {"we all love indeed, and everyone use python and nobody like java, but we would hire java developer"}; 
	 * 让你return result = and everyone use python and nobody like java, but we would.这道不难，就维护两个指针 pre and post. 
	 * 把这段话分成3段存入result =[pre-3 to pre]+ python ==== java + [post to post +3]
	 */
	
	public static String getSnippet(String s, String key1, String key2) {
		String[] words = s.split(" ");
		int pre  = 0;
		int post = 0;
		
		for (int i = 0; i < words.length; i++) {
			if (words[i].replace(",", "").equals(key1)) {
				pre = Math.max(0, i - 3);
			}
			
			if (words[i].replace(",", "").equals(key2)) {
				post = Math.min(words.length - 1, i + 3);
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = pre; i <= post; i++) {
			sb.append(words[i]).append(" ");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String test1 = "we all love indeed, and everyone use python and nobody like java, but we would hire java developer"; 
		System.out.println(getSnippet(test1, "python", "java"));
	}
}
