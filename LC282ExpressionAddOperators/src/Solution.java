import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string that contains only digits 0-9 and a target value, 
	 * return all possibilities to add binary operators (not unary) +, -, or * 
	 * between the digits so they evaluate to the target value.
	 * 
	 * Examples: 
	 * "123", 6 -> ["1+2+3", "1*2*3"] 
	 * "232", 8 -> ["2*3+2", "2+3*2"]
	 * "105", 5 -> ["1*0+5","10-5"]
	 * "00", 0 -> ["0+0", "0-0", "0*0"]
	 * "3456237490", 9191 -> []
	 */
	
	// https://leetcode.com/discuss/58614/java-standard-backtrace-ac-solutoin-short-and-clear
	// 时间复杂度
	// T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
	// T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
	// Thus T(n) = 4T(n-1);
	
    public static  List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        dfs(result, num, target, 0, sb, 0, 0);
        
        return result;
    }
    
    private static void dfs(List<String> result, String num, int target, int pos, StringBuilder sb, long eval, long mul) {
        if (num.length() == pos) {
            if (eval == target) {
                result.add(sb.toString());
            }
            return;
        }
        
        int len = sb.length();
        
        for (int i = pos; i < num.length(); i++) {
            if (pos != i && num.charAt(pos) == '0') {
                break; // 对于"05232"，只需要检查"0"即可，"05", "052", "0523"... 都不需要再检查，因此直接break，此处用continue也可以
            }
            
            long cur = Long.parseLong(num.substring(pos, i + 1));
            //System.out.println("pos = " + pos + " i = " + i + " cur = " + cur + " sb = " + sb.toString());
            if (pos == 0) {
                dfs(result, num, target, i + 1, sb.append(cur), cur, cur);
                sb.setLength(len);
            } else {
                dfs(result, num, target, i + 1, sb.append("+").append(cur), eval + cur, cur);
                sb.setLength(len);
                dfs(result, num, target, i + 1, sb.append("-").append(cur), eval - cur, -cur);
                sb.setLength(len);
                // 对于 9-2*3, 当计算2*3时，传入的eval是9-2, mul 是-2, 因此要用cur(3) * mul(-2)， 这一步总的结果是(9 - 2 + 2) + (3 * -2)， 因为
                // -2用作(-2 * 3)的乘数， 所以上一步传入的eval中要减去这个-2
                dfs(result, num, target, i + 1, sb.append("*").append(cur), eval - mul + cur * mul, cur * mul);
                sb.setLength(len);
            } 
        }
    }
    
    public static void main(String[] args) {
		String test = "123";
		System.out.println(addOperators(test, 5));
	}
}
