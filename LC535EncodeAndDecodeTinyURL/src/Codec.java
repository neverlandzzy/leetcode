import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Codec {
	
	/*
	 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and 
	 * it returns a short URL such as http://tinyurl.com/4e9iAk.
	 * 
	 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. 
	 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
	 */
	
	// https://leetcode.com/problems/encode-and-decode-tinyurl/solution/
	
    // Encodes a URL to a shortened URL.
	
	// base62
	private final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String PREFIX = "http://shorturl.com/";
	private final int LENGTH = 6;
	Random random;
	
	Map<String, String> map;
	
	
	public Codec() {
		map = new HashMap<>();
		random = new Random();
	}
	
	private String getRandomKey() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < LENGTH; i++) {
			sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
		}
		
		return sb.toString();
	}
	
    public String encode(String longUrl) {
        String key = getRandomKey();
        while (map.containsKey(key)) {
        	key = getRandomKey();
        }
        map.put(key, longUrl);
        return PREFIX + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace(PREFIX, ""));
    }
}
