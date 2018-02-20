
public class Solution {
	public static void main(String[] args) {
		
		
		//int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
		int[][] matrix = {{-4, -5}};
		NumMatrix numMatrix = new NumMatrix(matrix);
		
		System.out.println(numMatrix.sumRegion(0, 0, 0, 1));
		//System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
		//System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
		
		int[][] test2 = {{}};
		NumMatrix numMatrix2 = new NumMatrix(test2);
		//System.out.println(numMatrix2.sumRegion(1, 2, 3, 4));
		
		
	}
}
