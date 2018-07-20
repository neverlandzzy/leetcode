import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class Test {
	
	private static Integer value(Integer a) {
		a = 13;
		
		return a;
	}
	
	public static void test() {
		TreeNode result = new TreeNode(4);
		
		helper(result);
		System.out.println(result);
	}
	public static void helper(TreeNode result) {
		result = new TreeNode(2);
	}
	
	public static void main(String[] args) {
		String[] test = {"def", "abc", "acb", "efet", "efe"};

		Arrays.sort(test, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				int i = 0;
				int j = 0;
				
				while (i < o1.length() && j < o2.length()) {
					if (o1.charAt(i) != o2.charAt(j)) {
						return o2.charAt(i) - o1.charAt(j);
					}
					i++;
					j++;
				}
				
				if (i == o1.length() && j == o2.length()) {
					return 0;
				} else if (i == o1.length()){
					return 1;
				} else {
					return -1;
				}
			}
			
		});

		/*
		String s1 = "eat";
		
		System.out.println(s1.codePointAt(0));
		System.out.println((int)s1.charAt(0));
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(5);
		queue.add(4);
		queue.add(7);
		queue.add(2);
		queue.add(1);
		queue.add(5);
		queue.remove(5);
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + ", ");
		}
		*/
		/*
		Integer testInt = 10;
		
		System.out.println(value(testInt));
		System.out.println(testInt);
		
		String test2 = "/r/e/d/e/";
		String[] test2Array = test2.split("/");
		
		for (String s: test2Array) {
			System.out.print(s + " ");
		}
		*/
		/*
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 1);
		System.out.println(map.size());
		
		int[][] dp = {{1, 2, 3}, {4, 5, 6}};
		System.out.println(Arrays.deepToString(dp));
		
		int[][] newDp = Arrays.copyOf(dp, dp.length);
		System.out.println(Arrays.deepToString(newDp));
		
		dp[0][1] = 8;
		System.out.println(Arrays.deepToString(dp));
		System.out.println(Arrays.deepToString(newDp));
		
		String s = "google.com";
		
		String[] a = s.split("\\.");
		
		System.out.println(a[1]);
		System.out.println(a[0]);
		
		int test1 = 1358;
		int test2 = 1368;
		int test3 = 1299;
		int test4 = 1600;
		
		System.out.println(convert(test1));
		System.out.println(convert(test2));
		System.out.println(convert(test3));
		System.out.println(convert(test4));

		
		TreeSet<Integer> treeset = new TreeSet<>();
		treeset.add(4);
		treeset.add(2);
		treeset.add(6);
		treeset.add(10);
		
		System.out.println(treeset);
		System.out.println(treeset.last());
		System.out.println(treeset);
		System.out.println(treeset.last());
		System.out.println(treeset.pollLast());
		System.out.println(treeset);


		
		int a = 0;
		int b = 1;
		int c = 1;
		
		System.out.println(a ^ b);
		System.out.println(c ^ b);
		*/

		Queue<Integer> queue = new LinkedList<>();
		Integer i = queue.poll();
		System.out.println(i);
	}
	
	
	
	
	private static int convert(int test) {
		if (test % 100 >= 60) {
			test = (test / 100) *100 + 59;
		}
		
		return test;
	}
}
