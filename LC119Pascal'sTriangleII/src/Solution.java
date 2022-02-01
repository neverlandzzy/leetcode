import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given an index k, return the kth row of the Pascal's triangle.
	 * 
	 * For example, given k = 3,
	 * Return [1,3,3,1].
	 * 
	 * Note:
	 * Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 */
	
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(result.get(j - 1) + result.get(j));
                }
            }
            result = list;
        }
        
        return result;
    }
    
    
	public static void main(String[] args) {
		int row = 5;
		
		System.out.println(getRow(row));

	}
}
