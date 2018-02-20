import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount {
	
	/*
	 * MapReduce中，输入输出都一定是以<key, value>形式出现
	 * <Object, Text> 是输入的 <key, value>
	 * <Text, IntWritable> 是输出的 <key, value>
	 * Text类似于String, IntWritable类似于int
	 */
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		
		@Override
		/*
		 * (non-Javadoc)
		 * @see org.apache.hadoop.mapreduce.
		 * Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
		 * 
		 * <key, value>是输入的key和value (<Object, Text>)
		 * 文件是逐行读取
		 * key 是读文件的offset，用来记录读文件的位置，一般用不到
		 * value 是当前的行
		 * context 是MapReduce和其它工具的接口，e.g. 可以用来写出和读入数据
		 */
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			
			// value = “i love big data”
			
			/*
			 * 推荐用StringTokenizer而不是String.split()因为StringTokenizer类似于iterator;
			 * 
			 * 相比于list，用iterator的好处是：list会把所有东西都load到内存，而iterator只是把当前指针
			 * 指向数据，而不会把整个数据load到内存
			 *  
			 */
			//value.toString().split(" ");
			StringTokenizer iter = new StringTokenizer(value.toString());
			
			while(iter.hasMoreTokens()) {
				Text outKey = new Text(iter.nextToken());
				IntWritable outValue = new IntWritable(1);
				
				// 输出数据
				context.write(outKey, outValue);
			}
			
		}
	}
	
	/*
	 * Reducer的输入就是Mapper的输出。所以<Text, IntWritable>是Reducer的输入；
	 * 第二组<Text, IntWritable>是Reducer的输出
	 */
	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		/*
		 * (non-Javadoc)
		 * @see org.apache.hadoop.mapreduce.
		 * Reducer#reduce(KEYIN, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
		 * 
		 * 此处用Iterable<IntWritable> values。因为reducer里面有两个阶段：combiner和reducer
		 * e.g.如果接收到的数据中，big出现两次：
		 * 		big 1
		 *      big 1
		 * reducer会将所有相同的key，形成一个Iterable => big <1, 1> 而不是两个<big, 1> pair。这个过程是在
		 * shuffle（在map和reduce之间，进行transfer data和根据key来sort）中实现
		 * 
		 * 所以此处每个Iterable value的key都是相同的。
		 */
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException {
			
			int sum = 0;
			
			for (IntWritable value: values) {
				sum += value.get();
			}
			
			context.write(key, new IntWritable(sum));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * Configuration可以用来设置MapReduce， 
		 * e.g.如果不想逐行读取文件，而想每次读一句，以句号为分界，可以在Configuration里面设置
		 */
		Configuration conf = new Configuration();
		
		/*
		 * Job是和Hadoop的接口，用来告诉hadoop 哪个是mapper哪个是reducer，以什么顺序来run
		 */
		
		// 以下为模板，基本都会重复用到
		Job job = new Job(conf, "word count");
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 从用户command line读取输入文件的位置，输入的是文件夹位置，会读取该文件夹下所有的文件
		FileInputFormat.addInputPath(job, new Path(args[0]));
		// 输出文件的位置
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
	}
}
