package com.howard.hadoop.mapreduce.test02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 分区FlowPartitioner
 * 要求将统计结果按照条件输出到不同文件中（分区）。比如：将统计结果按照手机归属地不同省份输出到不同文件中（分区）
 * 默认分区是根据key的hashCode对reduceTasks个数取模得到的。用户没法控制哪个key存储到哪个分区
 * 通过·继承·Partitioner实现自定义的分区
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowDriver3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息 不写 默认使用本地的mapreducer
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(FlowDriver3.class);

        // 设置mapper和reducer的class
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        // 设置输出mapper的数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 设置最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileSystem fs = FileSystem. get ( conf );

        // 设置输入与输出的数据路径 G:\tmp
        //FileInputFormat.setInputPaths(job, new Path(args[0]));
        //FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //FileInputFormat.setInputPaths(job, new Path(" /test/test02/wc.txt"));
       // Path path = new Path("/test/test02");
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test02/input2/"));
        // 输出路径 output文件夹必须不存在
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test02/output3"));

        // 设置reducer个数
        // 如果设置为1 不管FlowPartitioner返回几种结果 只会存放于一个文件
        // 如果设置为>1 并且小于FlowPartitioner设置的最大值 则会抛异常
        // 如果设置大于FlowPartitioner设置的最大值 会出现空文件part-r-000xx
        job.setNumReduceTasks(5);
        // 设置Partitioner分区
        job.setPartitionerClass(FlowPartitioner.class);

        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
