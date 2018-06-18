
public class Solution {
	
	
    public static int peakIndexInMountainArray(int[] A) {
        int index = 0;
        
        for (int i = 1; i < A.length; i++) {
        	if (A[i] > A[i - 1]) {
            	index = i;
        		continue;
        	}

        	break;
        }
        
        return index;
    }
    
    public static int carFleet(int target, int[] position, int[] speed) {
    return 0;   
    }
    
    
	public static void main(String[] args) {
		/*
		int[] test11 = {0, 2, 1, 0};
		int[] test12 = {0,1,0};
		System.out.println(peakIndexInMountainArray(test11));
		System.out.println(peakIndexInMountainArray(test12));
		
		int[] p1 = {10,8,0,5,3};
		int[] s1 = {2,4,1,1,3};
		System.out.println(carFleet(12, p1, s1));
		*/
		
		ExamRoom room = new ExamRoom(10);
		System.out.println(room.seat());
		System.out.println(room.seat());
		System.out.println(room.seat());
		System.out.println(room.seat());
		room.leave(4);
		System.out.println(room.seat());
		
		System.out.println("==============");
		ExamRoom room2 = new ExamRoom(8);
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		room2.leave(0);
		room2.leave(7);
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		System.out.println(room2.seat());
		
	}
}
