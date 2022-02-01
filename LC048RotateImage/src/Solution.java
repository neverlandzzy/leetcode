
public class Solution {
	/**
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 * 
	 * Follow up:
	 * Could you do this in-place?
	 */
	
    public static void rotate(int[][] matrix) {        
    	// 由 (i, j) -> (j, n - 1- i) 可以推出：
    	//
    	// (i, j) -> (j, n - 1 - i)
    	// (j, n - 1 - i) -> (n - 1 - i, n - 1 - j)
    	// (n - 1 - i, n - 1 - j) -> (n - 1 - j, i)
    	// (n - 1 - j, i) -> (i, j)
    	
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        int n = matrix.length;
        int half = (n % 2) == 0 ? n / 2 : (n / 2) + 1;
        
        for (int i = 0; i < half; i++) {
        	// 对于奇数矩阵，j要小于n/2 若用half，则中间的元素会被查找到两次，即旋转两次
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1- j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }
    
    public static void main(String[] args) {
		//int[][] test = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		int[][] test = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				System.out.printf("%3d", test[i][j]);
			}
			System.out.println();
		}
		
		rotate(test);
		
		System.out.println("======= After rotate =======");
		
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				System.out.printf("%3d", test[i][j]);
			}
			System.out.println();
		}
	}
}
