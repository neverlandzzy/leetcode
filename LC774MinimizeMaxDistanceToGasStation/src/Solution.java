
public class Solution {
	/**
	 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
	 * 
	 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
	 * 
	 * Return the smallest possible value of D.
	 * 
	 * Example:
	 * 
	 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
	 * Output: 0.500000
	 * 
	 * Note:
	 * 1. stations.length will be an integer in range [10, 2000].
	 * 2. stations[i] will be an integer in range [0, 10^8].
	 * 3. K will be an integer in range [1, 10^6].
	 * 4. Answers within 10^-6 of the true value will be accepted as correct.
	 */
	
	// https://discuss.leetcode.com/topic/118701/easy-and-concise-solution-using-binary-search-c-java-python
	// https://leetcode.com/problems/minimize-max-distance-to-gas-station/solution/
	//
	// D 在0 ~ stations的range，即[0, 10^8]之间。用二分法逼近求D。
	// 当maximum distance为D时，对于stations中的任一区间X = station[i + 1] - station[i]，我们需要在station[i]和station[i + 1]间插入X/D个station使得新的区间小于D
	// 因此，对于每次尝试的D，若需要插入的station个数大于K(valid == false)，则说明D太小(start = mid)，反之说明D太大
    public static double minmaxGasDist(int[] stations, int K) {
    	int n = stations.length;
    	double start = 0;
        double end = stations[n - 1] - stations[0];
        
        while (end - start > 1e-6) {
        	double mid = start + (end - start) / 2;
        	
        	if (valid(stations, K, mid)) {
        		end = mid;
        	} else {
        		start = mid;
        	}
        }
        
        return start;
    }
    
    private static boolean valid(int[] stations, int K, double D) {
    	int count = 0;
    	for (int i = 0; i < stations.length - 1; i++) {
    		count += (int) ((stations[i + 1] - stations[i]) / D);
    	}
    	
    	return count <= K;
    }
    
    public static void main(String[] args) {
		int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		System.out.println(minmaxGasDist(stations, 9));
	}
}
