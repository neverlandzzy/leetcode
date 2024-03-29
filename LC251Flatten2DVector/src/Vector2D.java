import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Vector2D implements Iterator<Integer> {	
	/**
	 * Implement an iterator to flatten a 2d vector.
	 * 
	 * Example:
	 * 
	 * Input: 2d vector =
	 * [
	 *   [1,2],
	 *   [3],
	 *   [4,5,6]
	 * ]
	 * Output: [1,2,3,4,5,6]
	 * 
	 * Explanation: By calling next repeatedly until hasNext returns false, 
	 *              the order of elements returned by next should be: [1,2,3,4,5,6].
	 * Follow up:
	 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
	 */
	
	private Iterator<List<Integer>> i;
	private Iterator<Integer> j;
	List<List<Integer>> vec2d;
	
    public Vector2D(int[][] vec) {
		vec2d = new ArrayList<>();
		for (int[] innerVec: vec) {
			List<Integer> list = new ArrayList<>();

			for (int num: innerVec) {
				list.add(num);
			}

			vec2d.add(list);
		}
        i = vec2d.iterator();
    }

    @Override
    public Integer next() {
        hasNext();
        return j.next();
    }

    @Override
    public boolean hasNext() {
        while ((j == null || !j.hasNext()) && i.hasNext()) {
        	j = i.next().iterator();
        }
        
        return j != null && j.hasNext();
    }
}
