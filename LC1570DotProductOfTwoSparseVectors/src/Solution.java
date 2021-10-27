public class Solution {

    public static void main(String[] args) {
        int[] test1 = {1,0,0,2,3};
        int[] test2 = {0,3,0,4,0};
        int[] test3 = {0,1,0,0,0};
        int[] test4 = {0,0,0,0,2};
        int[] test5 = {0,1,0,0,2,0,0};
        int[] test6 = {1,0,0,0,3,0,4};


        SparseVector v1 = new SparseVector(test1);
        SparseVector v2 = new SparseVector(test2);
        SparseVector v3 = new SparseVector(test3);
        SparseVector v4 = new SparseVector(test4);
        SparseVector v5 = new SparseVector(test5);
        SparseVector v6 = new SparseVector(test6);

        System.out.println(v1.dotProduct(v2));
        System.out.println(v3.dotProduct(v4));
        System.out.println(v5.dotProduct(v6));
    }
}
