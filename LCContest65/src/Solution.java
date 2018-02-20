import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {

    public static int[] anagramMappings(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
        	map.put(B[i], i);
        }
        
        for (int i = 0; i < n; i++) {
        	result[i] = map.get(A[i]);
        }
        
        return result;
    }
    
    
    public static String boldWords(String[] words, String S) {
    	List<Interval> list = new ArrayList<>();
    	
        for (String s: words) {
        	int i = 0;
        	while (i < S.length() && i != -1) {        	
        		int index = S.indexOf(s, i);
        		if (index != -1) {
        			list.add(new Interval(index, index + s.length() - 1));
        			i = index + 1;
        		} else {
        			i = -1;
        		}
        	}
        }

        List<Interval> result = merge(list);

        StringBuilder sb = new StringBuilder(S);
        
        int offset = 0;
        
        for (Interval interval: result) {
        	sb.insert(interval.start + offset, "<b>");
        	offset += 3;
        	sb.insert(interval.end + offset + 1, "</b>");
        	offset += 4;
        }
        return sb.toString();
    }
    
    public static List<Interval> merge(List<Interval> intervals) {
    	
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
    		if (intervals.get(i+1).start <= intervals.get(i).end + 1) {
    			intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i+1).end);
    			intervals.remove(i+1);
    			i--;
    		}
    	}
    	
        return intervals;
    }
 
    
    public static List<Interval> merge2(List<Interval> intervals) {
    	
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
    
    public static  List<Interval> employeeFreeTime(List<List<Interval>> avails) {
    	List<Interval> list = new ArrayList<>();

    	
    	for (List<Interval> avail: avails) {
    		for (Interval interval: avail) {
    			
    			list.add(interval);
    		}
    	}
    	
    	List<Interval> sorted = merge2(list);
    	
    	/*
		for (Interval tmp: sorted) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
    	System.out.println();
    	*/
    	List<Interval> result = new ArrayList<>();
    	
    	
    	for (int i = 0; i < sorted.size() - 1; i++) {
    		Interval free = new Interval(sorted.get(i).end, sorted.get(i + 1).start);
    		result.add(free);
    	}
    	return result;
    }
    
	public static void main(String[] args) {
		/*
		int[] testA = {12, 28, 46, 32, 50};
		int[] testB = {50, 12, 32, 46, 28};
		int[] result11 = anagramMappings(testA, testB);
		
		for (int i: result11) {
			System.out.print(i + ", ");
		}
		System.out.println();

		
		String[] test21 = {"ab", "bc"};
		System.out.println(boldWords(test21, "aabcd"));
		
		String[] test22 = {"ccb","b","d","cba","dc"};
		System.out.println(boldWords(test22, "eeaadadadc"));	
		
		String[] test23 = {"b","dee","a","ee","c"};
		System.out.println(boldWords(test23, "cebcecceab"));
		
		String[] test24 = {"e","cab","de","cc","db"};
		System.out.println(boldWords(test24, "cbccceeead"));
		*/
		
		
		List<List<Interval>> list11 = new ArrayList<>();
		Interval interval1 = new Interval(1,2);
		Interval interval2 = new Interval(5,6);
		Interval interval3 = new Interval(1,3);
		Interval interval4 = new Interval(4,10);

		
		List<Interval> list1 = new ArrayList<Interval>();
		List<Interval> list2 = new ArrayList<Interval>();
		List<Interval> list3 = new ArrayList<Interval>();
		
		list1.add(interval1);
		list1.add(interval2);
		list2.add(interval3);
		list3.add(interval4);
		list11.add(list1);
		list11.add(list2);
		list11.add(list3);
		
		List<Interval> result = employeeFreeTime(list11);

		for (Interval tmp: result) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		
		
		List<List<Interval>> list22 = new ArrayList<>();
		Interval interval5 = new Interval(1,3);
		Interval interval6 = new Interval(6,7);
		Interval interval7 = new Interval(2, 4);
		Interval interval8 = new Interval(2, 5);
		Interval interval9 = new Interval(9, 12);
		
		List<Interval> list4 = new ArrayList<Interval>();
		List<Interval> list5 = new ArrayList<Interval>();
		List<Interval> list6 = new ArrayList<Interval>();
		
		list4.add(interval5);
		list4.add(interval6);
		list5.add(interval7);
		list6.add(interval8);
		list6.add(interval9);
		list22.add(list4);
		list22.add(list5);
		list22.add(list6);
		System.out.println();
		
		List<Interval> result2 = employeeFreeTime(list22);

		for (Interval tmp: result2) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}

		System.out.println();
	}
}
