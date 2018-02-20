
public class Solution {
	/*
	 * Design and implement a TwoSum class. It should support the following operations: add and find.
	 * 
	 * add - Add the number to an internal data structure.
	 * find - Find if there exists any pair of numbers which sum is equal to the value.
	 * 
	 * For example,
	 * add(1); add(3); add(5);
	 * find(4) -> true
	 * find(7) -> false
	 */
	
	public static void main(String[] args) {
		TwoSum ts = new TwoSum();
		
		ts.add(1);
		ts.add(3);
		ts.add(5);
		ts.add(1);
		
		System.out.println(ts.find(4));
		System.out.println(ts.find(7));
		System.out.println(ts.find(2));
	}
}


