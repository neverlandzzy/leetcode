package l02_ThreadSynchronization;

import java.util.Scanner;

class Processor extends Thread {
	// volatile - prevent thread to cache variables
	// run()在一个thread中，不断check running决定while loop是否继续。在某些系统里，这个thread会cache running的值。这样在main thread中即使调用了shutdown, 将running 
	// 改为false, 在某些系统中，run()的thread因为有了cache可能不会看到这个修改，而无法停止线程。用volatile修饰后的variable会阻止线程cache。另外的方法是用synchronization
	
	private volatile boolean running = true;
			
	public void run() {
		while(running) {
			System.out.println("hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false; 
	}
}
public class App {
	
	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
		scanner.close();
	}
}
