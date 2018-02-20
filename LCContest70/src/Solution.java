import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	
	
    public static int kthGrammar(int N, int K) {
    	if (N == 1) {
    		return 0;
    	}
    	
    	
    	if (K > N) {
    		return (~kthGrammar(N - 1, K - N)) & 1;
    	}
    	
    	return kthGrammar(N - 1, K);
    }
	
    public static int kthGrammarII(int N, int K) {
        StringBuilder sb = new StringBuilder();
    	
    	sb.append("0");
    	
        for (int i = 2; i <= N; i++) {
        	StringBuilder tmp = new StringBuilder();
        	for (int j = 0; j < sb.length(); j++) {
        		char c = sb.charAt(j);
        		if (c == '0') {
        			tmp.append("01");
        		} else {
        			tmp.append("10");
        		}
        	}
        	sb = tmp;
        }
        
        System.out.println(sb.toString());
        return Integer.valueOf(sb.charAt(K - 1) - '0');
    }
	public static void main(String[] args) {
		System.out.println(kthGrammar(1, 1));
		System.out.println(kthGrammar(3, 1));
		System.out.println(kthGrammar(4, 5));
		System.out.println(kthGrammar(5, 5));
		System.out.println(kthGrammar(2, 2));
	}
}
