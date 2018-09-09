import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that 
	 * no two queens attack each other.
	 * 
	 * Given an integer n, return all distinct solutions to the n-queens puzzle.
	 * 
	 * Each solution contains a distinct board configuration of the n-queens' placement, 
	 * where 'Q' and '.' both indicate a queen and an empty space respectively.
	 * 
	 * For example,
	 * There exist two distinct solutions to the 4-queens puzzle:
	 * 
	 * [
 	 *  [".Q..",  // Solution 1
 	 *  "...Q",
 	 *  "Q...",
 	 *  "..Q."],
 	 *  ["..Q.",  // Solution 2
 	 *  "Q...",
 	 *  "...Q",
 	 *   ".Q.."]
 	 * ]
	 */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        
        dfs(n, 0, 0, 0, result, list);
        
        return result;
    }
    /*
    public static void dfs (int n, int row, int ld, int rd, List<List<String>> result, List<String> list) {
    	int upperLimit =  (1 << n) - 1;

    	if (row != upperLimit) {
    		int pos = upperLimit & (~(row|ld|rd)) ;
    		while (pos > 0) {		
    			int p = pos & (~pos + 1);
    			pos = pos - p;
    			list.add(Integer.toBinaryString(p));
    			dfs(n, row|p, (p|ld)<<1, (p|rd)>>1, result, list);
    			list.remove(list.size()-1);
    		}
    	} else {
    		result.add(new ArrayList<String>(list));

    	}
    	
    }
    */
    private static void dfs (int n, int row, int ld, int rd, List<List<String>> result, List<String> list) {
        
        int upperLimit =  (1 << n) - 1;
        
        if (row == upperLimit) {
            result.add(new ArrayList<String>(list));
        } else {
            int candidate = upperLimit & (~(row|rd|ld));
            
            while (candidate > 0) {
                int pos = candidate & (~candidate + 1); //取最后一位
                candidate = candidate - pos;

                StringBuilder sb = new StringBuilder(Integer.toBinaryString(pos).replace('0', '.').replace('1', 'Q'));
                while (sb.length() < n) {
                	sb.insert(0, '.');
                }
                list.add(sb.toString());
          
                dfs(n, row|pos, (ld | pos) << 1, (rd | pos) >> 1, result, list);
                list.remove(list.size() - 1);
            }
        }
    	
    }
    
    public static void main(String[] args) {
		System.out.println(solveNQueens(4));
		System.out.println(solveNQueens(8));

		
	}
}
