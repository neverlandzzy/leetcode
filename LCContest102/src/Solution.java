import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	
    public static int[] sortArrayByParity(int[] A) {
        if (A == null || A.length <= 1) {
        	return A;
        }
        int n = A.length;
        
        int i = 0;
        int j = n - 1;
        
        while (i < j) {
        	if (A[i] % 2 != 0) {
        		swap(A, i, j);
        		j--;
        	} else {
        		i++;
        	}
        }
        
        return A;
    }
    
    private static void swap(int[] A, int i, int j) {
    	int tmp = A[i];
    	A[i] = A[j];
    	A[j] = tmp;
    }
    
    public static class Node {
    	int key;
    	int val;
    	
    	public Node(int k, int v) {
    		this.key = k;
    		this.val = v;
    	}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[" + this.key + ", " + this.val + "] ";
		}
    	
    	
    }
    
    public static int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }
        
        int counter = 0;
        int result = 0;
        int j = 0;
        int n = tree.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            while (j < n) {
                if (map.containsKey(tree[j])) {
                    map.put(tree[j], map.get(tree[j]) + 1);
                } else {
                    if (counter == 2) {
                        break;
                    }
                    map.put(tree[j], 1);
                    counter++;                  
                }
                result = Math.max(result, j - i + 1);
                j++;
            }
            
            map.put(tree[i], map.get(tree[i]) - 1);
            
            if (map.get(tree[i]) == 0) {
                counter--;
                map.remove(tree[i]);
            }
        }
        
        return result;
    }

    
    public static int sumSubarrayMins(int[] A) {
    	List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int n = A.length;
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
        	subarray(result, list, A, i);
        }
        
        for (List<Integer> l: result) {
        	sum += minElement(l);
        }
        
        return sum;
    }
    
    private static void subarray(List<List<Integer>> result, List<Integer>list, int[] A, int pos) {    	
    	if (pos >= A.length) {
    		return;
    	}
    	
    	list.add(A[pos]);
    	result.add(new ArrayList<>(list));
    	subarray(result, list, A, pos + 1);
    	list.remove(list.size() - 1);
    	
    }
    
    private static int minElement(List<Integer>list) {
    	int min = Integer.MAX_VALUE;
    	
    	for (int i: list) {
    		min = Math.min(i, min);
    	}
    	
    	return min;
    }
    
	public static void main(String[] args) {
		/*
		int[] test11 = {3,1,2,4};
		int[] result11 = sortArrayByParity(test11);
		
		print(result11);
		
		int[] test21 = {1,2,1};
		int[] test22 = {0,1,2,2};
		int[] test23 = {1,2,3,2,2};
		int[] test24 = {3,3,3,1,2,1,1,2,3,3,4};
		int[] test25 = {1, 0, 3, 4, 3};
		int[] test26 = {1,0,1,4,1,4,1,2,3};
		

		System.out.println(totalFruit(test21));
		System.out.println(totalFruit(test22));
		System.out.println(totalFruit(test23));
		System.out.println(totalFruit(test24));
		System.out.println(totalFruit(test25));
		System.out.println(totalFruit(test26));
		*/
		

		int[] test31 = {3,1,2,4};
		System.out.println(sumSubarrayMins(test31));

	}
	
	private static void print(int[] nums) {
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		
		System.out.println();
	}
}
