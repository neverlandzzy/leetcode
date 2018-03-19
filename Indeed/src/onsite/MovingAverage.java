package onsite;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MovingAverage {
	/*
	 * 【题目】 一个Data Stream输入，每次读进来一个数。完成三个函数;
	 *		  1. record(int val)，输入的时候每次都自动调用record;
     *        2. getAvg(), 返回最近5分钟的平均值;
	 *        3. getNow(), 获取当前time_stamp;
	 *        
	 * 【Follow up 1】memory不够大怎么办（数据点非常密集，5分钟就把内存爆了）
	 * 【Follow up 2】findMedian 
	 * 
	 * 【思路】 基本题：用一个queue来存数据，一个sum存当前和，每次record和getAvg时pop掉过期的数据
	 *        
	 *        Follow up 1：数据点密集的话，选择10秒的时间段，合并数据，得到一个10秒的和和数据数量，那么queue.size就被一个int变量替换掉，
	 *        这样丢掉过期数据的时候要更新sum和数据总和。这样会造成一定的偏差，但是没办法，条件不好内存不够就忍忍吧。
	 *        
	 *        Follow up 2: 两种情况
	 *        (1) 若findMedian call得不多，可以用quickSelect，-- 平均复杂度O(n)，最坏O(n^2) -- LC215
	 *        (2) 若findMedian call得很频繁，可以用min/max Heap，插入时候每次复杂度是O(logN)，但findMedian的复杂度是O(1) -- LC295
	 *        
	 *        http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=166760
	 *  
	 */
	
	// 基本题
	public class BasicSolution {
		class Event{
		    int val;
		    int time;
		    public Event(int val, int time){
		        this.val = val;
		        this.time = time;
		    }
		}
		
		private Queue<Event> queue = new LinkedList<>();
		long sum = 0;
		
		// 这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
		// 假设返回的是秒
		private int getNow() {
			return 0;
		}
		
		private boolean isExpired(int curTime, int preTime) {
			return curTime - preTime > 300; // (60 * 5 = 300 秒)
		}
		
		private void removeExpiredEvent() {
			while (!queue.isEmpty() && isExpired(getNow(), queue.peek().time)) {
				Event event = queue.poll();
				sum -= event.val;
			}
		}
		
		public void record(int val) {
			Event event = new Event(val, getNow());
			queue.offer(event);
			sum += val;
			removeExpiredEvent();
		}
		
		public double getAvg() {
			removeExpiredEvent();
			if (!queue.isEmpty()) {
				return (double) sum / queue.size(); 
			}
			return 0;
		}
		
		// follow up 2: (1) quickSelect
		 public double getMedian(){
			 removeExpiredEvent();
			 List<Event> list = new ArrayList<>(queue);
			 
			 int n = list.size();
			 
			 if (n % 2 == 0) {
				 return 0.5 * (findKthLargest(list, n / 2 + 1) + findKthLargest(list, n / 2));
			 }
			 
			 return (double) findKthLargest(list, n / 2);
		 }
		 
		 public int findKthLargest(List<Event> list, int k) {
			int n = list.size();
			int i = quickSelect(list, 0, n - 1, k);
			
			return list.get(i).val;
		 }
			
		 // quick select 模板：找nums中第k小, LC215
		 private int quickSelect(List<Event> list, int low, int high, int k){
			 int left = low;
			 int right = high;
				
			 // 原方法选的是nums[high]做pivot，最好选随机点，或者中点。 否则，如果input是sorted array，时间复杂度就变成了n^2
			 int pivot = list.get((left + high) / 2).val;
			 swap(list, (left + high) / 2, high);
				
			 // put nums that are <= pivot to the left
			 // put nums that are  > pivot to the right
			 while (left < right) {
				 if (list.get(left).val > pivot) {
					 right--;
					 swap(list, left, right);
					 //right--;
				 } else {
					 left++;
				 }
			 }
			
			 swap(list, left, high);
				
			 // count the nums that are <= pivot from low
			 int count = left - low + 1;

			 if (count == k) {
				 return left;
			 }
				
			 if (count < k) {
				 // <= pivot的数不够，从右面继续找 k - count个
				 return quickSelect(list, left + 1, high, k - count);
			 } else {
				 // <= pivot的数太多，从左边找k个
				 return quickSelect(list, low, left - 1, k);
			 }
		 }
			
		 private void swap(List<Event> list, int i, int j) {
			 Event tmp = list.get(i);
			 list.set(i, list.get(j));
		     list.set(j, tmp);
		}
	}
	
	// follow up 1: 如果数据太密集，内存不够怎么办
	
	public class FollowUp1 {
		class Event {
			int val;
			int time;
			int size;
			
			public Event(int val, int time) {
				this.val = val;
				this.time = time;
				this.size = 1;
			}
		}
		
		private Deque<Event> deque = new ArrayDeque<>();
		private long sum = 0;
		private int size = 0;		
		private int interval = 10; // 每10s中合并一次数据，可以根据数据量adjust，越大误差越大，因为会删掉10s内的数据，10s内的数据有些可能在5min以内
		
		private int getNow() {
			return 0;
		}
		
		private boolean isExpired(int curTime, int preTime) {
			return curTime - preTime > 300; // (60 * 5 = 300 秒)
		}
		
		private void removeExpiredEvent() {
			while (!deque.isEmpty() && isExpired(getNow(), deque.peek().time)) {
				Event event = deque.poll();
				sum -= event.val;
				size -= event.size;
			}
		}
		
		public void record(int val) {
			Event lastEvent = deque.peekLast();
			
			// 若新来的数据与deque里最近一次event的间隔不超过interval(10s)，则与其合并
			// 否则，创建一个新event
			if (getNow() - lastEvent.time <= this.interval) {
				lastEvent.size++;
				lastEvent.val += val;
			} else {
				Event event = new Event(val, getNow()); 
				deque.offer(event);
			}
			
			size += 1;
			sum += val;
			removeExpiredEvent();
		}
		
		public double getAvg() {
			removeExpiredEvent();
			if (!deque.isEmpty()) {
				return (double) sum / size;
			}
			
			return 0;
		}
		
	}
	
	// follow up 2: (2) max/min heap
	public class FollowUp22 {
		class Event{
		    int val;
		    int time;
		    public Event(int val, int time){
		        this.val = val;
		        this.time = time;
		    }
		}
		
		private Queue<Event> queue = new LinkedList<>();
		long sum = 0;
		
		PriorityQueue<Event> minHeap = new PriorityQueue<>(new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				return e1.val - e2.val;
			}
		});
		
		PriorityQueue<Event> maxHeap = new PriorityQueue<>(new Comparator<Event>() {
			public int compare(Event e1, Event e2) {
				return e2.val - e1.val;
			}				
		}); 
		
		// 这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
		// 假设返回的是秒
		private int getNow() {
			return 0;
		}
		
		private boolean isExpired(int curTime, int preTime) {
			return curTime - preTime > 300; // (60 * 5 = 300 秒)
		}
		
		private void removeExpiredEvent() {
			while (!queue.isEmpty() && isExpired(getNow(), queue.peek().time)) {
				Event event = queue.poll();
				sum -= event.val;
				minHeap.remove(event);
				maxHeap.remove(event);
		        if (maxHeap.size() - minHeap.size() >= 2) {
		        	minHeap.add(maxHeap.poll());
		        } else if (minHeap.size() - maxHeap.size() >= 2) {
		        	maxHeap.add(minHeap.poll());
		        }
			}
		}
		
		public void record(int val) {
			Event event = new Event(val, getNow());
			queue.offer(event);
			sum += val;
			
	        if (maxHeap.isEmpty() || val <= maxHeap.peek().val) {
	        	maxHeap.add(event);
	        } else {
	        	minHeap.add(event);
	        }
	        
	        if (maxHeap.size() - minHeap.size() >= 2) {
	        	minHeap.add(maxHeap.poll());
	        } else if (minHeap.size() - maxHeap.size() >= 2) {
	        	maxHeap.add(minHeap.poll());
	        }
			removeExpiredEvent();
		}
		
		public double getAvg() {
			removeExpiredEvent();
			if (!queue.isEmpty()) {
				return (double) sum / queue.size(); 
			}
			return 0;
		}
		
		public double getMedian(){
	        if (maxHeap.size() > minHeap.size()) {
	        	return maxHeap.peek().val;
	        } else if (minHeap.size() > maxHeap.size()) {
	        	return minHeap.peek().val;
	        } else {
	        	return (double) (maxHeap.peek().val + minHeap.peek().val)/2;
	        }
		}
	}
	
}


