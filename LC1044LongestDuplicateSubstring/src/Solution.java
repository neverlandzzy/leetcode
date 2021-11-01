import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times.
     * The occurrences may overlap.
     *
     * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring,
     * the answer is "".
     *
     * Example 1:
     *
     * Input: s = "banana"
     * Output: "ana"
     *
     * Example 2:
     *
     * Input: s = "abcd"
     * Output: ""
     *
     * Constraints:
     *
     * 2 <= s.length <= 3 * 104
     * s consists of lowercase English letters.
     */

    /**
     *   分成两步来解决：
     *   1. Binary search 从N/2 -> 1检查每一段长度。
     *          e.g. length = 15: 8 -> 4 -> 2 （发现有重复）再回头检查 3
     *   2. Rabin-Karp 算法：
     *      https://coolcao.com/2020/08/20/rabin-karp/
     *      https://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
     *
     *      检查一段字符串中是否有特定的pattern，（此题中即对于一段给定的长度，检查是否有duplicate substring）
     *      传统的基于hash的字符串查找是将每一段子字符串进行hash运算，花费时间为O(L)(子字符串的长度)。Robin-Karp将hash运算的花费降为O(1)
     *      Rabin-Karp的思路是不单独计算每一个子字符串的hash，而是通过rolling利用上一个子字符串的hash，在O(1)时间内算出当前子字符串的hash
     *
     *      对于只有lowercase English letters的长度为L字符串，我们可以使用26进制来表示一段字符串 (a,b,c,d,e,...,y,z) -> (0,1,2,3,4,...,24,25)
     *      num = a[0]*26^L + a[1]*26^(L-1) + ... + a[L-2]*26^1 + a[L-1]*26^0
     *
     *      例如对字符串"abcdefg"，搜索长度为4的子字符串。我们先计算出"abcd"的hash
     *      hash0 = 0 * 26^3 + 1 * 26^2 + 2 * 26^1 + 3 * 26^0
     *
     *      然后，继续搜索到下一个子字符串"bcde"，我只需要在hash0中减去'a'对应的hash，加上新的'e'对应的hash，因此运算花费为O(1)
     *      hash1 = (hash0 - 0 * 26^3) * 26 + 4 * 26^0
     *
     *      即： hash1 = (hash0 - c[0] * a^(L-1)) * a + c[L+1] = (hash0 * a - c[0] * a^L) + c[L+1]
     *          a: 进制（只有数字：a=10, 只有小写：a=26, 只有ASCII：a=127）
     *          L: 搜索子字符串的长度
     *          c[i]: 原字符串中i位字符对应的数字
     *
     *      因为a^L可能会很大，我们需要一个modulus来防止溢出，即hash = hash % modulus
     *
     *      选择modulus：
     *      1. modulus可以被看成是number of bins that we will use to store the starting index of seen substrings。因此若modulus 过小，则发生collision
     *      的概率就增大，大约为 1/modulus。但modulus也不能太大，需要保证hash在32bit Integer的limit之内。
     *      2. modulus最好选择prime number，这样可以增大hash % modulus平均分布的几率
     *      3. 因此我们选择较常用的modulus = 10^9 + 7
     *
     *      为防止发生collision，当两个子字符串的hash相同时，我们需要逐位比较两个子字符串。因此Rabin-Karp 算法的实际复杂度在worst case时是O(L(N-L)) = O(N)
     *      (Collision 的概率大约为 1/modulus)
     *
     *      h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus; (aL = a^L)
     *      h = (h + nums[start + L - 1]) % modulus;
     *
     *
     */
    public static String longestDupSubstring(String s) {
        int n = s.length();

        int low = 1;
        int high = n;

        String[] result = new String[1];
        result[0] = "";

        int[] nums = new int[n];

        for (int i = 0; i < n; ++i) {
            nums[i] = s.charAt(i) - 'a';
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isValid(s, nums, mid, result)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return result[0];
    }

    private static boolean isValid(String s, int[] nums, int len, String[] result) {
        Map<Long, List<Integer>> map = new HashMap<>();
        long hash = 0;
        int modulus = 1_000_000_007;
        int a = 26;
        long aL = 1; // a^L

        // 先算出从0到L-1子字符串s[0:L]的hash，如"abcdefg"中"abcd"的hash
        for (int i = 0; i < len; i++) {
            hash = (hash * a + nums[i]) % modulus;
            aL = (aL * a) % modulus;
        }

        map.putIfAbsent(hash, new ArrayList<>());
        map.get(hash).add(0);

        for (int i = 1; i < nums.length - len + 1; i++) {
            hash = (hash * a - nums[i - 1] * aL % modulus + modulus) % modulus;
            hash = (hash + nums[i + len - 1]) % modulus;

            if (map.containsKey(hash) && collisionCheck(map.get(hash), i, len, s)) {
                result[0] = s.substring(i, i + len);
                return true;
            }

            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(i);
        }

        return false;
    }

    private static boolean collisionCheck(List<Integer> list, int curIndex, int len, String S) {
        for (int preIndex: list) {
            int i = 0;
            while (i < len) {
                if (S.charAt(curIndex + i) != S.charAt(preIndex + i)) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(longestDupSubstring("banana"));
        System.out.println(longestDupSubstring("abcd"));
        System.out.println(longestDupSubstring("aa"));
        System.out.println(longestDupSubstring("dcecccaedebdcedcbaaddbbdbccbcbdcecbddaaedecbeccdeabdceebcaaeccbbcdcdaceceeacedadcdddbbbaedeaebbbecbeebdcdebadabaecacdeeeabeaaaeacbdacedabbadcbeddebbbcebedeecdbebbdbcebacebdaaceabdbdcaebddeaeccaccbcebdacddbdddbadecbadaacaadcdececadeebbacedebeddcaacedacbcaadecdeecddbdbbbcccedeaaeecededdaccddbdccdcdddddcdacceabbcebdebddbcbbaaababceebdaaeababeacaeddccecadebeceeddaddbdbdebcbceceeacedcaedcebeeeadbbecdecaeabaeedbdddaececeebcaddbcaaeebcbeccbedcdeccebeebdbaceddebddabebbabbcedbbedacecbaebaaedbeaeaebbaaeeeebbbbeecdecdcedbcdbbbdcaecdbbcadbeebaebcaebccadecdccabbddbddeaeeaecccbeacdcbeadadadacbceaaeceebcdcddceeebebedececacadbedeeebdecadbebebebecaeeecbdceecdcacadceadcceaebcbaceeceeaaedecaddbaacacdbedebeeccadbccdeccdcadcdaadbabddbbdaacbaaaceeecaadcabadbeecadeebbdcebabedcddbebdcbaccdaabebcbacbedadeacbbcacadbdabbaccadadddeabdcbbdeacdbeededdecebceacabdceaeaaeaacbbbacabccedddeceadceedacccedaaceadeabccbdaeadcabbccadaecddebbadeecadccbacebdaabbeacdccaedeeeaaadaaaddebeadcbbdcebeeaebecdbacccbaeadcbacabbbeedeaaccaebddaaceadebeedebabdbbecebeabddeeecaeedddabacbbdbddebbadadcbeacecdaabcaeddceebceebaecccadeecbbdbeaeedcdeaabadcdeaebaaaadbdadeeaadadcabeceadbbdedadaaaecdacdbcedbeabcbeaeacaecebdeaedacbcbeaebcabdaabaddecadbeaecdcbebbbccbbddbccdebaaeecccddaaeccedebabacdeecbbbbccdedcbcebeceedcaacbeaacabdbbeeebdcddbceebdaddacadadceaaeecabaecadcdabbccaabcdbeededadcbecbebcdceabccecbebbbccbcddccebdeacbebabeecbebacceecbdddeeeeabeaabcdbecbbdacdccadbeeaccecdbcddcdacbaddcacdacdbebeaabdadeaadcacedcccdcdbddaaebacecbdccdbaabdecadddedcbbaaacdeaddcdcebcaeccbcbcdaaeceabaedeeeadebcaadbbedbccbaabdbdccbabceabeeccbbadbaecedddccedaeaabcbaaeabcdbdbdaabcedbbbdbeabaedcbdabeeabbadecadeadabcbdaebdbcbecccaaeadcaaebcccdacabdbecebbedaaeebcadbeeeebebccdaceedecdedceaceaeabccbebaccebdceabbdadcdccbeeebbbbaedebecbbbeabebdabdcdcbcbedaedcbcaebbccdbacdccbbcbadcbbaccbebaaabaaaedccdceecababeacddacceeabeaabaaeccceccecddeecdadedeaadabaccabbadaaecbebccbabaadcbecebedbaececeabdebcadaddcaebeeabbebedeadcdccbadabcecbacecbdbccebcbbaadceadbacadeeebcddcdddccaeaababdcddeebaaddcbcaaddceedacdaaeaadcedeeaeaddebdabaddddbadccbdbebcecdccebeebdcccbeedbcceebbadcdebaeedbecccdbaedcdbcbbaaaaabbbdaddabdbbeebdeabedededbdbcecedadbaebddcabbbabecacdedccbeaacaeaccadcbeebaadceddbdacbbadccacbadcdddabcccaababebdceeddcaebbaebeeedabdbcacabdcbaceabcccdcacacbbaeeaaddedaabccaabbeaccaceecbaacbeebcbeaecdbcaeaaaedcdbebcaaaaacaabbbbccbceebbbaaaceccabdeceeeddbabadddeeaaebbabbeaadaacacdebbaadbaeebbdebbabbbbbeadbebadacdcdbaccaebaadccbabcadccbeaacadeabcadebbcadabaaaaeccaddbbebcdabdededddeadcbceacebaeeddeaebedbebaeabebcadcdbccdbdbdeccaebddeaabccdaecebdededddcbbcacdcbaedcebdedaaceebccbcdababaeaacabcddaadacaadbadcdbbedaebddebedceddddceadeaebbebbaabdeabdcdabccdcedaddeaeddecdecbeebeedaadbcbbebcbeecedaabcacaabdbaecaeebeaedadbabecbaabbebadaccadeebeeebbecdcaecaeacadeabbadceaeccbcdbebaaabebaacdebbcaaeadccbebcebebebbedddacbbccbedbecbeaebecedacdcdbbebdaedeabcabaaaeebbdbbaddadcebbacecbaacddecbddeecbbedecedbbaebddeeacbcdedbdaddbbaecdccaedcebeeebaacaaabcbddccacbccbacdbebaeadbeaacacbceddbaeabaaaadaabbcddaabcabeeccabcebacdacbadbdebddaddeaabeebbbbbbacbdddecbaddcdeaaaeecbbcedcbecddbebeccaacaedabdacabdaabdbadccddedecdebadcdcadbedeecbdddaacdbdcadddacaedceacaecaebcecedeaeadbabccdbdeacebaccaabccccdcecaecadeaddeaeddabcbadedceebdaacdadebbeebdeedcecebdeecaaeccbcdddddadbbbcbaabccabebabdcdcaacadaaeebbbedbdecabcceeccabadbbaddcdbeaaeeadaddeebaecaedecccaadbdeddbbbeeceeaaccaedadddbaecacccadaeecdecabccabbebcacedbeadccddeaadacdcbeedeaeabcbccbbdcdaeedbcabaaccaccbdaacebacaecdacbebddbbbdddebacadaaadbdcbcaccdddeedbecbcdcbaeaacbabbeaeebbadadaebbccaebebdeeceecbabdebcebeaadebdbbddcaeddbcadeadaabdccdecdcaccaecccdeaaacdbdccadacbdbaaccdbddbecaacbcbbdbedecedecbaacadccaedbacabddaeccdecaaeeecadddeaaadcdaedeadedbeeccccabeadaebcaaecacebdcdaaadedccacdceaeededcaaedceccedccebdacbececeeaebaecbdeecdbaeeeeeadececcdcabbddadecdddabdeeedccabbaebddedecccacdbdcbbcddcaecbdaddcadacbbccceccecacddcbaedcbbeecedbeecdeaaeeeecdcabcebecbcebbceabaddeacaedaabadebcecdadbebcbbeeaceaacbdcaabacadaebebcdceeeacaacaddeaadabaceeecbccbecbdabebebccdadeedaadaecceacaabeacdaebeecbdcbbdeddbeebdadbabbeaddbcdccdbcebddaebeddcaecdededebebdaabdddaecedddabbeeebadbbbdebeeecbbebaebdeebceaeaedbecbbcadadbeacceeecbabaeadbdedccbbbbaabeabbbdadceedaaddcbbceadbbaebebebbdeadcaabdeebaaebbeecaddecccbddcdebadcebbaacddbbeecadbcaebdebaaeeaadbaeeeedbcabebceccebabbeebcddebcdbdabcbcbbcadecbdacedadacdbbebeebaddecbccaeaeccedbcddededdecbcecdeaabeceeadbcbeaaccaeccceadecaaacbaaccaccbeeeceddadaabdeeaedbcceabeabbbcaeededdcdcbdbbedecbbdbeeeccadcadedeacbbcdbebcbeaedbcbdbdabbdcacacadeedbebddeddccaeebceddeeebdbbabcaebdbdbedeeadbabaadddcaabbbbcabeeddbbadabacceabcbceddeceadcdbaeadaedebecdecbdbabdcacddbccbaedcceabbecabdacbdcbaeaeccceecabbaeaecdeadabaeeedeeadeebbacdaceadedecedebbbcbaccaacebbebadaeaecbeebadaaceaabebdbddeecedcbcbcdbcdaccacbbbdaddacbcaaaebdcadbcaeeaacacebeebaeeecbdeedddcebcbeaabdedbaddabcadbbaeadccdbcebddadebaaabcaeadcedaadcaeedcbbcdbcdbcdcbdcebaaadededdccebebbedcadeadeeadeebbdceebdddacebdedbbcdabbcdacbbdcccacdbacbbaeeeacbedceaedebbacdbeebaebbaeacaccaecdeacbbaacaceebedaacdcebaaacadcdeceaeeadcadceaeeabeecaabbabbebdecdcadadebabddbabebdbadeabcdadbbcaadcdbdbabddebcbcbeddabbcabaebcbccddbbbbebdbdabcdcddbdbceecadbadadbdcbadedecdcbaebcaaddbeebceaceeaacceabcacaccddcebcabbebddecddbedbddaeadebbcdccddeebebbaceaaeeebcdceeebdccbaababcdcbdcebbbbcaeccecaadcaeabababbcdaeaecdcbaddbacbaeecdcdecccabcddbbbcdcadadabcbcedaadaacceaededbaecaeadabdcd"));
    }
}
