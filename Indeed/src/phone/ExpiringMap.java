package phone;

import java.util.HashMap;
import java.util.Map;

public class ExpiringMap<K, V> {
	/*
	 *【题目描述】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=203157 截屏在dropbox
	 * 要求写一个类似于hashmap的结构，其中除了传统的key和value之外，另加了一个duration。要求实现将一个数据存入Map后，在duration的时间范围内get()可以得到相应的value。
	 * 但一旦超过duration时间，get()只能返回null
	 * 
	 * e.g.
	 * put(10, 35, 3000) 
	 * 
	 * // 在3000ms以内
	 * get(10) --> return 35;
	 * 
	 * // 超过3000ms
	 * get(10) --> return null;
	 * 
	 */
	
	// basic version
	public class Data {
		V value;
		long duration;
		long timeStamp;
		
		public Data(V value, long duration, long timeStamp) {
			this.value = value;
			this.duration = duration;
			this.timeStamp = timeStamp;
		}
	}
	
	Map<K, Data> map;
	
	public ExpiringMap() {
		map = new HashMap<>();
	}
	
	public void put(K key, V value, long duration) {
		long timeStamp = System.currentTimeMillis();
		Data data = new Data(value, duration, timeStamp);
		map.put(key, data);
	}
	
	public V get(K key) {
		Data data = map.get(key);
		if (data == null) {
			return null;
		}
		
		if (data.timeStamp + data.duration >= System.currentTimeMillis()) {
			return data.value;
		} else {
			map.remove(key);
			return null;
		}
	}
	
	// Follow up 1: 如果在4个小时内你的程序挂了，可能是什么原因
	// 答： 如果一直put 不get，就会挂
	//
	// Follow up 2: 怎么解决？
	// 答： (1) 可以用heap维护size = k个元素，若heap.size() == k，则插入新data时，删掉heap顶的元素O(logk), 然后每次get()时候删除元素时间变成了O(k) --> 适用于get()多
	//     (2) 或者可以每次get()的时候去掉m个超时的元素 O(mlogk)，put()依旧是O(1) --> 适用于put多
	//
	// Follow up 3: 进一步优化 http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=203157
	// 一个map放key和对应的linked list node其中包含key, value, ts, ttl还有next node 和 pre node．这样你就可以直接通过map get到linked list中对应节点的位置．　
	// 这个实现和LRU是一样的.另外一个ttl的map其实是给不同ttl时间这个需求准备的，如果整个实现，对所有的key/value都只用同样一个ttl那就不需要，如果这个ttl不是固定的而是一个可调的参数，
	// 那就需要一个map来指向各个ttl的linkedlist.其实简单做就参照LRU，然后cleanup的策略改成检查ttl就行了
	
}
