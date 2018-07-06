package l13_CallableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// Callable() can return value;
		// Callable<Type>(): Type -- return type, should match return value of call()
		// if we don't want return value, we can use following line:
		// Future<?> future = executor.submit(new Callable<Void>() {
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				if (duration > 2000) {
					throw new IOException("sleeping for too long");
				}
				
				System.out.println("Starting... ");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Finished.");
				return duration;
			}
		});
		
		executor.shutdown();
		
		// future.get() will block until the thread associated with future object finishes. Like executor.awaitTermination()
		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			// above throw new IOException("sleeping for too long"); will be caught here
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}
