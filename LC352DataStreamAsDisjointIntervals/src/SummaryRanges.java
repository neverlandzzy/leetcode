import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


class SummaryRanges {

    /** Initialize your data structure here. */
	
	TreeMap<Integer, Interval> treeMap;
	
    public SummaryRanges() {
        treeMap = new TreeMap<>();
    }
    
    public void addNum(int val) {
    	if (treeMap.containsKey(val)) {
    		return;
    	}
    	
    	Integer lowerKey = treeMap.lowerKey(val);
    	Integer higherKey = treeMap.higherKey(val);
    	
    	if (lowerKey != null && higherKey != null && treeMap.get(lowerKey).end + 1 == val && higherKey == val + 1) {
    		treeMap.get(lowerKey).end = treeMap.get(higherKey).end;
    		treeMap.remove(higherKey);
    	} else if (higherKey != null && higherKey == val + 1) {
    		treeMap.put(val, new Interval(val, treeMap.get(higherKey).end));
    		treeMap.remove(higherKey);
    	} else if (lowerKey != null && treeMap.get(lowerKey).end + 1 == val) {
    		treeMap.get(lowerKey).end = Math.max(treeMap.get(lowerKey).end, val);
    	} else {
    		treeMap.put(val, new Interval(val, val));
    	}
    }
    
    public List<Interval> getIntervals() {
        return new ArrayList<>(treeMap.values());
    }
}
