package com.howard.hadoop.mapreduce.test08;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by Howard Yao on 2018/11/18.
 */
public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        // 输入和输出路径
        args = new String[] { "D:/tmp/test08/input/", "D:/tmp/test08/output/" };

        // 获取job信息
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 加载jar包
        job.setJarByClass(LogDriver.class);

        // 关联map
        job.setMapperClass(LogMapper.class);

        // 设置最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 提交
        job.waitForCompletion(true);

    }
}
