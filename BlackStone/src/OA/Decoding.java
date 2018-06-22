package OA;

public class Decoding {
	private static String encode(String text) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			b.append(c += c + i);
		}
		return b.reverse().toString();
	}
	
    public static String decode(String encodedMessage) {
    	char[] arr = encodedMessage.toCharArray();
    	reverse(arr);
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < arr.length; i++) {
    		char c = arr[i];
    		sb.append((char)((c - i) / 2));
    	}
    	
    	return sb.toString();
    }
    
    private static void reverse(char[] arr) {
    	int i = 0;
    	int j = arr.length - 1;
    	
    	while (i < j) {
    		char c = arr[i];
    		arr[i] = arr[j];
    		arr[j] = c;
    		i++;
    		j--;
    	}
    }
    
    public static void main(String[] args) {
		String test1 = "test";
		String encode1 = encode(test1);
		
		System.out.println(encode(encode1));
		System.out.println(decode(encode1));
	}
}
