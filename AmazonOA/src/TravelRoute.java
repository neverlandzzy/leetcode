import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TravelRoute {
	public static List<List<Integer>> travelRoute(int maxDistance, List<List<Integer>> forwardList, List<List<Integer>> backwardList) {
		List<List<Integer>> result = new ArrayList<>();
		
		int max = 0;
		
		for (List<Integer> forward: forwardList) {
			for (List<Integer> backward: backwardList) {
				if (forward.get(1) + backward.get(1) <= maxDistance) {
					max = Math.max(forward.get(1) + backward.get(1), max);
				}
			}
		}
		
		for (List<Integer> forward: forwardList) {
			for (List<Integer> backward: backwardList) {
				if (forward.get(1) + backward.get(1) == max) {
					result.add(new ArrayList<>(Arrays.asList(forward.get(0), backward.get(0))));
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		List<List<Integer>> forwardList1 = new ArrayList<>();
		forwardList1.add(new ArrayList<>(Arrays.asList(1, 2000)));
		forwardList1.add(new ArrayList<>(Arrays.asList(2, 4000)));
		forwardList1.add(new ArrayList<>(Arrays.asList(3, 6000)));
		
		List<List<Integer>> returnList1 = new ArrayList<>();
		returnList1.add(new ArrayList<>(Arrays.asList(1, 2000)));
		
		System.out.println(travelRoute(7000, forwardList1, returnList1)); //[[2, 1]]
		
		
		List<List<Integer>> forwardList2 = new ArrayList<>();
		forwardList2.add(new ArrayList<>(Arrays.asList(1, 3000)));
		forwardList2.add(new ArrayList<>(Arrays.asList(2, 5000)));
		forwardList2.add(new ArrayList<>(Arrays.asList(3, 7000)));
		forwardList2.add(new ArrayList<>(Arrays.asList(4, 10000)));
		
		List<List<Integer>> returnList2 = new ArrayList<>();
		returnList2.add(new ArrayList<>(Arrays.asList(1, 2000)));
		returnList2.add(new ArrayList<>(Arrays.asList(2, 3000)));
		returnList2.add(new ArrayList<>(Arrays.asList(3, 4000)));
		returnList2.add(new ArrayList<>(Arrays.asList(4, 5000)));
		
		System.out.println(travelRoute(10000, forwardList2, returnList2)); //[[2, 4], [3, 2]]
		System.out.println(travelRoute(16000, forwardList2, returnList2)); //[[2, 4], [3, 2]]
	}
}
