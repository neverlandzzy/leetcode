
public class Solution {
	public static void main(String[] args) {
		
		FileSystem obj = new FileSystem();
		System.out.println(obj.ls("/"));
		obj.mkdir("/a/b/c");
		obj.addContentToFile("/a/b/c/d","hello");
		System.out.println(obj.ls("/"));
		System.out.println(obj.readContentFromFile("/a/b/c/d"));
	}
}
