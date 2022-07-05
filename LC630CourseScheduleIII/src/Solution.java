import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
	/**
	 * There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. 
	 * A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.
	 * 
	 * Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
	 * 
	 * Example:
	 * Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
	 * Output: 3
	 * 
	 * Explanation: 
	 * There're totally 4 courses, but you can take 3 courses at most:
	 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
	 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
	 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
	 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
	 * 
	 * Note:
	 * 1. The integer 1 <= d, t, n <= 10,000.
	 * 2. You can't take two courses simultaneously.
	 */
	
	// https://discuss.leetcode.com/topic/93790/short-java-code-using-priorityqueue
	// 将course按deadline排序，deadline早的课程在前面
	// 创建一个Priority Queue, 从大到小的顺序记录course的duration
	// 每次从array中取出一门课，加入到pq中，并计算完成这门课所需要的累积的时间，若发现不能完成这门课，则从pq中poll出一门课，因为pq中的按课程的duration排列，poll出的课的duration
	// 大于或等于当前这门课，因此poll出后就能保证当前这门课可以上。每次poll出duration相对较大的课，从而保证能够上尽可能多的课程
	
    public static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        for (int[] c: courses) {
        	time += c[0];
        	pq.offer(c[0]);
        	
        	if (time > c[1]) {
        		time -= pq.poll();
        	}
        }       
        
        return pq.size();
    }
    
    public static void main(String[] args) {
		int[][] test = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
		
		System.out.println(scheduleCourse(test));
	}
}
