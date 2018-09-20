
public class Solution {
	public static void main(String[] args) {
		
		int[] test =  {4,5,8,2};
		KthLargest kthLargest = new KthLargest(3, test);
		
		System.out.println(kthLargest.add(3));   // returns 4
		System.out.println(kthLargest.add(5));   // returns 5
		System.out.println(kthLargest.add(10));  // returns 5
		System.out.println(kthLargest.add(9));   // returns 8
		System.out.println(kthLargest.add(4));   // returns 8
	}
}
