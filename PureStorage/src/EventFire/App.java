package EventFire;

public class App {
	public static void main(String[] args) throws InterruptedException {
		//EventFireV2 ef2 = new EventFireV2();
		EventFireV3 ef2 = new EventFireV3();
		
		CallBack cb1 = new CallBack("cb1");
		CallBack cb2 = new CallBack("cb2");
		CallBack cb3 = new CallBack("cb3");
		CallBack cb4 = new CallBack("cb4");
		
		CallBack cb5 = new CallBack("cb5");
		CallBack cb6 = new CallBack("cb6");
		CallBack cb7 = new CallBack("cb7");
		CallBack cb8 = new CallBack("cb8");
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t1: Id " +  Thread.currentThread().getId());
				System.out.println("t1_1: " + ef2.eventQueue + " isFired: " + ef2.isFired);

				ef2.reg_cb(cb1);
				ef2.reg_cb(cb2);
				
				ef2.reg_cb(cb3);
				ef2.reg_cb(cb4);
				ef2.fire();
				
				System.out.println("t1_2: " + ef2.eventQueue + " isFired: " + ef2.isFired);
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t2: Id " +  Thread.currentThread().getId());
				ef2.reg_cb(cb5);				
				ef2.reg_cb(cb6);
				ef2.reg_cb(cb7);
				System.out.println("t2_1: " + ef2.eventQueue + " isFired: " + ef2.isFired);
				ef2.reg_cb(cb8);
				System.out.println("t2_2: " + ef2.eventQueue + " isFired: " + ef2.isFired);
				
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
