import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class tinyUrl {
	
	static HashMap<String, String> mLongToShort = new HashMap<String, String>();
	static HashMap<String, String> mShortToLong = new HashMap<String, String>();
	
	public static void insert(String longUrl) {
		if (!mLongToShort.containsKey(longUrl)) {
			String shortUrl = generateShortURL(longUrl);
			mLongToShort.put(longUrl, shortUrl);
			mShortToLong.put(shortUrl, longUrl);
		}
		
	}
	
	public static String generateShortURL(String longUrl) {
		int num= randNumber(1, Integer.MAX_VALUE);
		
		return convertTo62(num);
	}
	
	public static int randNumber(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static String convertTo62(int num) {
		StringBuilder result = new StringBuilder();
		
		char encode[] = new char[62];
		
		int i = 0;
		for(; i <= 9; i++) {
			
			encode[i] = Integer.toString(i).charAt(0);
			
		}
		
		for (char c='A'; c<='Z'; c++) {
			encode[i++] = c;
		}
		
		for (char c='a'; c<='z'; c++) {
			encode[i++] = c;
		}
		
		result.append("http://myTU/");
		while(num > 0) {
			result.append(encode[num%62]);
			num /= 62;
		}
		
	
		return result.toString();
	} 
	
	public static void main(String[] args) {
		String test1 = "www.google.com";
		String test2 = "www.sina.com";
		String test3 = "www.yahoo.com";
		String test4 = "www.cnn.com";
		String test5 = "www.163.com";
		String test6 = "www.baidu.com";
		String test7 = "www.msn.com";
		insert(test1);
		insert(test2);
		insert(test3);
		insert(test4);
		insert(test5);
		insert(test6);
		insert(test7);
		
		for(Map.Entry<String, String> entry: mLongToShort.entrySet()) {
			System.out.println(entry);
		}
	}
}


