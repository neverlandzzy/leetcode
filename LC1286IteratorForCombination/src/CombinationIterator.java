import java.util.LinkedList;
import java.util.Queue;

public class CombinationIterator {

    /**
     * Design the CombinationIterator class:
     *
     * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
     * next() Returns the next combination of length combinationLength in lexicographical order.
     * hasNext() Returns true if and only if there exists a next combination.
     *
     *
     * Example 1:
     *
     * Input
     * ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
     * [["abc", 2], [], [], [], [], [], []]
     * Output
     * [null, "ab", true, "ac", true, "bc", false]
     *
     * Explanation
     * CombinationIterator itr = new CombinationIterator("abc", 2);
     * itr.next();    // return "ab"
     * itr.hasNext(); // return True
     * itr.next();    // return "ac"
     * itr.hasNext(); // return True
     * itr.next();    // return "bc"
     * itr.hasNext(); // return False
     *
     *
     * Constraints:
     *
     * 1 <= combinationLength <= characters.length <= 15
     * All the characters of characters are unique.
     * At most 104 calls will be made to next and hasNext.
     * It's guaranteed that all calls of the function next are valid.
     */

    int[] nums;
    boolean hasNext;
    String characters;
    int charactersLength;
    int combinationLength;

    public CombinationIterator(String characters, int combinationLength) {
        this.charactersLength = characters.length();
        this.combinationLength = combinationLength;
        this.characters = characters;

        hasNext = true;
        nums = new int[combinationLength];

        for (int i = 0; i < combinationLength; i++) {
            nums[i] = i;
        }
    }

    // Generate next combination of nums
    // nums starts with [0, 1]
    public String next() {
        StringBuilder sb = new StringBuilder();

        for (int i: nums) {
            sb.append(characters.charAt(i));
        }

        int j = combinationLength - 1;

        while (j >= 0 && nums[j] == charactersLength - combinationLength + j) {
            j--;
        }

        if (j >= 0) {
            nums[j]++;
            for (int i = j + 1; i < combinationLength; i++) {
                nums[i] = nums[j] + i - j;
            }
        } else {
            hasNext = false;
        }

        return sb.toString();
    }

    public boolean hasNext() {
        return hasNext;
    }

    /*
    // Solution 2: backtracking + pre-computation
    Queue<String> queue;
    String characters;
    int combinationLength;

    public CombinationIterator(String characters, int combinationLength) {
        queue = new LinkedList<>();
        this.characters = characters;
        this.combinationLength = combinationLength;
        backTrack(0, new StringBuilder());
    }

    private void backTrack(int index, StringBuilder sb) {
        if (sb.length() == combinationLength) {
            queue.offer(sb.toString());
            return;
        }

        for (int i = index; i < characters.length(); i++) {
            sb.append(characters.charAt(i));
            backTrack(i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public String next() {
        return queue.poll();
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

     */
}
