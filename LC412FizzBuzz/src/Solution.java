import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;


public class Solution {
	/*
	 * Write a program that outputs the string representation of numbers from 1 to n.
	 * 
	 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
	 * For numbers which are multiples of both three and five output “FizzBuzz”.
	 * 
	 * Example:
	 * 
	 * n = 15,
	 * 
	 * Return:
	 * [
	 *     "1",
	 *     "2",
	 *     "Fizz",
	 *     "4",
	 *     "Buzz",
	 *     "Fizz",
	 *     "7",
	 *     "8",
	 *     "Fizz",
	 *     "Buzz",
	 *     "11",
	 *     "Fizz",
	 *     "13",
	 *     "14",
	 *     "FizzBuzz"
	 * ]
	 */
	
    public static List<String> fizzBuzz(int n) {
    	List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
        	if (i % 3 == 0 && i % 5 == 0) {
        		result.add("FizzBuzz");
        	} else if (i % 3 == 0) {
        		result.add("Fizz");
        	} else if (i % 5 == 0) {
        		result.add("Buzz");
        	} else {
        		result.add(String.valueOf(i));
        	}
        }
        
        return result;
    }
   
    // [Follow up] how do you optimize your code to make it more concise and extendable.
    // https://leetcode.com/problems/fizz-buzz/discuss/89936/Java-Fuzz-Buzz-Follow-up(no-if-else-and-extendable)
    /*
    public static List<String> fizzBuzz(int n) {

        TreeMap<Integer, String> map = new TreeMap<>(); 
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        // map.put(7, "Yuzz"); 
        // map.put(9, "Mozz"); add more encoding options here ... 
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) { 
            StringBuilder sb = new StringBuilder();
            for (int num : map.keySet()) {
                if (i % num == 0) sb.append(map.get(num));
            }
            result.add((sb.length() == 0) ? String.valueOf(i) : sb.toString());
        }
        
        return result;
    }   
    */
    public static void main(String[] args) {
		System.out.println(fizzBuzz(15));
	}
}
