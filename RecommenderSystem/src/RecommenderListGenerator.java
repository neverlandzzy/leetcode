import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class RecommenderListGenerator {
	public static class RecommenderListGeneratorMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
		
		/*
		 * filter out watched movies
		 * match movie_name to movie_id
		 */
		
		Map<Integer, List<Integer>> watchHistory = new HashMap<>();
		
		@Override
		protected void setup(Context context) throws IOException {
			// read movie watch history
			// 实际公司中可以用mysql做query去match以防止OOM， 不会直接从文件match
			Configuration conf = context.getConfiguration();
			String filePath = conf.get("watchHistory");
			Path pt = new Path(filePath);
			FileSystem fs = FileSystem.get(conf);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
			String line = br.readLine();

			while (line != null) {
				//user, movie, rating
				int user = Integer.parseInt(line.split(",")[0]);
				int movie = Integer.parseInt(line.split(",")[1]);
				if (watchHistory.containsKey(user)) {
					watchHistory.get(user).add(movie);
				} else {
					List<Integer> list = new ArrayList<>();
					list.add(movie);
					watchHistory.put(user, list);
				}
				line = br.readLine();
			}
			br.close();
		}
		
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			
			//recommender user \t movie:rating
			String[] tokens = value.toString().split("\t");
			int user = Integer.parseInt(tokens[0]);
			int movie = Integer.parseInt(tokens[1]);
			
			if (!watchHistory.get(user).contains(movie)) {
				context.write(new IntWritable(user), new Text(movie + ":" + tokens[2]));
			}
		}

	}
	
	public static class RecommenderListGeneratorReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
		
		Map<Integer, String> movieTitles = new HashMap<>();
		
		//match movie_name to movie_id
		@Override
		protected void setup(Context context) throws IOException {
			//<movie_id, movie_title>
			//read movie title from file
			
			Configuration conf = context.getConfiguration();
			String filePath = conf.get("movieTitles");
			Path pt = new Path(filePath);
			FileSystem fs = FileSystem.get(conf);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
			String line = br.readLine();
			
			//movie_id,movie_name
			while (line != null) {
				int movie_id = Integer.parseInt(line.trim().split(",")[0]);
				movieTitles.put(movie_id, line.trim().split(",")[1]);
				line = br.readLine();
			}
			br.close();
		}
		
		@Override
		public void reduce(IntWritable key, Iterable<Text> values, Context context) 
				throws IOException, InterruptedException {
			//movie_id:rating
			while (values.iterator().hasNext()) {
				String cur = values.iterator().next().toString();
				int movie_id = Integer.parseInt(cur.split(":")[0]);
				String rating = cur.split(":")[1];
				
				context.write(key, new Text(movie_id));
			}

		}
		
		public static void main(String[] args) throws Exception{
			Configuration conf = new Configuration();
			conf.set("watchHistory", args[0]);
			conf.set("movieTitles", args[1]);
			Job job = Job.getInstance(conf);

			job.setJarByClass(RecommenderListGenerator.class);
			job.setMapperClass(RecommenderListGeneratorMapper.class);
			job.setReducerClass(RecommenderListGeneratorReducer.class);
			
			TextInputFormat.setInputPaths(job, new Path(args[2]));
			TextOutputFormat.setOutputPath(job, new Path(args[3]));
			
			job.waitForCompletion(true);
		}
	}
}
