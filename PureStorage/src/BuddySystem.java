
public class BuddySystem {
	/*
	 * 定义一个Buddy System为complete binary tree，一个node可能为0或者1，当且仅当所有child的值为1的时候node值为1；(a complete binary tree is a binary tree in every level, except possibly the last, 
	 * is completely filled, and all nodes are as far left as possible)
	 * 
	 * Given a complete binary tree with nodes of values of either 1 or 0, the following rules always hold:
	 *     (1) a node's value is 1 if and only if all its subtree nodes' values are 1
	 *     (2) a leaf node can have value either 1 or 0
	 *     
	 *     Implement the following 2 APIs:
	 *     set_bit(offset, length), set the bits at range from offset to offset+length-1
	 *     clear_bit(offset, length), clear the bits at range from offset to offset+length-1
	 * 
	 *     i.e. The tree is like:
	 *                   0
	 *               /      \
	 *              0         1
	 *            /   \      /  \
	 *           1     0    1    1
	 *          / \   / \  /
	 *         1   1 1  0  1
	 *         Since it's complete binary tree, the nodes can be stored in an array:
	 *         [0,0,1,1,0,1,1,1,1,1,0,1]
	 *	从offset开始到offset+len-1置为1或0，但是同时也会影响到上面的元素，所以要迭代处理。
	 * 	BuddyBitmapSystem(alienware)（offset并不只限于最后一行）
	 * 	假设bit存储在bits[level][number]里(offset从最后一层开始) : 考虑一层一层的改，而不是由下改到顶点；
	 */
	
	// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=226030
	// offset 和length指的是最后一行。但对最后一行的改动，会影响上面
	
	// 从最后一行开始set
	public static void set_bit(int[][] mem, int offset, int length) {
		int level = mem.length - 1;
		int left = offset;
		int right = offset + length - 1;
		
		while (level >= 0) {
			if (left > right) {
				break;
			}
			// 将当前行需要置1的位置置1
			for (int i = left; i <= right; i++) {
				mem[level][i] = 1;
			}
			
			// 检查左右边界：e.g.当left = 2时，leftBuddy = 3 即2和3会一起影响上一层的一位(1); 而假如left = 3, leftBuddy = 2，结果不会变
			// 同理检查右边界
			// 对于边界，只有两个值（如left = 2和leftBuddy = 3）同时为1时，上一层才需要置为1。因此用leftBit来判断上一层该位是否需要置为1
            int leftBuddy = left + (left % 2 == 1 ? -1 : 1);
            int rightBuddy = right + (right % 2 == 1 ? -1 : 1);

            int leftBit = mem[level][left] * mem[level][leftBuddy];
            int rightBit = mem[level][right] * mem[level][rightBuddy];
			
            left /= 2;
			right /= 2;
            level--;
            
            // 当leftBit为0时，说明上一层左边界不需要置为1，因此left++ (此时的left代表了上一层的左边界)
            // rightBit 同理
            if (leftBit == 0) {
            	left++;
            }
            
            if (rightBit == 0) {
            	right--;
            }
		}
	}
	
	// 从最后一行开始clear
	public static void clear_bit(int[][] mem, int offset, int length) {
		int level = mem.length - 1;
		int left = offset;
		int right = offset + length - 1;
		
		while (level >= 0) {
			for (int i = left; i <= right; i++) {
				mem[level][i] = 0;
			}
			
			// e.g. 对于最后一层2 ~ 5的修改会影响倒数第二层1~2 (2/2 ~ 5/2)
			left /= 2;
			right /= 2;
			level--;
		}
	}
	
	public static void main(String[] args) {
		// 2^n > offset + length
		// c = 2^(r - 1);  c == 2^(4 - 1) = 8 -- > 4层则每层需要8个数
		int[][] mem = new int[4][8];
		
		print(mem);
		set_bit(mem, 0, 8);
		print(mem);
		clear_bit(mem, 3, 4);
		print(mem);
	}
	
	private static void print(int[][] mem) {
		for (int[] row: mem) {
			for (int i: row) {
				System.out.print(i + ", ");
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
}
