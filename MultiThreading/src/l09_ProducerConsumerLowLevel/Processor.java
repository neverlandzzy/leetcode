package l09_ProducerConsumerLowLevel;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void producer() throws InterruptedException {	
		
		int value = 0;
		
		while (true) {
			synchronized(lock) {
				
				while (list.size() == LIMIT) {
					// once list is full, this thread will wait until being notified
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}			
		}
	}
	
	public void consumer() throws InterruptedException {
		
		Random random = new Random();
		while (true) {
			synchronized(lock) {
				
				while (list.size() == 0) {
					// once list is empty, this thread will wait until being notified
					lock.wait();
				}
				
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));
		}
		
	}
}
