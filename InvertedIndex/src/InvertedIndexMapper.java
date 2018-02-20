import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;


public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
	List<String> stopWords = new ArrayList<>();
	
	@Override
	// 在initialize时被调用一次，不会被重复调用。
	// stopWords不需要每次map被调用时候都run，只需要run一次。
	
	public void setup(Context context) throws IOException {
		// 1. generate dataset<stopWords>		
		// command line 定义stopwords的地址		
		Configuration conf = context.getConfiguration();
		String filePath = conf.get("filePath");  // "filePath是一个key，指向一个地址. e.g /input/stopWords.txt"		
		
		Path path = new Path("hdfs:" +  filePath); // --> hdfs:/input/stopWords.txt
		FileSystem fs = FileSystem.get(new Configuration());
		BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(path)));
		String line;
		line = br.readLine();
		
		while(line != null) {
			stopWords.add(line.toLowerCase().trim()); // trim 去掉单词周围的空格等
			line = br.readLine();
		}
	}
			 
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		// 2. split file into words
		// key -> value value: name
		
		// 获取文件名， 【模板】记住！
		String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
		Text name = new Text(fileName);
		
		StringTokenizer tokenizer = new StringTokenizer(value.toString());
		while(tokenizer.hasMoreTokens()) {
			String curWord = tokenizer.nextToken().toString().toLowerCase();
			
			// 去除所有非字母字符（如标点等 . , ） 【模板】记住！
			curWord = curWord.replaceAll("[^a-zA-Z]", ""); //所有非a-z A-Z的字符替换成空
			
			if (!stopWords.contains(curWord)) {
				context.write(new Text(curWord), name);
			}
		}
	}
		
}
