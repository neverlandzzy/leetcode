package CallBackSample;

// 没有callback机制
public class Student {
    private String name = null;
 
    public Student(String name) {
        this.name = name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    private int calcADD(int a, int b) {
        return a + b;
    }
 
    private int useCalculator(int a, int b) {
        return new Calculator().add(a, b);
    }
    
    public void fillBlank(int a, int b) {
        int result = calcADD(a, b);
        System.out.println(name + "心算:" + a + " + " + b + " = " + result);
    }
    
    public void fillBlank2(int a, int b) {
        int result = useCalculator(a, b);
        System.out.println(name + "使用计算器:" + a + " + " + b + " = " + result);
    }
}







