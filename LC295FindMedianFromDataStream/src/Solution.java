import java.util.PriorityQueue;
import java.util.Queue;


public class Solution {

	public static void main(String[] args) {
		
		MedianFinder mf = new MedianFinder();
		mf.addNum(1);
		System.out.println(mf.findMedian());
		mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(3);
		System.out.println(mf.findMedian());
		mf.addNum(4);
		System.out.println(mf.findMedian());
		mf.addNum(5);
		System.out.println(mf.findMedian());
		mf.addNum(6);
		System.out.println(mf.findMedian());
		
	}
}
