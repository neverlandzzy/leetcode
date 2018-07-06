package EventFire;

import java.util.LinkedList;
import java.util.Queue;

public class EventFireV2 {
	
    public Queue<CallBack> eventQueue = new LinkedList<>();
    public boolean isFired = false;
    
    public void reg_cb(CallBack cb) {
        if (!isFired) {
        	eventQueue.offer(cb);
        } else {
            cb.call();
        }
    }
    
    public void fire() {
    	System.out.println("fire!");
        isFired = true;
        while (!eventQueue.isEmpty()) {
        	eventQueue.poll().call();
        }
                

    }

}
