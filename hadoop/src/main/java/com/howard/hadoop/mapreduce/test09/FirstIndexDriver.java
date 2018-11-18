package com.howard.hadoop.mapreduce.test09;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计单词在每个文件中出现的次数（类似倒排索引）
 * 这里分执行两次dirver
 *
 * 第一次 输出为：
 * Google--a.txt	3
 * Google--b.txt	2
 * Google--c.txt	2
 * 第二次输出为：Google	c.txt-->2	b.txt-->2	a.txt-->3
 *
 * 第二次的输入基于第一次的输出
 *
 * Created by Howard Yao on 2018/11/18.
 */
public class FirstIndexDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 输入和输出路径
        args = new String[] { "D:/tmp/test09/input/", "D:/tmp/test09/output/" };

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);
        job.setJarByClass(FirstIndexDriver.class);

        job.setMapperClass(FirstIndexMapper.class);
        job.setReducerClass(FirstIndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);

    }
}
