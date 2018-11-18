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
 * 流量统计
 * 测试: 文件夹下方多个小文件
 * 关于大量小文件的优化策略： 方法1 预处理 先把小文件合并为大文件 再上传hdfs处理 方法2：使用CombineTextInputFormat做切片
 * 默认情况下TextInputformat对任务的切片机制是按文件规划切片，不管文件多小，都会是一个单独的切片，都会交给一个maptask，这样如果有大量小文件，就会产生大量的maptask，处理效率极其低下。
 * 可以使用另一种InputFormat来做切片（CombineTextInputFormat），它的切片逻辑跟TextFileInputFormat不同：它可以将多个小文件从逻辑上规划到一个切片中，这样，多个小文件就可以交给一个maptask。
 *
 * Created by Howard Yao on 2018/11/10.
 */
public class FlowDriver2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取配置信息 不写 默认使用本地的mapreducer
        Configuration conf = new Configuration();
        // conf.set("mapreduce.framework.name", "yarn");
        //conf.set("yarn.resourcemanager.hostname", "192.168.1.114");
        //System. setProperty ( "HADOOP_USER_NAME" , "yhw" );
        //conf .set( "fs.defaultFS" , "hdfs://192.168.1.114:8020" );
        Job job = Job.getInstance(conf);

        // 设置加载jar位置
        job.setJarByClass(FlowDriver2.class);

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
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test02/output2"));
        // 提交

        // 如果下面注释不打开 并且D:/tmp/test02/input2/下存放2个小文件 则日志会看到number of splits:2 说明分成2个切片
        // 如果使用CombineTextInputFormat即打开下面三行代码注释 运行日志看到number of splits:1
        // 如果不设置InputFormat,它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);
        // 4M
        CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);
        // 2M
        CombineTextInputFormat.setMinInputSplitSize(job, 2097152);


        job.submit();
        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
