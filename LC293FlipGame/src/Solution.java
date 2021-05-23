import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * You are playing the following Flip Game with your friend: Given a string that contains 
	 * only these two characters: + and -, you and your friend take turns to flip two consecutive 
	 * "++" into "--". The game ends when a person can no longer make a move and therefore the other 
	 * person will be the winner.
	 * 
	 * Write a function to compute all possible states of the string after one valid move.
	 * 
	 * For example, given s = "++++", after one move, it may become one of the following states:
	 * [
	 *   "--++",
	 *   "+--+",
	 *   "++--"
	 * ]
	 * 
	 * If there is no valid move, return an empty list [].
	 */
	
    public static List<String> generatePossibleNextMoves(String currentState) {
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < currentState.length() - 1; i++) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                result.add(currentState.substring(0, i) + "--" + currentState.substring(i + 2));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(generatePossibleNextMoves("++++"));
		String s = "test";
		System.out.println(s.substring(4));

	}
}
