import java.util.*;


public class Solution {
	/**
	 * Given a collection of intervals, merge all overlapping intervals.
	 * 
	 * For example,
	 * Given [1,3],[2,6],[8,10],[15,18],
	 * return [1,6],[8,10],[15,18].
	 */

	public static int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}

		Arrays.sort(intervals, Comparator.comparingInt((int[] i) -> i[0]));
		List<int[]> list = new ArrayList<>();

		int[] newInterval = intervals[0];
		list.add(newInterval);

		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i][0] <= newInterval[1]) {
				newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
			} else {
				newInterval = intervals[i];
				list.add(intervals[i]);
			}
		}

		return list.toArray(new int[list.size()][]);
	}

	// Java 8, lambda expression
	public static List<Interval> merge_oldVersion(List<Interval> intervals) {
		Collections.sort(intervals, (Interval o1, Interval o2) -> o1.start - o2.start);
		
		for (int i = 0; i < intervals.size() - 1; i++) {
			if (intervals.get(i + 1).start <= intervals.get(i).end) {
				intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
				intervals.remove(i + 1);
				i--;
			}
		}
		
		return intervals;
	}
	
	/*
    public static List<Interval> merge_oldVersion(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i + 1).start <= intervals.get(i).end) {
                intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
                intervals.remove(i + 1);
                i--;
            }
        }
        
        return intervals;
    }
    */
    /*
    public static List<Interval> merge_oldVersion(List<Interval> intervals) {
    	
    	Comparator<Interval> comparator = new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				
				if (o1.start > o2.start) {
					return 1;
				} else if (o1.start < o2.start) {
					return -1;
				} else {
					return 0;
				}
			}
    		
    	};
    	
    	Collections.sort(intervals, comparator);
    	
    	for (int i = 0; i < intervals.size()-1; i++) {
    		if (intervals.get(i+1).start <= intervals.get(i).end) {
    			intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i+1).end);
    			intervals.remove(i+1);
    			i--;
    		}
    	}
    	
        return intervals;
    }
    */
    
    public static void main(String[] args) {
//		Interval interval1 = new Interval(1,3);
//		Interval interval2 = new Interval(8,10);
//		Interval interval3 = new Interval(2,6);
//		Interval interval4 = new Interval(15,18);
//		Interval interval5 = new Interval(1,4);
//		Interval interval6 = new Interval(0,2);
//		Interval interval7 = new Interval(3,5);
//
//		List<Interval> list = new ArrayList<Interval>();
//
//		list.add(interval1);
//		list.add(interval2);
//		list.add(interval3);
//		list.add(interval4);
//		list.add(interval5);
//		list.add(interval6);
//		list.add(interval7);
//
//		for (Interval tmp: list) {
//			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
//		}
//		System.out.println();
//
//		list = merge_oldVersion(list);
//		for (Interval tmp: list) {
//			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
//		}

		int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		int[][] intervals2 = {{1, 4}, {4, 5}};

		int[][] result1 = merge(intervals1);
		int[][] result2 = merge(intervals2);

		print(result1);
		print(result2);
	}

	private static void print(int[][] intervals) {
    	for (int[] interval: intervals) {
    		System.out.print("[" + interval[0] +  ", " + interval[1] + "]" + ", ");
		}
		System.out.println();
	}
}
