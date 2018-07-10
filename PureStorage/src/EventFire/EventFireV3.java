package EventFire;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventFireV3 {
	/*
	 * 顺序需要保证
	 * https://github.com/xieqilu/Qilu-leetcode/blob/master/PureStorage/TriggerSystem.cs
	 * 
	 * http://www.1point3acres.com/bbs/thread-298004-1-1.html
	 */
	
	
    public Queue<CallBack> eventQueue = new LinkedList<>();
    public boolean isFired = false;
    private Lock lock =  new ReentrantLock();
    
    public void reg_cb(CallBack cb) {
    	lock.lock();
        if (!isFired) {
        	eventQueue.offer(cb);
        	lock.unlock();
        } else {
        	lock.unlock();
            cb.call();
        }   	
    }
    
    public void fire() {
    	System.out.println("fire! -- " + Thread.currentThread().getId());        
        while (true) {
        	lock.lock();
        	if (!eventQueue.isEmpty()) {
            	lock.unlock();
        		CallBack cb = eventQueue.poll();
            	cb.call();
        	} else {
        		isFired = true; // atomic 
        		lock.unlock();
        		break;
        	}
        }
    }
}
