package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRooms {
	/*
	 * 【基本题】类似LC252 meeting rooms，输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 -> 1300， 9：30 -> 930， 
	 *  看新的meeting 能不能安排到meetings.初始给的intervals可能互相重叠
	 *  eg: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;
	 * 
	 * 【Follow up 1】类似merge interval，唯一的区别是输出，输出空闲的时间段，merge完后，再把两两个之间的空的输出就好，注意要加上0 - 第一个的start time
	 * 
	 * 【Follow up 2】目前没有面经，有人提到用PriorityQueue, 可能类似LC253 meeting roomsII， 即找区间的overlapping -- 练习LC253, LC731, LC732
	 *  
	 */
	
	// 【基本题】 当新插入的time.start < 已有meeting.end 并且time.end > meeting.start时，会有冲突。 LC252中meetings已经按start排好序， 
	//          所以已经保证intervals[i + 1].end > intervals[i].start 只需if (intervals[i + 1].start < intervals[i].end)，则可判定有重复
	//  O(n)
	public static boolean canAttendMeetings(List<int[]> meetings, int[] time) {
		for (int i = 0; i < meetings.size(); i++) {
			int[] meeting = meetings.get(i);
			
			// time.start < meeting.end && time.end > meeting.start
			if (time[0] < meeting[1] && time[1] > meeting[0]) {
				return false;
			}
		}
		
		return true;
	}
	
	// 【Follow up 1】类似merge interval
	// O(nlogn + n)
	public static List<int[]> freeTime(List<int[]> meetings) {
		List<int[]> result = new ArrayList<>();
		if (meetings == null || meetings.size() == 0) {
			return result;
		}
		
		Collections.sort(meetings, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for (int i = 0; i < meetings.size() - 1; i++) {
			if (meetings.get(i + 1)[0] <= meetings.get(i)[1]) {
				meetings.get(i)[1] = Math.max(meetings.get(i)[1], meetings.get(i + 1)[1]);
				meetings.remove(i + 1);
				i--;
			}
		}
		
		for (int i = 0; i < meetings.size() - 1; i++) {
			int[] free = {meetings.get(i)[1], meetings.get(i + 1)[0]};
			result.add(free);
		}
		
	    if (meetings.get(0)[0] > 0) {
	        result.add(0, new int[] {0, meetings.get(0)[0]});
	    }
	      
	    if (meetings.get(meetings.size() - 1)[1] < 2400) {
	    	result.add(new int[] {meetings.get(meetings.size() - 1)[1], 2400});
	    }
	      
		return result;
	}
	
	/*
	private static int convert(int test) {
		if (test % 100 >= 60) {
			test = (test / 100) *100 + 59;
		}
		
		return test;
	}
	
	*/
	// 【Follow up 2】 找最大overlap  O(nlogn)
	public static int overlap(List<int[]> meetings) {
        if (meetings == null || meetings.size() == 0) {
    		return 0;
    	}
        
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		
		for (int i = 0; i < meetings.size(); i++) {
			treeMap.put(meetings.get(i)[0], treeMap.getOrDefault(meetings.get(i)[0], 0) + 1);
			treeMap.put(meetings.get(i)[1], treeMap.getOrDefault(meetings.get(i)[1], 0) - 1);
		}
		
		int overlap = 0;
		int result = 0;
		
		for (int val: treeMap.values()) {
			overlap += val;
			result = Math.max(result, overlap);
		}
		//System.out.println(treeMap);
		return result;
	}
	// 【Follow up 2】 找最大overlap 解法2
	
	public static int overlap2(List<int[]> meetings) {
		if (meetings == null || meetings.size() == 0) {
    		return 0;
    	}
		
		// 将输入按start升序排序
		Collections.sort(meetings, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		// heap中按end升序排序
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		queue.offer(meetings.get(0));
		int result = 1;
		
		for (int i = 1; i < meetings.size(); i++) {
			// 没有冲突的时候，就弹出queue里的int[]
			while (!queue.isEmpty() && meetings.get(i)[0] >= queue.peek()[1]) {
				queue.poll();
			}
			
			queue.offer(meetings.get(i));
			result = Math.max(result, queue.size());
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【基本题】判断是否能安排一个新的meeting ***********");
		List<int[]> meetings1 = new ArrayList<>(Arrays.asList(new int[]{1300, 1500}, new int[]{930, 1200}, new int[]{830, 945}));
		int[] test11 = {820, 830};
		int[] test12 = {1450, 1500};
		int[] test13 = {750, 830};
		int[] test14 = {830, 930};
		int[] test15 = {830, 1200};
		int[] test16 = {1200, 1300};
		int[] test17 = {1130, 1330};
		
		System.out.println(canAttendMeetings(meetings1, test11)); //true
		System.out.println(canAttendMeetings(meetings1, test12)); //false
		System.out.println(canAttendMeetings(meetings1, test13)); //true
		System.out.println(canAttendMeetings(meetings1, test14)); //false
		System.out.println(canAttendMeetings(meetings1, test15)); //false
		System.out.println(canAttendMeetings(meetings1, test16)); //true
		System.out.println(canAttendMeetings(meetings1, test17)); //false
		System.out.println();
		System.out.println("*********** 【Follow up 1题】输出空闲时段 ***********");
		List<int[]> meetings21 = new ArrayList<>(Arrays.asList(new int[]{1300, 1500}, new int[]{930, 1200}, new int[]{830, 945}));
		List<int[]> meetings22 = new ArrayList<>(Arrays.asList(new int[]{1300, 1500}, new int[]{930, 1200}, new int[]{830, 945}, new int[]{0, 330}));
		//print(meetings21);
		List<int[]> result21 = freeTime(meetings21);
		List<int[]> result22 = freeTime(meetings22);
		//print(meetings21);
		print(result21);
		print(result22);
		System.out.println();
		System.out.println("*********** 【Follow up 2题】最大overlap ***********");
		List<int[]> meetings31 = new ArrayList<>(Arrays.asList(new int[]{1300, 1500}, new int[]{930, 1200}, new int[]{830, 945}));
		System.out.println(overlap(meetings31));
		System.out.println(overlap2(meetings31));
	}
	
	private static void print(List<int[]> list) {
		if (list == null || list.size() == 0) {
			System.out.println("null");
			return;
		}
		for (int[] t: list) {
			System.out.print("[" + t[0] + ", " + t[1] + "] ");
		}
		
		System.out.println();
	}
}
