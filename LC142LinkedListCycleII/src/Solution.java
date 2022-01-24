
public class Solution {
	/**
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 *
	 * Note: Do not modify the linked list.
	 *
	 * Follow up:
	 * Can you solve it without using extra space?
	 */
	// 证明
	// https://monkeysayhi.github.io/2017/08/19/【刷题】Linked-List-Cycle-II/
	// https://www.cnblogs.com/hiddenfox/p/3408931.html
	// 首先需要证明“第一次相遇一定发生在slow第一次入环的过程中”
	// 因为fast的速度是slow的两倍。则，fast第一次到entrance时（“第一次”指通过“环”到达entrance），slow到达链表中部节点mid，
	// 如果ENTRANCE等于HEAD -- 此情况下环最长。经过s个节点，slow恰好第一次到达entrance；经过2s个节点，fast恰好第二次到达entrance。
	// 					     在此过程中，slow有且仅有一次从mid走到entrance，fast有且仅有一次从head经过mid走到entrance，从而，fast与slow必然
	//                       有且仅有一次在mid于entrance之间相遇，这是二者第一次相遇
	// 如果ENTRANCE不等于HEAD -- 此情况下，环均比第一种情况短。重复上述过程，slow仍然有且仅有一次从mid走到entrance，但fast却由于环的缩短，可能不止一次
	//                         与slow相遇并走到entrance。我们只关注第一次相遇，显然，第一次相遇仍然发生在slow第一次入环的过程中。
	// 改进：
	// slow : a + b
	// fast : a + n(b + c) + b
	// 2(a + b) = a + b + n(b + c)
	// a = (n - 1)b + (n - 1)c + c
	// a = (n - 1)(b + c) + c --> (n - 1)(b + c)即绕环(n - 1)次
	
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode joint = null;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                joint = slow;
                break;
            }
        }
        
        if (joint != null) {
            slow = head;
            while (slow != joint) {
                joint = joint.next;
                slow = slow.next;
            }
        }
         
        return joint;
    }
    
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node2; // cycle
		
		ListNode head = node1;
		
		System.out.println(detectCycle(head).val);	
	}
}
