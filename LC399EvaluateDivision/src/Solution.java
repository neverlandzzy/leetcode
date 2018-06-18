import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number 
	 * (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
	 * 
	 * Example:
	 * Given a / b = 2.0, b / c = 3.0. 
	 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
	 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
	 * 
	 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
	 * where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
	 * 
	 * According to the example above:
	 * 
	 * equations = [ ["a", "b"], ["b", "c"] ],
	 * values = [2.0, 3.0],
	 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
	 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
	 */
	
	// https://discuss.leetcode.com/topic/59146/java-ac-solution-using-graph
	
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<String>> pairMap = new HashMap<>();
        Map<String, List<Double>> valueMap = new HashMap<>();
        
        for (int i = 0; i < equations.length; i++) {
        	String[] equation = equations[i];
        	
        	if (!pairMap.containsKey(equation[0])) {
        		pairMap.put(equation[0], new ArrayList<String>());
        		valueMap.put(equation[0], new ArrayList<Double>());
        	}
        	
        	if (!pairMap.containsKey(equation[1])) {
        		pairMap.put(equation[1], new ArrayList<String>());
        		valueMap.put(equation[1], new ArrayList<Double>());
        	}
        	
        	pairMap.get(equation[0]).add(equation[1]);
        	pairMap.get(equation[1]).add(equation[0]);
        	valueMap.get(equation[0]).add(values[i]);
        	valueMap.get(equation[1]).add(1/values[i]);
        }
        
        double[] result = new double[queries.length];
    	
    	//System.out.println(pairMap);
    	//System.out.println(valueMap);
        for (int i = 0; i < queries.length; i++) {
        	String[] query = queries[i];
        	
        	result[i] = helper(query[0], query[1], new HashSet<String>(), pairMap, valueMap, 1.0);
        	
        	if (result[i] == 0.0) {
        		result[i] = -1.0;
        	}
        }
        
        return result;
    }
    
    private static double helper(String start, String end, HashSet<String> set, Map<String, List<String>> pairMap, 
    	Map<String, List<Double>> valueMap, double value) {

    	if (set.contains(start)) {
    		return 0.0;
    	}
    	
    	if (!pairMap.containsKey(start)) {
    		return 0.0;
    	}
    	
    	if (start.equals(end)) {
    		return value;
    	}
    	
    	set.add(start);
    	double result = 0.0;
    	
    	for (int i = 0; i < pairMap.get(start).size(); i++) {
    		result = helper(pairMap.get(start).get(i), end, set, pairMap, valueMap, value * valueMap.get(start).get(i));
    		//System.out.println("result = " + result + " start = " +  start + " size = " + pairMap.get(start).size() + " i = " + i);
    		if (result != 0) {
    			break;
    		}
    	}
    	
    	//set.remove(start);
    	return result;
    }
    
    public static void main(String[] args) {
    	String[][] equations = {{"a", "b"}, {"b", "c"}, {"a", "c"}};
    	double[] values = {2.0, 3.0, 6.0};
    	//String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    	String[][] queries = {{"b", "a"}, {"a", "c"}};
    	double[] result = calcEquation(equations, values, queries);
    	
    	for(double d: result) {
    		System.out.print(d);
    		System.out.println(", ");
    	}
    	
    	System.out.println();
	}
}
