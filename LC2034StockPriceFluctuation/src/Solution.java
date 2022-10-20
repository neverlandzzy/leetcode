public class Solution {

    public static void main(String[] args) {
        StockPrice sp = new StockPrice();

        sp.update(1, 10);
        sp.update(2, 5);

        System.out.println(sp.current());
        System.out.println(sp.maximum());

        sp.update(1, 3);
        System.out.println(sp.maximum());

        sp.update(4, 2);
        System.out.println(sp.minimum());
    }
}
