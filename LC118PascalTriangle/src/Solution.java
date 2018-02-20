import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*Given numRows, generate the first numRows of Pascal's triangle.
	 * For example, given numRows = 5,
	 * Return
	 * 
	 * [
	 *     [1],
	 *    [1,1],
	 *   [1,2,1],
	 *  [1,3,3,1],
	 * [1,4,6,4,1]
	 * ]
	 */
	
    public static List<List<Integer>> generate(int numRows) {
      
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	
    	for(int i = 0; i < numRows; i++) {
    		List<Integer> list = new ArrayList<Integer>();
    		for(int j = 0; j <= i; j++) {
    			if(j == 0 || j == i) {
    				list.add(1);
    			} else {
    				list.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
    			}
    		}
    		
    		result.add(list);
    	}
    	
    	return result;
    }
    
    
	public static void main(String[] args) {
		int row = 5;
		
		System.out.println(generate(row));

	}
}
