package onsite;

public class DiceSum {
	/*
	 * 写一个函数float sumPossibility(int dice, int target)，就是投dice个骰子，求最后和为target的概率。
	 * 因为总共的可能性是6^dice，所以其实就是combination sum，求dice个骰子有多少种组合，使其和为target。
	 * 
	 * 先用brute force的dfs来一个O(6^dice)指数复杂度的，然后要求优化，用dp，最后结束代码写的是两者结合的memorized search吧。
	 */
	
	// Solution 1: burte-force  Time: O(6 ^ dice) 每个dice有6种可能，一一尝试
	public static float sumPossibility1(int dice, int target) {
		if (dice <= 0 || target < dice || target > 6 * dice) {
			return 0;
		}
		
		double total = Math.pow(6, dice);
		
		int[] count = new int[1];
		helper1(dice, target, count);
		
		//System.out.println(count[0]);
		//System.out.println(total);
		return (float)count[0] / (float)total;
	}
	
	private static void helper1(int dice, int target, int[] count) {
		if (dice < 0 || target < dice || target > 6 * dice) {
			return;
		}
		
		if (dice == 0) {
			if (target == 0) {
				count[0]++;
			}
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			helper1(dice - 1, target - i, count);
		}
	}
	
	// Solution 2: DFS + mem 
	public static float sumPossibility2(int dice, int target) {
		if (dice <= 0 || target < dice || target > 6 * dice) {
			return 0;
		}
		
		double total = Math.pow(6, dice);
		
		int[][] cache = new int[dice + 1][target + 1];
		int count = helper2(dice, target, cache);

		return (float)count / (float)total;
	}
	
	private static int helper2(int dice, int target, int[][] cache) {
		int result = 0;
		
		if (dice == 0) {
			if (target == 0) {
				return 1;
			}
			return 0;
		}
		
		if (target > dice * 6 || target < dice) {
			return 0;
		}
				
		if (cache[dice][target] != 0) {
			return cache[dice][target];
		}
		
		for (int i = 1; i <= 6; i++) {
			result += helper2(dice - 1, target - i, cache);
		}
		
		cache[dice][target] = result;
		return result;
	}
	

	// Solution 3: DP
	public static float sumPossibility3(int dice, int target) {
		if (dice <= 0 || target < dice || target > 6 * dice) {
			return 0;
		}
		
		int[][] dp = new int[dice + 1][target + 1];
		double total = Math.pow(6, dice);
		
		// 1 个dice 可以构成1~6
		for (int i = 1; i <= Math.min(6, target); i++) {
			dp[1][i] = 1;
		}
		
		for (int i = 2; i <= dice; i++) {
			for (int j = i; j <= target; j++) {
				// i个dice构成j ==> i-1个dice构成j-1 && 第i个dice==1 +  i-1个dice构成j-2 && 第i个dice==2 + ...i-1个dice构成j-6 && 第i个dice==6
				for (int k = 1; k <= 6 && j - k >= i - 1; k++) {
					dp[i][j] += dp[i - 1][j - k];
				}
			}
		}
		
		return (float)dp[dice][target] / (float) total;
	}
	
	public static void main(String[] args) {
		System.out.println("Solution 1: Brute Force");
		System.out.println(sumPossibility1(2, 2));
		System.out.println(sumPossibility1(10, 40));
		System.out.println(sumPossibility1(10, 50));
		System.out.println();
		System.out.println("Solution 2: DFS + mem");
		System.out.println(sumPossibility2(2, 2));
		System.out.println(sumPossibility2(10, 40));
		System.out.println(sumPossibility2(10, 50));
		System.out.println();
		
		System.out.println("Solution 3: DP");
		System.out.println(sumPossibility3(2, 2));
		System.out.println(sumPossibility3(10, 40));
		System.out.println(sumPossibility3(10, 50));
		System.out.println();
		
		System.out.println("参考面经");
		System.out.println(getPossibility(2, 2));
		System.out.println(getPossibility(10, 40));
		System.out.println(getPossibility(10, 50));
		System.out.println();
	}
	
	
	
    public static double getPossibility(int dice, int target){
        if (dice <= 0 || target < dice || target > 6*dice) {
            return 0.0;
        }
        int total = (int) Math.pow(6, dice); //这里注意一下，pow的返回类型是double
        int[][] memo = new int[dice+1][target+1];
        int count = dfsMemo(dice, target, memo);
    /*
        System.out.println(count);
        for(int r = 0; r < memo.length; r++){
            for (int j = 0; j < memo[0].length; j++) {
                System.out.print(memo[r][j] + " ");
            }
            System.out.println();
        }
    */
        return (double)count/total;
    }
    public static int dfsMemo(int dice, int target, int[][] memo){
        int res = 0;
        //三个终止条件，能加速就加速吧。
        if (dice == 0){
            if (target == 0) return 1;
        }
        if (target > dice * 6 || target < dice){
            return 0;
        }
        if (memo[dice][target] != 0){
            return memo[dice][target];
        }

        for (int i = 1; i <= 6; i++){
            res += dfsMemo(dice-1, target - i, memo);
        }
        //这一步是更新记忆矩阵
        memo[dice][target] = res;
        return res;
    }
}

