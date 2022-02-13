public class Solution {
    /**
     * Each ship is located at an integer point on the sea represented by a cartesian plane, and each integer point may contain at most 1 ship.
     *
     * You have a function Sea.hasShips(topRight, bottomLeft) which takes two points as arguments and returns true If there is at least one ship in the rectangle represented
     * by the two points, including on the boundary.
     *
     * Given two points: the top right and bottom left corners of a rectangle, return the number of ships present in that rectangle. It is guaranteed that there are at most
     * 10 ships in that rectangle.
     *
     * Submissions making more than 400 calls to hasShips will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
     *
     * Example 1:
     *
     * Input:
     * ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
     * Output: 3
     * Explanation: From [0,0] to [4,4] we can count 3 ships within the range.
     *
     * Example 2:
     *
     * Input: ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
     * Output: 3
     *
     *
     * Constraints:
     *
     * On the input ships is only given to initialize the map internally. You must solve this problem "blindfolded". In other words, you must find the answer using the
     * given hasShips API, without knowing the ships position.
     * 0 <= bottomLeft[0] <= topRight[0] <= 1000
     * 0 <= bottomLeft[1] <= topRight[1] <= 1000
     * topRight != bottomLeft
     */

    public static int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1]) {
            return 0;
        }

        if (!sea.hasShips(topRight, bottomLeft)) {
            return 0;
        }

        if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
            // 上面已经判断过 sea.hasShips(topRight, bottomLeft)此处为true，所以当输入只是一个点时，这个点有且仅有一条船
            return 1;
        }

        int midX = (bottomLeft[0] + topRight[0]) / 2;
        int midY = (bottomLeft[1] + topRight[1]) / 2;

        return countShips(sea, new int[]{midX, midY}, bottomLeft) +
                countShips(sea, topRight, new int[]{midX + 1, midY + 1}) +
                countShips(sea, new int[] {midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1}) +
                countShips(sea, new int[] {topRight[0], midY}, new int[] {midX + 1, bottomLeft[1]});
    }
}
