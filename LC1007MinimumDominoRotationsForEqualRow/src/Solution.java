public class Solution {
    /**
     * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile
     * with two numbers from 1 to 6 - one on each half of the tile.)
     *
     * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
     *
     * Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.
     *
     * If it cannot be done, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
     * Output: 2
     * Explanation:
     * The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
     * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the
     * second figure.
     *
     * Example 2:
     *
     * Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
     * Output: -1
     * Explanation:
     * In this case, it is not possible to rotate the dominoes to make one row of values equal.
     *
     *
     * Constraints:
     *
     * 2 <= tops.length <= 2 * 10^4
     * bottoms.length == tops.length
     * 1 <= tops[i], bottoms[i] <= 6
     */

    public static int minDominoRotations(int[] tops, int[] bottoms) {
        int rotation = check(tops, bottoms, tops[0]);

        if (rotation != -1) {
            return rotation;
        } else {
            return check(tops, bottoms, bottoms[0]);
        }
    }

    private static int check(int[] tops, int[] bottoms, int firstElement) {
        int rotationTops = 0;
        int rotationBottoms = 0;

        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != firstElement && bottoms[i] != firstElement) {
                return -1;
            }

            if (tops[i] != firstElement) {
                rotationTops++;
            } else {
                rotationBottoms++;
            }
        }

        return Math.min(rotationTops, rotationBottoms);
    }

    public static void main(String[] args) {
        int[] tops1 = {2, 1, 2, 4, 2, 2};
        int[] bottoms1 = {5, 2, 6, 2, 3, 2};

        int[] tops2 = {3, 5, 1, 2, 3};
        int[] bottoms2 = {3, 6, 3, 3, 4};

        System.out.println(minDominoRotations(tops1, bottoms1));
        System.out.println(minDominoRotations(tops2, bottoms2));
    }
}
