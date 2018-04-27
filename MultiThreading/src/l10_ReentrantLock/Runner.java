package l10_ReentrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		lock.lock();
		// like wait()
		System.out.println("Waiting... ");
		cond.await();
		System.out.println("Woken up!");
		try {
			increment();
		} finally {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press the return key!");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.out.println("Got return key");
		
		// like notify()
		cond.signal();
		
		try {
			increment();
		} finally {			
			lock.unlock();
		}
		scanner.close();
	}
	
	public void finished() {
		System.out.println("Count is: " +  count);
	}
}
