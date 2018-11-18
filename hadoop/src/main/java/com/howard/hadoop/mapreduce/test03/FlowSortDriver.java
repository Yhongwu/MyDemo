package com.howard.hadoop.mapreduce.test03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 根据流量的总大小排序 倒序
 * flowbean对象实现WritableComparable并重写序列化和比较接口的实现
 * 缺点：无法对同一个手机号的流量进行合并作为一条数据
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowSortDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息 不写 默认使用本地的mapreducer
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(FlowSortDriver.class);

        // 设置mapper和reducer的class
        job.setMapperClass(FlowSortMapper.class);
        job.setReducerClass(FlowSortReducer.class);

        // 设置输出mapper的数据类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        // 设置最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileSystem fs = FileSystem. get(conf);
        // 设置输入与输出的数据路径 G:\tmp
        //FileInputFormat.setInputPaths(job, new Path(args[0]));
        //FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //FileInputFormat.setInputPaths(job, new Path(" /test/test02/wc.txt"));
        // Path path = new Path("/test/test02");
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test03/input/test03.txt"));
        // 输出路径 output文件夹必须不存在
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test03/output"));

        // 提交
        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);

    }
}
