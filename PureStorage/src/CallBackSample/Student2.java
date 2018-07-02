package CallBackSample;

public class Student2 {
    private String name = null;
    
    public Student2(String name) {
        this.name = name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void callHelp (int a, int b) {
        new SuperCalculator().add(a, b, this);
    }
 
    // 回调函数
    public void fillBlank(int a, int b, int result) {
        System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
    }
}
