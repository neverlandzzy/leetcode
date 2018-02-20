
public class NumMatrix {
	
	int m;
	int n;
	int[][] a;
	int[][] BIT;
	
    public NumMatrix(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
    		return;
    	}
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.a = matrix;
        BIT = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		init(i, j, matrix[i][j]);
        	}
        }
    }
    
    private int lowbit(int k) {
    	return k & (-k);
    }
    
    private void init(int row, int col, int val) {
    	for (int i = row + 1; i <= m; i += lowbit(i)) {
    		for (int j = col + 1; j <= n; j += lowbit(j)) {
    			BIT[i][j] += val;
    		}
    	}
    }
    
    public void update(int row, int col, int val) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
        	return;
        }
        
        int delta = val - a[row][col];
        a[row][col] = val;
        
        init(row, col, delta);
    }
    
    private int getSum(int row, int col) {
    	int sum = 0;
    	
    	for (int i = row + 1; i > 0; i -= lowbit(i)) {
    		for (int j = col + 1; j > 0; j -= lowbit(j)) {
    			sum += BIT[i][j];
    		}
    	}
    	
    	return sum;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
    		return 0;
    	}
        return getSum(row2, col2) + getSum(row1 - 1, col1 - 1) - getSum(row1 - 1, col2) - getSum(row2, col1 - 1);
    }
}
