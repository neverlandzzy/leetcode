import java.util.ArrayList;
import java.util.List;


public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    	children = new ArrayList<>();
    }

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
}
