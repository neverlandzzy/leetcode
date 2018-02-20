import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class DataDividerByUser {
	public static class DataDividerMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
		
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] user_movie_rating = value.toString().trim().split(",");
			int userID = Integer.parseInt(user_movie_rating[0]);
			String movieID = user_movie_rating[1];
			String rating = user_movie_rating[2];
			
			context.write(new IntWritable(userID), new Text(movieID + ":" + rating));
	
		}
	}
	
	public static class DataDividerReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
		@Override
		public void reduce(IntWritable key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException {
			
			//key = user_id value = <movieid:rating, movieid:rating...>
			StringBuilder sb = new StringBuilder();
			while(values.iterator().hasNext()) {
				sb.append("," + values.iterator().next().toString());
			}
			
			//key value
			context.write(key,  new Text(sb.toString().replaceFirst(",", "")));
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);

		job.setJarByClass(DataDividerByUser.class);
		job.setMapperClass(DataDividerMapper.class);
		job.setReducerClass(DataDividerReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		TextInputFormat.setInputPaths(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
