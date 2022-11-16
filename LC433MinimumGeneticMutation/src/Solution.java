import java.util.*;

public class Solution {
    /**
     * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
     *
     * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in
     * the gene string.
     *
     * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
     * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
     *
     * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is
     * no such a mutation, return -1.
     *
     * Note that the starting point is assumed to be valid, so it might not be included in the bank.
     *
     *
     * Example 1:
     *
     * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
     * Output: 1
     *
     * Example 2:
     *
     * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
     * Output: 2
     *
     * Example 3:
     *
     * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
     * Output: 3
     *
     * Constraints:
     *
     * start.length == 8
     * end.length == 8
     * 0 <= bank.length <= 10
     * bank[i].length == 8
     * start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
     */

    public static int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        char[] validChars = {'A', 'C', 'G', 'T'};

        if (!set.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String gene = queue.poll();
                if (end.equals(gene)) {
                    return count;
                }

                char[] geneCharArray = gene.toCharArray();

                for (int i = 0; i < geneCharArray.length; i++) {
                    char c = geneCharArray[i];
                    for (char validChar: validChars) {
                        if (geneCharArray[i] != validChar) {
                            geneCharArray[i] = validChar;
                            String nextGeneString = new String(geneCharArray);
                            if (set.contains(nextGeneString)) {
                                queue.offer(nextGeneString);
                                set.remove(nextGeneString);
                            }
                        }
                    }
                    geneCharArray[i] = c;
                }
            }
            count++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] bank1 = {"AACCGGTA"};
        String[] bank2 = {"AACCGGTA","AACCGCTA","AAACGGTA"};
        String[] bank3 = {"AAAACCCC","AAACCCCC","AACCCCCC"};
        String[] bank4 = {"AACCGGTA","AACCGCTA","AAACGGTA"};

        System.out.println(minMutation("AACCGGTT", "AACCGGTA", bank1));
        System.out.println(minMutation("AACCGGTT", "AAACGGTA", bank2));
        System.out.println(minMutation("AAAAACCC", "AACCCCCC", bank3));
        System.out.println(minMutation("AACCGGTT", "AACCGCTA", bank4));
    }
}
