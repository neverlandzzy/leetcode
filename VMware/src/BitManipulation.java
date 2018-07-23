import java.util.ArrayList;
import java.util.Collections;
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
	
    public static List<Integer> getOneBits(int n) {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int counter = 0;
        int index = 0;
        int highest = 0;
        
        while (n > 0) {
            int bit = n & 1;
            if (bit == 1) {
                counter++;
                list.add(index);
            }
            index++;
            highest++;
            n = n >> 1;
        }
        
        result.add(counter);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            result.add(highest - list.get(size - i - 1));
        }
        return result;
    }
    
    public static List<Integer> getOneBits2(int n) {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int counter = 0;
        int index = 0;
        
        while (n > 0) {
            int bit = n & 1;
            if (bit == 1) {
                counter++;
                list.add(index);
            }
            index++;
            n = n >> 1;
        }
        
        result.add(counter);
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(index - list.get(i));
        }
        return result;
    }
    
    
    public static List<Integer> getOneBits3(int n) {
        List<Integer> result = new ArrayList<>();
        int counter = 0;
        int index = 0;
        
        while (n > 0) {
            int bit = n & 1;
            if (bit == 1) {
                counter++;
                result.add(index);
            }
            index++;
            n = n >> 1;
        }
        
        result.add(counter);
        Collections.reverse(result);
        
        for (int i = 1; i < result.size(); i++) {
            result.set(i, index - result.get(i));
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		
		System.out.println(getOneBits(3)); // 11
		System.out.println(getOneBits(8)); // 1000
		System.out.println(getOneBits(161)); // 10100001
		System.out.println(getOneBits(853));  // 1101010101
		System.out.println("=====");
		
		System.out.println(getOneBits2(3)); // 11
		System.out.println(getOneBits2(8)); // 1000
		System.out.println(getOneBits2(161)); // 10100001
		System.out.println(getOneBits2(853));  // 1101010101
		
		System.out.println("=====");
		
		System.out.println(getOneBits3(3)); // 11
		System.out.println(getOneBits3(8)); // 1000
		System.out.println(getOneBits3(161)); // 10100001
		System.out.println(getOneBits3(853));  // 1101010101
	}

}
