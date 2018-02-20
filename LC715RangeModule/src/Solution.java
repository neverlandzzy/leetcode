
public class Solution {
	public static void main(String[] args) {
		RangeModule obj = new RangeModule();
		obj.addRange(10, 20);
		obj.removeRange(14, 16);
		
		System.out.println(obj.queryRange(10, 14));
		System.out.println(obj.queryRange(13, 15));
		System.out.println(obj.queryRange(16, 17));
	}
}
