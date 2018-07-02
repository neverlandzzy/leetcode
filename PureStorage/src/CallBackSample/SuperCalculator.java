package CallBackSample;

public class SuperCalculator {
	// 本方法的局限在于需要传递Student2作为参数，而无法被其它class使用
    public void add(int a, int b, Student2 xiaoming) {
        int result = a + b;
        xiaoming.fillBlank(a, b, result);
    }
}
