public class Solution {

    /**
     * You are given an n x n binary grid board. In each move, you can swap any two rows with each other,
     * or any two columns with each other.
     *
     * Return the minimum number of moves to transform the board into a chessboard board.
     * If the task is impossible, return -1.
     *
     * A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
     * Output: 2
     * Explanation: One potential sequence of moves is shown.
     * The first move swaps the first and second column.
     * The second move swaps the second and third row.
     *
     * Example 2:
     *
     *
     * Input: board = [[0,1],[1,0]]
     * Output: 0
     * Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
     *
     * Example 3:
     *
     *
     * Input: board = [[1,0],[1,0]]
     * Output: -1
     * Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.
     *
     *
     * Constraints:
     *
     * n == board.length
     * n == board[i].length
     * 2 <= n <= 30
     * board[i][j] is either 0 or 1.
     */

    // https://leetcode.com/problems/transform-to-chessboard/discuss/114847/C%2B%2BJavaPython-Solution-with-Explanation
    // https://leetcode.com/problems/transform-to-chessboard/discuss/132113/Java-Clear-Code-with-Detailed-Explanations
    //
    // 首先一个valid棋盘有几个特性：
    //  1. 当两列交换时，行之间的关系不变：相同的行扔然相同，不同的行仍然不同，行交互同理。
    //  2. 最终棋盘只有两种行和两种列，因此，由于1，初始状态的board也应该只有两种行和两种列
    //  3. 每一行和每一列都有一半的0，一半的1（n为奇数时，1和0的个数相差1）
    //  4. 对于一个valid棋盘，每一行要么和第一行相同，要么和第一行相反，对列同理
    //
    // 根据4，当第一行和第一列valid后，整个棋盘就valid，因此只需要计算需要多少步可以使第一行和第一列valid
    // 对于第一行(或列)，
    //  当N为偶数时(e.g. N=6)，最终形式可以是010101, 也可以是101010，可以随便选一个，然后比较当前状态变成valid状态需要几步，
    //  即比较当前状态和valid状态有几位不同，因为每次swap可以修复两位，所以需要move的步数就是 不同位数/2。然后从变成101010和010101
    //  中，选取步数较少的一个。当前状态如果和010101差k位，则和101010差(N-k)位，所以从k和N-k中取较小的一个即可
    //      e.g. 010011 变成 101010 有4位不同，需要swap 2步，变成010101有(6-4)/2 = 2位不同，需要swap 1步
    //
    //  当N为奇数时，则只能从k和N-k中选取偶数的一个。
    //      e.g. N=5, 最终形式可以是10101 或者01010。当前形式11000，只能变成后者
    public static int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowCounter = 0;
        int colCounter = 0;
        int rowToMove = 0;
        int colToMove = 0;

        // 判断当前board是否可以称为valid棋盘
        // 根据4，两行要么完全相同，要么完全相反，两列同理，因此valid board上任意一点与边界上的点组成的矩形中，有偶数个的0和偶数个的1
        // 即判断每一行是否与第一行要么相同要么相反，每一列是否与第一列要么相同要么相反
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((board[i][j] ^ board[0][0] ^ board[0][j] ^ board[i][0]) != 0) {
                    return -1;
                }
            }
        }

        // 接下来，判断第一行和第一列是否满足条件3。由于上面的判断，这里只需要判断第一行和第一列，不需要判断其它行列
        // 同时统计第一行和第一列需要swap的步数(这里与0101...比较)
        for (int i = 0; i < n; i++) {
            rowCounter += board[0][i] == 1 ? 1 : -1;
            colCounter += board[i][0] == 1 ? 1 : -1;
            if (board[0][i] != i % 2) {
                rowToMove++;
            }

            if (board[i][0] != i % 2) {
                colToMove++;
            }
        }

        if (Math.abs(rowCounter) > 1 || Math.abs(colCounter) > 1) {
            return -1;
        }

        if (n % 2 == 1) {
            // 当n为奇数时，只能从k和N-k中选取偶数的一个
            if (rowToMove % 2 != 0) {
                rowToMove = n - rowToMove;
            }
            if (colToMove % 2 != 0) {
                colToMove = n - colToMove;
            }
        } else {
            // 当n为偶数时，从k和N-k中选较小的一个
            rowToMove = Math.min(rowToMove, n - rowToMove);
            colToMove = Math.min(colToMove, n - colToMove);
        }

        return (colToMove + rowToMove) / 2;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 1, 1, 0}, {0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}};
        int[][] test2 = {{0, 1}, {1, 0}};
        int[][] test3 = {{1, 1}, {1, 0}};

        System.out.println(movesToChessboard(test1));
        System.out.println(movesToChessboard(test2));
        System.out.println(movesToChessboard(test3));
    }
}
