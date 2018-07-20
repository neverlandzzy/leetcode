
public class App {
	public static void main(String[] args) {
		O1Map map = new O1Map(15);
		
		printMap(map);
		
		map.add(2);
		map.add(6);
		map.add(1);
		map.add(9);
		
		printMap(map);
		
		System.out.println(map.search(6)); // true
		System.out.println(map.search(8)); // false
		System.out.println();
		
		map.delete(8);
		map.delete(1);
		
		printMap(map);
		
		map.add(9);
		printMap(map);
		
		map.add(7);
		printMap(map);
		
		System.out.println(map.search(9)); // true
		System.out.println(map.search(1)); // false
		System.out.println();
		
		map.clear();
		printMap(map);
		
		// all false
		System.out.println(map.search(2));
		System.out.println(map.search(6));
		System.out.println(map.search(7));
		System.out.println(map.search(9));
		System.out.println();
		
		map.add(9);

		printMap(map);
		
		System.out.println(map.search(2));
		System.out.println(map.search(9));
		
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
		System.out.println(map.next());
	}
	
	private static void print(int[] nums) {
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
	
	private static void printMap(O1Map map) {
		System.out.println("index = " + map.index);
		print(map.bucket);
		print(map.nums);
		System.out.println();
	}
}
