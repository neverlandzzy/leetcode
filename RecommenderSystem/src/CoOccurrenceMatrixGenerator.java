import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CoOccurrenceMatrixGenerator {
	public static class MatrixGeneratorMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			//Input: value = userid \t movie1: rating, movie2: rating2...
			//By default, use \t to split
			//Output: key = movie1: movie2 value = 1
			
			String line = value.toString().trim();
			String[] user_movieRatings = line.split("\t");
			String user = user_movieRatings[0];
			String[] movie_ratings = user_movieRatings[1].split(",");
			// {movie1:rating, movie2:rating, ...}
			
			for (int i = 0; i < movie_ratings.length; i++) {
				String movie1 = movie_ratings[i].trim().split(":")[0];
				
				for (int j = 0; j < movie_ratings.length; j++) {
					String movie2 = movie_ratings[j].trim().split(":")[0];
					context.write(new Text(movie1 + ":" + movie2), new IntWritable(1));
				}
			}
	
		}
	}
	
	public static class MatrixGeneratorReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException {
			//Input: key: movie2:movie2 value = iterable<1,1,1>
			
			int sum = 0;
			
			while (values.iterator().hasNext()) {
				sum += values.iterator().next().get();
			}
			
			context.write(key, new IntWritable(sum));
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);

		job.setJarByClass(CoOccurrenceMatrixGenerator.class);
		job.setMapperClass(MatrixGeneratorMapper.class);
		job.setReducerClass(MatrixGeneratorReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//可以设置多个输入位置，但只能有一个输出位置
		TextInputFormat.setInputPaths(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
