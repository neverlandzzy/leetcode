import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Solution {
	/*
	 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
	 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. 
	 * Write an algorithm to reconstruct the queue.
	 * 
	 * Note:
	 * The number of people is less than 1,100.
	 * 
	 * Example
	 * 
	 * Input:
	 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	 * 
	 * Output:
	 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
	 */
	
	// https://leetcode.com/problems/queue-reconstruction-by-height/discuss/89345/Easy-concept-with-PythonC++Java-Solution
    public static int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        
        List<int[]> list = new ArrayList<>();
        for (int[] p: people) {
            list.add(p[1], p);
        }
        
        int[][] result = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            result[i] = list.get(i);
        }
        
        return result;
     }
    
    public static void main(String[] args) {
		int[][] test = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		int[][] result = reconstructQueue(test);
		
		for (int[] r: result) {
			System.out.print("[" + r[0] + ", " + r[1] + "]" + ", ");
		}
		System.out.println();
	}
}
