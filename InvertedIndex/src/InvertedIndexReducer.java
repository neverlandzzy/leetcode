import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(final Text key, final Iterable<Text> values, final Context context) 
			throws IOException, InterruptedException {
		
		//Input:  <keyword, <doc1, doc1, doc1, doc2, doc2...>>
		//Output: <keyword, doc1\tdoc2>
		
		String lastBook = null;
		StringBuilder sb = new StringBuilder();
		int count = 0; // 记录当前单词出现多少次
		int threshold = 100; // 出现次数小于threshold的不输出
		
		for (Text value: values) {
			if (lastBook != null && value.toString().trim().equals(lastBook)) {
				count++;
				continue;
			}
			
			// lastbook == null OR
			// value != lastbook
			
			if (lastBook != null && count < threshold) {
				count = 1;
				lastBook = value.toString().trim();
				continue;
			}
			
			if (lastBook == null) {
				lastBook = value.toString().trim();
				count++;
				continue;
			}
			
			sb.append(lastBook);
			sb.append("\t");
			
			count = 1;
			lastBook = value.toString().trim();
		}
		
		// 对于最后一个book
		if (count > threshold) {
			sb.append(lastBook);
		}
		
		// 写入到hdfs的output上
		if (!sb.toString().trim().equals("")) {
			context.write(key, new Text(sb.toString()));
		}
	}
}
