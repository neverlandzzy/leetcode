package onsite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NaryTree {
	/*
	 * 【问题】给一颗N-ary tree，有以下数据结构
	 *  class Edge {
     *   	Node node;
     *   	int cost; //大于等于0
     *	}
     *
     *  class Node {
     *      List<Edge> edges;
	 *  } 
	 *  找从root到叶节点cost之和最小的路径，返回该leaf node。（dfs）
	 *  
	 * 【follow-up】如果不是N-ary tree的结构，而是任意的单向图，问代码还是否work（yes）
	 *  有没有优化的地方？Dijkstra O(E + VlogV)
	 */
	
     static class Node {
          List<Edge> edges; //表示从这个头出发的所有edge
          
          public Node() {
        	  this.edges = new ArrayList<>(); 
          }
          
          @Override
          public String toString() {
        	  return edges.toString();
          }
	 } 
    
	 static class Edge {
		 Node node;
		 Node from;
	     int cost; //大于等于0
	     
	     public Edge(Node n, Node f, int cost) {
	         this.node = n;
	         this.from = f;
	         this.cost = cost;
	     }
	     
	     @Override
	     public String toString() {
	    	 return String.valueOf(cost);
	     }
	 }
	
	 //【基本题】（1）返回最小cost
	 public static int minCostPathI(Node root) {
		 if (root == null) {
			 return 0;
		 }
		 int[] minCost = new int[1];
		 minCost[0] = Integer.MAX_VALUE;
		 helperI(root, minCost, 0);
		 
		 return minCost[0];
	 }
	 
	 private static void helperI(Node root, int[] minCost, int cost) {
		 if (root.edges.size() == 0) {
			 minCost[0] = Math.min(minCost[0], cost);
			 return;
		 }
		 
		 for (Edge e: root.edges) {
			 Node neighbor = e.node;
			 helperI(neighbor, minCost, cost + e.cost);
		 }
	 }
	 
	 //【基本题】（2）返回最小cost路径上的所有edge
	 public static List<Edge> minCostPathII(Node root) {
		 List<Edge> result = new ArrayList<>();
		 List<Edge> list = new ArrayList<>();
		 
		 int[] minCost = new int[1];
		 minCost[0] = Integer.MAX_VALUE;
		 helperII(root, result, list, minCost, 0);
		
		 return result; 
	 }
	 
	 private static void helperII(Node root, List<Edge> result, List<Edge> list, int[] minCost, int cost) {
		 if (root == null) {
			 return;
		 }
		 
		 if (root.edges.size() == 0) {
			 if (cost < minCost[0]) {
				 minCost[0] = cost;
				 result.clear();
				 result.addAll(list);
				 return;
			 }
		 }
		 
		 for (Edge e: root.edges) {
			 Node neighbor = e.node;
			 list.add(e);
			 helperII(neighbor, result, list, minCost, cost + e.cost);
			 list.remove(list.size() - 1);
		 }
	 }
	 
	 //【Follow up】DAG O(VlogV + E)
	 public static int minCostPathIV(Node root) {
		 
		 Map<Node, Integer> map = new HashMap<>();
		 if (root == null) {
			 return 0;
		 }
		 
		 PriorityQueue<Edge> heap = new PriorityQueue<>(new Comparator<Edge>() {
			 public int compare(Edge e1, Edge e2) {
				 return e1.cost - e2.cost;
			 }
		 }); 
		 
		 map.put(root, 0);
		 for (Edge edge: root.edges) {
			 heap.offer(edge);
		 }
		
		 while (!heap.isEmpty()) {
			 Edge edge = heap.poll();
			 
			 Node neighbor = edge.node;

			 if (map.containsKey(neighbor)) {
				 continue;
			 }
			 map.put(neighbor, edge.cost);

			 for (Edge next: neighbor.edges) {
				 Edge updatedNext = new Edge(next.node, next.from, next.cost + map.get(next.from));
				 heap.offer(updatedNext);
			 }
		 }
		 

		 int min = Integer.MAX_VALUE;
		 
		 for (Node node: map.keySet()) {
			 if (node.edges.size() == 0) {
				 min = Math.min(min, map.get(node));
			 }
		 }
		 
		 // 如果要返回最短的路径，则可以在Map里存<Node, Pair>, Pair = cost + edge 这样最后可以通过pair找到leaf的root
		 return min;
	 }
	
	 // Solution 1: 改良上面的DFS, 用map记录访问过的点 -- 有问题，并没有优化
	 
	 public static List<Edge> minCostPathIII(Node root) {
		 List<Edge> result = new ArrayList<>();
		 List<Edge> list = new ArrayList<>();
		 Map<Node, Integer> map = new HashMap<>();
		 
		 int[] minCost = new int[1];
		 minCost[0] = Integer.MAX_VALUE;
		 helperIII(root, result, list, minCost, 0, map);
		 
		 return result;
	 }
	 
	 private static void helperIII(Node node, List<Edge> result, List<Edge> list, int[] minCost, int cost, Map<Node, Integer> map) {
		 if (node == null) {
			 return;
		 }
		 
		 if (map.containsKey(node) && cost >= map.get(node)) {
			 return;
		 }
		 
		 map.put(node, cost);
		 
		 if (node.edges.size() == 0) {
			 if (cost < minCost[0]) {
				 minCost[0] = cost;
				 result.clear();
				 result.addAll(list);
				 return;
			 }
		 }
		 
		 for (Edge e: node.edges) {
			 Node neighbor = e.node;
			 list.add(e);
			 helperIII(neighbor, result, list, minCost, cost + e.cost, map);
			 list.remove(list.size() - 1);
		 }
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
	
		
		Edge edge1 = new Edge(node2, node1, 2);
		Edge edge2 = new Edge(node3, node1, 1);
		Edge edge3 = new Edge(node4, node1, 3);
		Edge edge4 = new Edge(node5, node2, 4);
		Edge edge5 = new Edge(node6, node2, 5);
		Edge edge6 = new Edge(node7, node3, 8);
		Edge edge7 = new Edge(node8, node4, 7);
		
		node1.edges.add(edge1);
		node1.edges.add(edge2);
		node1.edges.add(edge3);
		node2.edges.add(edge4);
		node2.edges.add(edge5);
		node3.edges.add(edge6);
		node4.edges.add(edge7);
		
		//System.out.println(minCostPathI(node1));
		//System.out.println(minCostPathII(node1));
		
		Edge edge8 = new Edge(node6, node5, 8);
		Edge edge9 = new Edge(node7, node6, 6);

		node5.edges.add(edge8);
		node6.edges.add(edge9);

		
		System.out.println(minCostPathIII(node1));
		System.out.println(minCostPathIV(node1));
		
		Node node11 = new Node();
		Node node12 = new Node();
		Node node13 = new Node();
		Node node14 = new Node();
		Node node15 = new Node();
		Node node16 = new Node();
		
		Edge edge11 = new Edge(node12, node11, 2);
		Edge edge12 = new Edge(node13, node11, 10);
		Edge edge13 = new Edge(node14, node12, 3);
		Edge edge14 = new Edge(node15, node14, 3);
		Edge edge15 = new Edge(node16, node15, 4);
		Edge edge16 = new Edge(node16, node13, 1);
		
		node11.edges.add(edge11);
		node11.edges.add(edge12);
		node12.edges.add(edge13);
		node13.edges.add(edge16);
		node14.edges.add(edge14);
		node15.edges.add(edge15);
		
		System.out.println(minCostPathIII(node11));
		System.out.println(minCostPathIV(node11));
	 }
}
