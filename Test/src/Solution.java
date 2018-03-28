import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
	
	/*
	 * 11
	 * 	update pf1 skills Java
	 * 	update pf1 education UT
	 * 	update pf2 workexperience Indeed
	 * 	update pf1 skills Java,Python
	 * 	get pf2 1
	 * 	get pf1 0
	 * 	get pf1 2
	 * 	get pf1 3
	 * 	get pf1 4
	 * 	getfield pf1 2 skills
	 * 	getfield pf1 3 education
	 */
	
	/*
	 * Profile for pf2 at version 1:
	 * workexperience - Indeed
	 * Profile for pf1 at version 0:
	 * Invalid request!
	 * Profile for pf1 at version 2:
	 * education - UT
	 * skills - Java
	 * Profile for pf1 at version 3:
	 * education - UT
	 * skills - Java,Python
	 * Profile for pf1 at version 4:
	 * Invalid request!
	 * skills for pf1 at version 2:
	 * Java
	 * education for pf1 at version 3:
	 * UT
	 */
	
	Map<String, TreeMap<String, TreeMap<Integer, String>>> map;
	Map<String, Integer> versionMap;
	//int nextVersion;
	
    public Solution() {
        map = new HashMap<>();
        versionMap = new HashMap<>();
        //nextVersion = 1;
    }
    
    public void update(String profileId, String fieldName, String fieldValue) {
		if (!map.containsKey(profileId)) {
			map.put(profileId, new TreeMap<>());
		}
		
		versionMap.put(profileId, versionMap.getOrDefault(profileId, 0) + 1);
		
		if (!map.get(profileId).containsKey(fieldName)) {
			map.get(profileId).put(fieldName, new TreeMap<>());
			map.get(profileId).get(fieldName).put(versionMap.get(profileId), fieldValue);
		} else {
			//String newValue = map.get(profileId).get(fieldName).lastEntry().getValue() + "," + fieldValue;
			String newValue = fieldValue;
			map.get(profileId).get(fieldName).put(versionMap.get(profileId), newValue);
		}
		
		System.out.println(map);
		System.out.println(versionMap);
    }
    
    // Profile for pf2 at version 1:
    // workexperience - Indeed
    public void get(String profileId, int version) {
		StringBuilder prefix = new StringBuilder();
		prefix.append("Profile for ");
		prefix.append(profileId).append(" at version ").append(version).append(":").append("\n");
		
		StringBuilder result = new StringBuilder();
		
		if (!map.containsKey(profileId)) {
			result.append("Invalid request!");
			System.out.println(prefix.toString() + result.toString());
			return;
		}
		
		if (version <= 0 || version > versionMap.get(profileId)) {
			result.append("Invalid request!");
			System.out.println(prefix.toString() + result.toString());
			return;
		}
		
		TreeMap<String, TreeMap<Integer, String>> fileds = map.get(profileId);
		
		for (Map.Entry<String, TreeMap<Integer, String>> entry: fileds.entrySet()) {
			String key = entry.getKey();
			if (entry.getValue().floorKey(version) == null) {
			    continue;
            }
			String value = entry.getValue().get(entry.getValue().floorKey(version));
			result.append(key).append(" - ").append(value).append("\n");
		}
		
		result.deleteCharAt(result.length() - 1);
		System.out.println(prefix.toString() + result.toString());
		
    }
    
    // education for pf1 at version 3:
    public void getField(String profileId, int version, String fieldName) {
		StringBuilder prefix = new StringBuilder();
		prefix.append(fieldName).append(" for ").append(profileId).append(" at version ").append(version).append(":").append("\n");
		
		StringBuilder result = new StringBuilder();
		
		if (!map.containsKey(profileId)) {
			result.append("Invalid request!");
			System.out.println(prefix.toString() + result.toString());
			return;
		}
		
		if (version <= 0 || version > versionMap.get(profileId)) {
			result.append("Invalid request!");
			System.out.println(prefix.toString() + result.toString());
			return;
		}
		
		TreeMap<Integer, String> fileMap = map.get(profileId).get(fieldName);
		Integer key = fileMap.floorKey(version);
		
		if (key == null) {
			result.append("Invalid request!");
			System.out.println(prefix.toString() + result.toString());
			return;
		}
		System.out.println(prefix + map.get(profileId).get(fieldName).get(key));   	
    }
    
    
    public static void main(String args[] ) throws Exception {
    	
    	/*
        Scanner in = new Scanner(System.in);
        Solution sol = new Solution();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            String queryType = in.next();
            String profileId = in.next();
            if ("update".equals(queryType)) {
                String fieldName = in.next();
                String fieldValue = in.next();
                sol.update(profileId, fieldName, fieldValue);
            } else if ("get".equals(queryType)) {
                int version = in.nextInt();
                sol.get(profileId, version);
            } else if ("getfield".equals(queryType)) {
                int version = in.nextInt();
                String fieldName = in.next();
                sol.getField(profileId, version, fieldName);
            }
           }
           */
    	
    	
    	Solution ps = new Solution();
    	
    	
		ps.update("pf1", "skills", "Java");
		ps.update("pf1", "education", "UT");
		ps.update("pf2", "workexperience", "Indeed");
		ps.update("pf1", "skills", "Java,Python");
		
		System.out.println();
		
		ps.get("pf2", 1);
		ps.get("pf1", 0);
		ps.get("pf1", 2);
		ps.get("pf1", 3);
		ps.get("pf1", 4);
		//ps.get("pf4", 4);
	
		ps.getField("pf3", 2, "skills");
		ps.getField("pf1", 3, "education");
		
    	
    	/*
    	ps.update("pf1", "a", "1");
    	ps.update("pf1", "b", "1");
    	ps.update("pf1", "a", "2");
    	System.out.println();
		ps.get("pf1", 0);
		ps.get("pf1", 1);
		ps.get("pf1", 2);
		ps.get("pf1", 3);
		ps.get("pf1", 3);
		*/
		
    	/*
		Solution ps = new Solution();
		ps.update("ABC", "skills", "java");
		ps.update("ABC", "skills", "python");
		ps.update("ABC", "education", "USC");
		
		
		ps.get("ABC", 1);
		ps.get("ABC", 2);

	
		ps.getField("ABC", 1, "skills");
		ps.getField("ABC", 2, "skills");
        */
        
    }
}
