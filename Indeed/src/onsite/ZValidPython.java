package onsite;

import java.util.Stack;

public class ZValidPython {
	public static int validate(String[] input) {
		
	}
	
	public static void main(String[] args) {
        String[] lines1 = {
                "def:",
                " abc:",
                "  b2c:",
                "   cc",
                " b5c",
                "b6c"
        };
        
        String[] lines2 = {
                "def",
                "abc:",
                "  bcc",
                "  abc:",
                "   def",
                "   def",
                "  bcc"
        };
        
        String[] lines3 = {
                "def",
                "abc:",
                "  bcc",
                "  abc:   ",
                "   def",
                "    def",  // 违反rule 3
                "  bcc"
        };
        
        String[] lines4 = {
                "def",
                "abc:",
                "  bcc",
                "  abc:",
                "   def",
                "   def",
                "  bcc: "   // 以冒号结尾
        };
        
        String[] lines5 = {
                "def",
                "abc:",
                "  bcc",
                "  abc:",
                "# comment",
                "   def",
                "   def",
                "  bcc "
        };
        
        String[] lines6 = {
        		"  # comment",
                "def",
                "abc:",
                "  bcc",
                "  abc:",
                "# comment",
                "# comment",
                "   def",
                "   def",
                "  bcc "
        };
        
        System.out.println(validate(lines1));
        System.out.println(validate(lines2));
        System.out.println(validate(lines3));
        System.out.println(validate(lines4));
        System.out.println(validate(lines5));
        System.out.println(validate(lines6));
        
	}
}
