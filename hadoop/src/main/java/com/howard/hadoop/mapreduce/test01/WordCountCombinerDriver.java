package com.howard.hadoop.mapreduce.test01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * combiner合并
 * 在到reduce前先对mapper的任务结果做一次合并
 *
 * combiner是MR程序中Mapper和Reducer之外的一种组件
 * combiner组件的父类就是Reducer
 * Combiner是在每一个maptask所在的节点运行
 * Reducer是接收全局所有Mapper的输出结果；
 * combiner的意义就是对每一个maptask的输出进行局部汇总，以减小网络传输量
 * Created by Howard Yao on 2018/11/3.
 */
public class WordCountCombinerDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(WordCountCombinerDriver.class);

        // 设置mapper和reducer的class
        job.setMapperClass(WordCountMap.class);
        job.setReducerClass(WordCountReduce.class);

        // 设置输出mapper的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置最终输出数据类型

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileSystem fs = FileSystem. get ( conf );

        // 设置输入与输出的数据路径 G:\tmp
        /*FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));*/
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test01/input/wc.txt"));
        // 输出路径 output文件夹必须不存在
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test01/output"));

        // 设置combiner
        job.setCombinerClass(WordCountCombiner.class);

        // 提交
        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }
}
