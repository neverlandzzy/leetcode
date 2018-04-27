package l12_Semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	// Semaphore vs lock:
	// lock: lock and unlock needs to be in same thread
	// semaphore: can lock and unlock from different threads
	// Semaphore is used to control access to some resource
	public static void main(String[] args) throws InterruptedException {
		
		/* Introduction
		// initialization: 1 permit
		Semaphore sem = new Semaphore(1);
		
		// after release(), we have 2 permits, like unlock
		sem.release();
		System.out.println("Avaialbe permits: " + sem.availablePermits());
		
		// after acquire(), we have 1 permit, like lock
		// if there is 0 permit, acquire() will wait until a permit gets released
		sem.acquire();
		System.out.println("Avaialbe permits: " + sem.availablePermits());
		*/		
		
		// newCachedThreadPool() will create new thread when calling executor.submit() and it will also try to 
		// reuse the threads
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 200; i++) {
			executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
}
