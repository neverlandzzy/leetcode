import java.util.Random;


public class sorting {

	public static void insertionSorting(int[] nums) {
		//O(n^2)
		
		for (int i = 1; i < nums.length; i++) {
			int tmp = nums[i];
			
			int j = i - 1;
			
			while (j >= 0 && nums[j] > tmp) {
				nums[j + 1] = nums[j];
				j--;
			}
			
			nums[j + 1] = tmp;
		}
	}
	
	public static void selectionSorting(int[] nums) {
		//O(n^2)
		
		for (int i = 0; i < nums.length; i++) {
			
			int index = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[index]) {
					index = j;
				}
			}
			int min = nums[index];
			nums[index] = nums[i];
			nums[i] = min;
		}
	}
	
	public static void mergeSorting(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
	}
	
	private static void mergeSort(int[] nums, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			
			mergeSort(nums, start, mid);
			mergeSort(nums, mid + 1, end);
			merge(nums, start, mid, end);
			
		}
	}
	
	private static void merge(int[]nums, int start, int mid, int end) {
		int [] tmp = new int[nums.length];
		
		for (int i = start; i <= end; i++) {
			tmp[i] = nums[i];
		}
		/*
        for (int i: tmp) {
        	System.out.print("tmp: " + i + ", ");
        }
        System.out.println();
        */
		int i = start; 
		int j = mid + 1;
		int k = start;
		
		while (i <= mid && j <= end) {
			if (tmp[i] <= tmp[j]) {
				nums[k] = tmp[i];
				i++;
			} else {
				nums[k] = tmp[j];
				j++;
			}
			k++;
		}
		
		while (i <= mid) {
			nums[k] = tmp[i];
			k++;
			i++;
		}
	}
	
	
	public static void quickSorting(int[] nums) {
		quickSortingHelper(nums, 0, nums.length - 1);
	}
	
	private static void quickSortingHelper(int[] nums, int start, int end) {
		if (start < end) {
			Random randomGenerator = new Random();
			int pivotIndex = randomGenerator.nextInt((end - start) + 1) + start;
			
			int pivot = nums[pivotIndex];
			nums[pivotIndex] = nums[end];
			nums[end] = pivot;
			
			int i = start - 1;
			int j = end;
			
			do {
				do {
					i++;
				} while(nums[i] < pivot);

				do {
					j--;
				} while(nums[j] > pivot && j > start) ;
				
				if (i < j) {
					int tmp = nums[i];
					nums[i] = nums[j];
					nums[j] = tmp;
				}
			} while (i < j);
			
			nums[end] = nums[i];
			nums[i] = pivot;
			quickSortingHelper(nums, start, i - 1);
			quickSortingHelper(nums, i + 1, end);
			
			
		}
		
	}
	
	
	public static void countingSortingV1(int[] nums) {
		int[] counter= new int[10];
		
		for (int i = 0; i < nums.length; i++) {
			counter[nums[i]]++;
		}

		for (int i = 0, j = 0; i < nums.length && j < counter.length; j++) {
			while (counter[j] != 0) {
				nums[i] = j;
				counter[j]--;
				i++;
			}
		}
	}
	
	public static void countingSortingV2(int[] nums) {
		int[] counter = new int[10];
		int[] output = new int[nums.length];
		
		for (int i = 0; i < nums.length; i++) {
			counter[nums[i]]++;
		}
		
		int sum = 0;
		for (int i = 0; i < counter.length; i++) {
			int tmp = counter[i];
			counter[i] = sum;
			sum += tmp;
		}
		
		for (int i = 0; i < nums.length; i++) {
			output[counter[nums[i]]] = nums[i];
			counter[nums[i]]++;
		}
		
		for(int n: output) {
			System.out.print(n + ", ");
		}
		System.out.println();

	}
	
	public static void main(String[] args) {
		
		int array[] = {5,3,1,2,6,4,8,1};
		
		int test1[] = array.clone();
		insertionSorting(test1);
		
		for (int i : test1) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		int test2[] = array.clone();
		selectionSorting(test2);
		for (int i : test2) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		
		int test3[] = array.clone();
		mergeSorting(test3);
		for (int i : test3) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		int test4[] = array.clone();
		quickSorting(test4);
		for (int i : test4) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		int test5[] = array.clone();
		countingSortingV1(test5);
		for (int i : test5) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		int test6[] = array.clone();
		countingSortingV2(test6);
		for (int i : test6) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
