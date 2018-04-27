package l09_ProducerConsumerLowLevel;

public class App {
	public static void main(String[] args) throws InterruptedException {
		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consumer();
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
}
