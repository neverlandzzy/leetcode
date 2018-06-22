package OA;


public class Order {

    public static int[][] splitOrderFills(int[] orders, int[] fills) {
    	
    	int total = 0;
    	
    	int m = fills.length;
    	int n = orders.length;
    	int[][] result = new int[m][n];
    	double[] avg = new double[n];
    	
    	for (int i = 0; i < n; i++) {
    		total += orders[i];
    	}
    	
    	for (int i = 0; i < n; i++) {
    		avg[i] = orders[i] / total;
    	}
    	
    	for (int i = 0; i < m; i++) {
    		int fill = fills[i];   		
    		for (int j = 0; j < orders.length; j++) {
    			result[i][j] = fills[i] * orders[j] / total;
    			orders[j] -= result[i][j]; 
    			fill -= result[i][j];
    		}
    		
    		if (fill > 0) {
        		int index = n - 1;
	    		while (fill > 0) {
	    			result[i][index] += 1;    			
	    			fill--;
	    		}
    		} else if (fill < 0) {
    			int index = n - 1;
    			while (fill < 0) {
    				result[i][index] -= 1; 
    				fill++;
    			}
    		}
    		
    		
    		total -= fills[i];
    	}
    	
    	return result;
    }

    public static void main(String[] args) {
		int[] orders1 = {400, 600};
		int[] fills1 = {1000};
		int[][] result1 = splitOrderFills(orders1, fills1);
		
		
		int[] orders2 = {400, 600};
		int[] fills2 = {500, 500};
		int[][] result2 = splitOrderFills(orders2, fills2);
		
		
		int[] orders3 = {100, 100, 100};
		int[] fills3 = {100, 200};
		int[][] result3 = splitOrderFills(orders3, fills3);
		
		int[] orders4 = {100, 100, 10};
		int[] fills4 = {100, 110};
		int[][] result4 = splitOrderFills(orders4, fills4);
		
		int[] orders5 = {100, 200};
		int[] fills5 = {10, 10};
		int[][] result5 = splitOrderFills(orders5, fills5);
		
		
		print(result1);
		print(result2);
		print(result3);
		print(result4);
		print(result5);
	}
    
    private static void print(int[][] arr) {
    	for (int i = 0; i < arr.length; i++) {
    		for (int j = 0; j < arr[0].length; j++) {
    			System.out.print(arr[i][j] + ", ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
}
