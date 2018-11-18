package com.howard.hadoop.mapreduce.test02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 流量统计
 * 默认对单个文件的切片大小：
 * computeSliteSize(Math.max(minSize,Math.max(maxSize,blocksize)))=blocksize=128M
 * maxsize（切片最大值）：参数如果调得比blocksize小，则会让切片变小，而且就等于配置的这个参数的值。
 * minsize （切片最小值）：参数调的比blockSize大，则可以让切片变得比blocksize还大
 * // 根据文件类型获取切片信息
 * FileSplit inputSplit = (FileSplit) context.getInputSplit();
 * // 获取切片的文件名称
 * String name = inputSplit.getPath().getName();
 *
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息 不写 默认使用本地的mapreducer
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(FlowDriver.class);

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
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test02/input/test02.txt"));
        // 输出路径 output文件夹必须不存在
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test02/output"));
        // 提交
        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
