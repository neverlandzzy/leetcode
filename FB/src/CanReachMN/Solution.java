package CanReachMN;

public class Solution {
	/*
	 * 给一个Pair (M, N) 代表坐标，你从(1, 1)出发，每次 (x, y) => (x + y, y) or (x, x + y)向右下方移动，如果能达到(M, N)就是True，反之False. 
	 * 我一开始说BFS，面试官说不是最优，最优是 O(m+n)，然后就到QA环节了。
	 * 
	 * 思路：从(M, N) 出发，M 和 N 必定一大一小，否则不可能满足上述条件。所以两者中较大是 X + Y， 较小是 X 或 Y。由此从右下往左上反推，每一步都只可能有一个路径，
	 * 所以最终能到达(1, 1)则为 True。
	 */
	
	
	public boolean canReachMN(int m, int n) {
		int[] prev = {m, n};
		
		while (prev[0] >= 1 && prev[1] >= 1) {
			getPreviousPos(prev);
			
			if (prev[0] == 1 && prev[1] == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	private void getPreviousPos(int[] pos) {
		if (pos[0] < pos[1]) {
			pos[1] -= pos[0];
		} else {
			pos[0] -= pos[1];
		}
	}
}
