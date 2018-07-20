import java.util.ArrayList;
import java.util.List;


public class BitManipulation {
	/*
	 * counting set bit and output their positions, left most set bit position will be 1
	 * 
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=434189
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=272491
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=396933
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=271825
	 * 给一个数返回一个array，array第一个数是count input 数二进制里面有几个1， 后面是按顺序输出1所在的index位置
	 */
	
	public static int[] countSetBit(int num) {
		int counter = 0;
		List<Integer> list = new ArrayList<>();
		int index = 0;
		int highest = 0;
		
		while (num > 0) {
			int bit = num & 1;
			if (bit == 1) {
				counter++;
				list.add(index);
			}
			index++;
			highest++;
			num = num >> 1;
		}
		
		int size = list.size();
		int[] result = new int[size + 1];
		result[0] = counter;
		
		for (int i = 0; i < size; i++) {
			result[i + 1] = highest - list.get(size - i - 1);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] result1 = countSetBit(3);   // 11
		int[] result2 = countSetBit(8);   // 1000
		int[] result3 = countSetBit(236); // 11101100
		int[] result4 = countSetBit(853); // 1101010101
		
		print(result1);
		print(result2);
		print(result3);
		print(result4);
	}
	
	private static void print(int[] nums) {
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		
		System.out.println();
	}
}
