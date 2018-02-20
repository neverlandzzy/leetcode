
public class Solution {
	public static void main(String[] args) {
		Excel excel = new Excel(3, 'C');
		excel.set(1, 'A', 2);
		String[] s = {"A1", "A1:B2"}; 
		excel.sum(3, 'C', s);
		excel.set(2, 'B', 2);
		
		System.out.println(excel.get(3, 'C'));
	}
}
