package l08_WaitAndNotify;

import java.util.Scanner;

public class Processor {
	
	// sequence:
	// 1. producer run first, then it will release transient lock and wait
	// 2. consumer then obtain the lock and run. And then wait for user input and notify()
	
	public void producer() throws InterruptedException {	
		synchronized(this) {
			System.out.println("Producer thread running");			
			// waits until being notified. During wait, it will release the transient lock
			// wait() and notify() have to be locked to same object, in this example, both of 
			// them are locked to this.
			
			// Here, both are locked to this, so wait() and notify() are corresponding to this.
			// if locked to other object, e.g. lock, then, here we should use lock.wait() and lock.notify()
			wait(); // -- this.wait()
			
			System.out.println("Resumed");
		}
	}
	
	public void consumer() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			
			// notify() will NOT release the release the transient lock. It just awake the waiting thread. 
			// The transient lock will be released when current thread completes.
			notify();
			Thread.sleep(5000);
		}
		
		scanner.close();
	}
}
