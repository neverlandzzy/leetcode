package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MeetingRoomsII {
	/*
	 *  问题大概是，给一堆time range，可能有重合。比如[1,3], [2,4], [5,6]
	 * 【基本题】总共的uniq的时间（去掉重合部分后求和）。上面例子返回3+1=4.
	 * 【Follow up 1】最长的连续range（能合并的合并，最长的range）。上面返回[1,4]的长度3
	 * 【Follow up 2】出现次数最多的range。上面[2,3]出现两次，所以返回[2,3]
	 */
	
	// 【基本题】去掉重合部分后的总共时间（类似LC56）
	//  O(nlogn)
	public static int totalTime(List<int[]> meetings) {
		if (meetings == null || meetings.size() == 0) {
			return 0;
		}
		
		int result = 0;
		
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
		
		for (int i = 0; i < meetings.size(); i++) {
			result += meetings.get(i)[1] - meetings.get(i)[0];
		}
		
		return result;
	}
	
	// 【Follow up 1】最长的连续range（能合并的合并，最长的range）
	//  O(nlogn)
	public static int longestRange(List<int[]> meetings) {
		if (meetings == null || meetings.size() == 0) {
			return 0;
		}
		
		int result = 0;
		
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
		
		for (int i = 0; i < meetings.size(); i++) {
			result = Math.max(result, meetings.get(i)[1] - meetings.get(i)[0]);
		}
		
		return result;
	}
	
	//【Follow up 2】出现次数最多的range。上面[2,3]出现两次，所以返回[2,3]
	// O(nlogn)
	public static List<int[]> mostOverlappingRange(List<int[]> meetings) {
		List<int[]> result = new ArrayList<>();
		
		if (meetings == null || meetings.size() == 0) {
			return result;
		}
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for (int[] meeting: meetings) {
			map.put(meeting[0], map.getOrDefault(meeting[0], 0) + 1);
			map.put(meeting[1], map.getOrDefault(meeting[1], 0) - 1);
		}

		//System.out.println(map);
		int max = 0;
		int overlap = 0;

		Integer start = null;
		Integer end = null;
		
		for (int value: map.values()) {
			overlap += value;
			max = Math.max(max, overlap);
		}

		overlap = 0;
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() == 0) {
				continue;
			}
			
			overlap += entry.getValue();
			if (overlap == max) {
				start = entry.getKey();
				
				continue;
			}
			
			if (start != null) {
				end = entry.getKey();
				
				result.add(new int[]{start, end});
				start = null;
				end = null;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【基本题】去掉重合部分后的总共时间（类似LC56） ***********");
		List<int[]> meetings11 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 4}, new int[]{5, 6}));
		List<int[]> meetings12 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}));
		System.out.println(totalTime(meetings11));
		System.out.println(totalTime(meetings12));
		System.out.println();
		
		System.out.println("*********** 【【Follow up 1】最长的连续range（能合并的合并，最长的range）***********");
		List<int[]> meetings21 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 4}, new int[]{5, 6}));
		List<int[]> meetings22 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}));
		System.out.println(longestRange(meetings21));
		System.out.println(longestRange(meetings22));
		
		System.out.println("*********** 【【Follow up 2】出现次数最多的range ***********");
		List<int[]> meetings31 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{2, 4}, new int[]{5, 6}));
		List<int[]> meetings32 = new ArrayList<>(Arrays.asList(new int[]{1, 5}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}));
		List<int[]> meetings33 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{3, 4}, new int[]{5, 6}));
		List<int[]> meetings34 = new ArrayList<>(Arrays.asList(new int[]{1, 3}, new int[]{3, 4}, new int[]{5, 6}, new int[]{3, 5}));
		printList(mostOverlappingRange(meetings31));
		printList(mostOverlappingRange(meetings32));
		printList(mostOverlappingRange(meetings33));
		printList(mostOverlappingRange(meetings34));
	}
	
	private static void printList(List<int[]> list) {
		for (int[] ele: list) {
			System.out.print("[" + ele[0] + ", " + ele[1] + "] ");
		}
		System.out.println();
	}
}
