
public class Solution {
	public static void main(String[] args) {
		RandomizedSet randomSet = new RandomizedSet();
		
		System.out.println(randomSet.insert(1));
		System.out.println(randomSet.remove(2));
		System.out.println(randomSet.insert(2));
		for (int i = 0; i < 50; i++) {
			System.out.print(randomSet.getRandom() + ", ");
		}
		System.out.println();
		System.out.println(randomSet.remove(1));
		System.out.println(randomSet.insert(2));
		System.out.println(randomSet.getRandom());		
		
	}
}
