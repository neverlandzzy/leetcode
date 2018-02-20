
public class NumArray {
	
	// solution 1: Binary Indexed Tree
	// http://blog.csdn.net/lulipeng_cpp/article/details/7816527
	// http://zhaoleyi.github.io/2016/04/06/转：搞懂树状数组/
	// http://blog.csdn.net/u013974420/article/details/41942435
	
	int n;
    int[] a;
    int[] BIT;
    
    public NumArray(int[] nums) {
        this.a = nums;
        this.n = nums.length;
        BIT = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            init(i, a[i]);
        }
    }
    
    private int lowbit(int k) {
        return k & (-k);
    }
    
    private void init(int i, int val) {
        i++;
        
        while (i <= n) {
            BIT[i] += val;
            i += lowbit(i);
        }
    }
    
    public void update(int i, int val) {
        if (i < 0 || i > n) {
            return;
        }
        int num = val - a[i];
        a[i] = val;
        init(i, num);
    }
    
    private int getSum(int i) {
        int sum = 0;
        i++;
        
        while (i > 0) {
            sum += BIT[i];
            i -= lowbit(i);
        }
        
        return sum;
    }
    
    public int sumRange(int i, int j) {
        if (i < 0 || i > n || j < 0 || j > n) {
            return 0;
        }
        
        return getSum(j) - getSum(i - 1);
    }
	
	// solution 2: Segment Tree
	/*
	private class SegmentTreeNode {
		int sum;
		int start;
		int end;
		SegmentTreeNode left;
		SegmentTreeNode right;
		
		private SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.sum = 0;
			this.left = null;
			this.right = null;
		}
		
	}
	
	SegmentTreeNode root = null;
	
	private SegmentTreeNode buildTree(int[] nums, int start, int end) {
		
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		
		if (start > end) {
			return null;
		} else {
			if (start == end) {
				node.sum = nums[start];
			} else {
				int mid = start + (end -start)/2;
				node.left = buildTree(nums, start, mid);
				node.right = buildTree(nums, mid+1, end);
				node.sum = node.left.sum + node.right.sum;
			}
			return node;
		}
	}
	
    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    void update(int i, int val) {
        update(root, i, val);
    }

    private void update(SegmentTreeNode root, int i, int val) {
    	
    	if (i > root.end || i < root.start) {
    		return;
    	}
    	
    	if (root.start == root.end) {
    		root.sum = val;
    	} else {
	    	int mid = root.start + (root.end - root.start) / 2;
	    	
	    	if (i <= mid) {
	    		update(root.left, i, val);
	    	} else {
	    		update(root.right, i, val);
	    	} 
	    	
	    	root.sum = root.left.sum + root.right.sum;
	    }
    }
    
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    private int sumRange(SegmentTreeNode root, int i, int j) {
    	
    	if (i < root.start || j > root.end) {
    		return 0;
    	}   	
    	
    	if (root.start == i && root.end == j) {
    		return root.sum;
    	} 
    	
    	int mid = root.start + (root.end - root.start) / 2;
    	
    	if (j <= mid) {
    		return sumRange(root.left, i, j);
    	} else if (i >= mid + 1) {
    		return sumRange(root.right, i, j);
    	} else {
    		return sumRange(root.left,i, mid) + sumRange(root.right, mid + 1, j);
    	}
    }
    */
    
}


//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.update(1, 10);
//numArray.sumRange(1, 2);
