import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class Solution {
	/*
	 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
	 * Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or 
	 * just be idle.
	 * 
	 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are 
	 * doing different tasks or just be idle.
	 * 
	 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
	 * 
	 * Example 1:
	 * Input: tasks = ["A","A","A","B","B","B"], n = 2
	 * Output: 8
	 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
	 * 
	 * Note:
	 * The number of tasks is in the range [1, 10000].
	 * The integer n is in the range [0, 100].
	 */
	
	// 本题和LC358相似，但不同之处在于本题需要考虑是否加idle，另外结尾处要减去一个不必要的idle
	// FB高频题，follow-up及变种很多
	// https://weirenwang1.gitbooks.io/algorithm/
	// https://www.bbsmax.com/A/gAJGnoDozZ/
	
	// 更简洁的写法：
    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char t: tasks) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        PriorityQueue<Character> heap = new PriorityQueue<>(new Comparator<Character>() {
            public int compare(Character c1, Character c2) {
                return map.get(c2) - map.get(c1);
            }
        });

        heap.addAll(map.keySet());
        Queue<Character> queue = new LinkedList<>();
        
        int result = tasks.length;
        int counter = 0;
        
        while (!heap.isEmpty()) {
            char task = heap.poll();
            map.put(task, map.get(task) - 1);
            queue.offer(task);
            
            if (queue.size() <= n) {
                if (!heap.isEmpty()) {
                    continue;
                } else {
                    counter = n - queue.size() + 1;
                }
            }
            
        	// 此处与LC358不同，当queue的size大于n或者maxHeap为空时，要把queue中的元素全部加回到maxHeap中。否则，若像LC358一样，只加回1个元素，
        	// 则以后每一步都会有queue.size() <= n且maxHeap.isEmpty == true， 也就是每一步都要加idle，这样就错了
        	// 而LC358也不能像本题这样把queue的元素一次全部加回到maxHeap中。如果这样，对于"aabbcc"的例子，当第一遍生成了"abc"后，maxHeap中有数量相等的'a', 'b', 'c'
        	// 这样下一次maxHeap.poll()弹出的元素会是三者中随机的任一个，也就会有"acbabc"这种情况发生     
            
            while (!queue.isEmpty()) {
                char t = queue.poll();
                if (map.get(t) > 0) {
                    heap.offer(t);
                }
            }
            
            result += counter;
        }
        
        return result - counter;
    }
    
    /*
    public static int leastInterval(char[] tasks, int n) {
    	if (tasks == null || tasks.length == 0) {
    		return 0;
    	}
    	
    	int result = tasks.length;
        int counter = 0;
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c: tasks) {
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
        	public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
        		return o2.getValue() - o1.getValue();
        	}
        });
        
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        
        while (!maxHeap.isEmpty()) {
        	Map.Entry<Character, Integer> entry = maxHeap.poll();
        	entry.setValue(entry.getValue() - 1);
        	queue.offer(entry);
        	if (queue.size() <= n) {
        		if (maxHeap.isEmpty()) {
        			counter = n - queue.size() + 1;
        		} else {
        			continue;
        		}
        	}

        	// 此处与LC358不同，当queue的size大于n或者maxHeap为空时，要把queue中的元素全部加回到maxHeap中。否则，若像LC358一样，只加回1个元素，
        	// 则以后每一步都会有queue.size() <= n且maxHeap.isEmpty == true， 也就是每一步都要加idle，这样就错了
        	// 而LC358也不能像本题这样把queue的元素一次全部加回到maxHeap中。如果这样，对于"aabbcc"的例子，当第一遍生成了"abc"后，maxHeap中有数量相等的'a', 'b', 'c'
        	// 这样下一次maxHeap.poll()弹出的元素会是三者中随机的任一个，也就会有"acbabc"这种情况发生
        	while (!queue.isEmpty()) {
        		Map.Entry<Character, Integer> next = queue.poll();
        		if (next.getValue() > 0) {
        			maxHeap.offer(next);
        		}
        	}
        	
            result += counter;
        }
        
        return result - counter;
        
    }
    */
    public static void main(String[] args) {
		char[] test = {'A', 'A', 'A', 'B', 'B', 'B'};
		
		System.out.println(leastInterval(test, 2));
	}
}
