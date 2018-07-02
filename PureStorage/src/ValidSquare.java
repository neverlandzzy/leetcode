import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ValidSquare {
	
	// Base: 给平面上四个点，判断是否能组成一个正方形。每个点是由（x，y）坐标表示
	// Solution 1: 计算每两个点之间的距离，只有每个距离不为0，且只有两个不同值（边和对角线）时，为true
	/*
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int[] dist = {getDist(p1, p2), getDist(p1, p3), getDist(p1, p4), getDist(p2, p3), getDist(p2, p4), getDist(p3, p4)};
        
        for (int d: dist) {
        	map.put(d, map.getOrDefault(d, 0) + 1);
        }
        
        return !map.containsKey(0) && map.size() == 2;
    }
    */
    
    // Solution 2: 计算每两个点之间的距离，排序后检查0~3 是否相同，4和5是否相同
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    	int[] dist = {getDist(p1, p2), getDist(p1, p3), getDist(p1, p4), getDist(p2, p3), getDist(p2, p4), getDist(p3, p4)};
    	Arrays.sort(dist);
    	
    	return dist[0] == dist[3] && dist[4] == dist[5] && dist[4] > dist[0];
    }
    
    private static int getDist(int[] p1, int[] p2) {
    	return (p1[0] - p2[0]) * (p1[0] - p2[0]) +  (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    
    // http://www.1point3acres.com/bbs/thread-313528-1-1.html
    // https://blog.csdn.net/winddreams/article/details/41853127
    // Follow up: 给n个点，问可以组成多少个valid square，要求先O(n^4)，再改进到O(n^3)，最后改进到 O(n^2)
    // e.g. input:  {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}}
    //      output: 2
    // Solution 1: 暴力检查每四个点，O(n^4)
    // Solution 2: 固定3个点，可以推导出第四个点的坐标，O(n^3)
    // Solution 3: 固定2个对角线上的点，可以推导出另外两个点坐标，再用hashset判断是否存在：
    // 
    // 已知正方形对角线上两点坐标(x1, y1), (x3, y3)， 根据梯形中位线定理可以求得正方形中心坐标为((x1+x3)/2，(y1+y3)/2)
    // https://zhidao.baidu.com/question/138071998.html
    // X2=(X1+X3+Y3-Y1)/2 ， Y2=(Y1+Y3+X1-X3)/2
    // X4=(X1+X3-Y3+Y1)/2 ， Y4=(Y1+Y3-X1+X3)/2
    
    public static int validSquareII(int[][] points) {
    	Set<String> set = new HashSet<>();
    	int result = 0;
    	
    	for (int[] point: points) {
    		set.add(getKey(point));
    	}
    	
    	for (int i = 0; i < points.length; i++) {
    		for (int j = i + 1; j < points.length; j++) {
    			int[][] otherPoints = getOtherTwoPoints(points[i], points[j]);
    			if (otherPoints == null) {
    				continue;
    			}
    			int[] p3 = otherPoints[0];
    			int[] p4 = otherPoints[1];
    			
    			if (set.contains(getKey(p3)) && set.contains(getKey(p4))) {
    				result++;
    				//System.out.println("p1 = " +  getKey(points[i]) + " p2 = " + getKey(points[j]) + " p3= " + getKey(p3) + " p4 = " + getKey(p4));
    			}
    		}
    	}
    	
    	return result / 2;
    }
    
    private static String getKey(int[] point) {
    	return point[0] + ", " + point[1];
    }
    
    private static int[][] getOtherTwoPoints(int[] p1, int[] p2) {
    	int x1 = p1[0];
    	int y1 = p1[1];
    	int x2 = p2[0];
    	int y2 = p2[1];
    	
        if (x1 == x2 && y1 == y2) {
            return null;
        }
        
        int tmp1 = x1 + x2 + y1 - y2;
        int tmp2 = y1 + y2 - x1 + x2;
        int tmp3 = x1 + x2 - y1 + y2;
        int tmp4 = y1 + y2 + x1 - x2;
        
        if (tmp1 % 2 != 0 || tmp2 % 2 !=0 || tmp3 % 2 != 0 ||tmp4 % 2 != 0) {
        	return null;
        }       
        
        int[] p3 = new int[2];
        int[] p4 = new int[2];
        
        p3[0] = tmp1 / 2;
        p3[1] = tmp2 / 2;
        p4[0] = tmp3 / 2;
        p4[1] = tmp4 / 2;
        
        return new int[][] {p3, p4};
    }
    
    public static void main(String[] args) {
    	int[]p1 = {0, 0};
    	int[]p2 = {1, 1};
    	int[]p3 = {1, 0};
    	int[]p4 = {0, 1};
    	
    	System.out.println(validSquare(p1, p2, p3, p4));
    	
    	int[][] points = {{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}};
    	System.out.println(validSquareII(points));
	}
}
