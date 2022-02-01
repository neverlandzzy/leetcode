import java.util.HashMap;
import java.util.Map;


public class Solution {
	
	// https://segmentfault.com/a/1190000005678407
	
	
	// 此方法已经不能AC，因为double有精度误差，double是有限的，斜率是无限的， 使用double表示斜率，是不严谨的也是不正确的。
	// 表示斜率最靠谱的方式是用最简分数，即分子分母都无法再约分了。分子分母同时除以他们的最大公约数gcd即可得到最简分数
	
	 public static int maxPoints(int[][] points) {
		 if (points == null || points.length == 0) {
			 return 0;
		 }
		 
		 int n = points.length;
		 int max = 0;
		 
		 for (int i = 0; i < n; i++) {
			 Map<String, Integer> map = new HashMap<>();
			 int samePoint = 1;
			 int curMax = 0;
			 
			 for (int j = i + 1; j < n; j++) {
				 if (points[j][1] == points[i][1] && points[j][0] == points[i][0]) {
					 samePoint++;
				 } else {
					 String slope = slopeInString(points[i], points[j]);
					 if (!map.containsKey(slope)) {
						 map.put(slope, 1);
					 } else {
						 map.put(slope, map.get(slope) + 1);
					 }
					 
					 curMax = Math.max(curMax, map.get(slope));
				 }
			 }
			 
			 max = Math.max(max, curMax + samePoint);
		 }
		 
		 return max;
	 }
	
	
	private static String slopeInString(int[] p1, int[] p2) {
		int dy = p1[1] - p2[1];
		int dx = p1[0] - p2[0];
		
		// 这里不考虑正负也可以，但为了gcd概念清晰，计算gcd时只考虑正数，符号单独处理
		String sign = ((dy >= 0 && dx >= 0) || (dy <= 0 && dx <= 0)) ? "+" : "-";
		int gcd = gcd(Math.abs(dy), Math.abs(dx));
		String slope = sign + Math.abs(dy) / gcd + "/" + Math.abs(dx) / gcd;
		
		return slope;
	}
	
	// greatest common divisor
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		
		return gcd(b, a % b);
	}
	
	
	/*  WRONG -- common mistake, previously can AC 
    public static int maxPoints(Point[] points) {
       int n = points.length;
       int max = 0;
       
       for (int i = 0; i < n; i++) {
           HashMap<Double, Integer> map = new HashMap<Double, Integer>();
           int samePoints = 0;
           int maxPoints = 0;
           
           for (int j = i + 1; j < n; j++) {
        	   if (points[i].x == points[j].x && points[i].y == points[j].y) {
        		   samePoints++;
        	   } else {       	   
	        	   double slope = slope(points[i], points[j]);
	
	        	   if (!map.containsKey(slope)) {
	        		   map.put(slope, 1);
	        	   } else {
	        		   map.put(slope, map.get(slope)+1);
	        	   }
	        	   maxPoints = Math.max(maxPoints, map.get(slope));
        	   }    		  
           }
           max = Math.max(max, maxPoints + samePoints + 1);
    	   
       }
       
       return max;
    }
    
    private static double slope(Point p1, Point p2) {
    	if (p1.x == p2.x) {
    		return Double.POSITIVE_INFINITY;
    	} 

    	if (p1.y == p2.y) {
    		return 0;
    	}
    	
    	return (double)(p1.y - p2.y) / (double)(p1.x - p2.x);
    }
    */

	
    public static void main(String[] args) {
    	int[][] test1 = {{0, 0}, {1, 1}, {2, 2}, {2, 2}, {3, 3}, {3, 5}, {6, 7}, {6, 7}, {6, 7}, {0, 0}};
    	int[][] test2 = {{0, 0}, {94911151, 94911150}, {94911152, 94911151}};

		System.out.println(maxPoints(test1));
		System.out.println(maxPoints(test2));
	}
}
