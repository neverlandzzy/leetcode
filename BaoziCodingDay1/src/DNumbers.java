import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class DNumbers {

    public static void main(String[] args) {
    	//1, 2, 1, 3, 4, 3
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(1);
    	list.add(2);
    	list.add(1);
    	list.add(3);
    	list.add(4);
    	list.add(3);    
    	
    	System.out.println(list);
    	System.out.println(numOfDistinct(list));
    	System.out.println(dNums(list, 3));
    }
     
   
  
     public static List<Integer> dNums(List<Integer> a, int k) {
    	 HashSet<ArrayList<Integer>> listHash = new HashSet<ArrayList<Integer>>();
    	 List<Integer> result = new ArrayList<Integer>();
    	 
    	 int size = a.size();
    	 
    	 if (k > size) {
    		 
    		 return result;
    	 }
    	 
    	 for (int i = 0; i <= size - k; i++) {
    		 List<Integer> temp = new ArrayList<Integer>();
    		 
    		 int start = i;
    		 while (temp.size() < k) {
    			 temp.add(a.get(start));
    			 start++;
    		 }
    		 
    		 if (!listHash.contains(temp)) {
    			 result.add(numOfDistinct(temp));
    		 }
    	 }
    	 
    	 return result;
     }
     
     public static int numOfDistinct (List<Integer> a) {
    	 HashSet<Integer> nums = new HashSet<Integer>();
    	 
    	 for (int i: a) {
    		 if (!nums.contains(i)) {
    			 nums.add(i);
    		 }
    	 }
    	 
    	 return nums.size();
     }
  
}


