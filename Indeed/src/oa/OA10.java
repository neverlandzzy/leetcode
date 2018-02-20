package oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class OA10 {
	/*
	 * 【题目描述】http://www.1point3acres.com/bbs/thread-203167-1-1.html
	 *  大概是说 每个员工有个manager， manager的创新分数会限制员工的创新分数。 
	 *  比如：  如果manager 是5分，  员工是6分，那么员工就要decrease 到5分。 
	 *  
	 *  输入： 员工个数
	 *  ［0号员工的分数,－1］    //-1表示 这个员工没有上司，是CEO
	 *  ［1号员工的分数, x]     //x是他的manger，不一定是0
	 *  ［2号员工的分数, y］  
	 *  
	 *  输出：   公司总分数。
	 * 【代码】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=223896&highlight=indeed
	 * 
	 * 【Follow up】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=295767
	 */



 public static void main(String[] argv){
        System.out.println("Please enter: ");
        Scanner scanner = new Scanner(System.in);
        List<Integer> rateAry = new ArrayList<>();
        List<Integer> midAry = new ArrayList<>();
        Map<Integer, List<Integer>> idMap = new HashMap<>();
        int rootId = -1;
        int id = 0;
        while(scanner.hasNextInt()) {
            int curRate = scanner.nextInt();
            int curMid = scanner.nextInt();
            rateAry.add(curRate);
            midAry.add(curMid);
            idMap.putIfAbsent(curMid, new ArrayList<>());
            idMap.get(curMid).add(id);
            if(curMid == -1) {
                rootId = id;
            }
            id++;
        }
        scanner.close();

        for(int rate : rateAry) {
            System.out.println("Rate is: " + rate);
        }
        System.out.println("---------------------------------");
        
        Queue<Integer> q = new LinkedList<>();
        q.add(rootId);
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(idMap.containsKey(cur)) {
                int curRate = rateAry.get(cur);
                for(int child : idMap.get(cur)) {
                    if(rateAry.get(child) > curRate) {
                        rateAry.set(child, curRate);
                    }
                    q.offer(child);
                }
            }
        
            for(int rate : rateAry) {
            	System.out.println("Rate is: " + rate);
            }
        }
    }
}
