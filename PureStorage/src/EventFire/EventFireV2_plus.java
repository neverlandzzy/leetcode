package EventFire;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventFireV2_plus {
    
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
    	lock.lock();
        isFired = true; // atomic 
        
        while (!eventQueue.isEmpty()) {
        	CallBack cb = eventQueue.poll();
        	lock.unlock();
        	cb.call();
        	lock.lock();
        }
                
        lock.unlock();

    }
}
