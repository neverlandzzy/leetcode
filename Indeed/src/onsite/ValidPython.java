package onsite;

import java.util.Stack;

public class ValidPython {
	/*
	 * 【题目】
	 *  给一段Python代码，还有一些列规则，然后写程序检测该代码是否符合该规则（主要是Python的缩进规则）。返回第一个出错行
	 *  1.第一行无缩进；
	 *  2.前一行是冒号结尾，那么说明它是一个control statement，下一行缩进要比这一行多
	 *  3.同一个块里面缩进相同
	 *  4.如果下一行缩进变少，必须要变少到之前出现过的有效缩进。
	 *  The input is String[], each line is a string
	 *  if it's valid, return -1, otherwise return the line number.
	 *  
	 *  http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216457
	 *  http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216804
	 * 
	 * 【思路】我的作法使用stack 记录每个 block的indentation, 还有一个 flag 确定前一个statement是不是control statement. 
	 *  For loop, 一行一行 scan, 是control statement, 下一行的indentation就是这个block的规定indentation. 
	 *  此外当然也要确定indentation是递增.
	 *  
	 *  思路就是用boolean记录上一行是不是control block，然后计算每一行的space。
	 *  if isControlBlock:
	 *      if current space is <= stack.peek()
	 *        return false;
	 *      else
     *	      stack.push;
	 *  else:
     *    compare the space with peek of stack until find invalid.
     *  update isControlBlock
	 *  
	 * 【corner case/follow up】
	 *  follow up1: what is the last line is control block? -- ':'结尾的
	 *  follow up2: what if there is comment line ("  #") -- 有注释行的，就是'#'为第一个字符的
	 */
	
	public static int validate(String[] input) {
		Stack<Integer> stack = new Stack<>();
		boolean inBlock = false;
		
		for (int i = 0; i < input.length; i++) {	
			String line = input[i];
			
			// follow up2: 如果某行以'#' 起始，则跳过这行
			if (line.trim().charAt(0) == '#') {
				continue;
			}

			int indentation = getIndentation(line);			
			
			// 检查 rule 1：第一行无缩进。若有缩进，直接返回line#
			if (i == 0) {
				if (indentation > 0) {
					return i;
				} else {
					stack.push(indentation);
				}
			}
			
			// rule 2: 若上一行结尾是':'，则当前行的缩进要大于上一行，否则返回line#
			if (inBlock) {
				if (indentation <= stack.peek()) {
					return i;
				}
			} else {
				// 这里用while，e.g.下面的例子，对于efg，stack要pop()两次
				// abc:
				//  bcd:
				//   def
				// efg
				while (!stack.isEmpty() && indentation < stack.peek()) {
					stack.pop();
				}
				
				// rule 3：同一个块里面缩进相同
				if (!stack.isEmpty() && indentation != stack.peek()) {
					return i;
				}
			}
			
			// 若允许':'后面有空白字符，则需要trim()
			if (line.trim().charAt(line.trim().length() - 1) == ':') {
				// follow up1: 如果最后一行以':'结尾
				if (i == input.length - 1) {
					return i;
				}
				inBlock = true;
			} else {
				inBlock = false;
			}
			
			stack.push(indentation);		
		}
		
		return -1;
	}
	
	//这里如果它说n个空格算一次tab的话，就最后返回的时候res/n好了。
	private static int getIndentation(String s) {
		int result = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				result++;
			} else {
				break;
			}
		}
		
		return result;
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
