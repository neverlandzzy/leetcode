
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	/*
	 * The API: int read4(char *buf) reads 4 characters at a time from a file.
	 * 
	 * The return value is the actual number of characters read. For example, 
	 * it returns 3 if there is only 3 characters left in the file.
	 * 
	 * By using the read4 API, implement the function int read(char *buf, int n) that 
	 * reads n characters from the file.
	 * 
	 * Note:
	 * The read function will only be called once for each test case.
	 */
	
    public int read(char[] buf, int n) {
        int counter = 4;
        char[] buf4 = new char[4];
        int pos = 0;
        
        while (counter == 4 && pos < n) {
            counter = read4(buf4);
            
            int len = Math.min(counter, n - pos);
            
            for (int i = 0; i < len; i++) {
                buf[pos++] = buf4[i];
            }
        }
        
        return pos;
    }
    
    /*
    public int read(char[] buf, int n) {
        int size = 4;
        char[] buf4 = new char[4];
        int length = 0;
         
        while (size == 4 && length < n) {
            size = read4(buf4);
            
            int curLen = Math.min(size, n - length);
            
            for (int i = 0; i < curLen; i++) {
                buf[length + i] = buf4[i];
            }
            
            length += curLen;
        }
        
        return length;
    }
    */
    /*
    public int read(char[] buf, int n) {
        int size = 4;
        char[] buf4 = new char[4];
        int numRead = 0;
         
        while (size == 4 && numRead < n) {
        	size = read4(buf4);
        	
        	int bytes = Math.min(size, n - numRead);
        	System.arraycopy(buf4, 0, buf, numRead, bytes);
        	numRead += bytes;
        }
        
        return numRead;
    }
    */
}
