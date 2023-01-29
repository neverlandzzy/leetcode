
public class Solution {
	/**
	 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint 
	 * intervals.
	 * 
	 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
	 * 
	 * [1, 1]
	 * [1, 1], [3, 3]
	 * [1, 1], [3, 3], [7, 7]
	 * [1, 3], [7, 7]
	 * [1, 3], [6, 7]
	 * 
	 * Follow up:
	 * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
	 */
	
	public static void main(String[] args) {
		SummaryRanges obj = new SummaryRanges();
		obj.addNum(1);
		
		for (Interval interval: obj.getIntervals()) {
			System.out.print("[" + interval.start + "," + interval.end + "]");
		}
		System.out.println();
		
		obj.addNum(3);
		
		for (Interval interval: obj.getIntervals()) {
			System.out.print("[" + interval.start + "," + interval.end + "]");
		}
		System.out.println();
		
		obj.addNum(7);
		
		for (Interval interval: obj.getIntervals()) {
			System.out.print("[" + interval.start + "," + interval.end + "]");
		}
		System.out.println();
		
		obj.addNum(2);
		
		for (Interval interval: obj.getIntervals()) {
			System.out.print("[" + interval.start + "," + interval.end + "]");
		}
		System.out.println();
		
		obj.addNum(6);
		
		for (Interval interval: obj.getIntervals()) {
			System.out.print("[" + interval.start + "," + interval.end + "]");
		}
		System.out.println();
	}
}
