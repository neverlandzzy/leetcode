import java.util.ArrayList;
import java.util.List;


public class DrawCircle {
	/*
	 * 
	 * https://www.cs.uic.edu/~jbell/CourseNotes/ComputerGraphics/Circles.html
	 * https://www.geeksforgeeks.org/mid-point-circle-drawing-algorithm/
	 * 
	 * 证明：(x+1)^2 + (y-1)^2 <= x^2 + y^2 ==> x + 1 <= y ==> x < y (0~45度之间);
	 *      (x+1)^2 + y^2 > x^2 + y^2 = 1;
	 *      
	 * Given a parameter r2, where the equation x^2+y^2=r^2 holds.
	 * Return a list of points that 
	 *     (1) x and y are both integers
	 *     (2) fits the circle equation
	 * 产生一个圆上的所有坐标。不用sqrt, sin, cos等内建函数。 提示：所有的点都是整点。首先我们可以利用对称性把圆分成8块，先画出0-45度角内
	 * 的点，然后映射之。对于其中0-45度角中的点，当X＋1时，Y的值或者不变或者－1，然后放入圆方程中看哪一个是对的。
	 * 
	 * 画圆的题目，这个题目说起来简单，一个简单的循环加对称就完了，但是
	 * follow up是很多的，关键有3点，
	 * 1 - 当r很小，如r==3时，如何多画几个点，使他尽量多画一些点，这些点可能不exactly在圆上，但是有这些点要比孤零零的一个圆四个点要好很多
	 * 2 - performance，尽量减少乘法以及开方的使用数量
	 *     （1）x从r变到0.707r 要比从0变到0.707r可以多画几个点， 
	 *     （2）终止条件可以写成for(x=0; x<y;x++ ),这样就可以少用一次乘法或开方 
	 *     （3）x加1之后的y只有两种可能，要么是和上次的y相等，要么是减去1，因为在这段范围内的圆斜率是小于1的，所以这里又可以少用一次乘法。最终版本的代码里面，只用了一次乘法
	 * 
	 */	    		
	public static List<int[]> circle1(int r) {
		/*
		List<int[]> result = new ArrayList<>();
		
		for (int x = (int)Math.sqrt(r); x <= r; x++) {
			for (int y = 0; y <= x; y++) {
				if (x * x + y * y == r * r) {
					result.add(new int[]{x, y});	
					result.add(new int[]{y, x});
					result.add(new int[]{-x, y});
					result.add(new int[]{y, -x});
					
					if (y != 0) {
						result.add(new int[]{-y, -x});
						result.add(new int[]{x, -y});
						result.add(new int[]{-x, -y});
						result.add(new int[]{-y, x});
					}					
				}
			}
		}
		
		return result;
		*/
		List<int[]> result = new ArrayList<>();
		
		for (int x = 0; x <= 0.707 * r; x++) {
			for (int y = x; y <= r; y++) {
				if (x * x + y * y == r * r) {
					addPoints(x, y, result);				
				}
			}
		}
		
		return result;
	}
	
	
	public static List<double[]> circle2(int r) {
		/*
		List<double[]> result = new ArrayList<>();
		
		for (double x = Math.sqrt(r); x <= r; x += 1) {
			for (int y = 0; y <= x; y++) {
				
				if (Math.abs(x * x + y * y - r * r) < 1e10) {
					result.add(new double[]{x, y});	
					result.add(new double[]{y, x});
					result.add(new double[]{-x, y});
					result.add(new double[]{y, -x});
					
					if (y != 0) {
						result.add(new double[]{-y, -x});
						result.add(new double[]{x, -y});
						result.add(new double[]{-x, -y});
						result.add(new double[]{-y, x});
					}					
				}
			}
		}
		
		return result;
		*/
		
		List<double[]> result = new ArrayList<>();
		
		for (double x = 0; x <= 0.707 * r; x += 1) {
			for (double y = x; y <= r; y++) {				
				if (Math.abs(x * x + y * y - r * r) < 1e-10) {
					result.add(new double[]{x, y});	
					result.add(new double[]{y, x});
					result.add(new double[]{x, -y});
					result.add(new double[]{-y, x});
					
					if (x != 0) {
						result.add(new double[]{-y, -x});
						result.add(new double[]{y, -x});						
						result.add(new double[]{-x, -y});
						result.add(new double[]{-x, y});
						
					}					
				}
			}
		}
		
		return result;
	}
	
	// 中点法：练习推导证明
	public static List<int[]> circle3(int r) {
		List<int[]> result = new ArrayList<>();
		int x = 0;
		int y = r;
		int d = 1 - r;
		addPoints(x, y, result);
		
		while (y > x) {
			if (d < 0) {
				d = d + 2 * x + 3;
				x += 1;
			} else {
				d = d + 2 * (x - y) + 5;
				x += 1;
				y -= 1;
			}
			
			addPoints(x, y, result);
		}
		
		return result;
	}
	
	private static void addPoints(int x, int y, List<int[]> result) {
		if (x != y) {
			result.add(new int[]{x, y});	
			result.add(new int[]{y, x});
			result.add(new int[]{x, -y});
			result.add(new int[]{-y, x});
			
			if (x != 0) {
				result.add(new int[]{-y, -x});
				result.add(new int[]{y, -x});
				result.add(new int[]{-x, -y});
				result.add(new int[]{-x, y});
			}	
		} else {
			result.add(new int[]{x, y});
			result.add(new int[]{x, -y});
			result.add(new int[]{-y, x});
			result.add(new int[]{-x, -y});
		}
	}
	
	public static void main(String[] args) {
		List<int[]> result1 = circle1(5);		
		print(result1);
		
		List<double[]> result2 = circle2(5);
		print2(result2);
		
		List<int[]> result3 = circle3(3);
		print(result3);
	}
	
	private static void print(List<int[]> list) {
		for (int[] points: list) {
			System.out.print("[" + points[0] + ", " + points[1] + "],  ");
		}
		
		System.out.println();
	}
	
	private static void print2(List<double[]> list) {
		for (double[] points: list) {
			System.out.print("[" + points[0] + ", " + points[1] + "],  ");
		}
		
		System.out.println();
	}
}
