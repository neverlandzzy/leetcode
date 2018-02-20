
public class Solution {
	public static void main(String[] args) {
		RandomizedCollection randomizedCollection = new RandomizedCollection();
		
		System.out.println(randomizedCollection.insert(1));
		System.out.println(randomizedCollection.remove(2));
		System.out.println(randomizedCollection.insert(2));
		for (int i = 0; i < 50; i++) {
			System.out.print(randomizedCollection.getRandom() + ", ");
		}
		System.out.println();
		System.out.println(randomizedCollection.remove(1));
		System.out.println(randomizedCollection.insert(2));
		System.out.println(randomizedCollection.getRandom());		
		
	}
}
