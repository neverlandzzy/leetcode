package onsite;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import onsite.MovingAverage.BasicSolution.Event;

public class ZMovingAverage {
	/*
	 * 【题目】 一个Data Stream输入，每次读进来一个数。完成三个函数;
	 *		  1. record(int val)，输入的时候每次都自动调用record;
     *        2. getAvg(), 返回最近5分钟的平均值;
	 *        3. getNow(), 获取当前time_stamp;
	 *        
	 * 【Follow up 1】memory不够大怎么办（数据点非常密集，5分钟就把内存爆了）
	 * 【Follow up 2】findMedian 
	 *  
	 */
	
	public class Basic {
		
		class Event {
			int val;
			int time;
		    public Event(int val, int time){
		        this.val = val;
		        this.time = time;
		    }
		}
		
		Queue<Event> queue;
		long sum;
		final int THRESHOLD = 300;
		
		public Basic() {
			queue = new LinkedList<>();
			sum = 0;
		}
		
		public void  record(int val) {
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
		
		public int getNow() {
			return 0;
		}
		
		private boolean isExpired(Event e) {
			if (e.time - getNow() > THRESHOLD) {
				return true;
			}
			
			return false;
		}
	
		private void removeExpiredEvent() {
			while (!queue.isEmpty()) {
				if (isExpired(queue.peek())) {
					Event e = queue.poll();
					sum -= e.val;
				}
			}
		}
		
		public double getMedian(){
			removeExpiredEvent();
			List<Event> list = new ArrayList<>(queue);
			int n = list.size();
			
			if (n % 2 == 0) {
				return 0.5 * (findKth(list, n / 2) + findKth(list, n / 2 + 1));
			} else {
				return (double) findKth(list, n / 2);
			}
		}
		
		private int findKth(List<Event> list, int k) {
			int n = list.size();
			int index = quickSelect(list, 0, n - 1, k);
			return list.get(index).val;
		}
		
		private int quickSelect(List<Event> list, int low, int high, int k) {
			int left = low;
			int right = high;
			int mid = left + (right - left) / 2;
			int pivot = list.get(mid).val;
			
			swap(list, mid, right);
			
			while (left < right) {
				if (list.get(left).val > pivot) {
					right--;
					swap(list, left, right);
				} else {
					left++;
				}
			}
			swap(list, left, high);
			int count = left - low + 1;
			
			if (count == k) {
				return left;
			}
			
			if (count < k) {
				return quickSelect(list, left + 1, high, k - count);
			} else {
				return quickSelect(list, low, left - 1, k);
			}
		}
		
		private void swap(List<Event> list, int i, int j) {
			Event tmp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, tmp);
		}
	}
	
	
	public class FollowUp1 {
		class Event {
			int val;
			int time;
			int size;
		    public Event(int val, int time){
		        this.val = val;
		        this.time = time;
		        this.size = 1;
		    }
		}
		
		Deque<Event> deque;
		long sum;
		int size = 0;
		final int THRESHOLD = 300;
		final int INTERVAL = 10;
		
		public FollowUp1() {
			deque = new ArrayDeque<>();
			sum = 0;
		}
		
		public int getNow() {
			return 0;
		}
		
		public void  record(int val) {
			if (deque.peekLast().time - getNow() < INTERVAL) {
				deque.peekLast().val += val;
				deque.peekLast().size++;
			} else {
				Event event = new Event(val, getNow());
				deque.offer(event);
			}
			size++;
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
		
		private void removeExpiredEvent() {
			while (!deque.isEmpty() && isExpired(deque.peek())) {
				Event e = deque.poll();
				sum -= e.val;
				size -= e.size;
			}
		}
		
		private boolean isExpired(Event e) {
			if (e.time - getNow() > THRESHOLD) {
				return true;
			}
			
			return false;
		}
	}
	
}
