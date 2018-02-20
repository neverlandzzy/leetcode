package phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendCycle {
	/*
	 * 【基本题】一个公司给你两个String[] employeeInput = {"1,Alice,HR", "2,Bob,Engineer".....}, String[] friendsInput = {"1,2","1,3","2,4"}
	 *         输出所有的employee的friendlist -> 就是用一个map存起来然后打印就好了（这个是无向图，e.g: 1和2是朋友，2的列表里也要有1）
	 *         
	 * 【Follow up 1】输出每个department里有多少人的朋友是其他部门的;
	 * 【Follow up 2】输出是否所有employee都在一个社交圈 
	 */
	
	// 【基本题】
	public static Map<String, List<String>> friendList(String[] employees, String[] friends) {
		Map<String, String> employeeIDMap = new HashMap<>();
		Map<String, List<String>> friendMap = new HashMap<>();
		//Map<String, List<String>> friendMap2 = new HashMap<>();
		Map<String, List<String>> result = new HashMap<>();
		
		for (String employee: employees) {
			String[] s = employee.split(",");
			employeeIDMap.put(s[0], s[1]);
		}
		
		for (String friend: friends) {
			String[] s = friend.split(",");
			String name1 = employeeIDMap.get(s[0]);
			String name2 = employeeIDMap.get(s[1]);
			
			if (!friendMap.containsKey(name1)) {
				friendMap.put(name1, new ArrayList<>());
			}
			
			if (!friendMap.containsKey(name2)) {
				friendMap.put(name2, new ArrayList<>());
			}
			
			friendMap.get(name1).add(name2);
			friendMap.get(name2).add(name1);
		}
		
		// 如果employees是按顺序输入，则可以省略employeeIDMap
		/*
		for (String friend: friends) {
			int id1 = Integer.parseInt(friend.split(",")[0]);
			int id2 = Integer.parseInt(friend.split(",")[1]);
			
			String name1 = employees[id1 - 1].split(",")[1];
			String name2 = employees[id2 - 1].split(",")[1];
			
			if (!friendMap2.containsKey(name1)) {
				friendMap2.put(name1, new ArrayList<>());
			}
			
			if (!friendMap2.containsKey(name2)) {
				friendMap2.put(name2, new ArrayList<>());
			}
			
			friendMap2.get(name1).add(name2);
			friendMap2.get(name2).add(name1);
		}
		*/
		// System.out.println(employeeIDMap);
		// System.out.println(friendMap);
		// System.out.println(friendMap2);
		
		for (String employee: employees) {
			//List<String> list = new ArrayList<>();
			String key = employee.split(",")[1];

			result.put(key, new ArrayList<>());
			for (String friend: friendMap.get(key)) {
				result.get(key).add(friend);
			}
			
		}
		
		return result;
	}
	
	// Follow up 1:
	public static Map<String, Integer> friendFromOtherDep(String[] employees, String[] friends) {
		Map<String, String> departmentIDMap = new HashMap<>();
		Map<String, List<String>> friendMap = new HashMap<>();
		Map<String, Integer> resultMap = new HashMap<>();
		
		for (String employee: employees) {
			String[] s = employee.split(",");
			departmentIDMap.put(s[0], s[2]);
		}
		
		for (String friend: friends) {
			String[] s = friend.split(",");
			String id1 = s[0];
			String id2 = s[1];
			
			if (!friendMap.containsKey(id1)) {
				friendMap.put(id1, new ArrayList<>());
			}
			
			if (!friendMap.containsKey(id2)) {
				friendMap.put(id2, new ArrayList<>());
			}
			
			friendMap.get(id1).add(id2);
			friendMap.get(id2).add(id1);
		}
		
		for (String employee: employees) {
			String[] s = employee.split(",");
			String department = s[2];
			String id = s[0];
			
			for (String friendID: friendMap.get(id)) {
				if (!departmentIDMap.get(friendID).equals(department)) {
					resultMap.put(department, resultMap.getOrDefault(department, 0) + 1);
					break;
				}
			}
			
		}
		
		//System.out.println(departmentIDMap);
		//System.out.println(friendMap);
		//System.out.println(resultMap);
		
		return resultMap;
	}
	
	// Follow up 2:
	public static class UnionFind {
		Map<Integer, Integer> parent;
		
		UnionFind(int n) {
			parent = new HashMap<>();
			for (int i = 1; i <= n; i++) {
				parent.put(i, i);
			}
		}
		
		int find(int x) {
            if (parent.get(x) == x) {
                return x;
            }
            
            int pX = find(parent.get(x));
            parent.put(x, pX);
            return pX;
		}
		
        void union(int x, int y){
            int pX = find(x);
            int pY = find(y);
            if(pX != pY) {
                parent.put(pX, pY);
            }
        }
	}
	
	public static boolean isAllInCycle(String[] employees, String[] friends) {
		int size = employees.length;
		UnionFind uf = new UnionFind(size);

		for (String friend: friends) {
			String[] s = friend.split(",");
			int id1 = Integer.parseInt(s[0]);
			int id2 = Integer.parseInt(s[1]);
			
			uf.union(id1, id2);
		}
		System.out.println(uf.parent);
		int parent = uf.find(1);

		for (String employee: employees) {
			String[] s = employee.split(",");
			int id = Integer.parseInt(s[0]);
			
			if (uf.find(id) != parent) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【基本题】输出所有的employee的friendlist ***********");
		String[] employees1 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] friends1 = {"1,2","1,3","2,4"};
		System.out.println(friendList(employees1, friends1));
		
		System.out.println("*********** 【Follow up 1】输出每个department里有多少人的朋友是其他部门的 ***********");
		String[] employees2 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] friends2 = {"1,2","1,3","2,4"};
		System.out.println(friendFromOtherDep(employees2, friends2));
		
		System.out.println("*********** 【Follow up 2】输出是否所有employee都在一个社交圈  ***********");		
		String[] employees31 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] friends31 = {"1,2","1,3","2,4"};
		String[] employees32 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager", "5,Tom,Manager"};
		String[] friends32 = {"1,2","1,3","4,5"};
		System.out.println(isAllInCycle(employees31, friends31));
		System.out.println(isAllInCycle(employees32, friends32));
	}
}
