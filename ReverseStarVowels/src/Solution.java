import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private static String ReverseStarVowels(String str) {
        if (str.length() <= 1) {
            return str;
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;
        int j = 0;
        int n = str.length();
        boolean startWithAt = false;

        while (j < n) {

            while (j < n && Character.isLetter(str.charAt(j))) {
                j++;
            }

            String substring = str.substring(i, j);

            if (!startWithAt) {
                substring = replaceVowels(substring);
            }

            if (j == n || str.charAt(j) != '@') {
                substring = reverse(substring);
                sb.append(substring);
                startWithAt = false;

                if (j < n) {
                    sb.append(str.charAt(j));
                }
            } else {
                startWithAt = true;
                sb.append(substring);
                if (j > 0 && j < n - 1 && !Character.isLetter(str.charAt(j - 1)) && !Character.isLetter(str.charAt(j + 1))) {
                    sb.append(str.charAt(j));
                }
            }

            i = j + 1;
            j++;
        }

        return sb.toString();
    }

    private static String replaceVowels(String str) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] charArray = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            if (vowels.contains(charArray[i])) {
                charArray[i] = '*';
            }
        }

        return new String(charArray);
    }

    private static String reverse(String str) {
        char[] charArray = str.toCharArray();

        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            char tmp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = tmp;
            i++;
            j--;
        }

        return new String(charArray);
    }

    public static void main(String[] args) {
        String test1 = "To live or die?";
        String test2 = "@Cafe Babe@";
        String test3 = "@wicked@";
        String test4 = "happy@birthday";
        String test5 = "work@school||@!@!work@home@room@table9";
        String test6 = "r";
        String test7 = "@";
        String test8 = "@ ";

        System.out.println(ReverseStarVowels(test1));
        System.out.println(ReverseStarVowels(test2));
        System.out.println(ReverseStarVowels(test3));
        System.out.println(ReverseStarVowels(test4));
        System.out.println(ReverseStarVowels(test5));
        System.out.println(ReverseStarVowels(test6));
        System.out.println(ReverseStarVowels(test7));
        System.out.println(ReverseStarVowels(test8));
    }
}
