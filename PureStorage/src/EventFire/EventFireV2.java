package EventFire;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EventFireV2 {
	
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
    	//System.out.println("fire! -- " + Thread.currentThread().getId());
    	lock.lock();
        isFired = true; // atomic 
        lock.unlock();
        
        // 这不加锁不会有race condition, 因为执行到这里，isFired已经为true，其它线程不会再往queue里加东西。
        // 而在reg_cb里检查isFired和往queue里加东西时加了锁，因此只有加完之后，这里才能修改isFired。不会出现这里修改了isFired值后，reg_cb还继续往里加东西的情况
        while (!eventQueue.isEmpty()) {
        	CallBack cb = eventQueue.poll();
        	//lock.unlock();
        	cb.call();
        	//lock.lock();
        }
                
        //lock.unlock();

    }

}
