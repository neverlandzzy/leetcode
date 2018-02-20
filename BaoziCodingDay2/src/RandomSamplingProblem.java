import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomSamplingProblem {
	/*
	 * Given a really large stream of elements (you don't know how large it is), 
	 * write a function to randomly select 10 elements from the stream.
	 * 
	 * You can iterate though the stream only once.
	 * "Random" means each element in the stream should have equal chance to be selected 
	 * in the final 10 elements. It'd be great if you can prove in math that your solution 
	 * guarantees randomness.
	 */
	
    public static List<Integer> sampleSelectTenElements(List<Integer> dataStream) {
        // also explain in comments how your algorithm guarantees randomness
    	
    	if (dataStream.size() < 10) {
    		return dataStream;
    	}
    	
    	List<Integer> result = new ArrayList<Integer>();
    	int resultSize = 10;
    	int i;
    	
    	Random randomGenerator = new Random();
    	
    	// 1. Adding first k = 10 elements into result list
    	for (i = 0; i < resultSize; i++) {
    		result.add(i, dataStream.get(i));
    	}
    	
    	// 2. For the first k elements, the probability of an element is in the result list is:
    	//    k/(k+1) * (k+1)/(k+2) *... *(n-1)/n;
    	//    which means it survives in each selection-replacement.
    	//
    	//    For the k+1 ~ n elements, the probability of the ith element is 
    	//    in the result list is the products of:
    	//    (1) One of the first k element is selected to be replaced: k/i;
    	//    (2) This position is not selected in furture operations: i/(i+1) * (i+1)/(1+2)...*(n-1)/n
    	//    Thus, it's k/n.

    	for (; i < dataStream.size(); i++) {
    		int j = randomGenerator.nextInt(i);

    		if (j < 10) {
    			result.set(j, dataStream.get(i));
    		}
    	}
    	return result;
    }
    
    public static void main(String[] args) {
		List<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.add(7);
		test.add(8);
		test.add(9);
		test.add(10);
		test.add(11);
		test.add(12);
		test.add(13);
		test.add(14);
		test.add(15);
		test.add(16);
		test.add(17);
		
		
		System.out.println(test);
		System.out.println(sampleSelectTenElements(test));
		
	}
}
