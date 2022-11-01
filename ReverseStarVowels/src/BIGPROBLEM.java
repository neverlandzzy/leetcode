public class BIGPROBLEM{

    public static String reverseStarVowels(String string) {
        String sub = "";
        String str = " " + string + " ";
        String output = "";
        boolean reverse = true;
        boolean replace = true;
        for (int i = 1; i < str.length() - 1; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                if (str.charAt(i) != '@' || !Character.isLetter(str.charAt(i + 1))){
                    output = output + "" +str.charAt(i);
                }
            } else {
                if (str.charAt(i - 1) == '@') {
                    replace = false;
                }
                for (int j = i; j < str.length(); j++) {
                    if (!Character.isLetter(str.charAt(j))) {
                        if (str.charAt(j) == '@') {
                            reverse = false;
                        }
                        if (!replace && !reverse) {
                            sub = str.substring(i - 1, j + 1);
                            i += sub.length() - 2;
                        } else if (replace && !reverse) {
                            sub = str.substring(i, j + 1);
                            i += sub.length() - 1;
                        } else if (!replace) {
                            sub = str.substring(i - 1, j);
                            i += sub.length() - 2;
                        } else{
                            sub = str.substring(i, j);
                            i += sub.length() - 1;
                        }
                        sub = substringWithoutAt(sub,0);
                        break;
                    }
                }
                if (reverse) {
                    sub = reverseStr(sub);
                }
                if (replace) {
                    sub = vowelsToStars(sub);
                }
                output = output + sub;
            }
            sub = "";
            replace = true;
            reverse = true;
        }
        return output;
    }

    public static String reverseStr(String str) {
        String output = "";
        for (int i = 1; i <= str.length(); i++) {
            output = output + str.charAt(str.length() - i);
        }
        return output;
    }

    public static String vowelsToStars(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' ||str.charAt(i) == 'e' ||str.charAt(i) == 'i' ||str.charAt(i) == 'o' ||str.charAt(i) == 'u' ||str.charAt(i) == 'A' ||str.charAt(i) == 'E' ||str.charAt(i) == 'I' ||str.charAt(i) == 'O' ||str.charAt(i) == 'U'){
                str = str.substring(0, i) + '*' + str.substring(i + 1);
            }
        }
        return str;
    }

    public static String substringWithoutAt(String str, int startIndex) {
        String sub = str.substring(startIndex);
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) == '@') {
                sub = sub.substring(0, i) + sub.substring(i + 1);
            }
        }
        return sub;
    }

    public static void main(String[] args){
        System.out.println(reverseStarVowels("To live or die?"));
        System.out.println(reverseStarVowels("@Cafe Babe@"));
        System.out.println(reverseStarVowels("@wicked@"));
        System.out.println(reverseStarVowels("happy@birthday"));
        System.out.println(reverseStarVowels("rev it@ @up, @dude@! Much$$$2BHad!?"));
        System.out.println(reverseStarVowels("hi @ at not @djacent oh $%@"));
        System.out.println(reverseStarVowels("him"));
        System.out.println(reverseStarVowels("It is AMAZING"));
        System.out.println(reverseStarVowels(""));
        System.out.println(reverseStarVowels("omg am I wrong@"));
        System.out.println(reverseStarVowels("@"));
        System.out.println(reverseStarVowels("ME@YOU##TRUE"));
        System.out.println(reverseStarVowels("work@school||@!@!work@home@room@table9"));
    }
}
