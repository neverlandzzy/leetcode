import java.util.Stack;


public class Solution {
	/*
	 * Given an absolute path for a file (Unix-style), simplify it.
	 * 
	 * 	For example,
	 * 		path = "/home/", => "/home"
	 * 		path = "/a/./b/../../c/", => "/c"
	 * 
	 * 
	 * 
	 * 
	 * 路径简化的依据是：
	 * 	当遇到"/../"则需要返回上级目录，需检查上级目录是否为空。
	 * 	当遇到"/./"则表示是本级目录，无需做任何特殊操作。 
	 * 	当遇到"//"则表示是本级目录，无需做任何操作。
	 * 	当遇到其他字符则表示是文件夹名，无需简化。
	 * 	当字符串是空或者遇到"/../"，则需要返回一个"/"。
	 * 	当遇见"/a//b"，则需要简化为"/a/b"。
	 */
	
	
    public static String simplifyPath(String path) {
    	String[] split = path.split("/");
    	StringBuilder result = new StringBuilder();
    	Stack<String> dir = new Stack<String>(); 
    	
    	for (int i = 1; i < split.length; i++) {
    		if (split[i].equals("/") || split[i].equals(".")||split[i].equals("")) {
    			continue;
    		} else if (split[i].equals("..")) {
    			if (!dir.isEmpty()) {
    				dir.pop();
    			}
    		} else {
    			dir.push(split[i]);
    		}
    	}
    	
    	if(dir.isEmpty()) {
    		result.append('/');
    	} else {
    	
    		for(String s: dir) {
    			result.append("/");
    			result.append(s);
    		}
    	}
    	return result.toString();
        
    }
    
    public static void main(String[] args) {
		System.out.println(simplifyPath("/home/"));
		System.out.println(simplifyPath("/a/./b/../../c"));
		System.out.println(simplifyPath("/"));
		System.out.println(simplifyPath("/home//foo/"));
	}
}
