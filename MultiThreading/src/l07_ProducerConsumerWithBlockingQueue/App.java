package l07_ProducerConsumerWithBlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Implement Producer-Consumer with BlockingQueue

// BlockingQueue: FIFO, thread-safe
//
// Insert: put(), add(), offer()
// if the queue is not full, all methods succeed;
// if the queue is full, add() fails with an exception, put() blocks, offer() just returns false
//
// Remove: remove(), poll(), take()
public class App {
	
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
	
	private static void producer() throws InterruptedException {
		Random random = new Random();
		
		while (true) {
			queue.put(random.nextInt(100));
		}
	}
	
	private static void consumer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			Thread.sleep(100);
			if (random.nextInt(10) == 0) {
				Integer value = queue.take();
				System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
			}
		}
	}
}
