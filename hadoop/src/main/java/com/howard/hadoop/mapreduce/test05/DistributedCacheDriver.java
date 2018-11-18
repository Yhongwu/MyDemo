package com.howard.hadoop.mapreduce.test05;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 多表处理
 * 在map端缓存多张表，提前处理业务逻辑，这样增加map端业务，减少reduce端数据的压力，尽可能的减少数据倾斜。
 * （1）在mapper的setup阶段，将文件读取到缓存集合中
 * （2）在驱动函数中加载缓存。
 * job.addCacheFile(new URI("file:/e:/mapjoincache/pd.txt"));// 缓存普通文件到task运行节点
 * order表
 * 1001	01	1
 * 1001	01	1
 * pd表
 * 01	小米
 * 合并为
 * 1001	小米	1
 * 1001	小米	1
 *
 * Created by Howard Yao on 2018/11/17.
 */
public class DistributedCacheDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 获取配置信息，或者job对象实例
        Configuration  configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 指定本程序的jar包所在的本地路径
        job.setJarByClass(DistributedCacheDriver.class);

        // 指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(DistributedCacheMapper.class);
        // mapper阶段完成需要的处理了 所以不需要reduce
        //job.setReducerClass(TableReducer.class);

        // 指定mapper输出数据的kv类型
        //job.setMapOutputKeyClass(Text.class);
        //job.setMapOutputValueClass(TableBean.class);

        // 指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 指定job的输入原始文件所在目录
        // Path path = new Path("/test/test02");
        // 读取order.txt
        FileInputFormat.setInputPaths(job, new Path("D:/tmp/test05/input/order.txt"));
        // 输出路径 output文件夹必须不存在
        FileOutputFormat.setOutputPath(job, new Path("D:/tmp/test05/output2"));

        // 加载缓存数据
        // 坑点：idea无法自动将utf-8 bom转utf-8 注意文件的格式 防止出现\ufeff问题 比如：02读取出现为三个字符的情况\ufeff02
        job.addCacheFile(new URI("file:/D:/tmp/test05/input/pd.txt"));

        // 将job中配置的相关参数，以及job所用的java类所在的jar包， 提交给yarn去运行
        // waitForCompletion实现了submit方法
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);

    }
}
