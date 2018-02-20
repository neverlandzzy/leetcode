package oa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OA7 {
	/*
	 *【题目描述】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=202481
	 * 第一题是给你题目，让你5分钟想想你准备用什么数据结构， 准备用什么算法，要打英文的，注意：hackerrank不带拼写检查，所以自己敲好了贴比较靠谱。
	 * 第二题就是让写code，跟平时一样，不过要自己定义Interval class以及要用标准输入输出， 还有问题问的不在是让你merge，而是让你数merge完了有多少个数。
	 * 第三题就是让你分析时间和空间复杂度， 让你说一下如果再给你很多时间，你怎么改进你的算法云云。
	 * 
	 *【解法】LC56 注意区别在于输出是merge之后的interval的个数
	 */
	
	static class Interval { 
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }

	}
	
    public static int merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        
        int counter = intervals.size();
        
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i + 1).start <= intervals.get(i).end) {
                intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
                intervals.remove(i + 1);
                i--;
                counter--;
            }
        }
        
        /*
		for (Interval tmp: intervals) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		*/
        return counter;
    }
    
    public static void main(String[] args) {
    	// [1,3] [8,10] [2,6] [15,18] [1,4] [0,2] [3,5] 
    	// [0,6] [8,10] [15,18] 
		Interval interval1 = new Interval(1,3);
		Interval interval2 = new Interval(8,10);
		Interval interval3 = new Interval(2,6);
		Interval interval4 = new Interval(15,18);
		Interval interval5 = new Interval(1,4);
		Interval interval6 = new Interval(0,2);
		Interval interval7 = new Interval(3,5);
		
		List<Interval> list = new ArrayList<Interval>();
		
		list.add(interval1);
		list.add(interval2);
		list.add(interval3);
		list.add(interval4);
		list.add(interval5);
		list.add(interval6);
		list.add(interval7);
		
		for (Interval tmp: list) {
			System.out.print("[" + tmp.start + "," + tmp.end+"] ");
		}
		System.out.println();
		
		System.out.println(merge(list));
	}
}
