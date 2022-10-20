public class Solution {
    /**
     * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding (i.e. it translates to a sequence of valid UTF-8 encoded characters).
     *
     * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
     *
     * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
     * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.
     * This is how the UTF-8 encoding would work:
     *
     *      Number of Bytes   |        UTF-8 Octet Sequence
     *                        |              (binary)
     *    --------------------+-----------------------------------------
     *             1          |   0xxxxxxx
     *             2          |   110xxxxx 10xxxxxx
     *             3          |   1110xxxx 10xxxxxx 10xxxxxx
     *             4          |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * x denotes a bit in the binary form of a byte that may be either 0 or 1.
     *
     * Note: The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.
     *
     *
     *
     * Example 1:
     *
     * Input: data = [197,130,1]
     * Output: true
     * Explanation: data represents the octet sequence: 11000101 10000010 00000001.
     * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
     *
     *
     * Example 2:
     *
     * Input: data = [235,140,4]
     * Output: false
     * Explanation: data represented the octet sequence: 11101011 10001100 00000100.
     * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
     * The next byte is a continuation byte which starts with 10 and that's correct.
     * But the second continuation byte does not start with 10, so it is invalid.
     *
     *
     * Constraints:
     *
     * 1 <= data.length <= 2 * 10^4
     * 0 <= data[i] <= 255
     */

    // https://leetcode.com/problems/utf-8-validation/discuss/400575/case
    public static boolean validUtf8(int[] data) {
        if(data == null || data.length == 0) return true;
        for(int i = 0; i < data.length;){
            int next = checkUTF(data,i);
            if(next == -1){
                return false;
            }
            i = next;
        }
        return true;
    }

    // 这个函数从position开始读的数，返回读入的数是否是有效的UTF-8编码。如果是 返回下一个需要读取的位置，如果不是返回-1，表示无效的UTF-8码.
    public static int checkUTF(int[] data, int position) {
        int allOne = helper(data[position]);
        if (allOne == 0) {
            return position + 1;
        }
        if (allOne >= 5 || allOne == 1) {
            return -1;
        }

        int i = position + 1;
        for(; i < data.length && i < allOne + position; i++) {
            int curr = helper(data[i]);
            if (curr != 1) {
                break;
            }
        }

        if (allOne + position == i) {
            return i ;
        }
        return -1;
    }

    // 这个函数可以帮我们count从左开始有一个连续的1 在 num中. 返回1的个数。
    public static int helper(int num){
        int result = 0;
        for(int i = 7; i >= 0; i-- ){
            if(((num >> i) & 1) == 1){
                result = 8 - i;
            }else{
                return result;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {197, 130, 1};
        int[] test2 = {235, 140, 4};

        System.out.println(validUtf8(test1));
        System.out.println(validUtf8(test2));
    }
}
