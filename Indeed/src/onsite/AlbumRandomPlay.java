package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class AlbumRandomPlay {
	/*
	 * 【题目】给出专辑Number和Tracking Numbers。
	 *       比如说，
	 *       	Album   Track
	 * 			  1       4
	 *            2       1
	 *            1       2
	 *            2       3
	 *            2       2
	 *            1       3
	 *            1       1
	 *       可以输出 (1,1),(1,2),(1,3),(1,4),(2,1),(2,2),(2,3)，也可以输出(2,1),(2,2),(2,3),(1,1),(1,2),(1,3),(1,4)。
	 *       要求随机输出听歌的顺序，要求同专辑歌的要在一起按顺序排列。
	 *       
	 * 【思路】用Map<Integer, List<Integer>>将统一专辑的track归类O(n)，然后sort (O(nlogn)), 然后随机输出。
	 *       随机是先生存0~n-1的list，random选择一个数，然后交换至队尾，然后再random一个范围-1的数，以此类推，time complexity:O(n)
	 */
	
	// Time: O(m * klogk -- m个album，每次插入平均k个track + n (输出n首track))
	// Space: O(n) n首track
	public static List<int[]> randomPlay(List<int[]> input) {
		List<int[]> result = new ArrayList<>();
	
		if (input == null || input.size() == 0) {
			return result;
		}
		
		// 如果输入有重复，则可以用TreeSet
		Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
		
		for (int[] music: input) {
			int key = music[0];
			int val = music[1];
			
			if (!map.containsKey(key)) {
				map.put(key, new PriorityQueue<>());
			}
			
			map.get(key).offer(val);
		}
		
		int albumSize = map.size();
		int range = albumSize;
		List<Integer> keyList = new ArrayList<>(map.keySet());
				
		for (int i = 0; i < albumSize; i++) {
			Random random = new Random(); 
			int randomNum = random.nextInt(range);
			int key = keyList.get(randomNum); // random number -- [0, range - 1]
			PriorityQueue<Integer> music = map.get(key);
			while (!music.isEmpty()) {
				result.add(new int[] {key, music.poll()});
			}
			
			// 交换当前randomNum位置的元素和list结尾的元素
			int tmp = keyList.get(randomNum);
			keyList.set(randomNum, keyList.get(range - 1));
			keyList.set(range - 1, tmp);
			
			range--;
		}

		return result;
	}
	
	public static void main(String[] args) {
		List<int[]> test11 = new ArrayList<>(Arrays.asList(new int[] {1, 4}, new int[] {2, 1}, new int[] {1, 2}, new int[] {2, 3}, 
														   new int[] {2, 2}, new int[] {3, 1}, new int[] {1, 3}, new int[] {1, 1}, 
														   new int[] {3, 3}, new int[] {3, 2}));
		
		for (int i = 0; i < 10; i++) {
			List<int[]> result11 = randomPlay(test11);
			printList(result11);
		}
	}
	
	private static void printList(List<int[]> list) {
		for (int[] e: list) {
			System.out.print("[" + e[0] + ", " + e[1] + "]  ");
		}
		System.out.println();
	}
}
