package onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GitCommit {
	
	/*
	 * 【基本题】给出一个Git的commit，找出所有的parents。每个节点都有一个或多个parent。（逐层打印）
	 * 【Follow up】 两个commits （nodes），找到他们的最近的公共parent
	 */
	static class GitNode{
	    int id;
	    List<GitNode> parents;
	    public GitNode(int id){
	        this.id = id;
	        this.parents = new ArrayList<>();
	    }
	    
	    @Override
	    public String toString() {
	    	return "" + id;
	    }
	}
	
	// 基本题 BFS
	public static List<List<GitNode>> findAllCommits(GitNode node){
		List<List<GitNode>> result = new ArrayList<>();
		
		if (node == null) {
			return result;
		}
		
		Queue<GitNode> queue = new LinkedList<>();
		Set<GitNode> visited = new HashSet<>();
		
		queue.offer(node);
		visited.add(node);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<GitNode> list = new ArrayList<>();
			
			for (int i = 0; i < size; i++) {
				GitNode cur = queue.poll();
				list.add(cur);
				
				for (GitNode parent: cur.parents) {
					if (!visited.contains(parent)) {
						queue.offer(parent);
						visited.add(parent);
					}
				}
			}
			result.add(list);
		}
		
		return result;
	}
	
	// Follow up 先对node1做bfs，用hashMap记录node1 到每个parent的距离
	//           然后对node2做bfs，遇到map中的点就算下距离，取最小的距离
	public static  GitNode findLCA(GitNode node1, GitNode node2){
		if (node1 == node2) {
			return node1;
		}
		
		// 对node1做bfs，并建立map
		Map<GitNode, Integer> map = new HashMap<>();
		Queue<GitNode> queue = new LinkedList<>();
		queue.offer(node1);
		
		int level = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				GitNode node = queue.poll();
				map.put(node, level);
				
				for (GitNode parent: node.parents) {
					if (!map.containsKey(parent)) {
						queue.offer(parent);
					}
				}
				level++;
			}
		}

		// 再对node2 做bfs
		queue.offer(node2);
		level = 0;
		GitNode result = null;
		int minPath = Integer.MAX_VALUE;
		
		Set<GitNode> visited = new HashSet<>();
		visited.add(node2);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				GitNode node = queue.poll();
				if (map.containsKey(node)) {
					if (minPath > level + map.get(node)) {
						result = node;
						minPath = level + map.get(node);
					}
				}
				
				for (GitNode parent: node.parents) {
					if (!visited.contains(parent)) {
						queue.offer(parent);
						visited.add(parent);
					}
				}
				
				level++;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		// 基本题
		//        1
		//    /   |   \
		//  2     3    5
		//  |     |    |
		//  |     4    |
		//  \     |    /
		//        6   
       
		GitNode g1 = new GitNode(1);
        GitNode g2 = new GitNode(2);
        GitNode g3 = new GitNode(3);
        GitNode g4 = new GitNode(4);
        GitNode g5 = new GitNode(5);
        GitNode g6 = new GitNode(6);

        g2.parents.add(g1);
        g3.parents.add(g1);
        g5.parents.add(g1);
        g4.parents.add(g3);
        g6.parents.add(g2);
        g6.parents.add(g4);
        g6.parents.add(g5);
        
        System.out.println(findAllCommits(g6));
        
        
        // Follow up
        /*
        *
        *   5 <-  4  <- 2
        *    \       \
        *     \ <- 3 <- 1
        * */
        GitNode g21 = new GitNode(1);
        GitNode g22 = new GitNode(2);
        GitNode g23 = new GitNode(3);
        GitNode g24 = new GitNode(4);
        GitNode g25 = new GitNode(5);

        g21.parents.add(g23);
        g21.parents.add(g24);
        g22.parents.add(g24);
        g23.parents.add(g25);
        g24.parents.add(g25);

        System.out.println(findLCA(g23, g24));
	}
}
