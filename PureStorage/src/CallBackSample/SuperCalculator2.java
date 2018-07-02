package CallBackSample;

public class SuperCalculator2 {
    public void add(int a, int b, doJob customer) {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
