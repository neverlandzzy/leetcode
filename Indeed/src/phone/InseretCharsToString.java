package phone;

public class InseretCharsToString {
	/*
	 * 题目是：“all” ，“There is all in all, two all”
	 * 输出：“There is <b>all</b> in <b>all</b>, two all”;
	 * 
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=302282
	 */
	
	private static String convert(String str, String key) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		
		for (int i = 0; i < str.length();) {
			for (int j = 0; j < key.length(); ) {
				if (i < str.length() && j < key.length() && str.charAt(i) == key.charAt(j)) {
					i++;
					j++;
				} else {
					
					// e.g. there is alall in all. 
					//                 i = 11, j = 2 时发现不match， 则i回退到 11-2+1 = 10（即上一次尝试的下一位开始）， j从0开始。
					//
					i = i - j + 1;
					break;
				}
				
				if (j == key.length()) {
					sb.append(str.substring(index, i - j)).append("<b>").append(str.substring(i - j, i)).append("</b>");
					j = 0;
					index = i;
				}
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String test = "There is all in all, two all";
		System.out.println(convert(test, "all"));
	}
}
