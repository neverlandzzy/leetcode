import java.util.HashMap;


public class Solution {
	/*
	 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). 
	 * You also have several balls in your hand.
	 * 
	 * Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). 
	 * Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls 
	 * can be removed.
	 * 
	 * Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
	 * 
	 * Examples:
	 * 
	 * Input: "WRRBBW", "RB"
	 * Output: -1
	 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
	 * 
	 * Input: "WWRRBBWW", "WRBRW"
	 * Output: 2
	 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
	 * 
	 * Input:"G", "GGGGG"
	 * Output: 2
	 * Explanation: G -> G[G] -> GG[G] -> empty 
	 * 
	 * Input: "RBYYBBRRB", "YRBGB"
	 * Output: 3
	 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 
	 * 
	 * Note:
	 * 1. You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
	 * 2. The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
	 * 3. The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
	 * 4. Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
	 */
	
    public static int findMinStep(String board, String hand) {
    	
    	int[] map = new int[26];
    	
    	for(int i = 0; i < hand.length(); i++) {
    		map[hand.charAt(i) - 'A']++;
    	}
    	
    	int result = dfs(board + "#", map);
    	return result == 10 ? -1 : result;
    }
    
    private static int dfs(String board, int[] map) {
    	board = removeConsecutive(board);

    	if (board.equals("#")) {
    		return 0;
    	}
    	
    	int counter = 10; 
    	int need = 0;
    	
    	for (int i = 0, j = 0; j < board.length(); j++) {
    		if (board.charAt(i) == board.charAt(j)) {
    			continue;
    		}
    		need = 3 - (j - i);
    		
    		if (map[board.charAt(i) - 'A'] >= need) {
    			map[board.charAt(i) - 'A'] -= need;
    			counter = Math.min(counter, (need + dfs(board.substring(0,  i) + board.substring(j), map)));
    			map[board.charAt(i) - 'A'] += need;
    		}
    		i = j;
    	}
    	
    	return counter;
    }
    
    private static String removeConsecutive(String board) {
    	for (int i = 0, j = 0; j < board.length(); j++) {
    		if (board.charAt(j) == board.charAt(i)) {
    			continue;
    		}
    		
    		if (j - i >= 3) {
    			return removeConsecutive(board.substring(0, i) + board.substring(j));
    		} else {
    			i = j;
    		}
    	}
    	
    	return board;
    }
    
    public static void main(String[] args) {
		//System.out.println(findMinStep("WRRBBW", "RB"));
		System.out.println(findMinStep("WWRRBBWW", "WRBRW"));
		//System.out.println(findMinStep("G", "GGGGG"));
		//System.out.println(findMinStep("RBYYBBRRB", "YRBGB"));
	}
}
