import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	
	
    public static int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        
        for (char c: J.toCharArray()) {
        	set.add(c);
        }
        
        int counter = 0;
        
        for (char c: S.toCharArray()) {
        	if(set.contains(c)) {
        		counter++;
        	}
        }

        return counter;
    }
    
    
    public static boolean isIdealPermutation(int[] A) {
        int global = 0;
        int local = 0;
        
        int n = A.length; 

        for (int i = 0; i < n - 1; i++) {
        	if (A[i + 1] < A[i]) {
        		local++;
        	}
        }
        
        List<Integer> list = countSmaller(A);
        

        for (int k: list) {
        	global += k;
        }

        return global == local;
    }
    
    
	private static int[] count;
	
    public static List<Integer> countSmaller(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	count = new int[nums.length];
    	int[] index = new int[nums.length];
    	
    	for (int i = 0; i < nums.length; i++) {
    		index[i] = i;
    	}
    	
        mergesort(nums, index, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
        	result.add(count[i]);
        }

    	return result;
    	
    }
    
    private static void mergesort(int[] nums, int[] index, int start, int end) {
    	
    	if (start < end) {
    		int mid = (start + end) / 2;
    	
    		mergesort(nums, index, start, mid);
    		mergesort(nums, index, mid+1, end);
    		
    		merge(nums, index, start, mid, end);
    	}
    }
    
    private static void merge(int[] nums, int[] index, int start, int mid, int end) {
    	int[] tmp = new int[end - start + 1];
		
		int i = start; 
		int j = mid + 1;
		int k = 0;
		int rightCount = 0;
		

		while (i <= mid && j <= end) {
			if (nums[index[j]] < nums[index[i]]) {
				tmp[k] = index[j];
				rightCount++;
				j++;
			} else {
				tmp[k] = index[i];
				count[index[i]] += rightCount;
				i++;
			}
				k++;
		}

		while (i <= mid) {
			tmp[k] = index[i];
			count[index[i]] += rightCount;
			k++;
			i++;
		}
		
		while (j <= end) {
			tmp[k] = index[j];
			k++;
			j++;
		}
		
		for (int m = start; m <= end; m++) {
			index[m] = tmp[m - start];
		}
    }
    
    
    static int min = Integer.MAX_VALUE;
    
    public static int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        int indexI = 0;
        int indexJ = 0;
        min = Integer.MAX_VALUE;
        
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if (board[i][j] == 0) {
        			indexI = i;
        			indexJ = j;
        		}
        		
        		sb.append(board[i][j]);
        	}
        }
        
        if (sb.toString().equals("123450")) {
        	return 0;
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        if(dfs(board, indexI, indexJ, map, 0) == -1) {
        	return -1;
        }
        
        return min;
    }
    
    private static int dfs(int[][] board, int i, int j,  Map<String, Integer> map, int count) {
    	String key = getKey(board);
    	System.out.println("key = " + key + " count =  " + count);
    	int m = board.length;
    	int n = board[0].length;
    	int result = 0;
    	
    	int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    	
    	if (count >= 46656) {
    		map.put(key, -1);
    		return -1;
    	}    	
    	
    	if (map.containsKey(key)) {
    		return map.get(key);
    	}
    	
    	for (int[] dir: direction) {
    		int nextI = i + dir[0];
    		int nextJ = j + dir[1];
    		
    		if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
    			continue;
    		}
    		
    		swap(board, i, j, nextI, nextJ);
    		String newKey = getKey(board);
    		
    		if (newKey.equals("123450")) {
    			result = count + 1;
    			map.put(key, result);
    			return result;
    		}

    		
    		result = dfs(board, nextI, nextJ, map, count + 1) + 1;

    		swap(board, nextI, nextJ, i, j);
    	}
    	
    	return count;
    }
    
    private static String getKey(int[][] board) {
    	
    	StringBuilder sb = new StringBuilder();
    	
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {        		
        		sb.append(board[i][j]);
        	}
        }
        
        return sb.toString();
    }
    
    private static void swap(int[][] board, int i1, int j1, int i2, int j2) {
    	int tmp = board[i1][j1];
    	board[i1][j1] = board[i2][j2];
    	board[i2][j2] = tmp;
    }
    
	public static void main(String[] args) {
		/*
		String J1 = "aA";
		String S1 = "aAAbbbb";
		String J2 = "z";
		String S2 = "ZZ";
				
		System.out.println(numJewelsInStones(J1, S1));
		System.out.println(numJewelsInStones(J2, S2));


		int[] test21 = {1, 0, 2};
		int[] test22 = {1, 2, 0};
		int[] test23 = {2, 0, 1};
		
		System.out.println(isIdealPermutation(test21));
		System.out.println(isIdealPermutation(test22));
		System.out.println(isIdealPermutation(test23));
		*/
		
		int[][] test31 = {{1, 2, 3}, {4, 0, 5}};
		int[][] test32 = {{1, 2, 3}, {5, 4, 0}};
		int[][] test33 = {{4, 1, 2}, {5, 0, 3}};
		int[][] test34 = {{3, 2, 4}, {1, 5, 0}};
		
		System.out.println(slidingPuzzle(test31));
		//System.out.println(slidingPuzzle(test32));
		//System.out.println(slidingPuzzle(test33));
		//System.out.println(slidingPuzzle(test34));
	}
}
