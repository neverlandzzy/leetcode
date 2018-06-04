package scalyr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	
	private static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
        // read the string filename
        String filename = "src/testfile.txt";
        //filename = scan.nextLine();
        
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("req_" + filename));
        
        
        String line = null;
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        
        while ((line = br.readLine()) != null) {
        	Matcher matcher = pattern.matcher(line);
        	if (matcher.find()){
        		String key = matcher.group(1).substring(0, 20);
        		map.put(key, map.getOrDefault(key, 0) + 1);
        	}
        }
        
        for (String key: map.keySet()) {
        	if (map.get(key) > 1) {
        		writer.write(key + "\n");
        	}
        }
        br.close();
        writer.close();

	}
	
	
}
