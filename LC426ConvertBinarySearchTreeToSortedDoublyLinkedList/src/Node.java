
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + val;
	}
    
    
};
