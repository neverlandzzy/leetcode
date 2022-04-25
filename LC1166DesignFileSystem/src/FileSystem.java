import java.util.HashMap;
import java.util.Map;

public class FileSystem {
    /**
     * You are asked to design a file system that allows you to create new paths and associate them with different values.
     *
     * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English
     * letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
     *
     * Implement the FileSystem class:
     *
     * bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
     * Returns false if the path already exists or its parent path doesn't exist.
     * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
     *
     * Example 1:
     *
     * Input:
     * ["FileSystem","createPath","get"]
     * [[],["/a",1],["/a"]]
     * Output:
     * [null,true,1]
     * Explanation:
     * FileSystem fileSystem = new FileSystem();
     *
     * fileSystem.createPath("/a", 1); // return true
     * fileSystem.get("/a"); // return 1
     *
     * Example 2:
     *
     * Input:
     * ["FileSystem","createPath","createPath","get","createPath","get"]
     * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
     * Output:
     * [null,true,true,2,false,-1]
     * Explanation:
     * FileSystem fileSystem = new FileSystem();
     *
     * fileSystem.createPath("/leet", 1); // return true
     * fileSystem.createPath("/leet/code", 2); // return true
     * fileSystem.get("/leet/code"); // return 2
     * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
     * fileSystem.get("/c"); // return -1 because this path doesn't exist.
     *
     *
     * Constraints:
     *
     * The number of calls to the two functions is less than or equal to 104 in total.
     * 2 <= path.length <= 100
     * 1 <= value <= 10^9
     */

    // Another solution is to use HashMap. createPath() takes O(n) -- slower, get() takes O(1) -- faster
    class TrieNode {
        Map<String, TrieNode> children;
        int value;

        public TrieNode() {
            children = new HashMap<>();
            value = -1;
        }
    }

    TrieNode root;

    public FileSystem() {
        root = new TrieNode();
    }

    // Time: O(T) - T: trie contains T components;
    public boolean createPath(String path, int value) {
        TrieNode node = root;
        String[] directories = path.split("/");

        for (int i = 1; i < directories.length; i++) {
            String directory = directories[i];
            if (!node.children.containsKey(directory)) {
                if (i == directories.length - 1) {
                    node.children.put(directory, new TrieNode());
                } else {
                    return false;
                }
            }

            node = node.children.get(directory);
        }

        if (node.value != -1) {
            return false;
        }

        node.value = value;
        return true;
    }

    // Time: O(T) - T: trie contains T components;
    public int get(String path) {
        TrieNode node = root;
        String[] directories = path.split("/");

        for (int i = 1; i < directories.length; i++) {
            String directory = directories[i];
            if (!node.children.containsKey(directory)) {
                return -1;
            }
            node = node.children.get(directory);
        }

        return node.value;
    }
}
