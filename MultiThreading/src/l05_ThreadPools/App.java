package l05_ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Processor implements Runnable {
	
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Started: " + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: " + id);
	}
}

public class App {
	public static void main(String[] args) {
		
		// Thread pool has n(here we have 2) executors(threads)
	
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// submit n(n = 5) tasks to executor
		// meaning that we assign 5 tasks to 2 threads.One thread will do one task at a time.
		// The thread will work a new task once its previous task done.
		// The advantage is there are lots of overhead to start a new thread. So here we recycle thread in thread pool to avoid overhead.
		int n = 5;
		
		for (int i = 0; i < n; i++) {
			executor.submit(new Processor(i));
		}
		
		// wait for all the threads done then shutdown
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
	}
}
