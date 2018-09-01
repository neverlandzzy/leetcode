import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ScoreGathering {
	/*
	 * We have a system that records scores. We care about how many times we see the same score, and we want to maintain a rough ordering. We also want to 
	 * send this information over the wire so that it can be collated with other results. As such we have decided to represent the stream of scores, and 
	 * the count of how many times we see the same score, as an unbalanced binary search tree.
	 * 
	 * Your job is to write a method that will take a stream of integer scores, and put them into a tree while counting the number of times each score is 
	 * seen. The first score from the given list should occupy the root node. Then you need to traverse the three breadth-first to generate a string 
	 * representation of the tree. Scores are to be inserted into the tree in the order that they are given.
	 * 
	 * For example, if you were given the stream of scores: 4, 2, 5, 5, 6, 1, 4
	 * That would result in the tree with the following structure where each node is represented as <score>:<count>
	 * 
	 *            4:2
	 *           /   \
	 *          2:1  5:2
	 *          /     \
	 *         1:1    6:1
	 *  When serialized this tree is represented by string 4:2, 2:1, 5:2, 1:1, , , 6:1
	 *  Each <score>:<count> entry is delimited with a comma. Empty children with a sibling do not output anything, but retain the comma delimiter.
	 */
	private static class TreeNode {
		int score;
		int count;
	    
		TreeNode left;
	    TreeNode right;
	    
	    TreeNode(int score, int count) { 
	    	this.score = score; 
	    	this.count = count;
	    }
	}
	
	public static String scoreGather(List<Integer> stream){
		if (stream == null || stream.size() == 0) {
			return "";
		}
		
		TreeNode root = null;
		
		for(Integer i: stream){
			root = insert(root, i);
		}
		
		return serializeTree(root);		
	}
	
	private static TreeNode insert(TreeNode root, int target){
		if (root == null) {
			return new TreeNode(target, 1);
		}
		
		if (target > root.score){
			root.right = insert(root.right, target);
		}else if(target < root.score){
			root.left = insert(root.left, target);	
		}else{
			root.count += 1;
		}
		
		return root;
	}
	
	private static String serializeTree(TreeNode root) {
		List<StringBuilder> list = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			StringBuilder level = new StringBuilder();
			for(int i =0; i<size; i++){
				TreeNode cur = queue.poll();
				if(cur==null){
					level.append(",");
				}else{
					level.append(cur.score+":"+cur.count+",");
					queue.offer(cur.left);
					queue.offer(cur.right);
				}
			}
			list.add(level);		
		}
		
		System.out.println(list);
		list.remove(list.size()-1);
		//delete last ,
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i<list.size(); i++){
			if(i==list.size()-1){//last level need to delete last ,
				StringBuilder sb = list.get(i);
				sb.deleteCharAt(sb.length()-1);
			}
			ans.append(list.get(i));
		}
		return ans.toString();		
	}
	
	public static void main(String[] args) {
		
		System.out.println(scoreGather(Arrays.asList(4,2,5,5,6,1,4)));
		//System.out.println(scoreGather(Arrays.asList(4,1, 2,3, 4, 5, 6, 7)));
		//System.out.println(scoreGather(Arrays.asList(0)));
		//System.out.println(scoreGather(new ArrayList<Integer>()));
	}
}
