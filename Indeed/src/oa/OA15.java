package oa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OA15 {
	/*
	 * 【题目描述】 http://www.1point3acres.com/bbs/thread-172727-1-1.html (截屏) 和OA4, OA17相同
	 *  In this problem, you will need to find quantiles for a population of N values. These values are indexed 1, ..., N from lowest to
	 *  highest. The kth Q-quantile of such population is determined by computing the index Ik = N*k/Q. If Ik is not an integer, then it is rounded
	 *  up to the next integer to get the index. For a given value of Q, there will be (Q - 1) quantiles.
	 * 
	 *  For example, for Q=2 the 1st (and only) quantile of the population{3, 5, 6} is 5. This is also known as a median.
	 *  Explanation: N=3, Q=2, k=1, which means I1 = ceiling(3*1/2) = 2. Value at index 2 is 5.
	 *  
	 * 【Test case】http://www.1point3acres.com/bbs/thread-169620-1-1.html
	 * 
	 * 【代码】非二分法 http://www.1point3acres.com/bbs/thread-171621-1-1.html
	 * 【代码】二分法 http://www.1point3acres.com/bbs/thread-148840-1-1.html
	 * 【思路】二分法 http://www.1point3acres.com/bbs/thread-157890-1-1.html
	 * 
	 * 【输入】
	 *  Q
	 *  M
	 *  v1, c1
	 *  v2, c2
	 *  ...
	 *  vm, cm
	 *  
	 *  Q is the Q-quantile. M is the number of (v, c) pairs. vi is a value in the population and ci is its count. vi are 
	 *  guaranteed to be distinct, but may not necessarily appear in order.
	 *  1 <= N <= 10^12
	 *  2 <= Q, however, Q is not guaranteed to be less than N
	 *  1 <= M <= 100000
	 *  1 <= ci
	 */
	
	// Solution 1: 非二分法 O(M + Q) + Q(MlogM)
	// http://www.1point3acres.com/bbs/thread-171621-1-1.html
	// 遍历M个pair<value, count>的count，然后每count下找到是否有对应的index(通过公示算出每个Q对应的index)，有的话把pair的value加入到result中，换成下一个index，
	// 没有则遍历下一个M。这样程序只遍历的一遍M个pair，一遍(Q－1)个index。所以时间复杂度是O(M+Q)
	
	/*
    static class Pair{
        int val;
        int count;
        public Pair(int val, int count){
            this.val = val;
            this.count = count;
        }
    }

    private static List<Integer> findQuantiles(long N, long Q, int M, Pair[] pairs){
        List<Integer> result = new LinkedList<Integer>();
        long sum = 0, k = 1;
        long idx = (long)Math.ceil((double)N * k / Q);

        for(int i = 0; i < M; i++){
            sum += pairs[i].count;
            while(k <= Q - 1 && idx <= sum){
                //System.out.print(idx + " ");
                result.add(pairs[i].val);
                idx = (long)Math.ceil((double)N * ++k / Q);
            }
            if(k == Q) break;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Q = sc.nextInt();
        int M = sc.nextInt();
        long N = 0;
        Pair[] pairs = new Pair[M];

        for(int i = 0; i < M; i++){
            pairs[i] = new Pair(sc.nextInt(), sc.nextInt());
            N += pairs[i].count;
        }

        Arrays.sort(pairs, new Comparator<Pair>(){
            
            public int compare(Pair p1, Pair p2){
                return p1.val - p2. val;
            }
        });

        List<Integer> result = findQuantiles(N, Q, M, pairs);
        //System.out.println();
        for(int res: result) System.out.print(res + " ");

        sc.close();
    }
    */
    // Solution 2: 二分法 参考思路：http://www.1point3acres.com/bbs/thread-157890-1-1.html
	// O(Q * logM + M * logM) 进行了Q - 1次二分搜索，每次复杂度是logM， 排序的复杂度是M * logM
	
    static class Pair{
        int val;
        long count;
        public Pair(int val, long count){
            this.val = val;
            this.count = count;
        }
    }
    
    static private List<Integer> findQuantiles (long N, long Q, int M, Pair[] pairs) {
    	List<Integer> result = new LinkedList<Integer>();
    	
    	for (int i = 1; i < Q; i++) {
    		long index = (long)Math.ceil((double)N * i / Q);

    		int low = 0;
    		int high = M - 1;
    		
    		while (low < high) {
    			int mid = low + (high - low) / 2;
    			
    			if (pairs[mid].count < index) {
    				low = mid + 1;
    			} else {
    				high = mid;
    			}
    		}
    		
    		result.add(pairs[low].val);
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Q = sc.nextLong();
        int M = sc.nextInt();
        long N = 0;
        Pair[] pairs = new Pair[M];
        
        for (int i = 0; i < M; i++) {
        	Pair pair = new Pair(sc.nextInt(), sc.nextInt());
        	pairs[i] = pair;
        }
        // 此时，pairs里有：(5, 2) (6, 2) (7, 2) 
        Arrays.sort(pairs, new Comparator<Pair>(){           
            public int compare(Pair p1, Pair p2){
                return p1.val - p2. val;
            }
        });
        
        for (int i = 0; i < M; i++) {
        	N += pairs[i].count;
        	pairs[i].count = N;
        }
        // 将pairs里的元素转化为: (5, 2) (6, 4) (7, 6) 
        // 在此基础上做二分查找
        /*
        for (Pair p: pairs) {
        	System.out.print("(" + p.val + ", " + p.count + ") ");
        }
        System.out.println();
        */
        List<Integer> result = findQuantiles(N, Q, M, pairs);
        for(int res: result) System.out.print(res + " ");
        sc.close();
    }
    
}
