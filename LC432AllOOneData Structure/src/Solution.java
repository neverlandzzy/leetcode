
public class Solution {
	public static void main(String[] args) {
		 AllOne allOne = new AllOne();
		 allOne.inc("abc");
		 allOne.inc("edf");
		 allOne.inc("abc");
		 allOne.inc("fgh");
		 allOne.inc("edf");
		 allOne.inc("abc");		 
		 System.out.println(allOne.getMaxKey());
		 System.out.println(allOne.getMinKey());
		 
		 allOne.dec("abc");
		 allOne.dec("fgh");
		 System.out.println(allOne.getMaxKey());
		 System.out.println(allOne.getMinKey());
	}
}
