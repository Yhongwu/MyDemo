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
 * 相当于一个yarn集群的客户端，
 * 需要在此封装我们的mr程序相关运行参数，指定jar包
 * 最后提交给yarn
 *
 * 注：可使用本地直接运行的方式 也可打成jar包放入虚拟机的hadoop目录下运行(此时输入和输出参数用args[0]和args[1])
 * 本地直接运算使用远程的hadoop集群 但本地仍然需要配置安装hadoop 注意需要使用正确编译版本的hadhoop解压(比如win10编译)
 * 并配置环境变量 如果这样运行后出现空指针等异常 把安装目录下bin/hadoop.dll复制一份到C:\Windows\System32下
 * Created by Howard Yao on 2018/11/3.
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息
        Configuration conf = new Configuration();
       // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(WordCountDriver.class);

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
        FileInputFormat.setInputPaths(job, new Path(" /test/test02/wc.txt"));
        Path path = new Path("/test/test02");

        FileOutputFormat.setOutputPath(job, path);
        // 提交
        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }
}
