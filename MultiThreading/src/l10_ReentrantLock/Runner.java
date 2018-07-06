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
	
	// 1. firstThread() gets lock
	// 2. at cond.await(); -- firstThread() will release the transient lock and wait
	// 3. secondThread() gets lock
	// 4. at cond.signal(); -- secondThread() will notify all threads are waiting;
	// 5. after secondThread() unlock, firstThread will get lock and continue
	// It is good practice that putting lock.unlock() in finally block. Otherwise, if increment() throw exception, lock.unlock() will never be run.
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
			System.out.println("secondThread unlock");
			lock.unlock();
		}
		scanner.close();		
	}
	
	public void finished() {
		System.out.println("Count is: " +  count);
	}
}
