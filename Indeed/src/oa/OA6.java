package oa;

import java.util.Arrays;
import java.util.Scanner;

public class OA6 {
	/*
	 *【题目描述】http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=147506
	 * 
	 * 题目是求把一个node list中的全部node变成good node需要改变的node数目。说是list，其实就是一个array，每个位置就代表一个node，比如array[i]就表示第i个node。
	 * array[i]的值就表示它指向的node。node的数目在1到N之间，所以数组每个元素的值也在这个范围。
	 * 
	 * good node的定义是至少满足以下任意一点：
	 * 1.它是头节点（node 1）
	 * 2.它指向头节点
	 * 3.它指向一个good node
	 * 
	 * 题目输入为： N, array
	 * 输出为：需要改变的node的数目
	 * 例如: 
	 * Input: N = 5, array = {1, 2, 3, 4, 5}
	 * Output: 4
	 * 
	 * 因为要改变2, 3, 4, 5。比如都去指向1，或者1<-2<-3<-4<-5, etc.
	 * 
	 *【题解】 并查集 http://yuanhsh.iteye.com/blog/2200515  
	 */
	
	private static int find(int[] parent, int x) {
		if (x == 0) {
			return 0;
		}
		
        if (parent[x] == -1 || parent[x] == x) {
            return x;
        }
        
        int p = find(parent, parent[x]);
        parent[x] = p;
        return p;
	}
	
    private static void union(int[] parent, int x, int y){
        int pX = find(parent, x);
        int pY = find(parent, y);
        
        if(pX != pY) {
            parent[pX] = pY;
        }
    }
    
    public static int minChanges(int[] A) {  
        int n = A.length;  
        int[] parent = new int[n];  
        Arrays.fill(parent, -1);  
        
        for (int i = 0; i < n; i++) {  
            union(parent, i, A[i]);  
        }  
        int count = 0;  
        for (int i = 1; i < n; i++) {  
            if (find(parent, i) == i)  
                count++;  
        }  
        return count;  
    }
    
    public static void main(String args[] ) throws Exception {  
        Scanner s = new Scanner(System.in);  
        int n = s.nextInt();  
        int[] A = new int[n];  
        for (int i = 0; i < n; i++) {  
            A[i] = s.nextInt() - 1;  
        }  
        s.close();  
        System.out.println(minChanges(A));  
    }

}
