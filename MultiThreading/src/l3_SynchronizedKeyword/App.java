package l3_SynchronizedKeyword;

public class App {
	
	private int count = 0;

	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					count++;
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
