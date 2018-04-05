
public class Solution {
	
	public static void main(String[] args) {
		
		
		Codec codec = new Codec();
		
		String longURL = "http://www.google.com";
		String shortURL = codec.encode(longURL);
		System.out.println(shortURL);
		System.out.println(codec.decode(shortURL));

	}
}
