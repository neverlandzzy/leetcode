import java.util.ArrayList;
import java.util.List;


public class test {
	public static void main(String[] args) {
		List<Integer> testList = new ArrayList<Integer>();
		List<Integer> testList2 = new ArrayList<Integer>();
		
		testList.add(3);
		testList.add(4);
		testList.add(5);
		testList2.add(5);
		testList2.add(5);
		
		
		System.out.println(testList);
		System.out.println(testList2);
		testList = testList2;
		System.out.println(testList);
	}
}
