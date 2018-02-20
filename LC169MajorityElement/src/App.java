
public class App {
	/*
	 * Given an array of size n, find the majority element. 
	 * The majority element is the element that appears 
	 * more than ⌊ n/2 ⌋ times.
	 * 
	 * You may assume that the array is non-empty and the 
	 * majority element always exist in the array.
	 */
	public static void main(String[] args) {
		int[] array1 = {1,2,2,3,3,2,2,2,4,5};
		int[] array2 = {10,10,10,10,10,3,3,4,1};
		
		System.out.println("========  array 1 =======");
		System.out.println(majorityElement(array1));
		System.out.println("========  array 2 =======");
		System.out.println(majorityElement(array2));
	}
	
	
    public static int majorityElement(int[] num) {
        /*
         * Moore’s Voting Algorithm
         * RunTime O(n)
         * Space O(1)
         */
    	int major = num[0];
    	int count = 1;
    	
    	for(int i = 1; i < num.length; i++) {
    		
    		if(count == 0) {
    			major = num[i];
    			count++;
    		} else if (major == num[i]) {
    			count++;
    		} else {
    			count--;
    		}
    	}
    	
    	return major;
    }
}
