
public class Solution {
	public static void main(String[] args) {

		/*
		              1
		          /   |   \
		         3    2    4
		        / \
		       5   6
		*/
		
		
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		Node node4 = new Node();
		Node node5 = new Node();
		Node node6 = new Node();
		
		node1.val = 1;
		node2.val = 2;
		node3.val = 3;
		node4.val = 4;
		node5.val = 5;
		node6.val = 6;
		
		node1.children.add(node3);
		node1.children.add(node2);
		node1.children.add(node4);
		
		node3.children.add(node5);
		node3.children.add(node6);
		
		Codec codec = new Codec();
		System.out.println(codec.serialize(node1));
	}
}
