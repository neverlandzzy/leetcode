package onsite;

import onsite.NaryTree.Edge;
import onsite.NaryTree.Node;

public class ZNaryTree {
	
	public static int minCostPathI(Node root) {
		
		
	}
	
	
	 public static void main(String[] args) {
		 
		/*
		 *            1
		 *    (2)/ (1)| (3)\
		 *      2     3     4
		 * (4)/ (5)\(8)|  (7)\ 
		 *   5     6   7     8
		 */
		Node node1 = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		Node node4 = new Node();
		Node node5 = new Node();
		Node node6 = new Node();
		Node node7 = new Node();
		Node node8 = new Node();
	
		
		Edge edge1 = new Edge(node2, 2);
		Edge edge2 = new Edge(node3, 1);
		Edge edge3 = new Edge(node4, 3);
		Edge edge4 = new Edge(node5, 4);
		Edge edge5 = new Edge(node6, 5);
		Edge edge6 = new Edge(node7, 8);
		Edge edge7 = new Edge(node8, 7);
		
		node1.edges.add(edge1);
		node1.edges.add(edge2);
		node1.edges.add(edge3);
		node2.edges.add(edge4);
		node2.edges.add(edge5);
		node3.edges.add(edge6);
		node4.edges.add(edge7);
		
		System.out.println(minCostPathI(node1));
		System.out.println(minCostPathII(node1));
		
		Edge edge8 = new Edge(node6, 8);
		Edge edge9 = new Edge(node7, 6);

		node5.edges.add(edge8);
		node6.edges.add(edge9);

		
		System.out.println(minCostPathIII(node1));
	 }
}
