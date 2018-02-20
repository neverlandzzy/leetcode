import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * A city's skyline is the outer contour of the silhouette formed by all the buildings 
	 * in that city when viewed from a distance. Now suppose you are given the locations 
	 * and height of all the buildings as shown on a cityscape photo (Figure A), write a 
	 * program to output the skyline formed by these buildings collectively (Figure B).
	 * 
	 * The geometric information of each building is represented by a triplet of integers 
	 * [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the 
	 * ith building, respectively, and Hi is its height. It is guaranteed 
	 * that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all 
	 * buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
	 * 
	 * For instance, the dimensions of all buildings in Figure A are recorded as: 
	 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
	 * 
	 * The output is a list of "key points" (red dots in Figure B) in the format of 
	 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
	 * A key point is the left endpoint of a horizontal line segment. 
	 * Note that the last key point, where the rightmost building ends, is merely used to 
	 * mark the termination of the skyline, and always has zero height. Also, the ground in 
	 * between any two adjacent buildings should be considered part of the skyline contour.
	 * 
	 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], 
	 * [7 12], [12 0], [15 10], [20 8], [24, 0] ].
	 * 
	 * Notes:
	 * 
	 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
	 * The input list is already sorted in ascending order by the left x position Li.
	 * The output list must be sorted by the x position.
	 * There must be no consecutive horizontal lines of equal height in the output skyline. 
	 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three 
	 * lines of height 5 should be merged into one in the final output as such: 
	 * [...[2 3], [4 5], [12 7], ...]
	 */
	
	// SweepLine + heap 
	// (O(nlogn)) : 排序O(nlogn) + 2n个点每个要在PriorityQueue中进行删除操作(2n * logn)，
	
	public static class Point {
	    int pos;   		// 坐标
	    int height;     // 高度
	    int flag;       // 0 for start and 1 for end.
	    Point(int pos, int height, int flag) {
	        this.pos = pos;
	        this.height = height;
	        this.flag = flag;
	    }
	}
	
	public static List<int[]> getSkyline(int[][] buildings) {
		List<int[]> result = new ArrayList<>();
		
		if (buildings == null || buildings.length == 0) {
			return result;
		}
		
		List<Point> points = new ArrayList<>();
		int n = buildings.length;
		
		for (int i = 0; i < n; i++) {
			points.add(new Point(buildings[i][0], buildings[i][2], 0));
			points.add(new Point(buildings[i][1], buildings[i][2], 1));
		}
		
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point p1, Point p2){
				//坐标靠前的排前面
				if (p1.pos != p2.pos) {
					return p1.pos - p2.pos;
				}
				//坐标相同时，起点排在终点前
				if (p1.flag != p2.flag) {
					return  p1.flag - p2.flag;
				}
				
				//都是起点时，高度高的排前面 -- 高的高度先入栈，低的高度则跳过
				if (p1.flag == 0 && p2.flag == 0) {
					return p2.height - p1.height;
				}
				
				//都是终点时，高度低的排前面 -- 低的高度先出栈 e.g. [[1,2,1],[1,2,2],[1,2,3]]. 此时，只有高度最高的终点是最高点，否则每一个点都成了关键点
				return p1.height - p2.height;
			}
		});
		
		PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());
		
		for (Point p: points) {
			if (p.flag == 0) {
				// 当p是起点时，若p.height比heights里面的的值都大，则p是关键点，加入到result中
				// 然后要把p.height加入到heights里
				if (heights.isEmpty() || p.height > heights.peek()) {
					int[] keyPoint = new int[2];
					keyPoint[0] = p.pos;
					keyPoint[1] = p.height;
					result.add(keyPoint);
				}
				heights.add(p.height);
			} else {
				// 当p是终点时，若p.height是heights里的最大值，则将p.height从heights中poll出来，再次比较若p.height比heights里面的值都大p是关键点，加入到result中，
				// 然后要从heights里删除p.height
				// 这里poll()完再次比较，是因为若heights中还存在同等高度的点，则p不是关键点
				if (p.height == heights.peek()) {
					heights.poll();
					if (heights.isEmpty() || p.height > heights.peek()) {
						int[] keyPoint = new int[2];
						keyPoint[0] = p.pos;
						keyPoint[1] = heights.isEmpty() ? 0 : heights.peek(); // 这里要注意heights为空的情况，此时关键点的高度为0
						result.add(keyPoint);
					}
				} else {
					heights.remove(p.height);
				}
			}
		}
		return result;
	}
	
	/*
	// D & C
    public static List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<int[]>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private static LinkedList<int[]> recurSkyline(int[][] buildings, int p, int q) {
    	if (p < q) {
    		int mid = p + (q - p)/2;
    		return merge(recurSkyline(buildings, p, mid), recurSkyline(buildings, mid+1, q));
    	} else {
    		LinkedList<int[]> list = new LinkedList<int[]>();
    		list.add(new int[] {buildings[p][0], buildings[p][2]});
    		list.add(new int[] {buildings[p][1], 0});
    		return list;
    	}
    }

    private static LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> list = new LinkedList<int[]>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
        	int x = 0;
        	int h = 0;
        	
        	if (l1.getFirst()[0] < l2.getFirst()[0]) {
        		x = l1.getFirst()[0];
        		h1 = l1.getFirst()[1];
        		h = Math.max(h1, h2);
        		l1.removeFirst();
        	} else if (l1.getFirst()[0] > l2.getFirst()[0]) {
        		x = l2.getFirst()[0];
        		h2 = l2.getFirst()[1];
        		h = Math.max(h1, h2);
        		l2.removeFirst();
        	} else {
        		x = l1.getFirst()[0];
        		h1 = l1.getFirst()[1];
        		h2 = l2.getFirst()[1];
        		h = Math.max(h1, h2);
        		l1.removeFirst();
        		l2.removeFirst();
        	}
        	
            if (list.size() == 0 || h != list.getLast()[1]) {
                list.add(new int[] { x, h });
            }
        }
        
        list.addAll(l1);
        list.addAll(l2);
        return list;

    }
    */
    public static void main(String[] args) {
		int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {20, 24, 8}};
		
		List<int[]> list = getSkyline(buildings);
		
		for (int[] array: list) {
			System.out.print("[");
			for (int n: array) {
				System.out.print(n + " ");
			}
			
			System.out.print("] ");
		}
		
		
	}
}
