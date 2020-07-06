import java.util.*;

public class Solution {

    public static int xorOperation(int n, int start) {
        int result = start;
        int s = start;

        for (int i = 1; i < n; i++) {
            s = start + 2 * i;
            System.out.println(s);
            result = result ^ s;
        }

        return result;
    }

    public static String[] getFolderNames(String[] names) {
        int n = names.length;
        String[] result = new String[n];

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = names[i];
            String fileName = name;
            int version = 0;
            if (map.containsKey(fileName)) {
                version = map.get(fileName) + 1;
                while (map.containsKey(fileName)) {
                    fileName = appendVersion(name, version);
                    if (!map.containsKey(fileName)) {
                        break;
                    }
                    version++;
                }
            }

            map.put(name, version);
            if (!name.equals(fileName)) {
                map.put(fileName, map.getOrDefault(fileName, -1) + 1);
            }
            //System.out.println(map);
            result[i] = fileName;
        }

        return result;
    }

    private static String appendVersion(String name, int version) {
        return name + "(" + version + ")";
    }

    public static void main(String[] args) {
        // System.out.println(xorOperation(5, 0));

        String[] test21 = {"pes","fifa","gta","pes(2019)"};
        String[] test22 = {"onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"};
        String[] test23 = {"kaido","kaido(1)","kaido","kaido(1)"};
        String[] test24 = {"wano","wano","wano","wano"};
        String[] test25 = {"gta","gta(1)","gta","avalon"};
        String[] test26 = {"kaido","kaido(1)","kaido","kaido(1)","kaido(2)"};

        printArray(getFolderNames(test21));
        printArray(getFolderNames(test22));
        printArray(getFolderNames(test23));
        printArray(getFolderNames(test24));
        printArray(getFolderNames(test25));
        printArray(getFolderNames(test26));
    }

    public static void printArray(String[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
