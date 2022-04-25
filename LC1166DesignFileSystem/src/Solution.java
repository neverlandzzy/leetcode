public class Solution {

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        System.out.println(fileSystem.createPath("/leet",1));
        System.out.println(fileSystem.createPath("/leet/code", 2));
        System.out.println(fileSystem.get("/leet/code"));
        System.out.println(fileSystem.createPath("/c/d", 1));
        System.out.println(fileSystem.get("/c"));
    }
}
