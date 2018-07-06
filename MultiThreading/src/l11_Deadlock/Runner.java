package l11_Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while (true) {
			// Acquire locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try {
				// tryLock(): return true if gets the lock, otherwise return false
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {
				if (gotFirstLock && gotSecondLock) {
					return;
				}
				
				if (gotFirstLock) {
					firstLock.unlock();
				}
				
				if (gotSecondLock) {
					secondLock.unlock();
				}
			}
					
			// Locks not acquired
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException {
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++) {
			/*
			lock1.lock();
			lock2.lock();
			*/
			acquireLocks(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException {
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++) {
			/*
			lock1.lock();
			lock2.lock();
			*/
			
			// Following code will cause dead locking
			// because at some time, the 1st thread acquires lock1 and the 2nd thread acquires lock2; 
			// then 1st thread requires lock2 while 2nd thread requires lock1, neither of them can proceed
			//lock2.lock();
			//lock1.lock();
			
			// Two solutions to avoid deadlock:
			// 1. always lock the lock in same order;
			// 2. see - acquireLocks(lock1, lock2); -- 若不能同时获得所有的锁，则释放已经获得的锁
			
			acquireLocks(lock2, lock1);
			
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}
