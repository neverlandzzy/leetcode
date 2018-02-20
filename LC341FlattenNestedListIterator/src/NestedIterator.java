import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class NestedIterator implements Iterator<Integer> {
	/*
	 * Given a nested list of integers, implement an iterator to flatten it.
	 * 
	 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
	 * 
	 * Example 1:
	 * Given the list [[1,1],2,[1,1]],
	 * 
	 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
	 * 
	 * Example 2:
	 * Given the list [1,[4,[6]]],
	 * 
	 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
	 */
	
	// https://discuss.leetcode.com/topic/42042/simple-java-solution-using-a-stack-with-explanation/8?page=1
	
	Stack<NestedInteger> stack;
	
    public NestedIterator(List<NestedInteger> nestedList) {
    	stack = new Stack<>();
    	falttenList(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
        	return null;
        }
        
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
        	if (stack.peek().isInteger()) {
        		return true;
        	}
        	
        	flattenList(stack.pop().getList);
        }
        
        return false;
    }
    
    private void flattenList(List<NestedInteger> nestedList) {
    	for (int i = nestedList.size() - 1; i >= 0; i--) {
    		stack.push(nestedList.get(i));
    	}
    }
}
