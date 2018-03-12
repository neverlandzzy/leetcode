package onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Profile {
	/*
	 *【题目】
	 * 设计一个Resume的储存系统，每一份resume对应一个profileID，需要支持3个API
	 * Update(String profileID, String keyField, String valueField)
	 * Get(String profileID, int version)
	 * Get(String profileID, String keyField, int version)
	 * 
	 * 举个例子，比如我现在有一份简历，最开始的版本号是1,假设profileID叫做ABC
	 * update(ABC, "skills", "java")------>对应版本1
	 * update(ABC, "skills", "python")------>对应版本2，因为同样的key里面增加了新的元素
	 * 
	 * update(ABC, "education", "USC")------>对应版本2，因为education这个key是新key
	 * 这个时候，如果我们call Get(ABC, 版本1)，那么应该返回{ABC:{"skills" : "java"}}
	 * 如果我们call Get(ABC, 版本2)，那么应该返回{ABC:{"skills" : "java, python", "education": "USC"}}
	 * 
	 * 规律是，如果已经现有的key被更新，那么就生成最新版本，版本号+1，如果是新加进来的key，那么版本号就不变（如果我没记错的话是这样，印象有点模糊了，大家如果遇到了，仔细读一下题目！）
	 * 
	 * 之前面经有一个很重要的遗漏，就是get（profileid，version）需要返回的是所有小于等于给定version那个profile的fieldname和fieldvalue， 而不是只是那个version下的，
	 * 而且返回值需要按fieldname排序，所以用treemap的floor会很有效率。
	 * 
	 *【思路】
	 * 思路讲一下把：map<profileId, treemap<fieldname, treemap<version, fieldvalue>>>, 这样好处就是去掉了fieldname的重复，节省了空间。 代码就不贴了，
	 * 建议大家先写好，时间挺紧的
	 */
	
	public static class ProfileSystem {
		
		Map<String, TreeMap<String, TreeMap<Integer, String>>> map;  // map<profileId, treemap<fieldname, treemap<version, fieldvalue>>>
		int nextVersion = 1;
		
		public ProfileSystem() {
			map = new HashMap<>();
		}
		
		public void update (String profileID, String fieldKey, String fieldValue) {
			if (!map.containsKey(profileID)) {
				map.put(profileID, new TreeMap<>());
			}
			
			// 若fieldKey 不在treemap里，则新建一个entry，否则找treemap.get(fieldKey)的最大version(lastKey)，插入一个新的entry(lastKey + 1)
			if (!map.get(profileID).containsKey(fieldKey)) {
				map.get(profileID).put(fieldKey, new TreeMap<>());
				map.get(profileID).get(fieldKey).put(nextVersion, fieldValue);
			} else {
				nextVersion = map.get(profileID).get(fieldKey).lastKey() + 1;
				String newValue = map.get(profileID).get(fieldKey).lastEntry().getValue() + ", " + fieldValue;
				map.get(profileID).get(fieldKey).put(nextVersion, newValue);
			}
			
			//System.out.println(map);
		}
		
		public String get(String profileID, int version) {
			StringBuilder sb = new StringBuilder();
			sb.append(profileID).append(":").append("{");
			
			TreeMap<String, TreeMap<Integer, String>> fileds = map.get(profileID);
			
			for (Map.Entry<String, TreeMap<Integer, String>> entry: fileds.entrySet()) {
				String key = entry.getKey();
				if (entry.getValue().floorKey(version) == null) {
				    continue;
                }
				String value = entry.getValue().get(entry.getValue().floorKey(version));
				sb.append("\"").append(key).append("\"").append(": ").append("\"").append(value).append("\"").append(", ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
			sb.append("}");
			
			return sb.toString();
		}
		
		public String get(String profileID, String fieldKey, int version) {
			return map.get(profileID).get(fieldKey).get(version);
		}
	}
	
	public static void main(String[] args) {
		ProfileSystem ps = new ProfileSystem();
		ps.update("ABC", "skills", "java");
		ps.update("ABC", "skills", "python");
		ps.update("ABC", "education", "USC");
		
		System.out.println(ps.get("ABC", 1));
		System.out.println(ps.get("ABC", 2));
		System.out.println(ps.get("ABC", "skills", 1));
		System.out.println(ps.get("ABC", "skills", 2));
		System.out.println(ps.get("ABC", "education", 1));
		System.out.println(ps.get("ABC", "education", 2));
	}
}
