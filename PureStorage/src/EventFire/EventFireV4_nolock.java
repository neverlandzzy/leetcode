package EventFire;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;


/*
 * 不使用lock，
 */
public class EventFireV4_nolock {
    public Deque<CallBack> eventQueue = new LinkedBlockingDeque<>();
    public boolean isFired = false; // 为了简洁，这里还使用boolean
    //public AtomicBoolean isFiredAtomic = new AtomicBoolean(false);
    
    public void reg_cb(CallBack cb) {
    	if (!isFired) {
    		eventQueue.offer(cb);
    		if (isFired) {
    			// 三种情况：
    			// 1. fire()中的while loop还在run，因此刚加入的cb也会被run -- eventQueue.isEmpty() == false
    			// 2. fire()中的while loop已经结束 -- 即queue是empty的 eventQueue.isEmpty() == true
    			// 3. fire()中的while loop已经结束，而刚加的cb没有被run -- 因此在这里 eventQueue.isEmpty() == false
    			if (!eventQueue.isEmpty()) {
    				CallBack lastCb = eventQueue.pollLast();
    				// 再取出时，有可能这个cb已经被其它thread的while loop run掉了，因此这里要再判断是否是空
    				if (lastCb != null) {
    					lastCb.call();
    				}
    			}
    		}
    	} else {
    		cb.call();
    	}
    }
    
    public void fire() {
    	isFired = true;
    	while (!eventQueue.isEmpty()) {
    		CallBack cb = eventQueue.poll();
    		cb.call();
    	}
    }
}
