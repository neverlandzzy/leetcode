
public class Solution {
	/*
	 * Follow up for N-Queens problem.
	 * Now, instead outputting board configurations, return the total number of distinct solutions.
	 * 
	 */
	
    public static int totalNQueens(int n) {
        return dfs(n,0,0,0);
    }
    
    /*
    public static int dfs(int n, int row, int rd, int ld) {
    	int upperLimit = (1 << n) -1;
    	int result = 0;
    	
    	if (row == upperLimit) {
    		result = 1;
    	} else {
    		int pos = upperLimit & (~(row|rd|ld));

    		while (pos > 0) {
    			int p = pos & (~pos+1);
        		
    			pos = pos - p;
    			result = result + dfs(n, row|p, (rd|p) >> 1, (ld|p) << 1);
    		}
    	}
    	
    	return result;
    }
    */
    private static int dfs(int n, int row, int ld, int rd) {
        int result = 0;
        int upperLimit = (1 << n) - 1;
        
        if (row == upperLimit) {
            result = 1;
        } else {
            int candidate = upperLimit & (~(row|ld|rd));
            while (candidate > 0) {
                int pos = candidate & (~candidate + 1);
                candidate -= pos;
                result += dfs(n, row|pos, (ld|pos) << 1, (rd|pos) >> 1);
            }
        }
        return result;
    }
    
    
    public static void main(String[] args) {
		System.out.println(totalNQueens(4));
    	

	
	}
}
