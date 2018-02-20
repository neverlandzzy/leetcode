
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class FindATwoSumInABinarySearchTree {

public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		FindATwoSumInABinarySearchTree ins = new FindATwoSumInABinarySearchTree();

        Scanner in = new Scanner(System.in);

        int tests = Integer.parseInt(in.nextLine());
        int it = 0;

        while (it < tests) {
            String treeString = in.nextLine();
            int subTests = Integer.parseInt(in.nextLine());
            TreeNode root = ins.deserialize(treeString);
             
             int c = 0;
             while (c < subTests) {
               System.out.println(ins.isTwoSum(root, Integer.parseInt(in.nextLine())));    
                c++;
             }   
            it++;
        }      
        in.close();
    }
     
     // TODO: start coding here plz
     public boolean isTwoSum(TreeNode root, int target) {
         List<Integer> nodes = new ArrayList<Integer>();
         int lo = 0;
         int hi = nodes.size() - 1;
         
         while(lo < hi) {
             int sum = nodes.get(lo) + nodes.get(hi);
             if (sum > target) {
                 hi--;
             } else if (sum < target){
                 lo++;
             } else {
                 return true;
             }
         }    
         return false;
     }
  
    public TreeNode deserialize(String data) {
        TreeNode root = null;

        if (data == null || data.length() == 0 || data.charAt(0) == '#') {
            return root;
        }

        // "1,2,3,#,#,4,5,"
        String[] list = data.split(",");
        root = new TreeNode(Integer.parseInt(list[0]));
        int i = 1;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (i < list.length - 1) {
            TreeNode t = q.poll();

            if (!"#".equals(list[i])) {
                TreeNode left = new TreeNode(Integer.parseInt(list[i]));
                t.left = left;
                q.offer(left);
            }
            i++;
            if (!"#".equals(list[i])) {
                TreeNode right = new TreeNode(Integer.parseInt(list[i]));
                t.right = right;
                q.offer(right);
            }
            i++;
        }

        return root;
    }

  
    public class TreeNode {
        public int val;
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode(int x) {
            val = x;
      }
   }

}


