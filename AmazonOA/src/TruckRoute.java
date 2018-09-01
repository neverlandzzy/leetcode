import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class TruckRoute {
	/*
	 * 
	 * 大意是说Amazon 开了新的warehouse，要用卡车给其他地方送货
	 * 
	 * 参数：
	 * int N, 代表总共有N 个地点
	 * List<List<Integer>> 地点的坐标
	 * int M,代表需要送的crate数量
	 * 
	 * output：一个List<List<Integer>> 代表送货的地点坐标x,y
	 * 其实就是让你计算距离卡车最近的M个地点
	 * 
	 * starts from [0, 0]
	 * 
	 * 例1：N = 3, M = 2, List<List<Ingeter>> 是 [[2,3][3,4],[1,-3]]
	 * output: [[2,3],[1,-3]]
	 * 
	 * 例2：N = 3，M = 6， List<List<Integer>> 是[[1,8],[2,4],[8,9],[5,3],[2,7],[3,5]]
	 * output: [[2,4],[5,3],[3,5]]
	 * 
	 * 例3：N = 3, M = 2 List<List<Ingeter>> 是 [[1,2][3,4],[1,-1]]
	 * output: [[1,-1], [1,2]]
	 */
	
	
	// 参考答案
	// http://www.1point3acres.com/bbs/thread-301101-1-1.html
	/*
    public List<List<Integer>> topK(List<List<Integer>> input, int n,int m){
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(n,
                        new Comparator<List<Integer>>(){
                      public int compare (List<Integer> e1,List<Integer> e2){ 
                              return e1.get(0)*e1.get(0) + e1.get(1)*e1.get(1) - e2.get(0)*e2.get(0) - e2.get(1)*e2.get(1);
                      }
                });
        for(List<Integer> e1:input){
                pq.add(e1);
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0;i < m && i < n;i++){
                result.add(pq.remove());
        }
        return result;       
    }
    
    public static List<List<Integer>> topK(List<List<Integer>> input, int n,int m){
    	PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(n,
    			new Comparator<List<Integer>>(){
    		      public int compare (List<Integer> e1,List<Integer> e2){
    		    	  return e1.get(0)*e1.get(0) + e1.get(1)*e1.get(1) - e2.get(0)*e2.get(0) - e2.get(1)*e2.get(1);
    		      }
    	        });
    	for(List<Integer> e1:input){
    		pq.add(e1);
    	}
    	List<List<Integer>> result = new ArrayList<>();
    	for(int i = 0;i < m && i < n;i++){
    		result.add(pq.remove());
    	}
        return result;	
    }
    */
}
