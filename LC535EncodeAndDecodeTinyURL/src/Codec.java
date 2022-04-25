import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Codec {
	
	/**
	 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and 
	 * it returns a short URL such as http://tinyurl.com/4e9iAk.
	 * 
	 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. 
	 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
	 */
	
	// https://leetcode.com/problems/encode-and-decode-tinyurl/solution/
	
    // Encodes a URL to a shortened URL.
	
	// base62
    private final String CODING = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String PREFIX = "http://tinyurl.com/";
    private final int LENGTH = 6;
    Random random;
    
    Map<String, String> map;
    
    public Codec() {
        random = new Random();
        map = new HashMap<>();
    }
        
    private String getRandomKey() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CODING.charAt(random.nextInt(CODING.length())));
        }
        
        return sb.toString();
    }
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = getRandomKey();
        while (map.containsKey(shortUrl)) {
            shortUrl = getRandomKey();
        }
        
        map.put(shortUrl, longUrl);
        return PREFIX + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.substring(PREFIX.length()));
    }
}
