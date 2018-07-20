import java.util.Arrays;


public class O1Map {
	/*
	 * Implement set with integers in range {1…N}, implements five operations, add, remove, contains, clear, iterate. 
	 * 
	 * https://instant.1point3acres.com/thread/177053
	 * 
	 * 设计一个Map, 满足 add: O(1)  deletion: O(1)  lookup: O(1)  clear:O(1)  iterate: O(number of elements)
	 * 关键在于clear和iterate
	 */
	
	/* http://www.1point3acres.com/bbs/thread-229910-1-1.html
	 * 集合(set)存储了非负整数，且集合内的元素都是unique的，不在乎元素在集合里的顺序。在某些应用场景下，我们更在乎计算复杂度，而不在乎空间复杂度。
	 * 也就是说你可以假设你有无限大的内存空间可使用。在集合上的操作包括：插入一个元素、查找一个元素、删除一个元素、清空集合、判断集合是否为空、遍历所有元素（iterator）。
	 * 现在给你提供了集合的两种实现方式：
	 * (1)bit map，即一个长度为N的布尔数组，N足够大。
	 * (2)一个长度为N的整型数组，和一个记录集合里元素个数的counter，N足够大。
	 * 对于这两种集合的实现方式，上述几个集合上的操作已经提供好了
	 * 
	 * 题目要求：
	 * 1. 找出已给出的集合操作中的逻辑错误。（提示：关注集合的数学性质）
	 * 2. 对于集合上的各种操作，就时间复杂度而言，两种集合的实现各有优劣。要求设计一种集合的数据结构实现方式和对应的操作方式，使得其各个集合操作的时间复杂度不大于这个
	 *    操作在上述两种实现方式里较优的那种的时间复杂度。
	 *    
	 * 提示：
	 * 1.有的人可能想到了Java里的LinkedHashMap，不过清空上述第二种实现方式里的集合，只需要把counter设为0，而清空LinkedHashMap的时间复杂度并不是O(1)。
	 *   而且对时间复杂度的要求是要有保证的，像hash这种可能发生collision的情况是不允许的。
	 * 2. 发挥你的想象力。你所涉及的数据结构可能和面试官想的答案不一样，但只要您能证明你所涉及的数据结构符合要求，在任何集合操作中都不比已给出的两种实现方式差，就可以。
	 * 
	 * 
	 * There are 3 versions to solve this 0, 1, 2 
	 * v1: using only bucket array with size N+1, the operation costs are: O(1),O(1), O(1), O(N), O(N) for example: 
	 * if we add, 2, 0, 1, it will become list:[1, 1, 1] 
	 * 
	 * v2: using only sequential array(store them sequentially in array): O(1),O(count),O(count),O(1), O(count)for example: 
	 * if we add, 2, 0, 1, it will become map: [2, 0, 1] you need to keep an count
	 * 
	 * v3: achieve best of v1 and v2’s performance: O(1),O(1) O(1), O(1), O(count)
	 * 
	 * The right answer is to use the two array given. For bucket array value, it will be the index in the sequential array. 
	 * The sequential array value will be the actually value. in the above example it will become list:[1, 2, 0], map[2, 0, 1]
	 * 
	 * A couple of things to notice: 
	 * 1) how to remove? remove will not shift the rest of the array front, instead, it will pick the map[toRemove] = map[count - 1]; count—; 
	 * 
	 * 2) how to clear: map just need to set count to 0. 
	 * 
	 * 3) after clear, because we don’t clear list, how do we know if the list pointer to the map are invalidate 
	 * or not(this will affect contains and add)? 
	 * 
	 * There are two things to consider, 
	 * #1. if list[x] >= count - 1, then it is definitely invalid; 
	 * #2. if list[x] < count but it still possible that it’s replaced by other element(e.g. add 2, clear, add 1, 
	 * now list[2] and list[1] both points to map[0]). So you should also check if map[list[x]] == x.
	 */
	
	public int[] bucket;
	public int[] nums;
	public int index; // 即count
	public int iterator;
	
	// N: integer range
	public O1Map(int N) {
		bucket = new int[N];
		Arrays.fill(bucket, -1);
		nums = new int[N];
		index = 0;
		iterator = 0;
	}
	
	public void add(int num) {
		
		if (search(num)) {
			return;
		}
		
		bucket[num] = index;
		nums[index] = num;
		index++;
	}
	
	public void delete(int num) {		
		if (!search(num)) {
			bucket[num] = -1;
			return;
		}
		
		int indexToRemove = bucket[num];
		nums[indexToRemove] = nums[index - 1];
		bucket[num] = -1;
		bucket[nums[indexToRemove]] = indexToRemove;
		index--;
	}
	
	public boolean search(int num) {
		if (bucket[num] == -1) {
			return false;
		}
		
		int i = bucket[num];
		
		if (i >= index) {
			return false;
		}
		
		if (nums[i] != num) {
			return false;
		}
		
		return true;
	}
	
	public void clear() {
		index = 0;
	}
	
	public Integer next() {
		if (iterator >= index) {
			iterator = 0;
			return null;
		}
		
		return nums[iterator++];
	}
}
