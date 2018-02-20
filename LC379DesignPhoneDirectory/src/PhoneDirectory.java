import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class PhoneDirectory {
	/*
	 * Design a Phone Directory which supports the following operations:
	 * 
	 * get: Provide a number which is not assigned to anyone.
	 * check: Check if a number is available or not.
	 * release: Recycle or release a number.
	 * 
	 * Example:
	 * 
	 * Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
	 * PhoneDirectory directory = new PhoneDirectory(3);
	 * 
	 * It can return any available phone number. Here we assume it returns 0.
	 * directory.get();
	 * 
	 * Assume it returns 1.
	 * directory.get();
	 * 
	 * The number 2 is available, so return true.
	 * directory.check(2);
	 * 
	 * It returns 2, the only number that is left.
	 * directory.get();
	 * 
	 * The number 2 is no longer available, so return false.
	 * directory.check(2);
	 * 
	 * Release number 2 back to the pool.
	 * directory.release(2);
	 * 
	 * Number 2 is available again, return true.
	 * directory.check(2);
	 */
	
	// https://discuss.leetcode.com/topic/53094/java-ac-solution-using-queue-and-set
	
	HashSet<Integer> used;
	Queue<Integer> available;
	int max;
	
    /** Initialize your data structure here
    @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
	public PhoneDirectory(int maxNumbers) {
		this.used = new HashSet<>();
		this.available = new LinkedList<>();
		this.max = maxNumbers;
		
		for (int i = 0; i < maxNumbers; i++) {
			available.offer(i);
		}
	}

	/** Provide a number which is not assigned to anyone.
    @return - Return an available number. Return -1 if none is available. */
	public int get() {
		if (available.isEmpty()) {
			return -1;
		}
		
		int result = available.poll();
		used.add(result);
		return result;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		if (number >= max || number < 0) {
			return false;
		}
		
		return !used.contains(number);
	}

	/** Recycle or release a number. */
	public void release(int number) {
		if (used.remove(number)){
			available.offer(number);
		}
	}
}


/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */