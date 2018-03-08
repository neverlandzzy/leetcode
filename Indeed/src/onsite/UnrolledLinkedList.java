package onsite;

public class UnrolledLinkedList {
	/*
	 * 【题目】Given a LinkedList, every node contains a array. Every element of the array is char, implement two functions
     *        1. get(int index) find the char at the index. 
     *        2. insert(char ch, int index) insert the char to the index
     *        
     *        follow up了add和delete。
     *        有follow up问如何更快，没答出来，说是用rope可以，不是很了解
     *        
     * 【思路】关键点在于操作的时候要考虑node为空的或者满了的情况
     *        get比较容易，就是从head traverse，定位第index个char；insert有点麻烦，有几种情况需要考虑。
	 */
	
	
	class Node {
        char[] chars; //固定大小5
        int len; // char里实际有几个字母
        Node next;
        
        public Node() {
        	chars = new char[5];
        	next = null;
        }
        
	}
	
	class LinkedList {
		Node head;
		int size; // 需要清楚是最大容量还是当前容量。答案是按当前容量做的。
		
		public LinkedList(int size) {
			head = new Node();
			this.size = size;
		}
		
		public char get(int index) {
			if (index < 0 || index >= size || size == 0) {
				return ' ';
			}
			
			Node cur = head;
			
			while (cur != null && index >= 0) {
				if (index >= cur.len) {
					index -= cur.len;
				} else {
					return cur.chars[index];
				}
				
				cur = cur.next;
			}
			
			return ' ';
		}
		
		// insert需要考虑
		// 1.普通插入；
		// 2.插入的node满了，要在后面加个node;
	    // 3.插入的node是空的，那就要在尾巴上加个新node
		
		public void insert(char ch, int index) {
			if (index > size) {
				return;
			}
			
			Node cur = head;
			
			while (cur != null && index > 0) {
				if (index >= cur.len) {
					index -= cur.len;
				} else {
					// 当前的node满了，在后面加个node，把当前node最后一个字符挪到新node中					
					if (cur.len == 5) {
						Node node = new Node();
						node.chars[0] = cur.chars[4];
						node.len = 1;
						node.next = cur.next;
						cur.next = node;
						cur.len -= 1;
					}
					
					// 将ch插入到当前node的index处，并将插入处以及之后的点向后挪一位(index ~ cur.len - 2) 挪到(index + 1, cur.len - 1)
					
					cur.len += 1;
					for (int i = cur.len - 1; i > index; i--) {
						cur.chars[i] = cur.chars[i - 1];
					}
					cur.chars[index] = ch;
					break;
				}
				cur = cur.next;
			}
			
			// 插入的node是空的，那就要在尾巴上加个新node
			if (cur == null) {
				Node node = new Node();
				node.chars[0] = ch;
				node.len = 1;
				Node tail = head;
				while (tail.next != null) {
					tail = tail.next;
				}
				tail.next = node;
			}
			size += 1;
		}
		
		// 类似insert，先考虑清楚delete的情况
	    // 1.普通的去掉一个node里面的点。
		// 2.去掉node之后，这个点空了，那就把点删掉。
		
		public void delete(int index){
		    if (index < 0 || index >= size) {
		        return;
		    }
		    
		    Node pre = new Node();
		    pre.next = head;
		    Node cur = head;
		    
		    while (cur != null && index >= 0) {
		    	if (index >= cur.len) {
		    		index -= cur.len;
		    	} else {
		    		if (cur.len == 1) {
		    			// 当前节点只有一个元素，直接删掉该节点
		    			pre.next = cur.next;
		    		} else {
		    			for (int i = index; i < cur.len - 1; i++) {
		    				cur.chars[i] = cur.chars[i + 1];
		    			}
		    			cur.len -= 1;
		    		}
		    	}
		    	
		    	pre = pre.next;
		    	cur = cur.next;
		    }
		    
		    size -= 1;
		}
	}

}
