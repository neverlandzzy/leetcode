
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	
	/*
	 * The API: int read4(char *buf) reads 4 characters at a time from a file.
	 * 
	 * The return value is the actual number of characters read. For example, it returns 3 
	 * if there is only 3 characters left in the file.
	 * 
	 * By using the read4 API, implement the function int read(char *buf, int n) that 
	 * reads n characters from the file.
	 * 
	 * Note:
	 * The read function may be called multiple times.
	 */
	
	// 本题与LC157的区别是，read()可以被多次调用，因此上次调用后的buf4[]中可能有剩余的字符没有处理。因此，要用buf4Pos指针记录buf4[]中剩余的字符的位置
	
    private char[] buf4 = new char[4];
    private int counter = 0; // buf4内字符个数
    private int buf4Pos = 0; // buf4当前指针的位置
    
    public int read(char[] buf, int n) {
        int pos = 0; // buf的指针
        
        while (pos < n) {
        	
        	//当buf4Pos == 0时，buf4内无元素或者元素已经被读完
            if (buf4Pos == 0) {
                counter = read4(buf4);                
            }
            
            //当counter == 0时，已经读到file的结尾
            if (counter == 0) {
                break;
            }
            
            //copy buf4里的内容到buf
            while (pos < n && buf4Pos < counter) {
                buf[pos++] = buf4[buf4Pos++];
            }
            
            // buf4内的元素已经被读完
            if (counter == buf4Pos) {
                buf4Pos = 0;
            }
        }
        
        return pos;
    }
	
	/*
	private char[] buf4 = new char[4];
	private int offset = 0;
	private int size = 0;
	
    public int read(char[] buf, int n) {
        
    	int numRead = 0;
    	boolean eof = false;
    	
    	while (!eof && numRead < n) {
    		if (size == 0) {
    			size = read4(buf4);
    			if (size < 4) eof = true;
    		}
    		
    		int bytes = Math.min(size, n - numRead);
    		System.arraycopy(buf4, offset, buf, numRead, bytes);
    		offset = (offset + bytes) % 4;
    		size = size - bytes;
    		numRead += bytes;
    	}
    	
    	return numRead;
    }
    */
}