package OA;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NAV {
		
	public static String navDiff(String input) {
		String port = input.split("\\|")[0].split(":")[1];
		String bench = input.split("\\|")[1].split(":")[1];
		Map<String, Double> portMap = new HashMap<>();
		Map<String, Double> benchMap = new HashMap<>();
		TreeSet<String> treeSet = new TreeSet<>();
		
		double navPort = 0;
		double navBench = 0;
		
		StringBuilder sb = new StringBuilder();

		for (String p: port.split(";")) {
			String[] str = p.split(",");
			String name = str[0];
			int qty = Integer.parseInt(str[1]); 
			int price = Integer.parseInt(str[2]); 
			
			portMap.put(name, (double)qty * price);
			navPort += qty * price;
		}
		
		for (String b: bench.split(";")) {
			String[] str = b.split(",");
			String name = str[0];
			int qty = Integer.parseInt(str[1]); 
			int price = Integer.parseInt(str[2]); 
			
			benchMap.put(name, (double)qty * price);
			navBench += qty * price;
		}
		
		for (String name: portMap.keySet()) {
			double val = portMap.get(name);
			portMap.put(name, val * 100 / navPort);
			treeSet.add(name);
		}

		for (String name: benchMap.keySet()) {
			double val = benchMap.get(name);
			benchMap.put(name, val * 100 / navBench);
			treeSet.add(name);
		}
		
		
		for (String name: treeSet) {
			double portVal = portMap.containsKey(name) ? portMap.get(name) : 0;
			double benchVal = benchMap.containsKey(name) ? benchMap.get(name) : 0;
			
			double val = portVal - benchVal;
			String valStr;
			
			if (val == 0.0 && !portMap.containsKey(name)) {
				valStr = "-" + String.format("%.2f", val);
			} else {
				valStr = String.format("%.2f", val);
			}
			sb.append(name).append(":").append(valStr).append(",");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String test1 = "PORT:AXN,0,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20;XYZ,0,10";
		String test2 = "PORT:AXN,10,10;BGT,20,30;CXZ,10,30|BENCH:AXN,50,10;BGT,30,30;DFG,30,20";
		
		System.out.println(navDiff(test1));
		System.out.println(navDiff(test2));
	}
}
