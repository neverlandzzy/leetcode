
public class Solution {
	/**
	 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 * 
	 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i 
	 * to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
	 * 
	 * Return the starting gas station's index if you can travel around the circuit once, otherwise 
	 * return -1.
	 * 
	 * Note:
	 * The solution is guaranteed to be unique.
	 */
	
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int sum = 0;
        
        int start = 0;
        int n = gas.length;
        
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        
        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {
       int[] gas1 = {1,2,3,4,5};
       int[] cost1 = {3,4,5,1,2};
       int[] gas2 = {2,3,4};
       int[] cost2 = {3,4,3};

        System.out.println(canCompleteCircuit(gas1, cost1));
        System.out.println(canCompleteCircuit(gas2, cost2));
    }
}
