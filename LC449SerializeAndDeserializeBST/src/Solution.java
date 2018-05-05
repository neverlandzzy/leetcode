import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	public static void main(String[] args) {
		
		TreeNode node1 = new TreeNode(10);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(6);
		TreeNode node6 = new TreeNode(8);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.right = node6;
		
		TreeNode root = node1;
		
		Codec codec = new Codec();
		System.out.println(codec.serialize(root));
		
		String test = codec.serialize(root);
		
		Queue<String> nodes = new LinkedList<String>();
		nodes.addAll(Arrays.asList(test.split(",")));
		
		System.out.println(nodes);
		
		System.out.println(codec.deserialize(test));
	}
}
