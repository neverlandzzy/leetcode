package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomSelection {
	/*
	 * 【题目】给你一个List 里面有ads，然后写一个get() function，来随机get一个list里面的ad，不能重复，而且get 完了后 return null。
	 * 【Follow up】 如果每个元素都带一个概率权重，也就是，不是等概率而是有不同的概率的选择，
	 * 
	 * 【思路】我开始就说random 一个 number，然后get 后， 就把get的ad 删掉。但是说这种时间复杂度比较高，要优化。 我就把我get到的这个ad swap到我的arraylist最后，就可以了
	 */
	
	//【基本题】
	
	static class RandomSelectionI {		
		private int size;
		private List<Integer> list;
		
		public RandomSelectionI(List<Integer> list) {
			this.list = new ArrayList<>(list);
			this.size = list.size();
		}
		
		public Integer get() {
			if (size <= 0) {
				return null;
			}
			
			Random random = new Random();
			int index = random.nextInt(size);
			int result = list.get(index);
			int tmp  = result;
			result = list.get(size - 1);
			list.set(size - 1, tmp);
			size--;
			
			return result;
		}
	}
	//【Follow up】
	// http://www.cnblogs.com/UnGeek/p/5917995.html
	// 计算权重总和sum，然后在1到sum之间随机选择一个数R，之后遍历整个集合，统计遍历的项的权重之和，如果大于等于R，就停止遍历，选择遇到的项。
	static class Item {
		int val;
		int weight;
		
		public Item(int v, int w) {
			this.val = v;
			this.weight = w;
		}
		
		/*
		public String toString() {
			return "[" + val + ", " + weight + "] ";
		}
		*/
	}
	
	static class RandomSelectionII {		
		private int size;
		private List<Item> list;
		private int weightSum = 0;
		
		public RandomSelectionII(List<Item> list) {
			this.list = new ArrayList<>(list);
			this.size = list.size();
			
			for (Item item: list) {
				weightSum += item.weight;
			}
		}
		
		public Integer get() {
			if (weightSum <= 0) {
				return null;
			}
			
			Random random = new Random();
			int val = random.nextInt(weightSum);
			Integer result = null;
			
			for (int i = 0; i < size; i++) {
				Item item  = list.get(i);
				val -= item.weight;
				if (val < 0) {
					result = item.val;
					weightSum -= item.weight;
					Item tmp = list.get(size - 1);
					list.set(size - 1, item);
					list.set(i, tmp);
					size--;
					return result;
				}
			}
			
			return result;
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 3, 2, 7, 1, 10, 4, 6, 12));
		
		RandomSelectionI rs = new RandomSelectionI(list1);
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
		
		RandomSelectionII rsII = new RandomSelectionII(list2);
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
		System.out.println(rsII.get());
	}
}
