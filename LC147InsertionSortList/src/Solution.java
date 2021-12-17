
public class Solution {
	
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
		ListNode cur = head;

        while (cur != null) {
        	ListNode pre = dummy;
        	
        	while(pre.next != null && pre.next.val < cur.val) {
				pre = pre.next;
        	}
        	
        	ListNode next = cur.next;
        	/*
        	System.out.println("!!!!!!!!!!!!!!!!!!!");
        	if (head != null) System.out.print(" head = " + head.val);
        	if (node != null) System.out.print(" node = " + node.val);
        	System.out.println();
        	*/

			cur.next = pre.next;
			pre.next = cur;
			cur = next;
            /*
            System.out.println("+++++++++++++++++");
            if (temp != null) System.out.print(" temp = " + temp.val);
            if (head != null) System.out.print(" head = " + head.val);
            if (node != null) System.out.print(" node = " + node.val);
            System.out.println();
            System.out.println("=================");
            */
        }

        return dummy.next;
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		
		node4.next = node3;
		node3.next = node1;
		node1.next = node2;
		
		ListNode head = node4;
		
		while(head != null) {
			System.out.print(head.val + " -> ");
			head = head.next;
		}
		
		System.out.println();
		
		head = insertionSortList(node4);
		
		while(head != null) {
			System.out.print(head.val + " -> ");
			head = head.next;
		}
		
	}
}
