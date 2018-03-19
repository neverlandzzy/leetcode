package onsite;

import onsite.UnrolledLinkedList.Node;

public class ZUnrolledLinkedList {
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
}
