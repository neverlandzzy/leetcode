import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Driver {

	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		
		//job1
		Configuration conf1 = new Configuration();
		//按句子读入
		conf1.set("textinputformat.record.delimiter", ".");
		conf1.set("noGram", args[2]);
		
		Job job1 = Job.getInstance(conf1);
		job1.setJobName("NGram");
		job1.setJarByClass(Driver.class);
		job1.setMapperClass(NGramLibraryBuilder.NGramMapper.class);
		job1.setReducerClass(NGramLibraryBuilder.NGramReducer.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);
		
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		
		TextInputFormat.setInputPaths(job1, new Path(args[0]));
		TextOutputFormat.setOutputPath(job1, new Path(args[1]));
		
		// wait for job1 to complete, then start job2
		job1.waitForCompletion(true);
		
		// job2
		Configuration conf2 = new Configuration();
		conf2.set("threshold", args[3]);
		conf2.set("n", args[4]);
		
		// communicate with MySql
		DBConfiguration.configureDB(conf2, "com.mysql.jdbc.Driver", "jdbc:mysql://ip_address:port/test", "root",
				"password");
		Job job2 = Job.getInstance(conf2);
		job2.setJobName("Model");
		job2.setJarByClass(Driver.class);
		job2.addArchiveToClassPath(new Path("path_to_ur_connector"));
		
		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);
		job2.setOutputKeyClass(DBOutputWritable.class);
		job2.setOutputValueClass(NullWritable.class);
		
		job2.setMapperClass(LanguageModel.Map.class);
		job2.setReducerClass(LanguageModel.Reduce.class);
		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(DBOutputFormat.class);
		
		DBOutputFormat.setOutput(job2, "output", new String[]{"starting_phrase", "following_word", "count"});
		
	}
}
