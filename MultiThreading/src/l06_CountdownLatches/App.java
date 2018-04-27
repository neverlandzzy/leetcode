package l06_CountdownLatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// https://howtodoinjava.com/core-java/multi-threading/when-to-use-countdownlatch-java-concurrency-example-tutorial/
//
// CountDownLatch enables a java thread to wait until other set of threads completes their tasks.
// E.g. the main thread want to wait, till other service threads which are responsible for starting framework services 
//      have completed started all services.
// CountDownLatch works by having a counter initialized with number of threads, which is decremented each time a thread complete its execution. 
// When count reaches to zero, it means all threads have completed their execution, and thread waiting on latch resume the execution.

class Processor implements Runnable {
	
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// every time countDown() is called, CountDownLatch will decrease by 1
		latch.countDown();			
	}
}

public class App {
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		// 3 threads in thread pool
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		// each thread will get a task
		for (int i = 0; i < 10; i++) {
			executor.submit(new Processor(latch));
		}
		executor.shutdown();
		// wait CountDownLatch counts down to 0
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("Completed.");
	}
}
