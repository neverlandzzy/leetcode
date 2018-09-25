import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class TopVotedCandidate {
	Map<Integer, Integer> voteMap;
	TreeMap<Integer, Integer> timeMap;
	
    public TopVotedCandidate(int[] persons, int[] times) {
        voteMap = new HashMap<>();
        timeMap = new TreeMap<>();
        int max = 0;
        
        int n = persons.length;
        
        for (int i = 0; i < n; i++) {
            int p = persons[i];
            int t = times[i];            
            voteMap.put(p, voteMap.getOrDefault(p, 0) + 1);
            int vote = voteMap.get(p);
            
            if (max <= vote) {
                max = vote;
                timeMap.put(t, p);
            }
        }
    }
    
    public int q(int t) {
        return timeMap.get(timeMap.floorKey(t));
    }
}
