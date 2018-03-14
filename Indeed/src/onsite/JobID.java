package onsite;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class JobID {
	/*
	 * 【基本题】
     *  You are given a list of jobs, each job has an ID number(type is long).
     *  Implement two functions,
     *  1.expire(long jobid) to set a job as "expired"
     *  2.isexpired(long jobid) to check if a job is "expired"
	 *  给一堆jobid, implement两个function:void expire(long id), bool isExpired(long id)。
	 *  
	 *  
	 * 【Follow up】
	 *  http://blog.csdn.net/Korey_sparks/article/details/52512870
	 *  http://blog.csdn.net/jiangnan2014/article/details/53735429
	 *  如果job id 超多，如何用16MB的memory记录expired 的id。
	 *  64bit的操作系统里面，16GB的内存如何存下4 Billion个jobid。 
	 *  
	 * 【思路】 每个jobid 是一个long，占64bit， 4 Billion id = 10^9个id 占64 * 10^9 bit == 8GB
	 *        1个GB是10^9 byte = 8 * 10^9个bit 
	 *        用BitSet --> 只需要4billion bit就够了 --> 4 * 10^9 bit = 0.5GB
	 */
	
	//【基本题】	
	/*
	Map<Long, Boolean> map;
	
	public JobID() {
		map = new HashMap<>();
	}
	
	public void expire(long jobId) {
		map.put(jobId, true);
	}
	
	public boolean isExpired(long jobId){
		if (!map.containsKey(jobId)) {
			return false;
		}
		
		return !map.get(jobId);
	}
	*/
	
	// 【Follow up】
	BitSet bs;
	
	public JobID() {
		 bs = new BitSet();
	}
	
	public void expire(long jobId) {
		bs.set((int)jobId);
	}
	
	public boolean isExpired(long jobId){
		return bs.get((int)jobId);
	}
	
    public static void main(String[] args) {
    	/*
    	long[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        JobID jb = new JobID(list);
        jb.expire(3);
        jb.expire(5);
        jb.expire(4);
        jb.expire(8);
        jb.expire(9);
        System.out.println(jb.map);
        System.out.println(jb.isExpired(9));
        */

    	JobID jb = new JobID();
    	jb.expire(3);
    	jb.expire(4);
    	jb.expire(5);
    	jb.expire(9);
    	jb.expire(8);
    	jb.expire(1000000000);
    	System.out.println(jb.isExpired(9));
    	System.out.println(jb.isExpired(5));
    	System.out.println(jb.isExpired(9));
    	System.out.println(jb.isExpired(2));
    	System.out.println(jb.isExpired(0));
    	
    	System.out.println(jb.bs.size());
    	System.out.println(jb.bs.cardinality());
    	
    	int index = 0;
    	System.out.println();
    	for (int i = 0; i < jb.bs.cardinality(); i++) {
    		index = jb.bs.nextSetBit(index);
    		System.out.println(index);
    		index++;
    	}
    	
	}
}
