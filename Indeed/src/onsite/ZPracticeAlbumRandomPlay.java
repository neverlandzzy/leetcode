package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class ZPracticeAlbumRandomPlay {
	
	public static List<int[]> randomPlay(List<int[]> input) {

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
