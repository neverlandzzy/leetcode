package l04_MultipleLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	/*
	// In this way, Worker as an object has only 1 transient lock, if one thread having transient lock and running
	// stageOne(), even if the other thread wants to run stageTwo(), it still has to wait for the first thread release
	// the lock. When stageOne() runs, it requires transient lock of Worker, same as stageTwo().
	
	public synchronized void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list1.add(random.nextInt(100));
	}
	
	public synchronized void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		list2.add(random.nextInt(100));
	}
	*/
	
	// Alternatively, instead of using synchronized methods, we can use synchronized blocks.
	// In this way, two threads can run stageOne() or stageTwo() at the same time, but if one thread
	// has entered the synchronized block, the other thread will have to wait. Two thread can run stageOne() and stageTwo() simultaneously 
	// because they are requiring different locks (lock1 and lock2)
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting ...");
		
		long start = System.currentTimeMillis();
		
		// Single-thread:
		// process();
		
		// Multi-thread:
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() +  ", List2: " + list2.size());
	}
}
