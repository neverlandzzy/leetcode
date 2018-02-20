import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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

public class Multiplication {
	public static class MultiplicationMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
		
		/*
		 * [movie1 {movie1, movie2, 8}{movie1, movie3, 5}{movie1, movie7, 6}]
		 * [movie2 {movie2, movie1, 8}{movie2, movie5, 9}{movie2, movie9, 10}]
		 * 
		 * {user	movie:rating}
		 */
		
		Map<Integer, List<MovieRelation>> movieRelationMap = new HashMap<Integer, List<MovieRelation>>();
		Map<Integer, Integer> denominator = new HashMap<Integer, Integer>();//用来做归一化
		
		@Override
		protected void setup(Context context) throws IOException {
			Configuration conf = context.getConfiguration();
			String filePath = conf.get("coOccurrencePath");
			Path pt = new Path(filePath);
			FileSystem fs = FileSystem.get(conf);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
			String line = br.readLine();
			
			while(line != null) {
				//movieA:movieB \t relation
				String[] tokens = line.toString().trim().split("\t");
				String[] movies = tokens[0].split(":");
				
				int movie1 = Integer.parseInt(movies[0]);
				int movie2 = Integer.parseInt(movies[1]);
				int relation = Integer.parseInt(tokens[1]);
				
				MovieRelation movieRelation = new MovieRelation(movie1, movie2, relation);
				
				if (movieRelationMap.containsKey(movie1)) {
					movieRelationMap.get(movie1).add(movieRelation);
				} else {
					List<MovieRelation> list = new ArrayList<>();
					list.add(movieRelation);
					movieRelationMap.put(movie1, list);
				}
				line = br.readLine();
			}
			br.close();
			
			for(Map.Entry<Integer, List<MovieRelation>> entry: movieRelationMap.entrySet()) {
				int sum = 0;
				for (MovieRelation relation: entry.getValue()) {
					sum +=relation.getRelation();
				}
				denominator.put(entry.getKey(), sum);
			}
		}
		
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			//Input: user, movie, rating
			//Output: user:movie score
			String[] tokens = value.toString().trim().split(",");
			int user = Integer.parseInt(tokens[0]);
			int movie = Integer.parseInt(tokens[1]);
			double rating = Double.parseDouble(tokens[2]);
	
			for(MovieRelation relation:movieRelationMap.get(movie)) {
				double score = rating * relation.getRelation();
				//normalize
				score = score / denominator.get(relation.getMovie2());
				DecimalFormat df = new DecimalFormat("#.00");
				score = Double.valueOf(df.format(score));
				// user movieTag:score	优化：--> user+movieTag:score
				context.write(new Text(user + ":" + relation.getMovie2()), new DoubleWritable(score));
			}
		}
	}
	
	public static class MultiplicationReducer extends Reducer<Text, DoubleWritable, IntWritable, Text> {
		@Override
		public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
				throws IOException, InterruptedException {
			//user: movie score
			
			double sum = 0;
			while (values.iterator().hasNext()) {
				sum += values.iterator().next().get();
			}
			
			String[] tokens = key.toString().split(":");
			int user = Integer.parseInt(tokens[0]);
			context.write(new IntWritable(user), new Text(tokens[1] + ":" + sum));
		}
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("coOccurrencePath", args[0]);
		Job job = Job.getInstance(conf);

		job.setJarByClass(Multiplication.class);
		job.setMapperClass(MultiplicationMapper.class);
		job.setReducerClass(MultiplicationReducer.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		//Mapper 和 Reducer的key value class不一样，所以要设置
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		TextInputFormat.setInputPaths(job, new Path(args[1]));
		TextOutputFormat.setOutputPath(job, new Path(args[2]));
		
		job.waitForCompletion(true);
	}
}
