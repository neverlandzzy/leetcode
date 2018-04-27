package l03_SynchronizedKeyword;

public class App {
	
	private int count = 0;

	// Every object in java has a transient/monitor lock(mutex).
	// When adding synchronized, you'll have to require the transient lock before accessing it.
	// 
	// Only ONE thread can require the transient lock at a time. If one thread obtains a 
	// transient lock and other thread is also requiring the lock, it will have to wait until the
	// first thread release the transient lock
	//
	// And if synchronized is called, we don't need to call volatile as it is being handled by 
	// synchronized. 
	public synchronized void increment() {
		count++;
	}
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		// join() will put the current thread on wait until t1 and t2 are dead.
		// otherwise, t1 and t2 will continue running after main thread calls print
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("Count is " + count);
	}
}
