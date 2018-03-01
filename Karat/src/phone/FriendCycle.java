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
		
		  Map<Integer, String> idMap = new HashMap<>();
		  Map<Integer, List<Integer>> friendMap = new HashMap<>();
		  Map<String, List<String>> result = new HashMap<>();
		  
		  for (String employee: employees) {
		    String[] s = employee.split(",");
		    int id = Integer.parseInt(s[0]);
		    String name = s[1];
		    
		    idMap.put(id, name);
		  }
		  
		  for (String friend: friends) {
		    String[] s = friend.split(",");
		    int selfId = Integer.parseInt(s[0]);
		    int friendId = Integer.parseInt(s[1]);
		    
		    if (!friendMap.containsKey(selfId)) {
		      friendMap.put(selfId, new ArrayList<>());
		    }
		    
		    if (!friendMap.containsKey(friendId)) {
		      friendMap.put(friendId, new ArrayList<>());
		    }
		    
		    friendMap.get(selfId).add(friendId);
		    friendMap.get(friendId).add(selfId);
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
		  
		  for (int key: idMap.keySet()) {
		    String name = idMap.get(key);
		    if (!result.containsKey(name)) {
		      result.put(name, new ArrayList<>());
		    }
		    
		    if (friendMap.containsKey(key)) {
		      for (int friendId: friendMap.get(key)) {
		        result.get(name).add(idMap.get(friendId));
		      }
		    }
		  }
		  
		  return result;
	}
	
	// Follow up 1:
	public static Map<String, Integer> friendFromOtherDep(String[] employees, String[] friends) {
	    Map<Integer, String> departmentMap = new HashMap<>();
	    Map<Integer, List<Integer>> friendMap = new HashMap<>();
	    Map<String, Integer> result = new HashMap<>();
	    
	    for (String employee: employees) {
	      String[] s = employee.split(",");
	      int id = Integer.parseInt(s[0]);
	      String department = s[2];
	      
	      departmentMap.put(id, department);
	    }
	    
	    for (String friend: friends) {
	      String[] s = friend.split(",");
	      int selfId = Integer.parseInt(s[0]);
	      int friendId = Integer.parseInt(s[1]);
	      
	      if (!friendMap.containsKey(selfId)) {
	        friendMap.put(selfId, new ArrayList<>());
	      }
	      
	      if (!friendMap.containsKey(friendId)) {
	        friendMap.put(friendId, new ArrayList<>());
	      }
	      
	      friendMap.get(selfId).add(friendId);
	      friendMap.get(friendId).add(selfId);
	    }
	    
	    for (int key: departmentMap.keySet()) {
	      String department = departmentMap.get(key);
	      
	      if (friendMap.containsKey(key)) {
	        for (int friendId: friendMap.get(key)) {
	          if (!departmentMap.get(friendId).equals(department)) {
	            result.put(department, result.getOrDefault(department, 0) + 1);
	            break;
	          }
	        }
	      } else {
	        if (!result.containsKey(department)) {
	          result.put(department, 0);
	        }
	      }
	    }
	    
	   return result;
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
		String[] employees11 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] employees12 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager", "5,Tom,Manager", "6,Jane,Finance"};
		String[] friends1 = {"1,2","1,3","2,4"};
		System.out.println(friendList(employees11, friends1));
		System.out.println(friendList(employees12, friends1));
		
		System.out.println("*********** 【Follow up 1】输出每个department里有多少人的朋友是其他部门的 ***********");
		String[] employees21 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] employees22 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager", "5,Tom,Manager", "6,Jane,Finance"};
		String[] friends2 = {"1,2","1,3","2,4"};
		System.out.println(friendFromOtherDep(employees21, friends2));
		System.out.println(friendFromOtherDep(employees22, friends2));
		
		System.out.println("*********** 【Follow up 2】输出是否所有employee都在一个社交圈  ***********");		
		String[] employees31 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager"};
		String[] employees32 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager", "5,Tom,Manager", "6,Jane,Finance"};
		String[] friends31 = {"1,2","1,3","2,4"};
		String[] employees33 = {"1,Alice,HR", "2,Bob,Engineer", "3,Jack,Engineer", "4,Peter,Manager", "5,Tom,Manager"};
		String[] friends33 = {"1,2","1,3","4,5"};
		System.out.println(isAllInCycle(employees31, friends31));
		System.out.println(isAllInCycle(employees32, friends31));
		System.out.println(isAllInCycle(employees33, friends33));
	}
}
