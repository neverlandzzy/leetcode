package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import onsite.RandomSelection.Item;
import onsite.RandomSelection.RandomSelectionII;

public class ZRandomSelection {
	/*
	 * 【题目】给你一个List 里面有ads，然后写一个get() function，来随机get一个list里面的ad，不能重复，而且get 完了后 return null。
	 * 【Follow up】 如果每个元素都带一个概率权重，也就是，不是等概率而是有不同的概率的选择，
	 */
	
	static class ZRandomSelectionI {		
	}
	
	static class ZRandomSelectionII {		
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 3, 2, 7, 1, 10, 4, 6, 12));
		
		ZRandomSelectionI rs = new ZRandomSelectionI(list1);
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		System.out.println(rs.get());
		
		System.out.println("===================");
		
		Item item1 = new Item(3, 5);
		Item item2 = new Item(1, 4);
		Item item3 = new Item(2, 2);
		Item item4 = new Item(6, 7);
		Item item5 = new Item(5, 1);
		
		List<Item> list2 = new ArrayList<>(Arrays.asList(item1, item2, item3, item4, item5));
		
		ZRandomSelectionII rsII = new ZRandomSelectionII(list2);
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
	}
}
