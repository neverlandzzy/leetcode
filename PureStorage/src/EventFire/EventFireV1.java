package EventFire;

import java.util.LinkedList;
import java.util.Queue;

public class EventFireV1 {
	/*
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=228937
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=299273
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=293674
	 * 
	 * We want to implement a callback mechanism that allows listeners to register a function that will be invoked when the event fires.
	 * 
	 * The API functions are register_callback and event_fired.
	 * 	1. There is only one event and it will fire only once;
	 *  2. Callbacks registered before the event fires shouldn't block waiting for the event to fire.
	 *  3. Callbacks registered after the event fires should get invoked ASAP.
	 *  
	 *                                         event_fired()
	 *                                             |
	 *                                             |
	 *   ----------------------------------------------------------------------------> time
	 *       |               |                  cb1.call()              |
	 *       |               |                  cb2.call()              |
	 *   reg_cb(cb1)    reg_cb(cb2)                                  reg_cb(cb3)
	 *                                                               cb3.call()
	 *                                                               
	 *   CallBack.java 会给出
	 */
	
	// V1: 单线程基础版
	// 线程安全问题：
	// 1. visibility -- volatile 可以解决
	// 2. race condition -- volatile 没法解决，因此要加锁
	//    Thread_A的reg_cb在Thread_B的fire()修改isFired前，判定isFired == false，然后Thread_B执行了while loop(比如queue为空，while loop很快结束)，
	//    然后Thread_A又执行eventQueue.offer(cb);这个cb将永远没法被执行
	
    static Queue<CallBack> eventQueue = new LinkedList<>();
    static boolean isFired = false;
    
    public static void reg_cb(CallBack cb) {
        if (!isFired) {
        	eventQueue.offer(cb);
        } else {
            cb.call();
        }
    }
    
    public static void fire() {
    	System.out.println("fire!");
    	isFired = true;
        while (!eventQueue.isEmpty()) {
        	CallBack cb = eventQueue.poll();
        	cb.call();
        }
    }
    
    public static void main(String[] args) {
		CallBack cb1 = new CallBack("cb1");
		CallBack cb2 = new CallBack("cb2");
		CallBack cb3 = new CallBack("cb3");
		CallBack cb4 = new CallBack("cb4");
		
		reg_cb(cb1);
		reg_cb(cb2);
		reg_cb(cb3);
		reg_cb(cb2);
		System.out.println(eventQueue);
		fire();
		System.out.println(eventQueue);
		reg_cb(cb4);
		System.out.println(eventQueue);
		
		
	}
}
