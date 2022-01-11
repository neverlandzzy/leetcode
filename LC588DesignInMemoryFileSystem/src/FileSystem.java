import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileSystem {
	
	/**
	 * Design an in-memory file system to simulate the following functions:
	 * 
	 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. 
	 * If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) 
	 * should in lexicographic order.
	 * 
	 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories 
	 * in the path don't exist either, you should create them as well. This function has void return type.
	 * 
	 * addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file
	 * containing given content. If the file already exists, you need to append given content to original content. This function has void 
	 * return type.
	 * 
	 * readContentFromFile: Given a file path, return its content in string format.
	 * 
	 * Note:
	 * 1. You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
	 * 2. You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list 
	 *    a directory or file that does not exist.
	 * 3. You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
	 */
	
	class File {
		Map<String, File> map;
		boolean isFile;
		String content;
		
		public File() {
			map = new HashMap<>();
			isFile = false;
			content = "";
		}
	}
	
	File root;
	
    public FileSystem() {
        root = new File();
    }

    // 对于"/a/b/c/d"
	// String[] pathArray = path.split("/"); -> "", "a", "b", "c", "d"
    public List<String> ls(String path) {
        File f = root;
        List<String> files = new ArrayList<>();
        if (!path.equals("/")) {
        	String[] pathArray = path.split("/");
        	
        	// 从1开始，因为pathArray[0]是""
        	for (int i = 1; i < pathArray.length; i++) {
        		f = f.map.get(pathArray[i]);
        	}
        	if (f.isFile) {
        		files.add(pathArray[pathArray.length - 1]);
        		return files;
        	}
        }
        
        List<String> result = new ArrayList<>(f.map.keySet());
        Collections.sort(result);
        return result;
    }
    
    public void mkdir(String path) {
        File f = root;
        String[] pathArray = path.split("/");

    	for (int i = 1; i < pathArray.length; i++) {
    		if (!f.map.containsKey(pathArray[i])) {
    			f.map.put(pathArray[i], new File());
    		}
    		f = f.map.get(pathArray[i]);			
    	}
    }
    
    public void addContentToFile(String filePath, String content) {
        File f = root;
        String[] pathArray = filePath.split("/");
        
        // 到pathArray.length - 1结束，因为pathArray.length最后一个是file名，不是路径名
        for (int i = 1; i < pathArray.length - 1; i++) {
        	f = f.map.get(pathArray[i]);
        }
        
        String fileName = pathArray[pathArray.length - 1];
        if (!f.map.containsKey(fileName)) {
        	f.map.put(fileName, new File());
        }
        
        f = f.map.get(fileName);
        f.isFile = true;
        f.content = f.content + content;
    }
    
    public String readContentFromFile(String filePath) {
        File f = root;
        String[] pathArray = filePath.split("/");
        
        // 到pathArray.length - 1结束，因为pathArray.length最后一个是file名，不是路径名
        for (int i = 1; i < pathArray.length - 1; i++) {
        	f = f.map.get(pathArray[i]);
        }
        
        String fileName = pathArray[pathArray.length - 1];
        return f.map.get(fileName).content;
    }
}
