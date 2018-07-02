package CallBackSample;

public class Test {
	/* 
	 * http://www.importnew.com/19301.html
	 */
	
    public static void main(String[] args) {
    	// 没有callback
        int a1 = 1;
        int b1 = 1;
        Student s1 = new Student("小明");
        s1.fillBlank(a1, b1);
        
        
        int a2 = 168;
        int b2 = 291;
        Student s2 = new Student("小明");
        s2.fillBlank2(a2, b2);
        
        // 使用callback
        int a3 = 26549;
        int b3 = 16487;
        Student2 s3 = new Student2("小明");
        s3.callHelp(a3, b3);
        
        
        int a4 = 56;
        int b4 = 31;
        int c4 = 26497;
        int d4 = 11256;
        Student3 s4 = new Student3("小明");
        Seller s5 = new Seller("老婆婆");
 
        s4.callHelp(a4, b4);
        s5.callHelp(c4, d4);
    }
}
