import java.util.LinkedList;
import java.util.Queue;


public class StringIterator {
	/*
	 * Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.
	 * 
	 * The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter 
	 * existing in the original uncompressed string.
	 * 
	 * next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
	 * hasNext() - Judge whether there is any letter needs to be uncompressed.
	 * 
	 * Note:
	 * Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple 
	 * test cases. Please see here for more details.
	 * 
	 * Example:
	 * 
	 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
	 * 
	 * iterator.next(); // return 'L'
	 * iterator.next(); // return 'e'
	 * iterator.next(); // return 'e'
	 * iterator.next(); // return 't'
	 * iterator.next(); // return 'C'
	 * iterator.next(); // return 'o'
	 * iterator.next(); // return 'd'
	 * iterator.hasNext(); // return true
	 * iterator.next(); // return 'e'
	 * iterator.hasNext(); // return false
	 * iterator.next(); // return ' '
	 */
	
    private Queue<Character> queue;
    
    public StringIterator(String compressedString) {
        queue = new LinkedList<>();
        for (int i = 0; i < compressedString.length(); i++) {
            queue.offer(compressedString.charAt(i));
        }
    }
    
    public char next() {
        char c = queue.poll();
        int counter = queue.poll() - '0';
        

        if (counter > 1) {
            queue.offer((char)(counter--));
            queue.offer(c);
        }
        
        return c;
    }
    
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
