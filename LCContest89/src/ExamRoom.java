
public class ExamRoom {
    boolean[] seats;
    
    public ExamRoom(int N) {
        seats = new boolean[N];
    }
    
    public int seat() {
        int n = seats.length;
        
        if (!seats[0]) {
            seats[0] = true;
            return 0;
        }
        
        if (!seats[n - 1]) {
            seats[n - 1] = true;
            return n - 1;
        }
        
        int i = 0;
        int j = i + 1;
        int len = 0;
        int start = -1;
        int end = -1;
        
        while (j < n) {
            if (!seats[j]) {
                j++;
            } else {
                if ((len % 2 == 1 && len < j - i) ||(len % 2 == 0 && len < j - i + 1)) {
                    len = j - i + 1;
                    start = i;
                    end = j;
                }
                i = j;
                j++;
            }
        }

        
        seats[(start + end) /2] = true;
        return (start + end) / 2;
    }
    
    public void leave(int p) {
        seats[p] = false;
    }
}
