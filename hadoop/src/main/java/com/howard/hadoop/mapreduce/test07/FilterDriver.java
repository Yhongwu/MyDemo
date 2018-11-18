package com.howard.hadoop.mapreduce.test07;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 过滤日志及自定义日志输出路径
 * （自定义OutputFormat）
 *
 * 将文件中包含google的输出到google.log 其它的输出到other.log
 * 这里主要的暑促和路径配置在FileterRecordWriter
 *
 * Created by Howard Yao on 2018/11/18.
 */
public class FilterDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 输入和输出路径
        args = new String[] { "D:/tmp/test07/input/", "D:/tmp/test07/output/" };

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(FilterDriver.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setMapperClass(FilterMapper.class);
        job.setReducerClass(FilterReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 自定义输出格式
        job.setOutputFormatClass(FilterOutPutFormat.class);

        // 虽然自定义了outputformat，但是因为FilterOutputformat继承自fileoutputformat
        // 而fileoutputformat要输出一个_SUCCESS文件，所以，在这还得指定一个输出目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);

    }
}
