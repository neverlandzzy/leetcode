import java.util.Stack;

public class Solution {

    public static int getLastMoment(int n, int[] left, int[] right) {
        int max = 0;

        for (int i: left) {
            max = Math.max(max, i);
        }

        for (int i: right) {
            max = Math.max(max, n - i);
        }

        return max;
    }


    static class pair
    {
        int first, second;
        public pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }

    static void findPrefixCount(int p_arr[][],
                                int arr[][])
    {
        int m = arr.length;
        int n = arr[0].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = n - 1; j >= 0; j--)
            {
                if (arr[i][j] == 0)
                    continue;

                if (j != n - 1)
                    p_arr[i][j] += p_arr[i][j + 1];

                p_arr[i][j] += arr[i][j] == 1 ? 1 : 0;
            }
        }
    }
    static int numSubmat(int arr[][])
    {
        int m = arr.length;
        int n = arr[0].length;

        int [][]p_arr = new int[m][n];

        findPrefixCount(p_arr, arr);

        // variable to store the final answer
        int ans = 0;

        for (int j = 0; j < n; j++)
        {
            int i = m - 1;

            Stack<pair> q = new Stack<>();

            int to_sum = 0;

            while (i >= 0)
            {
                int c = 0;

                while (q.size() != 0 &&
                        q.peek().first > p_arr[i][j])
                {
                    to_sum -= (q.peek().second + 1) *
                            (q.peek().first - p_arr[i][j]);

                    c += q.peek().second + 1;
                    q.pop();
                }

                to_sum += p_arr[i][j];

                ans += to_sum;

                q.add(new pair(p_arr[i][j], c));

                i--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] left1 = {4, 3};
//        int[] right1 = {0, 1};
//        System.out.println(getLastMoment(4, left1, right1));
//
//        int[] left2 = {6};
//        int[] right2 = {1};
//        System.out.println(getLastMoment(9, left2, right2));

        int[][] test31 = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        int[][] test32 = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};
        int[][] test33 = {{1, 1, 1, 1, 1, 1}};
        int[][] test34 = {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};

        System.out.println(numSubmat(test31));
        System.out.println(numSubmat(test32));
        System.out.println(numSubmat(test33));
        System.out.println(numSubmat(test34));
    }
}
