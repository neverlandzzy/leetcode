package onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import onsite.GitCommit.GitNode;

public class ZGitCommit {
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
	
	public static List<List<GitNode>> findAllCommits(GitNode node){

	}
	
	public static  GitNode findLCA(GitNode node1, GitNode node2){

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
        System.out.println(findLCA(g22, g21));
	}
}
